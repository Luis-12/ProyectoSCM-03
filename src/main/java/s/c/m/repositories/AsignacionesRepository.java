package s.c.m.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import s.c.m.entities.*;

import java.util.List;

@Repository
public interface AsignacionesRepository extends CrudRepository<Asignaciones, String> {
    Asignaciones findByColaborador(Colaborador id);
}