package co.ucentral.RepuestosCarros.RepuestosCarros.servicios;

import co.ucentral.RepuestosCarros.RepuestosCarros.persistencia.entidades.Usuario;
import co.ucentral.RepuestosCarros.RepuestosCarros.persistencia.repositorios.UsuarioRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UsuarioServicio {

    UsuarioRepositorio usuarioRepositorio;

    public boolean validarUsuario(String username, String password) {
        Usuario usuario = usuarioRepositorio.validarUsuario(username, password);
        return usuario != null;
    }

}
