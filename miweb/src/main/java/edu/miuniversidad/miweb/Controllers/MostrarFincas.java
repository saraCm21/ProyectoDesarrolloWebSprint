package edu.miuniversidad.miweb.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import edu.miuniversidad.miweb.Model.Entities.Finca;
import edu.miuniversidad.miweb.Model.Repositories.FincaRepository;

import java.util.List;

@Controller
public class MostrarFincas {

    @Autowired
    private FincaRepository fincaRepository;

    @GetMapping("/home")
    public String mostrarFincas(Model model) {
        List<Finca> fincas = fincaRepository.findAll();
        model.addAttribute("fincas", fincas);
        return "Home";
    }
}
