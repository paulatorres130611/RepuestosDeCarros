package co.ucentral.RepuestosCarros.RepuestosCarros.servicios;

import co.ucentral.RepuestosCarros.RepuestosCarros.persistencia.entidades.*;
import co.ucentral.RepuestosCarros.RepuestosCarros.persistencia.repositorios.DetalleVentaRepositorio;
import co.ucentral.RepuestosCarros.RepuestosCarros.persistencia.repositorios.ProductoRepositorio;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Service
public class DetalleVentaServicio {
    DetalleVentaRepositorio detalleventaRepositorio;
    ProductoRepositorio productoRepositorio;
    private final VentaServicio ventaServicio;
    private final ProductoServicio productoServicio;

    public List<Producto> buscarProductosPorNombreYLinea(String nombre, String linea) {
        return productoRepositorio.buscarPorNombreYLinea(nombre, linea);
    }

    @Transactional
    public void guardarDetallesVenta(Long ventaId, List<Long> productosId, List<Integer> cantidades, List<BigDecimal> preciosVenta) {
        // 1. Validar que la compra exista
        Venta venta = ventaServicio.obtenerVentaPorId(ventaId);
        if (venta == null) {
            throw new IllegalArgumentException("La venta con ID " + ventaId + " no existe.");
        }

        // 2. Inicializar el total de la compra
        BigDecimal totalVenta = BigDecimal.ZERO;

        // 3. Procesar cada producto
        for (int i = 0; i < productosId.size(); i++) {
            Long productoId = productosId.get(i);
            int cantidad = cantidades.get(i);
            BigDecimal precioVenta = preciosVenta.get(i);

            // Validar que el producto exista
            Producto producto = productoServicio.obtenerProductoPorId(productoId);
            if (producto == null) {
                throw new IllegalArgumentException("El producto con ID " + productoId + " no existe.");
            }

            // Crear y guardar el detalle de compra
            DetalleVenta detalleVenta = new DetalleVenta();
            detalleVenta.setVenta(venta);
            detalleVenta.setProducto(producto);
            detalleVenta.setDetv_cantidad(cantidad);
            detalleVenta.setDetv_precioVenta(precioVenta);

            detalleventaRepositorio.save(detalleVenta);

            // Calcular el subtotal y sumar al total de la compra
            BigDecimal subtotal = precioVenta.multiply(BigDecimal.valueOf(cantidad));
            totalVenta = totalVenta.add(subtotal);
        }

        // 4. Actualizar el total de la compra
        venta.setVen_total(totalVenta);
        ventaServicio.actualizarVenta(venta); // Asegúrate de tener un mEtodo para actualizar la compra
    }
}
