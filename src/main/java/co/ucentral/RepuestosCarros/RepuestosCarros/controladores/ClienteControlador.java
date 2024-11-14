package co.ucentral.RepuestosCarros.RepuestosCarros.controladores;

import co.ucentral.RepuestosCarros.RepuestosCarros.persistencia.entidades.Cliente;
import co.ucentral.RepuestosCarros.RepuestosCarros.persistencia.entidades.Proveedor;
import co.ucentral.RepuestosCarros.RepuestosCarros.servicios.ClienteServicio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@Controller

public class ClienteControlador {

    ClienteServicio clienteServicio;

    @GetMapping("/clientes")
    public String listarClientes(Model model) {
        List<Cliente> clientes = clienteServicio.obtenerTodosLosClientes();
        model.addAttribute("clientes", clientes);
        return "Clientes";
    }
    //------------------------------------------------cu_12---------------------------------------------------------
    @GetMapping ("/clientes/crear")
    public String mostrarFormularioCrearCliente(Model model) {
        model.addAttribute("mostrarFormularioCrearCliente", true);
        model.addAttribute("cliente", new Cliente());
        model.addAttribute("clientes", clienteServicio.obtenerTodosLosClientes());
        return "Clientes";
    }
    @PostMapping("/clientes/crear")
    public String crearCliente(@ModelAttribute Cliente cliente){
        clienteServicio.guardarCliente(cliente);
        return "redirect:/clientes";
    }
    //------------------------------------------------cu_13---------------------------------------------------------
    // Cargar datos de producto para edición
    @GetMapping("/clientes/editar/{id}")
    public String mostrarFormularioEditarCliente(@PathVariable("id") Long id, Model model) {
        Cliente cliente = clienteServicio.obtenerClientePorId(id);
        model.addAttribute("clienteSeleccionado", cliente);
        model.addAttribute("mostrarFormularioEditarCliente", true);
        model.addAttribute("clientes", clienteServicio.obtenerTodosLosClientes());//aparezca tabla
        return "Clientes";
    }
    // Guardar cambios del producto editado
    @PostMapping("/clientes/editar")
    public String editarProveedor(@ModelAttribute("clienteSeleccionado") Cliente cliente) {
        clienteServicio.actualizarCliente(cliente);
        return "redirect:/clientes";
    }
    //------------------------------------------------cu_14---------------------------------------------------------
    @GetMapping("/clientes/eliminar/{id}")
    public String confirmarEliminacion(@PathVariable("id") Long id, Model model) {
        model.addAttribute("clienteId", id);
        model.addAttribute("mostrarConfirmacionEliminarCliente", true);
        model.addAttribute("clientes", clienteServicio.obtenerTodosLosClientes());
        return "Clientes";
    }
    @PostMapping("/clientes/eliminar")
    public String eliminarCliente(@RequestParam("clienteId") Long id) {
        clienteServicio.eliminarCliente(id);
        return "redirect:/clientes";
    }
    //------------------------------------------------cu_16---------------------------------------------------------
    @GetMapping("/clientes/buscar")
    public String buscarCliente(@RequestParam("criterioBusquedaCliente") String criterio, Model model) {
        List<Cliente> clientes = clienteServicio.buscarClientes(criterio);
        model.addAttribute("clientes", clientes);
        model.addAttribute("criterioBusquedaCliente", criterio); // Para mostrar el criterio en el campo de búsqueda
        return "Clientes";
    }
}
