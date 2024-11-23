package co.ucentral.RepuestosCarros.RepuestosCarros.persistencia.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table (name = "productos") /*plural tablas*/
@Entity
public class Producto { /*singular clases*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Producto")
    private long prod_id;/*llama*/

    @Column(name = "Nombre")
    private String prod_nombre;

    @Column(name = "Marca")
    private String prod_marca;

    @Column(name = "Linea")
    private String prod_linea;

    @Column(name = "Modelo")
    private String prod_modelo;

    @Column(name = "Cilindraje")
    private String prod_cilindraje;

    @Column(name = "Referencia")
    private String prod_referencia;

    @Column(name = "Descripcion")
    private String prod_descripcion;

    @Column(name = "Ubicacion")
    private String prod_ubicacion;

    @Column(name = "Stock")
    private int prod_stock;

    @Column(name = "Precio")
    private BigDecimal prod_precio;
}
