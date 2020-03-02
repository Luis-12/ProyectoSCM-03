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
import java.util.List;

@Repository
public interface ColaboradorRepository extends CrudRepository<Colaborador, String> {
    /*Interfaz para acceder a las funciones que ofrece spring JPA data para transaciones y consultas a la base para
    la tabla Colaborador.*/
    Colaborador findByDepartamentoAndPuestoAndEstado(Departamento idD,Puesto idP,String estado);//Se declara funcion para consultar el colaborador por estado y puesto
    List<Colaborador> findByDepartamento(Departamento idD);//Se declara funcion para consultar colaboradores por departamento
}


