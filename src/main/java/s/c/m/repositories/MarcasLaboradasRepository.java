package s.c.m.repositories;

import org.springframework.data.repository.CrudRepository;
import s.c.m.entities.Colaborador;
import s.c.m.entities.MarcaLaboradas;

import java.util.Date;

public interface MarcasLaboradasRepository extends CrudRepository<MarcaLaboradas,String> {
    /*Interfaz para acceder a las funciones que ofrece spring JPA data para transaciones y consultas a la base para
    la tabla Marcas laboradas.*/
    MarcaLaboradas findByFechaMarca(Date fecha);//Se declara funcion para consultar marca laborada por fecha
   MarcaLaboradas findByColaboradorAndAndFechaMarca(Colaborador idColaborador, Date fecha);//Se declara funcion para consultar marca
    MarcaLaboradas findTop1ByColaboradorOrderByFechaMarcaDesc(Colaborador idColaborador);//Se declara funcion para consultar la fecha en la que no se marco salidalaborada por id de colaborador y por fecha de marca
    MarcaLaboradas findByColaboradorAndEstado(Colaborador idColaborador , String estado);//Se declara funcion para consultar marcas laboradas por estado y por el id del colaborador
}
