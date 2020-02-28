package s.c.m.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import s.c.m.entities.Horarios;
import s.c.m.entities.Jornadas;
import s.c.m.entities.Puesto;
import s.c.m.repositories.HorarioRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HorarioService {
    //Es esta clase se implementan las funciones desclaradas en los repositorios para acceder
    // y hacer transacciones en la tabla Departamento
    @Autowired
    private HorarioRepository horarioRepository;

    public List<Horarios> getAllHorarios()
    {
        List<Horarios> list = new ArrayList<Horarios>();
        horarioRepository.findAll().forEach(e -> list.add(e));
        return list;
    }


    public Optional<Horarios> findHorario(String jornada)
    {
        return horarioRepository.findById(jornada);
    }

    public List<Horarios> findHorariosPorJornada(Jornadas idJornada){
        if(idJornada.equals(null)){
            System.out.println("No hay horarios para esa jornada");
            List<Horarios> horarios = new ArrayList<Horarios>();
            horarioRepository.findAll().forEach(e -> horarios.add(e));
            return horarios;
        }
        return horarioRepository.findByJornada(idJornada);
    }

}