package co.ucentral.RepuestosCarros.RepuestosCarros.persistencia.entidades;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "compras") /*plural tablas*/
@Entity
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id Compra")
    public long com_id;

    @Column(name = "Fecha de Compra", nullable = false)
    private LocalDate com_fechacompra = LocalDate.now();

    @ManyToOne
    @JoinColumn(name = "Proveedor", nullable = false)
    private Proveedor proveedor;

    @Column(name = "total", nullable = false)
    private BigDecimal total;
}
