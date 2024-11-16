package co.ucentral.RepuestosCarros.RepuestosCarros.servicios;

import co.ucentral.RepuestosCarros.RepuestosCarros.persistencia.entidades.Proveedor;
import co.ucentral.RepuestosCarros.RepuestosCarros.persistencia.repositorios.CompraRepositorio;
import co.ucentral.RepuestosCarros.RepuestosCarros.persistencia.repositorios.ProveedorRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CompraServicio {

    final CompraRepositorio compraRepositorio;
    final ProveedorRepositorio proveedorRepositorio;

    // Buscar proveedor por RUT
    public Proveedor obtenerProveedorPorRut(Long id) {
        return proveedorRepositorio.findById(id).orElse(null);
    }

}
