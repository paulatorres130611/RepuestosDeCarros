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

    //metodos
    public Producto guardarproducto(Producto producto){
        return productoRepositorio.save(producto); //llama el metodo save,save guarda el objeto producto en la BDA,retorna al obejto producto
    }

    public List<Producto> obtenerTodosLosProductos(){
        List<Producto> listado = (List<Producto>) productoRepositorio.findAll();//la salida dindAll la convierte a una listado
        return listado;
    }

}
