package s.c.m.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import s.c.m.entities.Puesto;

@Repository
public interface PuestoRepository extends CrudRepository<Puesto, Integer> {
    /*Interfaz para acceder a las funciones que ofrece spring JPA data para transaciones y consultas a la base para
    la tabla Puesto.*/
    Puesto findByDescripcion(String descripcion);//Se declara funcion para consultar puestos por descripcion.
}
