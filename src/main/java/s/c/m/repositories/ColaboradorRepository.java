package s.c.m.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import s.c.m.entities.Colaborador;
import s.c.m.entities.Departamento;
import s.c.m.entities.Puesto;

import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.Date;
import java.util.List;

@Repository
public interface ColaboradorRepository extends CrudRepository<Colaborador, String> {
    @Query("select pk_idColaborador,fk_codDepartamento,fk_idPuesto,nombre,clave,telefono,correo,fechainiciolaboral,estado from colaboradores where estado = 'Activo'")
    List<Colaborador> getAllDepartamentosByEstado();



}
