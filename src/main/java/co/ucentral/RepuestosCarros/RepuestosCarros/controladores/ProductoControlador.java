package co.ucentral.RepuestosCarros.RepuestosCarros.controladores;

import co.ucentral.RepuestosCarros.RepuestosCarros.persistencia.entidades.Producto;
import co.ucentral.RepuestosCarros.RepuestosCarros.servicios.ProductoServicio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
    public String crearProducto(@ModelAttribute Producto producto) {
        productoServicio.guardarProducto(producto); // Llama al servicio para guardar el producto
        return "redirect:/productos"; // Redirige a la lista de productos después de guardar
    }

    // Cargar datos de producto para edición
    @GetMapping("/productos/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable("id") Long id, Model model) {
        Producto producto = productoServicio.obtenerProductoPorId(id);
        model.addAttribute("productoSeleccionado", producto);
        model.addAttribute("mostrarFormularioEditar", true);
        model.addAttribute("productos", productoServicio.obtenerTodosLosProductos());
        return "Productos";
    }
    // Guardar cambios del producto editado
    @PostMapping("/productos/editar")
    public String editarProducto(@ModelAttribute("productoSeleccionado") Producto producto) {
        productoServicio.actualizarProducto(producto); // Metodo en el servicio que se encargará de la actualización
        return "redirect:/productos"; // Redirige a la lista de productos después de guardar los cambios
    }


}