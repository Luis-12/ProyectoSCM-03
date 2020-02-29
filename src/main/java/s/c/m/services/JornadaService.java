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
    //Es esta clase se implementan las funciones desclaradas en los repositorios para acceder
    // y hacer transacciones en la tabla Jornada
    @Autowired
    private JornadaRepository jornadaRepository;

    public List<Jornadas> getAllJornadas()//Funcion para cunsultar todas la jornadas de la base de datos
    {
        List<Jornadas> list = new ArrayList<Jornadas>();
        jornadaRepository.findAll().forEach(e -> list.add(e));//Se listan las jornada con la funcion del repository
        return list;
    }
}
