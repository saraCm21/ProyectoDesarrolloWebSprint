package edu.miuniversidad.miweb.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import edu.miuniversidad.miweb.Model.Entities.Finca;
import edu.miuniversidad.miweb.Model.Services.CreateFincaService;

@Controller
public class RegisterFincaController {
    
    @GetMapping("/registerFinca")
    public String registerFincaForm() {
        return "RegisterFinca"; 
    }

    @Autowired
    private CreateFincaService fincaService;

    @PostMapping("/registerFinca")
    public String createFinca(@ModelAttribute Finca finca, String codigoPropietario, String codigoCapataz,
            String codigoVendedor) {
        fincaService.createFinca(finca, codigoPropietario, codigoCapataz, codigoVendedor);
        return "redirect:/home"; // Redirige al home después de crear la finca
    }


}
