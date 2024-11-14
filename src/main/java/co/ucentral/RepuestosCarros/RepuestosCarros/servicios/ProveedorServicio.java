package co.ucentral.RepuestosCarros.RepuestosCarros.servicios;

import co.ucentral.RepuestosCarros.RepuestosCarros.persistencia.entidades.Producto;
import co.ucentral.RepuestosCarros.RepuestosCarros.persistencia.entidades.Proveedor;
import co.ucentral.RepuestosCarros.RepuestosCarros.persistencia.repositorios.ProveedorRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@AllArgsConstructor
@Service
public class ProveedorServicio {

    ProveedorRepositorio proveedorRepositorio;

    //-------------------------------------------cu_06---------------------------------------------------
    public List<Proveedor> obtenerTodosLosProveedores() {
        return (List<Proveedor>) proveedorRepositorio.findAll();
    }
    public void guardarProveedor(Proveedor proveedor) {
        proveedorRepositorio.save(proveedor);
    }
    //------------------------------------------------cu_07---------------------------------------------------------

    public Proveedor obtenerProveedorPorRut(Long id) {
        return proveedorRepositorio.findById(id).orElse(null);
    }
    public void actualizarProveedor(Proveedor proveedor) {
        proveedorRepositorio.save(proveedor);
    }
    //------------------------------------------------cu_08---------------------------------------------------------
    public void eliminarProveedor(Long id) {
        proveedorRepositorio.deleteById(id);
    }
    //------------------------------------------------cu_16---------------------------------------------------------

    public List<Proveedor> buscarProveedores(String criterio) {
        return proveedorRepositorio.buscarPorCriterioProveedor("%" + criterio + "%");
    }

}
