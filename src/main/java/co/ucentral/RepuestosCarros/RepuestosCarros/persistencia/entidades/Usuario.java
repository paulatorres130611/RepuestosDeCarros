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
@Table (name = "usuario")
@Entity
public class Usuario {
    @Id
    private Long id = 1L; // ID fijo.

    @Column(name = "username", nullable = false, unique = true)
    private String username = "AutopartesElSotano"; // Usuario fijo

    @Column(name = "password", nullable = false)
    private String password = "elsotano123";//contraseña fija
}

