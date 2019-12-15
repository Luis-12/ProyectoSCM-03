package s.c.m.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import s.c.m.entities.Horarios;
import s.c.m.entities.Jornadas;
import s.c.m.repositories.HorarioRepository;
import s.c.m.repositories.JornadaRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class JornadaService {
    @Autowired
    private JornadaRepository jornadaRepository;

    public List<Jornadas> getAllJornadas()
    {
        List<Jornadas> list = new ArrayList<Jornadas>();
        jornadaRepository.findAll().forEach(e -> list.add(e));
        return list;
    }
}
