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
    //Es esta clase se implementan las funciones desclaradas en los repositorios para acceder
    // y hacer transacciones en la tabla Descansos
    @Autowired
    private DescansosRepository descansosRepository;

    public List<Descansos> buscarDescansosPorHorario(Horarios idHorario)//Funcion para buscar los descansos por idHorario
    {
        List<Descansos> list = new ArrayList<Descansos>();
        descansosRepository.findByHorario(idHorario).forEach(d -> list.add(d));//Se llena la lista por los descanso por id de horario
        return list;
    }
}
