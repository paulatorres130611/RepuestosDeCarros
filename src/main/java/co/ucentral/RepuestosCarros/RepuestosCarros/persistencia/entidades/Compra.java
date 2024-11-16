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
@Table(name = "compras")
@Entity
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Compra")
    public long com_id;

    @Column(name = "fecha_Compra")
    public LocalDate com_fechacompra = LocalDate.now();

    @ManyToOne
    @JoinColumn(name = "proveedor_rut")
    public Proveedor proveedor;

    @Column(name = "total")
    public BigDecimal com_total;
}
