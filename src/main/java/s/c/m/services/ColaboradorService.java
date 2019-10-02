package s.c.m.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import s.c.m.entities.Colaborador;
import s.c.m.entities.Departamento;
import s.c.m.entities.Puesto;
import s.c.m.repositories.ColaboradorRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ColaboradorService {

    @Autowired
    private ColaboradorRepository colaboradorRepository;

    public List<Colaborador> getAllColaboradores()
    {
        List<Colaborador> list = new ArrayList<Colaborador>();
        colaboradorRepository.findAll().forEach(e -> list.add(e));
        return list;
    }

    public List<Colaborador> getAllColaboradoresActivos()
    {
        Colaborador miC=new Colaborador();
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

    public void createColaborador(Colaborador colaborador)
    {
        colaborador.setEstado("Activo");
        colaboradorRepository.save(colaborador);
    }

    public void deleteColaborador(Colaborador colaborador)
    {
        colaborador.toString();
        colaborador.setEstado("Inactivo");
        colaboradorRepository.save(colaborador);
    }

    public Optional<Colaborador> findColaborador(String id)
    {
        return colaboradorRepository.findById(id);
    }



    public void updateColaborador(Colaborador colaborador)
    {
        colaborador.setEstado("Activo");
        colaboradorRepository.save(colaborador);
    }


    public Colaborador findColaboradorEncargado(Departamento idD, Puesto idP)
    {
        return colaboradorRepository.findByDepartamentoAndPuestoAndEstado(idD,idP,"Activo");
    }

    public List<Colaborador> findColaboradorDepartamento(Departamento idD){
        List<Colaborador> list = new ArrayList<Colaborador>();
        List<Colaborador> listA = new ArrayList<Colaborador>();
        colaboradorRepository.findByDepartamento(idD).forEach(e -> list.add(e));
        for(Colaborador c: list){
            if(c.getEstado().equals("Activo")){
                listA.add(c);
            }
        }
        return listA;
    }
}
