package s.c.m.repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import s.c.m.entities.Horarios;
import s.c.m.entities.Jornadas;
import s.c.m.entities.Puesto;

import java.util.List;

@Repository
public interface HorarioRepository extends CrudRepository<Horarios,String> {
    List<Horarios> findByJornada(Jornadas idJ);
}
