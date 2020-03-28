package s.c.m.repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import s.c.m.entities.Colaborador;
import s.c.m.entities.Puesto;
import s.c.m.entities.Vacaciones;

import java.util.List;

@Repository
public interface VacacionesRepository extends CrudRepository<Vacaciones, Integer> {
    /*Interfaz para acceder a las funciones que ofrece spring JPA data para transaciones y consultas a la base para
    la tabla Vacaciones.*/
    Vacaciones findByColaborador(Colaborador idColaborador);//Se declara la funcion para consultar la vacaciones por colaborador.
   List<Vacaciones> findByEstado(String estado);//Se declara la funcion para consultar la vacaciones por colaborador.

}
