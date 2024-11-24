package co.ucentral.RepuestosCarros.RepuestosCarros.servicios;

import co.ucentral.RepuestosCarros.RepuestosCarros.persistencia.entidades.Venta;
import co.ucentral.RepuestosCarros.RepuestosCarros.persistencia.entidades.DetalleVenta;
import co.ucentral.RepuestosCarros.RepuestosCarros.persistencia.entidades.Producto;
import co.ucentral.RepuestosCarros.RepuestosCarros.persistencia.entidades.Cliente;
import co.ucentral.RepuestosCarros.RepuestosCarros.persistencia.repositorios.VentaRepositorio;
import co.ucentral.RepuestosCarros.RepuestosCarros.persistencia.repositorios.DetalleVentaRepositorio;
import co.ucentral.RepuestosCarros.RepuestosCarros.persistencia.repositorios.ClienteRepositorio;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Service
public class VentaServicio {
    private final VentaRepositorio ventaRepositorio;
    private final ClienteRepositorio clienteRepositorio;
    private final ClienteServicio clienteServicio;
    private final ProductoServicio productoServicio;
    private final DetalleVentaRepositorio detalleVentaRepositorio;

    // Buscar cliente por NIT
    public Cliente obtenerClientePorId(Long id) {
        return clienteRepositorio.findById(id).orElse(null);
    }

    @Transactional
    public void guardarVenta(Long clienteId, String fechaVenta, List<String> detalles) {
        // 1. Buscar el cliente
        Cliente cliente = clienteServicio.obtenerClientePorId(clienteId);
        if (cliente == null) {
            throw new IllegalArgumentException("El cliente con NIT " + clienteId + " no existe.");
        }

        // 2. Crear y guardar la venta
        Venta venta = new Venta();
        venta.setCliente(cliente);
        venta.setVen_fechaventa(LocalDate.parse(fechaVenta));
        venta.setVen_total(BigDecimal.ZERO); // Inicializa el total en 0
        venta = ventaRepositorio.save(venta);

        // 3. Procesar los detalles
        BigDecimal totalVenta = BigDecimal.ZERO;
        for (String detalle : detalles) {
            String[] partes = detalle.split(","); // Divide el string
            Long productoId = Long.parseLong(partes[0]);
            int cantidad = Integer.parseInt(partes[1]);
            BigDecimal precioVenta = new BigDecimal(partes[2]);

            // Buscar el producto
            Producto producto = productoServicio.obtenerProductoPorId(productoId);
            if (producto == null) {
                throw new IllegalArgumentException("El producto con ID " + productoId + " no existe.");
            }

            // Validar stock disponible
            if (producto.getProd_stock() < cantidad) {
                throw new IllegalArgumentException("El producto '" + producto.getProd_nombre() + "' no tiene suficiente stock. Stock disponible: " + producto.getProd_stock());
            }

            // Reducir el stock del producto
            producto.setProd_stock(producto.getProd_stock() - cantidad);
            productoServicio.actualizarProducto(producto); // Guardar el producto actualizado

            // Crear el detalle de venta
            DetalleVenta detalleVenta = new DetalleVenta();
            detalleVenta.setVenta(venta);
            detalleVenta.setProducto(producto);
            detalleVenta.setDetv_cantidad(cantidad);
            detalleVenta.setDetv_precioVenta(precioVenta);

            // Calcular el subtotal
            BigDecimal subtotal = precioVenta.multiply(BigDecimal.valueOf(cantidad));
            totalVenta = totalVenta.add(subtotal);

            // Guardar el detalle
            detalleVentaRepositorio.save(detalleVenta);
        }

        // 4. Actualizar el total de la venta
        venta.setVen_total(totalVenta);
        ventaRepositorio.save(venta); // Actualizar la venta con el total
    }

    public Venta obtenerVentaPorId(Long ventaId) {
        return ventaRepositorio.findById(ventaId)
                .orElseThrow(() -> new IllegalArgumentException("La venta con ID " + ventaId + " no existe."));
    }

    public void actualizarVenta(Venta venta) {
        if (venta == null || venta.getVen_id() == 0) {
            throw new IllegalArgumentException("La venta no es válida o no tiene un ID asignado.");
        }
        ventaRepositorio.save(venta); // Actualiza la venta en la base de datos
    }
}
