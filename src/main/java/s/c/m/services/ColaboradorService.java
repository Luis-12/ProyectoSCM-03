package s.c.m.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
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
    private  Colaborador colaborador;

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

            for (Colaborador c : list) {
                if (c.getEstado().equals("Activo")) {
                    listA.add(c);
                }
            }
            return listA;

    }

    public List<Colaborador> getAllColaboradoresActivos(Colaborador a)
    {
        Colaborador miC=new Colaborador();
        List<Colaborador> list = new ArrayList<Colaborador>();
        List<Colaborador> listA = new ArrayList<Colaborador>();
        colaboradorRepository.findAll().forEach(e -> list.add(e));

        if(a.getPuesto().getDescripcion().equals("Gerencia")&&a.getDepartamento().getNombre().equals("Recursos Humanos")) {
            for (Colaborador c : list) {
                if (c.getEstado().equals("Activo")) {
                    listA.add(c);
                }
            }

        }else {
            for (Colaborador c : list) {

                if (c.getEstado().equals("Activo")&&c.getDepartamento().getNombre().equals(a.getDepartamento().getNombre())) {
                    System.out.println(c.getDepartamento());
                    System.out.println(a.getDepartamento());
                    listA.add(c);
                }

            }

        }
        return listA;

    }


    public Date calculaVencimiento() throws ParseException {

        Date fechaDeVencimiento =null;
        Calendar fechaDeVencimiento2 = Calendar.getInstance();
        Calendar horaActual = Calendar.getInstance();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

        int mes;
        int dia;
        int year;

        String dia2 = null;
        String mes2 = null;

        mes= horaActual.get(Calendar.MONTH) + 1;
        dia= horaActual.get(Calendar.DAY_OF_MONTH);
        year = horaActual.get(Calendar.YEAR);

        if(dia==28 || dia==30||dia == 31){
            dia2="0"+1;
        }
        if(mes==10){
            year = year +1;
            mes=1;
        }
        if(mes==11){
            year = year +1;
            mes=2;
        }
        if(mes==12){
            year = year +1;
            mes=3;
        }
        else {
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
            dia2 = ""+dia;
        }
        String fVencimiento = year +"-"+mes2+"-"+dia2;
        fechaDeVencimiento = formato.parse(fVencimiento);
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
        colaborador.setJustificacion("N/A");
        colaborador.setFechaVencimiento(fechaA);
        colaborador.setClave(colaborador.getPk_idColaborador());//Aca pongo de clave el mismo id del colaborador agregado
        colaboradorRepository.save(colaborador);
    }


    public void deleteColaborador(Colaborador colaborador)
    {
        colaborador.toString();
        colaborador.setEstado("Inactivo");
        colaboradorRepository.save(colaborador);
    }

    public Colaborador findColaborador(String id) throws Exception
    {
        try
        {
            colaborador=colaboradorRepository.findById(id).get();
        }catch (Exception ex)
        {
            colaborador=null;
        }
        return colaborador;
    }



    public void updateColaborador(Colaborador colaborador)
    {
        colaboradorRepository.save(colaborador);
    }


    public Colaborador findColaboradorEncargado(Departamento idD, Puesto idP)
    {
        if(colaboradorRepository.findByDepartamentoAndPuestoAndEstado(idD,idP,"Activo")==null){
            System.out.println("No se encontro el encargado con rol " + idP.getDescripcion());
        }else{
            System.out.println("Si encontro el encargado con rol " + idP.getDescripcion());
        }
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
