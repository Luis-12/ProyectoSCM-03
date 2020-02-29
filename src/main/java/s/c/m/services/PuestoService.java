package s.c.m.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import s.c.m.entities.Puesto;
import s.c.m.repositories.PuestoRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class PuestoService {
    //Es esta clase se implementan las funciones desclaradas en los repositorios para acceder
    // y hacer transacciones en la tabla Puesto
    @Autowired
    private PuestoRepository puestoRepository;

    public List<Puesto> getAllPuestos()//Funcion para consultar todos los puestos de la base de datos
    {
        List<Puesto> list = new ArrayList<Puesto>();
        puestoRepository.findAll().forEach(e -> list.add(e));//Aca se enlistan los puestos despues de invocar la funcion del repository
        return list;
    }


    public Puesto findIdPuesto(String descripcion)//Funcion para consultar el puesto por la descripcion del mismo
    {
        return puestoRepository.findByDescripcion(descripcion);//Se invoca la funcion del repository para realizar la consulta
    }
}