package co.ucentral.RepuestosCarros.RepuestosCarros.persistencia.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "ventas")
@Entity
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Venta")
    private long ven_id;

    @Column(name = "fecha_Venta")
    private LocalDate ven_fechaventa = LocalDate.now();

    @ManyToOne
    @JoinColumn(name = "cliente_nit")
    private Cliente cliente;

    @Column(name = "total")
    private BigDecimal ven_total;

    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleVenta> detalles = new ArrayList<>();
}
