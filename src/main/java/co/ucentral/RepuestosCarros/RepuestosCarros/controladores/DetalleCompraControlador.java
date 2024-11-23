package co.ucentral.RepuestosCarros.RepuestosCarros.controladores;

import co.ucentral.RepuestosCarros.RepuestosCarros.persistencia.entidades.Producto;
import co.ucentral.RepuestosCarros.RepuestosCarros.servicios.DetalleCompraServicio;
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
public class DetalleCompraControlador {

    DetalleCompraServicio detallecompraServicio;

    @GetMapping("/buscar-productos")
    @ResponseBody
    public List<Producto> buscarProductos(
            @RequestParam("nombre") String nombre,
            @RequestParam("linea") String linea) {
        return detallecompraServicio.buscarProductosPorNombreYLinea(nombre, linea);
    }

    @PostMapping("/detallescompra/guardar")
    public ResponseEntity<String> guardarDetallesCompra(
            @RequestParam("compraId") Long compraId,
            @RequestParam("productosId") List<Long> productosId,
            @RequestParam("cantidades") List<Integer> cantidades,
            @RequestParam("preciosCompra") List<BigDecimal> preciosCompra) {

        // Validación básica de los parámetros
        if (productosId.size() != cantidades.size() || productosId.size() != preciosCompra.size()) {
            return ResponseEntity.badRequest().body("La cantidad de productos, cantidades y precios no coincide.");
        }

        try {
            // Llama al servicio para guardar los detalles
            detallecompraServicio.guardarDetallesCompra(compraId, productosId, cantidades, preciosCompra);
            return ResponseEntity.ok("Detalles de compra registrados con éxito.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al registrar los detalles de compra: " + e.getMessage());
        }
    }
}
