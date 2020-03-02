package s.c.m.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import s.c.m.entities.*;

@Repository
public interface AsignacionesRepository extends CrudRepository<Asignaciones, String> {
    /*Interfaz para acceder a las funciones que ofrece spring JPA data para transaciones y consultas a la base para
    la tabla Asignaciones de horarios.*/
    Asignaciones findByColaborador(Colaborador id);//Se declara funcion para consultar asignacion por colaborador.
}