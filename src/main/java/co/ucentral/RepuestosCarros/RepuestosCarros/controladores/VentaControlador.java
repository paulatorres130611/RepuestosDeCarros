package co.ucentral.RepuestosCarros.RepuestosCarros.controladores;

import co.ucentral.RepuestosCarros.RepuestosCarros.persistencia.entidades.Cliente;
import co.ucentral.RepuestosCarros.RepuestosCarros.servicios.ClienteServicio;
import co.ucentral.RepuestosCarros.RepuestosCarros.servicios.VentaServicio;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@AllArgsConstructor
@Controller
public class VentaControlador {
    VentaServicio ventaServicio;
    ClienteServicio clienteServicio;

    // Mostrar el formulario de compras
    @GetMapping("/ventas")
    public String mostrarFormularioVentas() {
        return "Ventas";
    }

    // Autocompletar Proveedor por RUT
    @GetMapping("/clientes/autocompletar")
    public ResponseEntity<String> autocompletarCliente(@RequestParam("nit") Long id) {
        Cliente cliente = clienteServicio.obtenerClientePorId(id);
        if (cliente != null) {
            return ResponseEntity.ok(cliente.getCli_nombre());
        } else {
            return ResponseEntity.badRequest().body("Cliente no encontrado");
        }
    }

    @PostMapping("/ventas/guardar")
    public String guardarVenta(@RequestParam("clienteId") Long clienteId,
                               @RequestParam("fechaVenta") String fechaVenta,
                               @RequestParam("detalles") String detalles,
                               RedirectAttributes redirectAttributes) {
        try {
            System.out.println("Cliente ID: " + clienteId);
            System.out.println("Fecha Venta: " + fechaVenta);
            System.out.println("Detalles: " + detalles);

            // Llama al servicio para guardar la venta
            ventaServicio.guardarVenta(clienteId, fechaVenta, List.of(detalles.split(";")));

            redirectAttributes.addFlashAttribute("mensaje", "Venta registrada con éxito");
            return "redirect:/ventas";
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/ventas";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al registrar la venta: " + e.getMessage());
            return "redirect:/ventas";
        }
    }

}
