package s.c.m.repositories;

import org.springframework.data.repository.CrudRepository;
import s.c.m.entities.MarcaDescansos;

import java.util.List;

public interface MarcaDescansosRepository extends CrudRepository<MarcaDescansos,String> {
    List<MarcaDescansos> findByMarcaLaboradas(int idMarcaLaborada);
    MarcaDescansos findByDescansosAndMarcaLaboradas(int idDes,int idMarca);
}
