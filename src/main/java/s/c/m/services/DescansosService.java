package s.c.m.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import s.c.m.entities.Descansos;
import s.c.m.entities.Horarios;
import s.c.m.repositories.DescansosRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class DescansosService {
    @Autowired
    private DescansosRepository descansosRepository;

    public List<Descansos> buscarDescansosPorHorario(Horarios idHorario)//Funcion para buscar los descansos por idHorario
    {
        List<Descansos> list = new ArrayList<Descansos>();
        descansosRepository.findByHorario(idHorario).forEach(d -> list.add(d));
        return list;
    }
}
