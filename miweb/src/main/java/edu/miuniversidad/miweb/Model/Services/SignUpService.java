package edu.miuniversidad.miweb.Model.Services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import edu.miuniversidad.miweb.Model.Entities.Usuario;
import edu.miuniversidad.miweb.Model.Repositories.UsuarioRepository;

@Service
public class SignUpService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     * @param usuario el usuario a registrar
     * @return el usuario guardado
     */
    public Usuario registrarUsuario(Usuario usuario) {
        String codigo = "%06d".formatted(new Random().nextInt(1000000));
        usuario.setCodigoUsuario(codigo);
        String hashedPassword = passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(hashedPassword);
        return usuarioRepository.save(usuario);
    }
}
