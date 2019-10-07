package s.c.m.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import s.c.m.entities.Colaborador;
import s.c.m.entities.Departamento;
import s.c.m.entities.Puesto;
import s.c.m.repositories.ColaboradorRepository;

import java.sql.DatabaseMetaData;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ColaboradorService {

    @Autowired
    private ColaboradorRepository colaboradorRepository;

    public List<Colaborador> getAllColaboradores()
    {
        List<Colaborador> list = new ArrayList<Colaborador>();
        colaboradorRepository.findAll().forEach(e -> list.add(e));
        return list;
    }

    public List<Colaborador> getAllColaboradoresActivos()
    {
        Colaborador miC=new Colaborador();
        List<Colaborador> list = new ArrayList<Colaborador>();
        List<Colaborador> listA = new ArrayList<Colaborador>();
        colaboradorRepository.findAll().forEach(e -> list.add(e));
        for(Colaborador c: list){
            if(c.getEstado().equals("Activo")){
                listA.add(c);
            }
        }
        return listA;
    }

    public void createColaborador(Colaborador colaborador) throws ParseException {
        int mes;
        int dia;
        int year;
        String dia2 = null;
        String mes2 = null;
        Date horaActual = new Date();
        Date fechaDeVencimiento = new Date();
       mes= horaActual.getMonth() + 1;//MES
       dia= horaActual.getDate();//DIA
       year = horaActual.getYear() + 1900;//

        System.out.println("El mes de hoy es " + mes);
       System.out.println("El dia de hoy es " + dia);
       System.out.println("El year de hoy es " + year);
       if(mes==10 || mes==11 || mes==12){
           year = year +1;
           mes=1;
       }

        if (mes < 10) {
            mes2 = "0" + mes;
        }

        if (dia < 10) {
            dia2 = "0" + dia;
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String fVencimiento = year +"-"+ mes2+"-"+dia2;
        System.out.println("La fecha de venciamiento de clave calculada antes de convertirlo: "+ fVencimiento);
        fechaDeVencimiento = formatter.parse(fVencimiento);
        System.out.println("La fecha de venciamiento de clave calculada es : "+ fechaDeVencimiento);

        colaborador.setEstado("Activo");
        colaborador.setClave(colaborador.getPk_idColaborador());//Aca pongo de clave el mismo id del colaborador agregado
        colaboradorRepository.save(colaborador);
    }

    public void deleteColaborador(Colaborador colaborador)
    {
        colaborador.toString();
        colaborador.setEstado("Inactivo");
        colaboradorRepository.save(colaborador);
    }

    public Colaborador findColaborador(String id)
    {
        return colaboradorRepository.findById(id).get();
    }



    public void updateColaborador(Colaborador colaborador)
    {
        colaborador.setEstado("Activo");
        colaboradorRepository.save(colaborador);
    }


    public Colaborador findColaboradorEncargado(Departamento idD, Puesto idP)
    {
        return colaboradorRepository.findByDepartamentoAndPuestoAndEstado(idD,idP,"Activo");
    }

    public List<Colaborador> findColaboradorDepartamento(Departamento idD){
        List<Colaborador> list = new ArrayList<Colaborador>();
        List<Colaborador> listA = new ArrayList<Colaborador>();
        colaboradorRepository.findByDepartamento(idD).forEach(e -> list.add(e));
        for(Colaborador c: list){
            if(c.getEstado().equals("Activo")){
                listA.add(c);
            }
        }
        return listA;
    }
}
