package s.c.m.repositories;

import org.springframework.data.repository.CrudRepository;
import s.c.m.entities.Colaborador;
import s.c.m.entities.MarcaLaboradas;

import java.util.Date;

public interface MarcasLaboradasRepository extends CrudRepository<MarcaLaboradas,String> {
    MarcaLaboradas findByFechaMarca(Date fecha);
    MarcaLaboradas findByColaboradorAndEstado(Colaborador idColaborador , String estado);
}
