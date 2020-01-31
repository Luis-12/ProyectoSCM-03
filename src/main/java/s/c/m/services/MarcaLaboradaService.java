package s.c.m.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import s.c.m.entities.MarcaLaboradas;
import s.c.m.repositories.MarcasLaboradasRepository;

@Service
public class MarcaLaboradaService {
    @Autowired
    private MarcasLaboradasRepository marcasLaboradasRepository;

    public void crearMarcaLaborada(MarcaLaboradas marcaLaboradas)
    {
        marcasLaboradasRepository.save(marcaLaboradas);
    }
}
