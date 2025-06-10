package edu.miuniversidad.miweb.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SalirController {

    @GetMapping("/salir")
    public String salir() {
        return "redirect:/logout";
    }
}
