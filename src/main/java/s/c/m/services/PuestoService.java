package s.c.m.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import s.c.m.entities.Puesto;
import s.c.m.repositories.PuestoRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class PuestoService {
    @Autowired
    private PuestoRepository puestoRepository;

    public List<Puesto> getAllPuestos() {
        List<Puesto> list = new ArrayList<Puesto>();
        puestoRepository.findAll().forEach(e -> list.add(e));
        return list;
    }
}
