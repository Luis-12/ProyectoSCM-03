package s.c.m.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import s.c.m.entities.Descansos;
import s.c.m.entities.Horarios;

import java.util.List;

@Repository
public interface DescansosRepository extends CrudRepository<Descansos,String> {
    List<Descansos> findByHorario(Horarios idHorario);
}
