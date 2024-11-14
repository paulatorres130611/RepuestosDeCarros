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
@Table (name = "clientes")
@Entity
public class Cliente {
    @Id
    @Column(name = "Nit o Cedula")
    public long cli_id;

    @Column(name = "Nombre")
    public String cli_nombre;

    @Column(name = "Telefono")
    public String cli_telefono;

    @Column(name = "Direccion")
    public String cli_direccion;

    @Column(name = "Correo")
    public String cli_correo;
}
