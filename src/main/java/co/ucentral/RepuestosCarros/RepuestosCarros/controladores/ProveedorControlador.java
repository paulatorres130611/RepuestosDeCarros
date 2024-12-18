package co.ucentral.RepuestosCarros.RepuestosCarros.controladores;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import co.ucentral.RepuestosCarros.RepuestosCarros.persistencia.entidades.Proveedor;
import co.ucentral.RepuestosCarros.RepuestosCarros.servicios.ProveedorServicio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@Controller

public class ProveedorControlador {

    ProveedorServicio proveedorServicio;

    @GetMapping("/proveedores")
    public String listarProveedores(Model model) {
        List<Proveedor> proveedores = proveedorServicio.obtenerTodosLosProveedores();
        model.addAttribute("proveedores", proveedores);
        return "Proveedores";
    }
    //------------------------------------------------cu_06---------------------------------------------------------
    @GetMapping ("/proveedores/crear")
    public String mostrarFormularioCrearProveedor(Model model) {
        model.addAttribute("mostrarFormularioCrearProveedor", true);
        model.addAttribute("proveedor", new Proveedor());
        model.addAttribute("proveedores", proveedorServicio.obtenerTodosLosProveedores());
        return "Proveedores";
    }
    @PostMapping("/proveedores/crear")
    public String crearProveedor(@ModelAttribute Proveedor proveedor,RedirectAttributes redirectAttributes){
        proveedorServicio.guardarProveedor(proveedor);
        redirectAttributes.addFlashAttribute("mensaje", "Proveedor creado con éxito.");
        return "redirect:/proveedores";
    }
    //------------------------------------------------cu_07---------------------------------------------------------
    // Cargar datos de producto para edición
    @GetMapping("/proveedores/editar/{id}")
    public String mostrarFormularioEditarProveedor(@PathVariable("id") Long id, Model model) {
        Proveedor proveedor = proveedorServicio.obtenerProveedorPorRut(id);
        model.addAttribute("proveedorSeleccionado", proveedor);
        model.addAttribute("mostrarFormularioEditarProveedor", true);
        model.addAttribute("proveedores", proveedorServicio.obtenerTodosLosProveedores());//aparezca tabla
        return "Proveedores";
    }
    // Guardar cambios del producto editado
    @PostMapping("/proveedores/editar")
    public String editarProveedor(@ModelAttribute("proveedorSeleccionado") Proveedor proveedor,RedirectAttributes redirectAttributes) {
        proveedorServicio.actualizarProveedor(proveedor);
        redirectAttributes.addFlashAttribute("mensaje", "Proveedor editado con éxito.");
        return "redirect:/proveedores";
    }
    //------------------------------------------------cu_08---------------------------------------------------------
    @GetMapping("/proveedores/eliminar/{id}")
    public String confirmarEliminacion(@PathVariable("id") Long id, Model model) {
        model.addAttribute("proveedorId", id);
        model.addAttribute("mostrarConfirmacionEliminarProveedor", true);
        model.addAttribute("proveedores", proveedorServicio.obtenerTodosLosProveedores());
        return "Proveedores";
    }
    @PostMapping("/proveedores/eliminar")
    public String eliminarProveedor(@RequestParam("proveedorId") Long id,RedirectAttributes redirectAttributes) {
        proveedorServicio.eliminarProveedor(id);
        redirectAttributes.addFlashAttribute("mensaje", "Proveedor eliminado con éxito.");
        return "redirect:/proveedores";
    }
    //------------------------------------------------cu_16---------------------------------------------------------
    @GetMapping("/proveedores/buscar")
    public String buscarProveedor(@RequestParam("criterioBusquedaProveedor") String criterio, Model model) {
        List<Proveedor> proveedores = proveedorServicio.buscarProveedores(criterio);
        model.addAttribute("proveedores", proveedores);
        model.addAttribute("criterioBusquedaProveedor", criterio); // Para mostrar el criterio en el campo de búsqueda
        return "Proveedores"; // Nombre de la plantilla
    }
}
