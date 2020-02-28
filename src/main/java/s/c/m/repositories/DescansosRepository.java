package s.c.m.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import s.c.m.entities.Descansos;
import s.c.m.entities.Horarios;

import java.util.List;

@Repository
public interface DescansosRepository extends CrudRepository<Descansos,String> {
    /*Interfaz para acceder a las funciones que ofrece spring JPA data para transaciones y consultas a la base para
    la tabla Descansos.*/
    List<Descansos> findByHorario(Horarios idHorario);//Se declara funcion para consultar los descansos por el horario al que pertenecen
}
