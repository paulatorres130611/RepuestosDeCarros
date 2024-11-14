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
    public long prov_rut;

    @Column(name = "Nombre")
    public String prov_nombre;

    @Column(name = "Telefono")
    public String prov_telefono;

    @Column(name = "Direccion")
    public String prov_direccion;

    @Column(name = "Correo")
    public String prov_correo;
}
