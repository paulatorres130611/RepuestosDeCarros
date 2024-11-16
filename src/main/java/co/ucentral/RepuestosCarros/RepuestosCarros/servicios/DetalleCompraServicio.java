package co.ucentral.RepuestosCarros.RepuestosCarros.servicios;

import co.ucentral.RepuestosCarros.RepuestosCarros.persistencia.entidades.Producto;
import co.ucentral.RepuestosCarros.RepuestosCarros.persistencia.repositorios.DetalleCompraRepositorio;
import co.ucentral.RepuestosCarros.RepuestosCarros.persistencia.repositorios.ProductoRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class DetalleCompraServicio {

    DetalleCompraRepositorio detalleCompraRepositorio;
    ProductoRepositorio productoRepositorio;

    public List<Producto> buscarProductosPorNombreYLinea(String nombre, String linea) {
        return productoRepositorio.buscarPorNombreYLinea(nombre, linea);
    }


}
