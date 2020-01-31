package s.c.m.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import s.c.m.entities.*;

@Repository
public interface AsignacionesRepository extends CrudRepository<Asignaciones, String> {
    Asignaciones findByColaborador(Colaborador id);
}