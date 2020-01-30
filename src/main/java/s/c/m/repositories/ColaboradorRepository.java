package s.c.m.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import s.c.m.entities.Colaborador;
import s.c.m.entities.Departamento;
import s.c.m.entities.Puesto;

import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
public interface ColaboradorRepository extends CrudRepository<Colaborador, String> {
    Colaborador findByDepartamentoAndPuestoAndEstado(Departamento idD,Puesto idP,String estado);
    List<Colaborador> findByDepartamento(Departamento idD);
}


