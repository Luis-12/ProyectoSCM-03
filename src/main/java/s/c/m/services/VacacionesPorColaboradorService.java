package s.c.m.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import s.c.m.entities.Colaborador;
import s.c.m.entities.Vacaciones;
import s.c.m.entities.VacacionesPorColaborador;
import s.c.m.repositories.VacacionesPorColaboradorRepository;

@Service
public class VacacionesPorColaboradorService {

    @Autowired
    private VacacionesPorColaboradorRepository vacacionesPorColaboradorRepository;

    public VacacionesPorColaborador findVacacionesPorColaborador(Colaborador idColaborador){//Funcion para consultar las vaciones que tiene disponible el colaborador
        VacacionesPorColaborador vacacionesPorColaborador=new VacacionesPorColaborador();
        try
        {
            vacacionesPorColaborador=vacacionesPorColaboradorRepository.findByColaborador(idColaborador);//Aca se invoca la funcion del repository para consultar las vacaciones
        }catch (Exception ex){}

        return vacacionesPorColaborador;
    }

    public void createVacacionesPorColaborador(VacacionesPorColaborador vacacionesPC) {//Funcion para agregar vacaciones a la base
        vacacionesPorColaboradorRepository.save(vacacionesPC);//Se invoca funcion del repository para agregar las vaciones
    }

    public void updateVacacionesPorColaborador(VacacionesPorColaborador vacacionesPC) {//Funcion para actualizar las vacaciones en la base
        VacacionesPorColaborador v=new VacacionesPorColaborador();
        v=vacacionesPorColaboradorRepository.findByColaborador(vacacionesPC.getColaborador());//se consulta las vacaciones por colaborador

        v.setDiasdisponibles(vacacionesPC.getDiasdisponibles());//Se actualizan los dias disponibles
        v.setFechaasignada(vacacionesPC.getFechaasignada());//Se actualiza la fecha asignada
        vacacionesPorColaboradorRepository.save(v);//Se guarda
    }

}
