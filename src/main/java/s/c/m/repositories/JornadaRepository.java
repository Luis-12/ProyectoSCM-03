package s.c.m.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import s.c.m.entities.Jornadas;

@Repository
public interface JornadaRepository extends CrudRepository<Jornadas,String> {

}
