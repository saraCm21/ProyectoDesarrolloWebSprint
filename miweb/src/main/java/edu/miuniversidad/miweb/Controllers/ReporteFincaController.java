package edu.miuniversidad.miweb.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ReporteFincaController {
    
    @GetMapping("/reporteFinca")
    public String registerFincaForm() {
        return "ReportFinca"; 
    }
    


}
