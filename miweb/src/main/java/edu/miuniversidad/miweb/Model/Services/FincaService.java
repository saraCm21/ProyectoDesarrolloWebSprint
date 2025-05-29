package edu.miuniversidad.miweb.Model.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.miuniversidad.miweb.Model.Entities.Finca;
import edu.miuniversidad.miweb.Model.Entities.Usuario;
import edu.miuniversidad.miweb.Model.Repositories.FincaRepository;
import edu.miuniversidad.miweb.Model.Repositories.UsuarioRepository;

@Service
public class FincaService {

    @Autowired
    private FincaRepository fincaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Finca crearFincaConCodigos(Finca finca, String codigoPropietario, String codigoCapataz,
            String codigoVendedor) {
        Usuario propietario = usuarioRepository.findByCodigoUsuario(codigoPropietario);
        Usuario capataz = usuarioRepository.findByCodigoUsuario(codigoCapataz);
        Usuario vendedor = usuarioRepository.findByCodigoUsuario(codigoVendedor);

        if (propietario != null)
            finca.setPropietarioId(propietario.getIdUsuario());
        if (capataz != null)
            finca.setCapatazId(capataz.getIdUsuario());
        if (vendedor != null)
            finca.setVendedorId(vendedor.getIdUsuario());

        return fincaRepository.save(finca);
    }
}