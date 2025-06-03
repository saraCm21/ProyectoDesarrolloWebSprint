package edu.miuniversidad.miweb.Model.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import edu.miuniversidad.miweb.Model.Entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    void deleteByCodigoUsuario(String codigoUsuario);
    Usuario findByCodigoUsuario(String codigoUsuario);
    Usuario findByUsername(String username);
    Usuario findByUsernameAndPassword(String username, String password);
    Usuario findByEmail(String email);
    
}

