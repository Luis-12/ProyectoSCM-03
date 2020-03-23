package s.c.m.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import s.c.m.entities.Descansos;
import s.c.m.entities.MarcaDescansos;
import s.c.m.entities.MarcaLaboradas;
import s.c.m.repositories.MarcaDescansosRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class MarcaDescansoService {
    //Es esta clase se implementan las funciones desclaradas en los repositorios para acceder
    // y hacer transacciones en la tabla Marca descanso

    @Autowired
   private MarcaDescansosRepository marcaDescansosRepository;


    public void crearMarcaDescanso(MarcaDescansos marcaDescansos)//Funcion para agregar la marca descanso a la base
    {
        try
        {
            marcaDescansosRepository.save(marcaDescansos);//Se invoca la funcion para agregar marca des del repository
        }catch (Exception ex)
        {
            System.out.println(ex);
        }

    }

    public void updateMarcaDescanso(MarcaDescansos marcaDescansos)//Funcion para actualizar la marca descanso en la base
    {
        marcaDescansosRepository.save(marcaDescansos);//Se invoca la funcion para modificar marca des del repository
    }

    public List<MarcaDescansos> buscarMarcaPorMarcaLab(MarcaLaboradas marcaLaboradas)//Funcion para consulta marda descanso por marca laborada
    {
        List<MarcaDescansos> list = new ArrayList<MarcaDescansos>();
        try{
            marcaDescansosRepository.findByMarcaLaboradas(marcaLaboradas).forEach(a -> list.add(a));//Se llena la lista de las marcar de descanso consultadas desde la funcion del repository por marca laborada
        }catch (Exception ex){}
        return list;
    }

    public  MarcaDescansos buscarMdescanso(Descansos des, MarcaLaboradas marca)//Funcion para consultar marca de descanso por el descanso y la marca laborada
    {
        MarcaDescansos marcaDescansos=null;
        try {
            marcaDescansos=marcaDescansosRepository.findByDescansosAndMarcaLaboradas(des,marca);//Aca se lista las marcas descanso usando la funcion del repository
        }catch (Exception ex){}
        return marcaDescansos;
    }
}
