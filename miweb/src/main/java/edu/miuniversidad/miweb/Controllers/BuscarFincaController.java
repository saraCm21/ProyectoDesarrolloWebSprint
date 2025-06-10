package edu.miuniversidad.miweb.Controllers;

import edu.miuniversidad.miweb.Model.Entities.Finca;
import edu.miuniversidad.miweb.Model.Repositories.FincaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@Controller
public class BuscarFincaController {

    @Autowired
    private FincaRepository fincaRepository;

    @PostMapping("/buscarFincaAjax")
    @ResponseBody
    public ResponseEntity<?> buscarFincaAjax(@RequestBody Map<String, String> body) {
        String codigoFinca = body.get("codigoFinca");
        System.out.println("Buscando finca con código: " + codigoFinca); // <-- LOG
        if (codigoFinca == null || codigoFinca.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("empty_search");
        }
        Finca finca = fincaRepository.findByCodigoFinca(codigoFinca);
        if (finca == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("finca_not_found");
        }
        return ResponseEntity.ok(finca);
    }
}
