package s.c.m.repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import s.c.m.entities.Horarios;
import s.c.m.entities.Jornadas;
import s.c.m.entities.Puesto;

import java.util.List;

@Repository
public interface HorarioRepository extends CrudRepository<Horarios,String> {
    /*Interfaz para acceder a las funciones que ofrece spring JPA data para transaciones y consultas a la base para
    la tabla Horarios.*/
    List<Horarios> findByJornada(Jornadas idJ);//Se declara la funcion para consultar los horarios por jornada.
}
