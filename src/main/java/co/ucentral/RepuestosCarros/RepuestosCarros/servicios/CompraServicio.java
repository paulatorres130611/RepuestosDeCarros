package co.ucentral.RepuestosCarros.RepuestosCarros.servicios;

import co.ucentral.RepuestosCarros.RepuestosCarros.persistencia.entidades.Compra;
import co.ucentral.RepuestosCarros.RepuestosCarros.persistencia.entidades.DetalleCompra;
import co.ucentral.RepuestosCarros.RepuestosCarros.persistencia.entidades.Producto;
import co.ucentral.RepuestosCarros.RepuestosCarros.persistencia.entidades.Proveedor;
import co.ucentral.RepuestosCarros.RepuestosCarros.persistencia.repositorios.CompraRepositorio;
import co.ucentral.RepuestosCarros.RepuestosCarros.persistencia.repositorios.DetalleCompraRepositorio;
import co.ucentral.RepuestosCarros.RepuestosCarros.persistencia.repositorios.ProveedorRepositorio;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Service
public class CompraServicio {

    private final CompraRepositorio compraRepositorio;
    private final ProveedorRepositorio proveedorRepositorio;
    private final ProveedorServicio proveedorServicio;
    private final ProductoServicio productoServicio;
    private final DetalleCompraRepositorio detalleCompraRepositorio;


    // Buscar proveedor por RUT
    public Proveedor obtenerProveedorPorRut(Long id) {
        return proveedorRepositorio.findById(id).orElse(null);
    }

    @Transactional
    public void guardarCompra(Long proveedorId, String fechaCompra, List<String> detalles) {
        // 1. Buscar el proveedor
        Proveedor proveedor = proveedorServicio.obtenerProveedorPorRut(proveedorId);
        if (proveedor == null) {
            throw new IllegalArgumentException("El proveedor con RUT " + proveedorId + " no existe.");
        }

        // 2. Crear y guardar la compra
        Compra compra = new Compra();
        compra.setProveedor(proveedor);
        compra.setCom_fechacompra(LocalDate.parse(fechaCompra));
        compra.setCom_total(BigDecimal.ZERO); // Inicializa el total en 0
        compra = compraRepositorio.save(compra);

        // 3. Procesar los detalles
        BigDecimal totalCompra = BigDecimal.ZERO;
        for (String detalle : detalles) {
            String[] partes = detalle.split(","); // Divide el string
            Long productoId = Long.parseLong(partes[0]);
            int cantidad = Integer.parseInt(partes[1]);
            BigDecimal precioCompra = new BigDecimal(partes[2]);

            // Buscar el producto
            Producto producto = productoServicio.obtenerProductoPorId(productoId);
            if (producto == null) {
                throw new IllegalArgumentException("El producto con ID " + productoId + " no existe.");
            }

            // Actualizar el stock del producto
            producto.setProd_stock(producto.getProd_stock() + cantidad); // Incrementar el stock
            productoServicio.actualizarProducto(producto); // Guardar el producto actualizado


            // Crear el detalle de compra
            DetalleCompra detalleCompra = new DetalleCompra();
            detalleCompra.setCompra(compra);
            detalleCompra.setProducto(producto);
            detalleCompra.setDet_cantidad(cantidad);
            detalleCompra.setDet_precioCompra(precioCompra);

            // Calcular el subtotal
            BigDecimal subtotal = precioCompra.multiply(BigDecimal.valueOf(cantidad));
            totalCompra = totalCompra.add(subtotal);

            // Guardar el detalle
            detalleCompraRepositorio.save(detalleCompra);
        }

        // 4. Actualizar el total de la compra
        compra.setCom_total(totalCompra);
        compraRepositorio.save(compra); // Actualizar la compra con el total
    }

    public Compra obtenerCompraPorId(Long compraId) {
        return compraRepositorio.findById(compraId)
                .orElseThrow(() -> new IllegalArgumentException("La compra con ID " + compraId + " no existe."));
    }
    public void actualizarCompra(Compra compra) {
        if (compra == null || compra.getCom_id() == 0) {
            throw new IllegalArgumentException("La compra no es válida o no tiene un ID asignado.");
        }
        compraRepositorio.save(compra); // Actualiza la compra en la base de datos
    }
}
