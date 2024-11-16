package co.ucentral.RepuestosCarros.RepuestosCarros.servicios;

import co.ucentral.RepuestosCarros.RepuestosCarros.persistencia.entidades.Producto;
import co.ucentral.RepuestosCarros.RepuestosCarros.persistencia.repositorios.ProductoRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ProductoServicio {

    ProductoRepositorio productoRepositorio; //la clase ProductoServicio depende de la interfaz ProductoRepositorio

    //--------------------------------------------cu_02-------------------------------------------------------------
    public void guardarProducto(Producto producto) {
        productoRepositorio.save(producto); // Guarda el producto en la base de datos
    }
    public List<Producto> obtenerTodosLosProductos() {
        return (List<Producto>) productoRepositorio.findAll();
    }

    //--------------------------------------------cu_03-------------------------------------------------------------
    public Producto obtenerProductoPorId(Long id) { // métodos para obtener un producto por ID
        return productoRepositorio.findById(id).orElse(null);
    }
    public void actualizarProducto(Producto producto) {
        productoRepositorio.save(producto); // Guarda el producto actualizado
    }

    //--------------------------------------------cu_04-------------------------------------------------------------
    public void eliminarProducto(Long id) {
        productoRepositorio.deleteById(id); // Elimina el producto de la base de datos
    }

    //--------------------------------------------cu_16-------------------------------------------------------------
    public List<Producto> buscarProductos(String criterio) {
        return productoRepositorio.buscarPorCriterio("%" + criterio + "%");
    }


}
