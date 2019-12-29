package s.c.m.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import s.c.m.entities.Colaborador;
import s.c.m.entities.Tipodedescansos;

import java.util.List;

@Repository
public interface DescansosRepository extends CrudRepository<Tipodedescansos,String> {
    List<Tipodedescansos> findByColaborador(Colaborador id);
}
