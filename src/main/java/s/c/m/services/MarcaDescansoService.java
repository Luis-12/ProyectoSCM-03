package s.c.m.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import s.c.m.entities.MarcaDescansos;
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
    public List<MarcaDescansos> buscarMarcaPorMarcaLab(int idmarcaLaboradas)
    {
        List<MarcaDescansos> list = new ArrayList<MarcaDescansos>();
        try{
            marcaDescansosRepository.findByMarcaLaboradas(idmarcaLaboradas).forEach(a -> list.add(a));
        }catch (Exception ex){}
        return list;
    }

    public  MarcaDescansos buscarMdescanso(int des,int marca)
    {
        MarcaDescansos marcaDescansos=null;
        try {
            marcaDescansos=marcaDescansosRepository.findByDescansosAndMarcaLaboradas(des,marca);
        }catch (Exception ex){}
        return marcaDescansos;
    }
}
