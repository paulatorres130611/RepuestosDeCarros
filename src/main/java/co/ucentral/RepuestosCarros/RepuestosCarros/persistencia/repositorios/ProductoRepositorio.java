package co.ucentral.RepuestosCarros.RepuestosCarros.persistencia.repositorios;

import co.ucentral.RepuestosCarros.RepuestosCarros.persistencia.entidades.Producto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductoRepositorio extends CrudRepository<Producto, Long> {

    @Query("SELECT p FROM Producto p WHERE p.prod_nombre LIKE :criterio OR p.prod_marca LIKE :criterio OR p.prod_referencia LIKE :criterio")
    List<Producto> buscarPorCriterio(@Param("criterio") String criterio);
}
