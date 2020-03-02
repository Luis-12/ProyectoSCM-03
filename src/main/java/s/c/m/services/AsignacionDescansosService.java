package s.c.m.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import s.c.m.entities.AsignacionDescansos;
import s.c.m.entities.Colaborador;
import s.c.m.repositories.AsignacionDescansosRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class AsignacionDescansosService {
    //Es esta clase se implementan las funciones desclaradas en los repositorios para acceder y hacer transacciones en la tabla Asignaciones Descansos
    @Autowired
    private AsignacionDescansosRepository asignacionDescansosRepository;//Se declara objeto para instanciar el repository de asignacion descansos

    @Transactional
    public void deletePorColaborador(Colaborador idColaborador){//Funcion para eliminar las asignaciones de descansos que tenga el colaborador
        asignacionDescansosRepository.deleteByColaborador(idColaborador);//Se invoca la funcion para implementar la funcion declarada en el repository
    }

    public List<AsignacionDescansos> buscarDescansosAsignadosPorColaborador(Colaborador idColaborador)//Funcion para buscar los descansos asignados por colaborador
    {
        List<AsignacionDescansos> list = new ArrayList<AsignacionDescansos>();//Se declara la list de asignaciones de decansos
        asignacionDescansosRepository.findByColaborador(idColaborador).forEach(a -> list.add(a));//Y aca se llena la lista con las que se consulten de la funcion declarada en el repository
        return list;
    }

    public void createAsignacionDescanso(AsignacionDescansos asignacionDescansos) {//Se declara funcion para agregar asignaciones de descansos a la base
        asignacionDescansosRepository.save(asignacionDescansos);//Se invoca la funcion para agregar asignaciones de descanso del repository
    }

    public void updateAsignacionDescanso(AsignacionDescansos asignacionDescansos){//Se declara funcion para modificar asignaciones de descansos en la base de datos
        asignacionDescansosRepository.save(asignacionDescansos);//Se invoca la funcion para modificar del repository de asignaciones descansos
    }
}
