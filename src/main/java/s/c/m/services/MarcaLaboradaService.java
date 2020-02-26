package s.c.m.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import s.c.m.entities.Colaborador;
import s.c.m.entities.MarcaLaboradas;
import s.c.m.repositories.MarcasLaboradasRepository;

import java.util.Date;

@Service
public class MarcaLaboradaService {
    @Autowired
    private MarcasLaboradasRepository marcasLaboradasRepository;

    public MarcaLaboradas buscaMarca(Date date)
    {
        MarcaLaboradas marcaLaboradas=null;
        try
        {
            marcaLaboradas=marcasLaboradasRepository.findByFechaMarca(date);
        }catch (Exception ex){}
        return marcaLaboradas;
    }

    public MarcaLaboradas validaMarcaDia(Colaborador idColaborador, Date date)
    {
        MarcaLaboradas marcaLaboradas=null;
        try
        {
            marcaLaboradas=marcasLaboradasRepository.findByColaboradorAndAndFechaMarca(idColaborador,date);
        }catch (Exception ex){}
        return marcaLaboradas;
    }

    public MarcaLaboradas buscaMarcaPorColaboradoYEstado(Colaborador idColaborador , String estado){
        MarcaLaboradas marcaLaboradas=null;
        try
        {
            marcaLaboradas=marcasLaboradasRepository.findByColaboradorAndEstado(idColaborador,estado);
        }catch (Exception ex){}
        return marcaLaboradas;
    }

    public void createMarcaLaborada(MarcaLaboradas marcaLaboradas)
    {
        marcasLaboradasRepository.save(marcaLaboradas);
    }

    public void updateMarcaLaborada(MarcaLaboradas marcaLaboradas) { marcasLaboradasRepository.save(marcaLaboradas); }
}
