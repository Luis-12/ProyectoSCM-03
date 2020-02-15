package s.c.m.repositories;

import org.springframework.data.repository.CrudRepository;
import s.c.m.entities.Descansos;
import s.c.m.entities.MarcaDescansos;
import s.c.m.entities.MarcaLaboradas;

import java.util.List;

public interface MarcaDescansosRepository extends CrudRepository<MarcaDescansos,String> {
        List<MarcaDescansos> findByMarcaLaboradas(MarcaLaboradas marcaLaboradas);
    MarcaDescansos findByDescansosAndMarcaLaboradas(Descansos descansos,MarcaLaboradas idMarca);
}
