package co.ucentral.RepuestosCarros.RepuestosCarros.controladores;

import co.ucentral.RepuestosCarros.RepuestosCarros.persistencia.entidades.Producto;
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
    public String crearProveedor(@ModelAttribute Proveedor proveedor){
        proveedorServicio.guardarProveedor(proveedor);
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
    public String editarProveedor(@ModelAttribute("proveedorSeleccionado") Proveedor proveedor) {
        proveedorServicio.actualizarProveedor(proveedor);
        return "redirect:/proveedores";
    }
}
