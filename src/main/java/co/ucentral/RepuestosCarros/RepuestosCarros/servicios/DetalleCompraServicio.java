package co.ucentral.RepuestosCarros.RepuestosCarros.servicios;

import co.ucentral.RepuestosCarros.RepuestosCarros.persistencia.entidades.Compra;
import co.ucentral.RepuestosCarros.RepuestosCarros.persistencia.entidades.DetalleCompra;
import co.ucentral.RepuestosCarros.RepuestosCarros.persistencia.entidades.Producto;
import co.ucentral.RepuestosCarros.RepuestosCarros.persistencia.repositorios.DetalleCompraRepositorio;
import co.ucentral.RepuestosCarros.RepuestosCarros.persistencia.repositorios.ProductoRepositorio;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Service
public class DetalleCompraServicio {

    DetalleCompraRepositorio detalleCompraRepositorio;
    ProductoRepositorio productoRepositorio;
    private final CompraServicio compraServicio;
    private final ProductoServicio productoServicio;

    public List<Producto> buscarProductosPorNombreYLinea(String nombre, String linea) {
        return productoRepositorio.buscarPorNombreYLinea(nombre, linea);
    }

    @Transactional
    public void guardarDetallesCompra(Long compraId, List<Long> productosId, List<Integer> cantidades, List<BigDecimal> preciosCompra) {
        // 1. Validar que la compra exista
        Compra compra = compraServicio.obtenerCompraPorId(compraId);
        if (compra == null) {
            throw new IllegalArgumentException("La compra con ID " + compraId + " no existe.");
        }

        // 2. Inicializar el total de la compra
        BigDecimal totalCompra = BigDecimal.ZERO;

        // 3. Procesar cada producto
        for (int i = 0; i < productosId.size(); i++) {
            Long productoId = productosId.get(i);
            int cantidad = cantidades.get(i);
            BigDecimal precioCompra = preciosCompra.get(i);

            // Validar que el producto exista
            Producto producto = productoServicio.obtenerProductoPorId(productoId);
            if (producto == null) {
                throw new IllegalArgumentException("El producto con ID " + productoId + " no existe.");
            }

            // Crear y guardar el detalle de compra
            DetalleCompra detalleCompra = new DetalleCompra();
            detalleCompra.setCompra(compra);
            detalleCompra.setProducto(producto);
            detalleCompra.setDet_cantidad(cantidad);
            detalleCompra.setDet_precioCompra(precioCompra);

            detalleCompraRepositorio.save(detalleCompra);

            // Calcular el subtotal y sumar al total de la compra
            BigDecimal subtotal = precioCompra.multiply(BigDecimal.valueOf(cantidad));
            totalCompra = totalCompra.add(subtotal);
        }

        // 4. Actualizar el total de la compra
        compra.setCom_total(totalCompra);
        compraServicio.actualizarCompra(compra); // Asegúrate de tener un mEtodo para actualizar la compra
    }

}
