package co.ucentral.RepuestosCarros.RepuestosCarros.persistencia.repositorios;

import co.ucentral.RepuestosCarros.RepuestosCarros.persistencia.entidades.Compra;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface CompraRepositorio extends CrudRepository<Compra, Long> {

    @Query("""
        SELECT DISTINCT c
        FROM Compra c
        LEFT JOIN FETCH c.proveedor p
        LEFT JOIN FETCH c.detalles d
        LEFT JOIN FETCH d.producto
        WHERE c.com_fechacompra BETWEEN :desde AND :hasta
          AND (
                :q IS NULL OR :q = '' OR
                LOWER(p.prov_nombre) LIKE LOWER(CONCAT('%', :q, '%')) OR
                CAST(p.prov_rut AS string) LIKE CONCAT('%', :q, '%') OR
                CAST(c.com_id AS string) LIKE CONCAT('%', :q, '%')
              )
        ORDER BY c.com_fechacompra DESC, c.com_id DESC
    """)
    List<Compra> filtrarComprasConDetalles(@Param("desde") LocalDate desde,
                                           @Param("hasta") LocalDate hasta,
                                           @Param("q") String q);

    @Query("""
        SELECT DISTINCT c
        FROM Compra c
        LEFT JOIN FETCH c.proveedor
        LEFT JOIN FETCH c.detalles d
        LEFT JOIN FETCH d.producto
        ORDER BY c.com_fechacompra DESC, c.com_id DESC
    """)
    List<Compra> listarComprasConDetalles();
}