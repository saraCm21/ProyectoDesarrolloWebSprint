package edu.miuniversidad.miweb.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import edu.miuniversidad.miweb.Model.Entities.Usuario;
import edu.miuniversidad.miweb.Model.Services.SignUpService;

@Controller
public class SignUpController {

    @Autowired
    private SignUpService signUpService;
    
    @GetMapping("/signup")
    public String loginForm() {
        return "SignUp"; // Retorna la vista signup.html
    }

    @PostMapping("/signup")
    public String registerUser(@ModelAttribute Usuario usuario) {
        signUpService.registrarUsuario(usuario);
        return "redirect:/login";
    }

}
