package s.c.m.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import s.c.m.entities.Colaborador;
import s.c.m.entities.Departamento;
import s.c.m.entities.Puesto;
import s.c.m.repositories.ColaboradorRepository;

import java.sql.DatabaseMetaData;
import java.text.DateFormat;
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


    public Date calculaVencimiento() throws ParseException {
        int mes;
        int dia;
        int year;
        String dia2 = null;
        String mes2 = null;
        Date horaActual = new Date();
        mes= horaActual.getMonth() + 1;//MES
        dia= horaActual.getDate();//DIA
        year = horaActual.getYear() + 1900;//

        System.out.println("El mes de hoy es " + mes);
        System.out.println("El dia de hoy es " + dia);
        System.out.println("El year de hoy es " + year);
        if(dia==28 || dia==30||dia == 31){
            dia2="0"+1;
        }
        if(mes==10 || mes==11 || mes==12){
            year = year +1;
            mes=1;
        }else {
            mes=mes+3;
        }

        if (mes < 10) {
            mes2 = "0" + mes;
        }else{
            mes2 = ""+mes;
        }

        if (dia < 10) {
            dia2 = "0" + dia;
        }else{
            dia2 = ""+dia2;
        }
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        String fVencimiento = year +"-"+mes2+"-"+dia2;
        System.out.println("La fecha de venciamiento de clave calculada antes de convertirlo: "+ fVencimiento);
        Date fechaDeVencimiento =null;
        fechaDeVencimiento = formato.parse(fVencimiento);
        System.out.println("La fecha de venciamiento de clave calculada es : "+ formato.format(fechaDeVencimiento));
        return fechaDeVencimiento;

    }
    public void actualizaClave(Colaborador c) throws ParseException {
        c.setFechaVencimiento(calculaVencimiento());//Cuando se cambia la clave se vuelve a calcula la nueva fecha de vencimiento
        colaboradorRepository.save(c);
    }

    public void createColaborador(Colaborador colaborador) throws ParseException {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaA = new Date();

        formato.format(fechaA);
        System.out.println("FECHA DE HOY: " + formato.format(fechaA));
        colaborador.setEstado("Activo");
        colaborador.setFechaVencimiento(fechaA);
        //colaborador.setFechaVencimiento(calculaVencimiento());
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
        colaborador.setClave(colaborador.getPk_idColaborador());//Aca pongo de clave el mismo id del colaborador agregado
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
