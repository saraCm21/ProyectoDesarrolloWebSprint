package edu.miuniversidad.miweb.Controllers;

import edu.miuniversidad.miweb.Model.Repositories.FincaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

@Controller
public class EliminarFincaController {

    @Autowired
    private FincaRepository fincaRepository;

    @Transactional
    @PostMapping("/eliminarFinca")
    public String eliminarFinca(@RequestParam("codigoFinca") String codigoFinca) {
        fincaRepository.deleteByCodigoFinca(codigoFinca);
        return "redirect:/home";
    }
}
