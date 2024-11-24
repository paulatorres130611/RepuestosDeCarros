package co.ucentral.RepuestosCarros.RepuestosCarros.persistencia.repositorios;


import co.ucentral.RepuestosCarros.RepuestosCarros.persistencia.entidades.DetalleVenta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleVentaRepositorio extends CrudRepository<DetalleVenta, Long> {

}
