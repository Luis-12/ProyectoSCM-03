package s.c.m.repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import s.c.m.entities.Horarios;

@Repository
public interface HorarioRepository extends CrudRepository<Horarios,Integer> {
}
