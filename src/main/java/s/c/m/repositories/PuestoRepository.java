package s.c.m.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import s.c.m.entities.Puesto;

@Repository
public interface PuestoRepository extends CrudRepository<Puesto, Integer> {
}
