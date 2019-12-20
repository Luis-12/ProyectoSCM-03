package s.c.m.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import s.c.m.entities.Colaborador;
import s.c.m.entities.Tipodedescansos;
import s.c.m.repositories.DescansosRepository;

@Service
public class DescansoServices {

    @Autowired
    private DescansosRepository descansosRepository;

    public Tipodedescansos buscarDescansos(Colaborador id)
    {
       return descansosRepository.findByColaborador(id);
    }

    public void createDescansos(Tipodedescansos descansos)
    {
        descansosRepository.save(descansos);
    }


}
