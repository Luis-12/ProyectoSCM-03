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
    @Autowired
    private AsignacionDescansosRepository asignacionDescansosRepository;

    @Transactional
    public void deletePorColaborador(Colaborador idColaborador){
        asignacionDescansosRepository.deleteByColaborador(idColaborador);
        //asignacionDescansosRepository.deleteById(idColaborador);
    }

    public List<AsignacionDescansos> buscarDescansosAsignadosPorColaborador(Colaborador idColaborador)
    {
        List<AsignacionDescansos> list = new ArrayList<AsignacionDescansos>();
        asignacionDescansosRepository.findByColaborador(idColaborador).forEach(a -> list.add(a));
        return list;
    }

    public void createAsignacionDescanso(AsignacionDescansos asignacionDescansos) {
        asignacionDescansosRepository.save(asignacionDescansos);
    }

    public void updateAsignacionDescanso(AsignacionDescansos asignacionDescansos){
        asignacionDescansosRepository.save(asignacionDescansos);
    }
}
