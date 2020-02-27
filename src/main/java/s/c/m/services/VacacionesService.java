package s.c.m.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import s.c.m.entities.AsignacionDescansos;
import s.c.m.entities.Colaborador;
import s.c.m.entities.Vacaciones;
import s.c.m.repositories.VacacionesRepository;

@Service
public class VacacionesService {

    @Autowired
    private VacacionesRepository vacacionesRepository;

    public Vacaciones diasDisponibles(Colaborador idColaborador){

        Vacaciones diasDisponibles=null;

        try
        {

            diasDisponibles=vacacionesRepository.findByColaborador(idColaborador);
        }catch (Exception ex){}

        return diasDisponibles;
    }

    public void createVacaciones(Vacaciones vacaciones) {

        vacacionesRepository.save(vacaciones);
    }

    public void updateVacaciones(Vacaciones vacaciones) {
        Vacaciones v=new Vacaciones();
        v=vacacionesRepository.findByColaborador(vacaciones.getColaborador());
        v.setDiasdisponibles(vacaciones.getDiasdisponibles());
        vacacionesRepository.save(v);
    }

}
