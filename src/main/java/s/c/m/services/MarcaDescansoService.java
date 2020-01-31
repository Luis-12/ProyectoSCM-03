package s.c.m.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import s.c.m.entities.MarcaDescansos;
import s.c.m.repositories.MarcaDescansosRepository;

@Service
public class MarcaDescansoService {

    @Autowired
   private MarcaDescansosRepository marcaDescansosRepository;


    public void crearMarcaDescanso(MarcaDescansos marcaDescansos)
    {
        marcaDescansosRepository.save(marcaDescansos);
    }
}
