package co.ucentral.RepuestosCarros.RepuestosCarros.persistencia.repositorios;


import co.ucentral.RepuestosCarros.RepuestosCarros.persistencia.entidades.Cliente;
import co.ucentral.RepuestosCarros.RepuestosCarros.persistencia.entidades.Proveedor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClienteRepositorio extends CrudRepository<Cliente, Long> {

    @Query("SELECT c FROM Cliente c WHERE c.cli_nombre LIKE :criterio OR CAST(c.cli_id AS string) LIKE :criterio")
    List<Cliente> buscarPorCriterioCliente(@Param("criterio") String criterio);
}
