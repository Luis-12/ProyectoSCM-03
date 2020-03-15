package s.c.m.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import s.c.m.entities.Colaborador;
import s.c.m.entities.MarcaLaboradas;
import s.c.m.repositories.MarcasLaboradasRepository;

import java.util.Date;

@Service
public class MarcaLaboradaService {
    //Es esta clase se implementan las funciones desclaradas en los repositorios para acceder
    // y hacer transacciones en la tabla Marca Laborada
    @Autowired
    private MarcasLaboradasRepository marcasLaboradasRepository;

    public MarcaLaboradas buscaMarca(Date date)//Funcion para consulta marca laborada por fecha
    {
        MarcaLaboradas marcaLaboradas=null;
        try
        {
            marcaLaboradas=marcasLaboradasRepository.findByFechaMarca(date);//Se lista las marcas laboradas usando la funcion del repository
        }catch (Exception ex){}
        return marcaLaboradas;
    }

    public MarcaLaboradas buscaMarcaSinSalida(Colaborador colaborador)//Funcion para consultar la fecha en que no se marco salida
    {
        MarcaLaboradas marcaLaboradas=null;
        try
        {
            marcaLaboradas=marcasLaboradasRepository.findTop1ByColaboradorOrderByFechaMarcaDesc(colaborador);//Se lista las marcas laboradas usando la funcion del repository
        }catch (Exception ex){}
        return marcaLaboradas;
    }

    public MarcaLaboradas validaMarcaDia(Colaborador idColaborador, Date date)//Funcion que consulta la marca diaria por colaborador y fecha
    {
        MarcaLaboradas marcaLaboradas=null;
        try
        {
            marcaLaboradas=marcasLaboradasRepository.findByColaboradorAndAndFechaMarca(idColaborador,date);//Aca se listan las marcas encontradas por la funcion del repository
        }catch (Exception ex){}
        return marcaLaboradas;
    }

    public MarcaLaboradas buscaMarcaPorColaboradoYEstado(Colaborador idColaborador , String estado){//Funcion para consultar marca por estado y colaborador
        MarcaLaboradas marcaLaboradas=null;
        try
        {
            marcaLaboradas=marcasLaboradasRepository.findByColaboradorAndEstado(idColaborador,estado);//Aca se listan las marcas por colaborador y estado usando la funcion del repository
        }catch (Exception ex){}
        return marcaLaboradas;
    }

    public void createMarcaLaborada(MarcaLaboradas marcaLaboradas)//Funcion para agregar marca laborada a la base.
    {
        marcasLaboradasRepository.save(marcaLaboradas);//Se invoca la funcion del repository para agregar la marca
    }

    public void updateMarcaLaborada(MarcaLaboradas marcaLaboradas)//Funcion para modificar una marca laborada ya existente
    {
        marcasLaboradasRepository.save(marcaLaboradas);//Aca se invoca la funcion para actualizar del repository
    }
}
