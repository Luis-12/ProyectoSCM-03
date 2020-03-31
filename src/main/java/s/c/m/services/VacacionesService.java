package s.c.m.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import s.c.m.entities.AsignacionDescansos;
import s.c.m.entities.Colaborador;
import s.c.m.entities.Departamento;
import s.c.m.entities.Vacaciones;
import s.c.m.repositories.VacacionesRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class VacacionesService {
    //Es esta clase se implementan las funciones desclaradas en los repositorios para acceder
    // y hacer transacciones en la tabla Vacaciones

    @Autowired
    private VacacionesRepository vacacionesRepository;

    public List<Vacaciones> getAllSolVacaciones(Colaborador a)//Funcion para listar todas las vacaciones
    {
        List<Vacaciones> list = new ArrayList<Vacaciones>();
        List<Vacaciones> listA = new ArrayList<Vacaciones>();
        vacacionesRepository.findAll().forEach(e -> list.add(e));//Aca se invoca funcion del repository de vacaciones
        if (a.getPuesto().getDescripcion().equals("Gerencia") && a.getDepartamento().getNombre().equals("Recursos Humanos")) {
            for (Vacaciones c : list) {
                if (c.getEstado().equals("Pendiente")) {
                    listA.add(c);
                }
            }

        } else {
            for (Vacaciones c : list) {

                if (c.getEstado().equals("Pendiente")&&c.getColaborador().getDepartamento().getNombre().equals(a.getDepartamento().getNombre())) {
                    listA.add(c);
                }
            }
        }
        return listA;
    }


    public Vacaciones diasDisponibles(Colaborador idColaborador){//Funcion para consultar las vaciones que tiene disponible el colaborador
        Vacaciones diasDisponibles=null;
        try
        {
            diasDisponibles=vacacionesRepository.findByColaborador(idColaborador);//Aca se invoca la funcion del repository para consultar las vacaciones
        }catch (Exception ex){}

        return diasDisponibles;
    }

    public void createVacaciones(Vacaciones vacaciones) {//Funcion para agregar vacaciones a la base
        vacacionesRepository.save(vacaciones);//Se invoca funcion del repository para agregar las vaciones
    }

    public void updateVacaciones(Vacaciones vacaciones) {//Funcion para actualizar las vacaciones en la base

        vacacionesRepository.save(vacaciones);//Se actualiza
    }

    public List<Vacaciones> buscarPorEstado(String estado, Colaborador a) {//Funcion para actualizar las vacaciones en la base

        List<Vacaciones> list = new ArrayList<Vacaciones>();
        List<Vacaciones> listA = new ArrayList<Vacaciones>();
        vacacionesRepository.findByEstado(estado).forEach(e -> list.add(e));//Aca se invoca funcion del repository de vacaciones
        if (a.getPuesto().getDescripcion().equals("Gerencia") && a.getDepartamento().getNombre().equals("Recursos Humanos")) {
            listA.addAll(list);
        } else {
            for (Vacaciones c : list) {

                if (c.getColaborador().getDepartamento().getNombre().equals(a.getDepartamento().getNombre())) {
                    listA.add(c);
                }
            }
        }
        return listA;

    }

}
