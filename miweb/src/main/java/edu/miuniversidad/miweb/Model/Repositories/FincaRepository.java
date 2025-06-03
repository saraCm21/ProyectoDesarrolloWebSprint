package edu.miuniversidad.miweb.Model.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import edu.miuniversidad.miweb.Model.Entities.Finca;

public interface FincaRepository extends JpaRepository<Finca, Integer> {

    void deleteByCodigoFinca(String codigoFinca);
    Finca findByCodigoFinca(String codigoFinca);
    Finca findByNombre(String nombre);




}

