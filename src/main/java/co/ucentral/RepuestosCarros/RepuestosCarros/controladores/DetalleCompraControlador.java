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
    @ResponseBody
    public List<Producto> buscarProductos(
            @RequestParam("nombre") String nombre,
            @RequestParam("linea") String linea) {
        return detallecompraServicio.buscarProductosPorNombreYLinea(nombre, linea);
    }

}
