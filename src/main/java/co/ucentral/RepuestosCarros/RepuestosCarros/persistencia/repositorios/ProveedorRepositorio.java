package co.ucentral.RepuestosCarros.RepuestosCarros.persistencia.repositorios;


import co.ucentral.RepuestosCarros.RepuestosCarros.persistencia.entidades.Proveedor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProveedorRepositorio extends CrudRepository<Proveedor, Long> {

    @Query("SELECT pr FROM Proveedor pr WHERE pr.prov_nombre LIKE :criterio OR CAST(pr.prov_rut AS string) LIKE :criterio")
    List<Proveedor> buscarPorCriterioProveedor(@Param("criterio") String criterio);

}
