package s.c.m.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import s.c.m.entities.Descansos;
import s.c.m.entities.MarcaDescansos;
import s.c.m.entities.MarcaLaboradas;
import s.c.m.repositories.MarcaDescansosRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class MarcaDescansoService {

    @Autowired
   private MarcaDescansosRepository marcaDescansosRepository;


    public void crearMarcaDescanso(MarcaDescansos marcaDescansos)
    {
        marcaDescansosRepository.save(marcaDescansos);
    }

    public void updateMarcaDescanso(MarcaDescansos marcaDescansos)
    {
        marcaDescansosRepository.save(marcaDescansos);
    }

    public List<MarcaDescansos> buscarMarcaPorMarcaLab(MarcaLaboradas marcaLaboradas)
    {
        List<MarcaDescansos> list = new ArrayList<MarcaDescansos>();
        try{
            marcaDescansosRepository.findByMarcaLaboradas(marcaLaboradas).forEach(a -> list.add(a));
        }catch (Exception ex){}
        return list;
    }

    public  MarcaDescansos buscarMdescanso(Descansos des, MarcaLaboradas marca)
    {
        MarcaDescansos marcaDescansos=null;
        try {
            marcaDescansos=marcaDescansosRepository.findByDescansosAndMarcaLaboradas(des,marca);
        }catch (Exception ex){}
        return marcaDescansos;
    }
}
