package co.ucentral.RepuestosCarros.RepuestosCarros.persistencia.repositorios;


import co.ucentral.RepuestosCarros.RepuestosCarros.persistencia.entidades.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface ClienteRepositorio extends CrudRepository<Cliente, Long> {
}
