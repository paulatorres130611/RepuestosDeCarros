package co.ucentral.RepuestosCarros.RepuestosCarros.controladores;

import co.ucentral.RepuestosCarros.RepuestosCarros.servicios.UsuarioServicio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@Controller

public class UsuarioControlador {

    UsuarioServicio usuarioServicio;

    // Muestra la vista del login
    @GetMapping("/login")
    public String mostrarLogin() {
        return "Login"; // Nombre del HTML de la vista
    }


    @PostMapping("/login")
    public String procesarLogin(@RequestParam("username") String username,
                                @RequestParam("password") String password,
                                Model model) {
        if (usuarioServicio.validarUsuario(username, password)) {
            return "redirect:/vistaPrincipal"; // Redirige a la vista principal si el login es exitoso
        } else {
            model.addAttribute("error", "Usuario o contraseña incorrectos");
            return "login"; // Devuelve a la misma vista con un mensaje de error
        }
    }
}
