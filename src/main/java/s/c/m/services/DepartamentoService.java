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
    //Es esta clase se implementan las funciones desclaradas en los repositorios para acceder
    // y hacer transacciones en la tabla Departamento


    @Autowired
    private DepartamentoRepository departamentoRepository;
    private Departamento departamento;
    public List<Departamento> getAllDepartamentos()//Funcion para listar todos los departamentos
    {
        List<Departamento> list = new ArrayList<Departamento>();
        departamentoRepository.findAll().forEach(e -> list.add(e));//Aca se invoca funcion del repository de departamentos
        return list;
    }

    public List<Departamento> getAllDepartamentosActivos()//Funcion para listar los departamentos por estado activo
    {
        List<Departamento> list = new ArrayList<Departamento>();
        List<Departamento> listA = new ArrayList<Departamento>();
        departamentoRepository.findAll().forEach(e -> list.add(e));//Se invoca la funcion para consultar departamentos
        for(Departamento d: list){
            if(d.getEstado().equals("Activo")){//Si el estado esta activo
                listA.add(d);//Se lista el departamento
            }
        }
        return listA;
    }

    public void createDepartamento(Departamento departamento)//Funcion para agregar departamento a la base de datos
    {
        departamento.setEstado("Activo");//Se asigna el estado Activo al departamento
        departamentoRepository.save(departamento);//Se agregar el departamento a la base
    }

    public void deleteDepartamento(Departamento departamento)//Funcion para desactivar el departamento
    {
        departamento.toString();
        departamento.setEstado("Inactivo");//Se actualiza el estado a inactivo
        departamentoRepository.save(departamento);//Se actualizar el estado en la base
    }

    public Departamento findDepartamento(String id)//Funcion para consultar departamento por id
    {

        return departamentoRepository.findById(id).get();
    }

    public void updateDepartamento(Departamento departamento)//Funcion para actualizar datos de departamento en la base
    {
        departamento.setEstado("Activo");//Se le asigna es estado activo
        departamentoRepository.save(departamento);//Se ingresan los nuevos datos a la base
    }


}
