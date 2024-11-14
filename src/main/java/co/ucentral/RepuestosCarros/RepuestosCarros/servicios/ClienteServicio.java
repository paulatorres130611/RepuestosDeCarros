package co.ucentral.RepuestosCarros.RepuestosCarros.servicios;

import co.ucentral.RepuestosCarros.RepuestosCarros.persistencia.entidades.Cliente;
import co.ucentral.RepuestosCarros.RepuestosCarros.persistencia.entidades.Proveedor;
import co.ucentral.RepuestosCarros.RepuestosCarros.persistencia.repositorios.ClienteRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@AllArgsConstructor
@Service
public class ClienteServicio {

    ClienteRepositorio clienteRepositorio;

    //-------------------------------------------cu_12---------------------------------------------------
    public List<Cliente> obtenerTodosLosClientes() {
        return (List<Cliente>) clienteRepositorio.findAll();
    }
    public void guardarCliente(Cliente cliente) {
        clienteRepositorio.save(cliente);
    }

    //------------------------------------------------cu_13---------------------------------------------------------

    public Cliente obtenerClientePorId(Long id) {
        return clienteRepositorio.findById(id).orElse(null);
    }
    public void actualizarCliente(Cliente cliente) {
        clienteRepositorio.save(cliente);
    }

    //------------------------------------------------cu_14---------------------------------------------------------
    public void eliminarCliente(Long id) {
        clienteRepositorio.deleteById(id);
    }

    //------------------------------------------------cu_16---------------------------------------------------------

    public List<Cliente> buscarClientes(String criterio) {
        return clienteRepositorio.buscarPorCriterioCliente("%" + criterio + "%");
    }

}
