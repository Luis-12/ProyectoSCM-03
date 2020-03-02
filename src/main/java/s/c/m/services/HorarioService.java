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

    public List<Horarios> getAllHorarios()//Funcion para consultar todos los horarios
    {
        List<Horarios> list = new ArrayList<Horarios>();
        horarioRepository.findAll().forEach(e -> list.add(e));//Se carga la lista de horarios con la funcion del repository
        return list;
    }


    public Optional<Horarios> findHorario(String jornada)
    {
        return horarioRepository.findById(jornada);
    }

    public List<Horarios> findHorariosPorJornada(Jornadas idJornada){//Funcion para consultar horarios por jornada
        if(idJornada.equals(null)){//Si la jornada es null quiere decir que no hay horarios para esa jornada
            System.out.println("No hay horarios para esa jornada");
            List<Horarios> horarios = new ArrayList<Horarios>();
            horarioRepository.findAll().forEach(e -> horarios.add(e));//Se listan todos
            return horarios;
        }
        return horarioRepository.findByJornada(idJornada);//Aca si se retorna la lista de horarios por jornada
    }

}