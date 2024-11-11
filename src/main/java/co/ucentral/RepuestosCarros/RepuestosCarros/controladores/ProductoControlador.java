package co.ucentral.RepuestosCarros.RepuestosCarros.controladores;

import co.ucentral.RepuestosCarros.RepuestosCarros.persistencia.entidades.Producto;
import co.ucentral.RepuestosCarros.RepuestosCarros.servicios.ProductoServicio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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

    //Metodo para mostrar el formularioCrear
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


}