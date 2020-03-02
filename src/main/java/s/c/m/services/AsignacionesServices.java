package s.c.m.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import s.c.m.entities.Asignaciones;
import s.c.m.entities.Colaborador;
import s.c.m.repositories.AsignacionesRepository;


@Service
public class AsignacionesServices {
    //Es esta clase se implementan las funciones desclaradas en los repositorios para acceder y hacer transacciones en la tabla Asignaciones
    @Autowired
    private AsignacionesRepository asignacionesRepository;//Se declara instancia para acceder a las funciones del repositorio asignaciones.


public Asignaciones buscarHorario(Colaborador id){//Funcion para consultar horario asignado por colaborador
    Asignaciones asignacion=null;
    try {
        asignacion=asignacionesRepository.findByColaborador(id);//Se invoca la funcion para realizar la consulta del repository
    }catch (Exception ex) { }
    return asignacion;//Aca retorna el objeto con la asignacion encontrada en caso de que asi sea
}

    public void createAsignacion(Asignaciones asignaciones) {//Funcion para agregar asignacion de un horario a un colaborador
        asignacionesRepository.save(asignaciones);//Se invoca la funcion del repository para agregar la asignacion a la base de datos.
    }

    public void updateAsignacion(Asignaciones asignacion) {//Funcion para modificar asignacion de horario a un colaborador
        asignacionesRepository.save(asignacion);//Se invoca la funcion para modificar del repository de asignaciones.
    }
}
