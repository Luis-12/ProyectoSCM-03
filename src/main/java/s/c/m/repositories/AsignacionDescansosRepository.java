package s.c.m.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import s.c.m.entities.AsignacionDescansos;
import s.c.m.entities.Colaborador;

import java.util.List;

@Repository
public interface AsignacionDescansosRepository extends CrudRepository<AsignacionDescansos,Integer> {
    /*Interfaz para acceder a las funciones que ofrece spring JPA data para transaciones y consultas a la base para
    la tabla AsignacionDescansos.*/

    List<AsignacionDescansos> findByColaborador(Colaborador idColaborador);//Se declara la funcion para encontrar asignaciones por colaborador
    @Transactional//Notacion que indica que se realizaran transacciones con la siguiente funcion
    void deleteByColaborador(Colaborador idColaborador);//Se declara la funcion para eliminar las asignaciones de descansos que tenga el colaborador
}
