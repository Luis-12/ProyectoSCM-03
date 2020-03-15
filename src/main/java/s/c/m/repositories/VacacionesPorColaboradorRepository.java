package s.c.m.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import s.c.m.entities.Colaborador;
import s.c.m.entities.VacacionesPorColaborador;

@Repository
public interface VacacionesPorColaboradorRepository extends CrudRepository<VacacionesPorColaborador, Integer> {
    VacacionesPorColaborador findByColaborador(Colaborador idColaborador);
}
