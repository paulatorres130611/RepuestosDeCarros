package co.ucentral.RepuestosCarros.RepuestosCarros.persistencia.repositorios;

import co.ucentral.RepuestosCarros.RepuestosCarros.persistencia.entidades.Venta;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface VentaRepositorio extends CrudRepository<Venta, Long> {

    @Query("""
        SELECT DISTINCT v
        FROM Venta v
        LEFT JOIN FETCH v.cliente c
        LEFT JOIN FETCH v.detalles d
        LEFT JOIN FETCH d.producto
        ORDER BY v.ven_fechaventa DESC, v.ven_id DESC
    """)
    List<Venta> listarVentasConDetalles();

    @Query("""
        SELECT DISTINCT v
        FROM Venta v
        LEFT JOIN FETCH v.cliente c
        LEFT JOIN FETCH v.detalles d
        LEFT JOIN FETCH d.producto
        WHERE v.ven_fechaventa BETWEEN :desde AND :hasta
          AND (
                :q IS NULL OR :q = '' OR
                LOWER(c.cli_nombre) LIKE LOWER(CONCAT('%', :q, '%')) OR
                CAST(c.cli_id AS string) LIKE CONCAT('%', :q, '%') OR
                CAST(v.ven_id AS string) LIKE CONCAT('%', :q, '%')
              )
        ORDER BY v.ven_fechaventa DESC, v.ven_id DESC
    """)
    List<Venta> filtrarVentasConDetalles(@Param("desde") LocalDate desde,
                                         @Param("hasta") LocalDate hasta,
                                         @Param("q") String q);
}
