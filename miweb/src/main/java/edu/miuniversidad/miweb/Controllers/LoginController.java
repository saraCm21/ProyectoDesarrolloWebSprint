package edu.miuniversidad.miweb.Controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {


    @GetMapping("/login")
    public String loginForm() {
        return "Login";
    }
}
