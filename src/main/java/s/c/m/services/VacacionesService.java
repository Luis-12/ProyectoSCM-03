package s.c.m.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import s.c.m.entities.AsignacionDescansos;
import s.c.m.entities.Colaborador;
import s.c.m.entities.Vacaciones;
import s.c.m.repositories.VacacionesRepository;

@Service
public class VacacionesService {
    //Es esta clase se implementan las funciones desclaradas en los repositorios para acceder
    // y hacer transacciones en la tabla Vacaciones

    @Autowired
    private VacacionesRepository vacacionesRepository;

    public Vacaciones diasDisponibles(Colaborador idColaborador){//Funcion para consultar las vaciones que tiene disponible el colaborador
        Vacaciones diasDisponibles=null;
        try
        {
            diasDisponibles=vacacionesRepository.findByColaborador(idColaborador);//Aca se invoca la funcion del repository para consultar las vacaciones
        }catch (Exception ex){}

        return diasDisponibles;
    }

    public void createVacaciones(Vacaciones vacaciones) {//Funcion para agregar vacaciones a la base

        vacacionesRepository.save(vacaciones);//Se invoca funcion del repository para agregar las vaciones
    }

    public void updateVacaciones(Vacaciones vacaciones) {//Funcion para actualizar las vacaciones en la base
        Vacaciones v=new Vacaciones();
        v=vacacionesRepository.findByColaborador(vacaciones.getColaborador());//se consulta las vacaciones por colaborador
        //v.setDiasdisponibles(vacaciones.getDiasdisponibles());//Se actualizan los dias disponibles
        vacacionesRepository.save(v);//Se guarda
    }

}
