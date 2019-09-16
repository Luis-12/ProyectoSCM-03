package s.c.m.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import s.c.m.entities.Colaborador;
import s.c.m.repositories.ColaboradorRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ColaboradorService {

    @Autowired
    private ColaboradorRepository colaboradorRepository;

    public List<Colaborador> getAllColaboradores() {
        List<Colaborador> list = new ArrayList<Colaborador>();
        colaboradorRepository.findAll().forEach(e -> list.add(e));
        return list;
    }

    public List<Colaborador> getAllColaboradoresActivos() {
        List<Colaborador> list = new ArrayList<Colaborador>();
        List<Colaborador> listA = new ArrayList<Colaborador>();
        colaboradorRepository.findAll().forEach(e -> list.add(e));
        for(Colaborador c: list){
            if(c.getEstado().equals("Activo")){
                listA.add(c);
            }
        }
        return listA;
    }

    public void createColaborador(Colaborador colaborador){
        colaborador.setEstado("Activo");
        colaboradorRepository.save(colaborador);
    }

    public void deleteColaborador(Colaborador colaborador){
        //ColaboradorRepository.deleteById(id);
        colaborador.setEstado("Inactivo");
        colaboradorRepository.save(colaborador);
    }

    public Colaborador findColaborador(String id){
        return colaboradorRepository.findById(id).get();
    }

    public void updateColaborador(Colaborador colaborador){
        colaborador.setEstado("Activo");
        colaboradorRepository.save(colaborador);
    }



}
