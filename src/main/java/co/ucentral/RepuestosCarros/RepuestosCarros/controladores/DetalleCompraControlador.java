package co.ucentral.RepuestosCarros.RepuestosCarros.controladores;

import co.ucentral.RepuestosCarros.RepuestosCarros.persistencia.entidades.Producto;
import co.ucentral.RepuestosCarros.RepuestosCarros.servicios.DetalleCompraServicio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@Controller
public class DetalleCompraControlador {

    DetalleCompraServicio detallecompraServicio;


    @GetMapping("/buscar-productos")
    public String buscarProductos(
            @RequestParam("nombre") String nombre,
            @RequestParam("linea") String linea,
            Model model) {
        List<Producto> productos = detallecompraServicio.buscarProductosPorNombreYLinea(nombre, linea);
        model.addAttribute("productos", productos);
        model.addAttribute("nombre", nombre);
        model.addAttribute("linea", linea);
        return "Compras";
    }

}
