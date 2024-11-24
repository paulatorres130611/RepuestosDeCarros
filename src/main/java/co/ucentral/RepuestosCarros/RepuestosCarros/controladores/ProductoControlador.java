package co.ucentral.RepuestosCarros.RepuestosCarros.controladores;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import co.ucentral.RepuestosCarros.RepuestosCarros.persistencia.entidades.Producto;
import co.ucentral.RepuestosCarros.RepuestosCarros.servicios.ProductoServicio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@AllArgsConstructor
@Controller
public class ProductoControlador {

    ProductoServicio productoServicio;

    //metodo para cargar y mostrar la lista de productos en la página
    @GetMapping("/productos")
    public String listarProductos(Model model) {
        List<Producto> productos = productoServicio.obtenerTodosLosProductos();
        model.addAttribute("productos", productos);
        return "Productos"; //nombre Productos.html
    }

    //--------------------------------------------cu_02-------------------------------------------------------------
    //Metodo para cargar,mostrar el formularioCrear
    @GetMapping("/productos/crear")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("mostrarFormularioCrear",true);
        model.addAttribute("producto", new Producto());
        model.addAttribute("productos", productoServicio.obtenerTodosLosProductos());
        return "Productos";
    }
    //Metodo para guardar el producto enviado desde el formulario
    @PostMapping("/productos/crear")
    public String crearProducto(@ModelAttribute Producto producto, RedirectAttributes redirectAttributes) {
        productoServicio.guardarProducto(producto); // Llama al servicio para guardar el producto
        redirectAttributes.addFlashAttribute("mensaje", "Producto creado con éxito.");
        return "redirect:/productos"; // Redirige a la lista de productos después de guardar
    }
    //--------------------------------------------cu_03-------------------------------------------------------------
    // Cargar datos de producto para edición
    @GetMapping("/productos/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable("id") Long id, Model model) {
        Producto producto = productoServicio.obtenerProductoPorId(id);
        model.addAttribute("productoSeleccionado", producto);
        model.addAttribute("mostrarFormularioEditar", true);
        model.addAttribute("productos", productoServicio.obtenerTodosLosProductos());//aparezca tabla
        return "Productos";
    }
    // Guardar cambios del producto editado
    @PostMapping("/productos/editar")
    public String editarProducto(@ModelAttribute("productoSeleccionado") Producto producto, RedirectAttributes redirectAttributes) {
        productoServicio.actualizarProducto(producto); // Metodo en el servicio que se encargará de la actualización
        redirectAttributes.addFlashAttribute("mensaje", "Producto editado con éxito.");
        return "redirect:/productos"; // Redirige a la lista de productos después de guardar los cambios
    }
    //--------------------------------------------cu_04-------------------------------------------------------------
    @GetMapping("/productos/eliminar/{id}")
    public String confirmarEliminacion(@PathVariable("id") Long id, Model model) {
        model.addAttribute("productoId", id); // Pasa el ID del producto a la vista
        model.addAttribute("mostrarConfirmacionEliminar", true);
        model.addAttribute("productos", productoServicio.obtenerTodosLosProductos());
        return "Productos"; // Asegúrate de que sea el nombre exacto de tu vista HTML
    }
    @PostMapping("/productos/eliminar")
    public String eliminarProducto(@RequestParam("productoId") Long id,RedirectAttributes redirectAttributes) {
        productoServicio.eliminarProducto(id); // Llama al servicio para eliminar el producto
        redirectAttributes.addFlashAttribute("mensaje", "Producto eliminado con éxito.");
        return "redirect:/productos";
    }

    //--------------------------------------------cu_05-------------------------------------------------------------
    // Muestra la vista principal
    @GetMapping("/vistaPrincipal")
    public String mostrarVistaPrincipal() {
        return "VistaPrincipal"; // Nombre del archivo HTML sin extensión
    }

    //--------------------------------------------cu_16-------------------------------------------------------------
    @GetMapping("/productos/buscar")
    public String buscarProducto(@RequestParam("criterioBusqueda") String criterio, Model model) {
        List<Producto> productos = productoServicio.buscarProductos(criterio);
        model.addAttribute("productos", productos);
        model.addAttribute("criterioBusqueda", criterio); // Para mostrar el criterio en el campo de búsqueda
        return "Productos"; // Nombre de la plantilla
    }
    //--------------------------------------------cu_-------------------------------------------------------------

}