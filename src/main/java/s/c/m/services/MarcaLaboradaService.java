package s.c.m.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    public void crearMarcaLaborada(MarcaLaboradas marcaLaboradas)
    {
        marcasLaboradasRepository.save(marcaLaboradas);
    }
}
