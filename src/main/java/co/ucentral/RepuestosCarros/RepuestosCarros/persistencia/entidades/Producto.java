package co.ucentral.RepuestosCarros.RepuestosCarros.persistencia.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    public long prod_id;/*llama*/

    @Column(name = "Nombre")
    public String prod_nombre;

    @Column(name = "Marca")
    public String prod_marca;

    @Column(name = "Linea")
    public String prod_linea;

    @Column(name = "Modelo")
    public String prod_modelo;

    @Column(name = "Cilindraje")
    public String prod_cilindraje;

    @Column(name = "Referencia")
    public String prod_referencia;

    @Column(name = "Descripcion")
    public String prod_descripcion;

    @Column(name = "Ubicacion")
    public String prod_ubicacion;

    @Column(name = "Stock")
    public int prod_stock;

    @Column(name = "valor")
    public long prod_valor;
}
