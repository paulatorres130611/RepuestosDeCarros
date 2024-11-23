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
@Table (name = "proveedores")
@Entity
public class Proveedor {
    @Id
    @Column(name = "Rut")
    private long prov_rut;

    @Column(name = "Nombre")
    private String prov_nombre;

    @Column(name = "Telefono")
    private String prov_telefono;

    @Column(name = "Direccion")
    private String prov_direccion;

    @Column(name = "Correo")
    private String prov_correo;
}
