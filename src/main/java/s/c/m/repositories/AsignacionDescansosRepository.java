package s.c.m.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import s.c.m.entities.AsignacionDescansos;
import s.c.m.entities.Colaborador;

import java.util.List;

@Repository
public interface AsignacionDescansosRepository extends CrudRepository<AsignacionDescansos,Integer> {
    List<AsignacionDescansos> findByColaborador(Colaborador idColaborador);
    @Transactional
    void deleteByColaborador(Colaborador idColaborador);
}
