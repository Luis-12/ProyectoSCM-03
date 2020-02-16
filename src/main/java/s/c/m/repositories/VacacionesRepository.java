package s.c.m.repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import s.c.m.entities.Colaborador;
import s.c.m.entities.Puesto;
import s.c.m.entities.Vacaciones;

@Repository
public interface VacacionesRepository extends CrudRepository<Vacaciones, Integer> {
    Vacaciones findByColaborador(Colaborador idColaborador);

}
