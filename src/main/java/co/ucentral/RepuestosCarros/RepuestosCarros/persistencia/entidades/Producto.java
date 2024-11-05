package co.ucentral.RepuestosCarros.RepuestosCarros.persistencia.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table (name = "productos")
@Entity
public class Producto {
    @Id
    @Column(name = "prod_id")
    public long id_producto;

    @Column(name = "prod_nombre")
    public String nombre;

    @Column(name = "prod_marca")
    public String marca;

    @Column(name = "prod_linea")
    public String linea;

    @Column(name = "pro_modelo")
    public String modelo;

    @Column(name = "pro_cilindraje")
    public String cilindraje;

    @Column(name = "pro_referencia")
    public String referencia;

    @Column(name = "pro_ubicacion")
    public String ubicacion;

    @Column(name = "pro_stock")
    public int stock;

    @Column(name = "pro_valor")
    public long valor;
}
