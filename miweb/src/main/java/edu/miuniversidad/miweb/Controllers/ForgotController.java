package edu.miuniversidad.miweb.Controllers;

import edu.miuniversidad.miweb.Model.Services.SendEmailService;
import edu.miuniversidad.miweb.Model.Services.ChangePasswordService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ForgotController {

    @Autowired
    private SendEmailService emailService; // Tu servicio para enviar emails

    @Autowired
    private ChangePasswordService changePasswordService; // Tu servicio para cambiar contraseñas

    @PostMapping("/sendEmail")
    public String sendEmail(HttpServletRequest request) {
        String email = request.getParameter("email");
        boolean enviado = emailService.sendEmail(email); // Implementa este método en tu servicio

        if (enviado) {
            return "Forgot"; // Muestra la vista Forgot.html
        } else {
            return "redirect:/login?error=email_failed";
        }
    }

    @PostMapping("/forgot")
    public String changePassword(HttpServletRequest request) {
        String email = request.getParameter("email");
        String code = request.getParameter("code");
        String password = request.getParameter("password");
        int codeInt = Integer.parseInt(code);

        boolean cambiado = changePasswordService.cambiarPassword(email, codeInt, password);

        if (cambiado) {
            return "redirect:/login?success=password_changed";
        } else {
            return "redirect:/forgot?error=invalid_code";
        }
    }

    @GetMapping("/forgot")
    public String forgotForm() {
        return "Forgot"; // Para mostrar la vista si se accede por GET
    }
}
