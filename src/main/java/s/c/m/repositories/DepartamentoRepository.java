package s.c.m.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import s.c.m.entities.Departamento;

import java.util.List;

@Repository
public interface DepartamentoRepository extends CrudRepository<Departamento, String> {
    @Query("select pk_coddepartamento,nombre,descripcion,fk_idadmin,estado from departamentos where estado = 'Activo'")
    List<Departamento> getAllDepartamentosByEstado();
}
