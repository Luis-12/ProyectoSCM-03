package s.c.m.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import s.c.m.entities.Asignaciones;
import s.c.m.entities.Colaborador;
import s.c.m.repositories.AsignacionesRepository;


@Service
public class AsignacionesServices {

    @Autowired
    private AsignacionesRepository asignacionesRepository;


public Asignaciones buscarHorario(Colaborador id){
    Asignaciones asignacion=null;
    try {
        asignacion=asignacionesRepository.findByColaborador(id);
    }catch (Exception ex) { }
    return asignacion;
}

    public void createAsignacion(Asignaciones asignaciones) {
        asignacionesRepository.save(asignaciones);
    }

    public void updateAsignacion(Asignaciones asignacion){
        asignacionesRepository.save(asignacion);
    }
}
