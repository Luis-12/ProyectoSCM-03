package s.c.m.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import s.c.m.entities.Departamento;
import s.c.m.repositories.DepartamentoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DepartamentoService {

    @Autowired
    private DepartamentoRepository departamentoRepository;
    public List<Departamento> getAllDepartamentos() {
        List<Departamento> list = new ArrayList<Departamento>();
        departamentoRepository.findAll().forEach(e -> list.add(e));
        return list;
    }

    public List<Departamento> getAllDepartamentosActivos() {
        List<Departamento> list = new ArrayList<Departamento>();
        List<Departamento> listA = new ArrayList<Departamento>();
        departamentoRepository.findAll().forEach(e -> list.add(e));
        for(Departamento d: list){
            if(d.getEstado().equals("Activo")){
                listA.add(d);
            }
        }
        return listA;
    }

    public void createDepartamento(Departamento departamento){
        departamento.setEstado("Activo");
        departamentoRepository.save(departamento);
    }

    public void deleteDepartamento(Departamento departamento){
        departamento.toString();
        departamento.setEstado("Inactivo");
        departamentoRepository.save(departamento);
    }

    public Departamento findDepartamento(String id){

        return departamentoRepository.findById(id).get();
    }

    public void updateDepartamento(Departamento departamento){
        departamento.setEstado("Activo");
        departamentoRepository.save(departamento);
    }

}
