package s.c.m.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import s.c.m.entities.Asignaciones;
import s.c.m.entities.Colaborador;
import s.c.m.entities.Departamento;
import s.c.m.entities.Horarios;
import s.c.m.repositories.AsignacionesRepository;
import s.c.m.repositories.DepartamentoRepository;

@Service
public class AsignacionesServices {

    @Autowired
    private AsignacionesRepository asignacionesRepository;




    public void createAsignacion(Asignaciones asignaciones) {

        System.out.println("Nombre: "+asignaciones.getColaborador().getNombre());
        System.out.println("Descanso: "+asignaciones.getDiaDescanso());
        System.out.println("Horario: "+ asignaciones.getHorario().getPk_idhorario());


        asignacionesRepository.save(asignaciones);

    }
}
