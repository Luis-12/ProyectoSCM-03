package s.c.m.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import s.c.m.entities.Colaborador;
import s.c.m.entities.Tipodedescansos;

@Repository
public interface DescansosRepository extends CrudRepository<Tipodedescansos,String> {
    Tipodedescansos findByColaborador(Colaborador id);
}
