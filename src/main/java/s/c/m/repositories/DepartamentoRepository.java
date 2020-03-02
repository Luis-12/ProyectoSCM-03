package s.c.m.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import s.c.m.entities.Departamento;

import java.util.List;

@Repository
public interface DepartamentoRepository extends CrudRepository<Departamento, String> {
    /*Interfaz para acceder a las funciones que ofrece spring JPA data para transaciones y consultas a la base para
    la tabla Departamento.*/
}
