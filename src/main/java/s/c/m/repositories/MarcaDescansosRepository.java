package s.c.m.repositories;

import org.springframework.data.repository.CrudRepository;
import s.c.m.entities.Descansos;
import s.c.m.entities.MarcaDescansos;
import s.c.m.entities.MarcaLaboradas;

import java.util.List;

public interface MarcaDescansosRepository extends CrudRepository<MarcaDescansos,String> {
    /*Interfaz para acceder a las funciones que ofrece spring JPA data para transaciones y consultas a la base para
    la tabla Marca Descansos.*/
        List<MarcaDescansos> findByMarcaLaboradas(MarcaLaboradas marcaLaboradas);//Se declara la funcion para consultar las marcas de
                                                                                // descansos por la marca laborada
    MarcaDescansos findByDescansosAndMarcaLaboradas(Descansos descansos,MarcaLaboradas idMarca);//Se declara la funcion para consultar las marcas de descansos por
                                                                                            //El id del descansos y por la marca laborada
}
