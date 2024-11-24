package co.ucentral.RepuestosCarros.RepuestosCarros.controladores;

import co.ucentral.RepuestosCarros.RepuestosCarros.persistencia.entidades.Producto;
import co.ucentral.RepuestosCarros.RepuestosCarros.servicios.DetalleVentaServicio;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Controller
public class DetalleVentaControlador {
    DetalleVentaServicio detalleventaServicio;

    @GetMapping("/buscar-productos-venta")
    @ResponseBody
    public List<Producto> buscarProductosVenta(
            @RequestParam("nombre") String nombre,
            @RequestParam("linea") String linea) {
        return detalleventaServicio.buscarProductosPorNombreYLinea(nombre, linea);
    }

    @PostMapping("/detallesventa/guardar")
    public ResponseEntity<String> guardarDetallesVenta(
            @RequestParam("ventaId") Long ventaId,
            @RequestParam("productosId") List<Long> productosId,
            @RequestParam("cantidades") List<Integer> cantidades,
            @RequestParam("preciosVenta") List<BigDecimal> preciosVenta) {

        // Validación básica de los parámetros
        if (productosId.size() != cantidades.size() || productosId.size() != preciosVenta.size()) {
            return ResponseEntity.badRequest().body("La cantidad de productos, cantidades y precios no coincide.");
        }

        try {
            // Llama al servicio para guardar los detalles
            detalleventaServicio.guardarDetallesVenta(ventaId, productosId, cantidades, preciosVenta);
            return ResponseEntity.ok("Detalles de venta registrados con éxito.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al registrar los detalles de venta: " + e.getMessage());
        }
    }
}
