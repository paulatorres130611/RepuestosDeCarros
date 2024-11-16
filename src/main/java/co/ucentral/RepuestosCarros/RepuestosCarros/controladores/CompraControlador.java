package co.ucentral.RepuestosCarros.RepuestosCarros.controladores;

import co.ucentral.RepuestosCarros.RepuestosCarros.persistencia.entidades.Proveedor;
import co.ucentral.RepuestosCarros.RepuestosCarros.servicios.CompraServicio;
import co.ucentral.RepuestosCarros.RepuestosCarros.servicios.ProveedorServicio;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@Controller
public class CompraControlador {

    CompraServicio compraServicio;
    ProveedorServicio proveedorServicio;

    // Mostrar el formulario de compras
    @GetMapping("/compras")
    public String mostrarFormularioCompras() {
        return "Compras";
    }

    // Autocompletar Proveedor por RUT
    @GetMapping("/proveedores/autocompletar")
    public ResponseEntity<String> autocompletarProveedor(@RequestParam("rut") Long id) {
        Proveedor proveedor = proveedorServicio.obtenerProveedorPorRut(id);
        if (proveedor != null) {
            return ResponseEntity.ok(proveedor.getProv_nombre());
        } else {
            return ResponseEntity.badRequest().body("Proveedor no encontrado");
        }
    }

}
