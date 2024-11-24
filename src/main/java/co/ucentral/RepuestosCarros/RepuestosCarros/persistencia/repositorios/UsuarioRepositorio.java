package co.ucentral.RepuestosCarros.RepuestosCarros.persistencia.repositorios;

import co.ucentral.RepuestosCarros.RepuestosCarros.persistencia.entidades.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepositorio extends CrudRepository<Usuario, Long> {

    @Query("SELECT u FROM Usuario u WHERE u.username = :username AND u.password = :password")
    Usuario validarUsuario(@Param("username") String username, @Param("password") String password);

}
