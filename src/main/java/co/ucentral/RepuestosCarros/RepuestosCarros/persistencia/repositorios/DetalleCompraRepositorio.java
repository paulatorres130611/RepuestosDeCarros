package co.ucentral.RepuestosCarros.RepuestosCarros.persistencia.repositorios;

import co.ucentral.RepuestosCarros.RepuestosCarros.persistencia.entidades.DetalleCompra;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleCompraRepositorio extends CrudRepository<DetalleCompra, Long> {

}
