package edu.miuniversidad.miweb.Model.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import edu.miuniversidad.miweb.Model.Entities.Usuario;
import edu.miuniversidad.miweb.Model.Repositories.UsuarioRepository;

@Service
public class LoginService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Verifica las credenciales del usuario.
     *
     * @param usuarioOEmail el nombre de usuario
     * @param password      la contraseña
     * @return true si las credenciales son válidas, false en caso contrario
     */

    public boolean login(String usuarioOEmail, String password) {
        Usuario usuario = usuarioRepository.findByUsername(usuarioOEmail);
        if (usuario == null) {
            usuario = usuarioRepository.findByEmail(usuarioOEmail);
        }
        if (usuario != null && passwordEncoder.matches(password, usuario.getPassword())) {
            return true;
        }
        return false;
    }

}
