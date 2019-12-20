package s.c.m.beans;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import s.c.m.entities.*;
import s.c.m.services.AsignacionesServices;
import s.c.m.services.DescansoServices;
import s.c.m.services.HorarioService;
import s.c.m.services.JornadaService;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Component
@ManagedBean
public class AsignacionesBean {
    @Autowired
    AsignacionesServices asignacionesService;

    @Autowired
    JornadaService jornadaService;

    @Autowired
    HorarioService horarioService;

    @Autowired
    DescansoServices descansoServices;


    private Colaborador colaborador = new Colaborador();
    private Horarios horario = new Horarios();
    private Jornadas jornada = new Jornadas();
    private String descanso;
    private String tiempoDescanso;
    private List<Asignaciones> asignaciones;

    public String getTiempoDescanso() {
        return tiempoDescanso;
    }

    public void setTiempoDescanso(String tiempoDescanso) {
        this.tiempoDescanso = tiempoDescanso;
    }

    private List<Jornadas>jornadas;
    private List<Horarios>horarios;
    private List<Horarios>horariosTemp;

    private Asignaciones asignacion = new Asignaciones();
    private Asignaciones selectAsignacion = new Asignaciones();
    private Tipodedescansos descansos=new Tipodedescansos();



    @PostConstruct
    public void init()
    {
        jornadas = jornadaService.getAllJornadas();
        horarios = horarioService.getAllHorarios();
    }

    public void handleJornadaChange(){
        if(this.jornada!=null && this.jornada.getPk_idjornada()!=null){
            this.horariosTemp = new ArrayList<>();

            this.horariosTemp = horarioService.findHorariosPorJornada(jornada);
        }
    }

    public AsignacionesServices getAsignacionesService() {
        return asignacionesService;
    }

    public void setAsignacionesService(AsignacionesServices asignacionesService) {
        this.asignacionesService = asignacionesService;
    }

    public List<Asignaciones> getAsignaciones() {
        return asignaciones;
    }

    public void setAsignaciones(List<Asignaciones> asignaciones) {
        this.asignaciones = asignaciones;
    }

    public Asignaciones getAsignacion() {
        return asignacion;
    }

    public void setAsignacion(Asignaciones asignacion) {
        this.asignacion = asignacion;
    }
    public Tipodedescansos getDescansos() {
        return descansos;
    }

    public void setDescansos(Tipodedescansos descansos) {
        this.descansos = descansos;
    }

    public Horarios getHorario() {
        return horario;
    }

    public void setHorario(Horarios horario) {
        this.horario = horario;
    }

    public String getDescanso() {
        return descanso;
    }

    public void setDescanso(String descanso) {
        this.descanso = descanso;
    }


    public Colaborador getColaborador() {
        return colaborador;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    public Jornadas getJornada() {
        return jornada;
    }

    public void setJornada(Jornadas jornada) {
        this.jornada = jornada;
    }

    public JornadaService getJornadaService() {
        return jornadaService;
    }

    public void setJornadaService(JornadaService jornadaService) {
        this.jornadaService = jornadaService;
    }

    public List<Jornadas> getJornadas() {
        return jornadas;
    }

    public void setJornadas(List<Jornadas> jornadas) {
        this.jornadas = jornadas;
    }

    public HorarioService getHorarioService() {
        return horarioService;
    }

    public void setHorarioService(HorarioService horarioService) {
        this.horarioService = horarioService;
    }

    public List<Horarios> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<Horarios> horarios) {
        this.horarios = horarios;
    }

    public List<Horarios> getHorariosTemp() {
        return horariosTemp;
    }

    public void setHorariosTemp(List<Horarios> horariosTemp) {
        this.horariosTemp = horariosTemp;
    }

    public Asignaciones getSelectAsignacion() {
        return selectAsignacion;
    }

    public DescansoServices getDescansoServices() {
        return descansoServices;
    }

    public void setDescansoServices(DescansoServices descansoServices) {
        this.descansoServices = descansoServices;
    }

    public void setSelectAsignacion(Asignaciones selectAsignacion) {
        this.selectAsignacion = selectAsignacion;
    }

    public void checkSelection()
    {
        PrimeFaces current = PrimeFaces.current();

         if(selectAsignacion.getColaborador() == null) {
            FacesMessage msg = new FacesMessage("Aviso", "Debe seleccionar un colaborador");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
             if (asignacionesService.buscarHorario(selectAsignacion.getColaborador())==null) {
                 current.executeScript("PF('datos').show();"); //si no esta vacío muestra el dialogo
             }
             else {
                 FacesMessage msg = new FacesMessage("Aviso", "¡Ya tiene un horario asignado!");
                 FacesContext.getCurrentInstance().addMessage(null, msg);
                 //selectAsignacion = new Asignaciones();
             }
        }
    }


    public void checkSelectionC()
    {
        PrimeFaces current = PrimeFaces.current();

        if(selectAsignacion.getColaborador() == null) {
            FacesMessage msg = new FacesMessage("Aviso", "Debe seleccionar un colaborador");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }else if(asignacionesService.buscarHorario(selectAsignacion.getColaborador())==null){
            FacesMessage msg = new FacesMessage("Aviso", "El colaborador no tiene horario asignado");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else{
            buscaHorarioAsignado();
            current.executeScript("PF('dH').show();"); //si no esta vacío muestra el dialogo
        }
    }

    public void buscaHorarioAsignado(){
        Horarios horarioAux = new Horarios();
        String diaDescanso= null;

        if(asignacionesService.buscarHorario(selectAsignacion.getColaborador())!=null){
            selectAsignacion = asignacionesService.buscarHorario(selectAsignacion.getColaborador());
            if(selectAsignacion.getDiaDescanso().equals("LU")){
                diaDescanso="Lunes";
            }else if(selectAsignacion.getDiaDescanso().equals("MA")){
                diaDescanso="Martes";
            }else if(selectAsignacion.getDiaDescanso().equals("MI")){
                diaDescanso="Miercoles";
            }else if(selectAsignacion.getDiaDescanso().equals("JU")){
                diaDescanso="Jueves";
            }else if(selectAsignacion.getDiaDescanso().equals("VI")){
                diaDescanso="Viernes";
            }else if(selectAsignacion.getDiaDescanso().equals("SA")){
                diaDescanso="Sabado";
            }else if(selectAsignacion.getDiaDescanso().equals("DO")){
                diaDescanso="Domingo";
            }
            selectAsignacion.setDiadescanso(diaDescanso);
        }else{
            System.out.println("No se encontro el horario");
        }
    }

    public void checkSelectionScheduleChange()
    {
        PrimeFaces current = PrimeFaces.current();

        if(selectAsignacion.getColaborador() == null) {
            FacesMessage msg = new FacesMessage("Aviso", "Debe seleccionar un colaborador");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            if (asignacionesService.buscarHorario(selectAsignacion.getColaborador())==null) {//Quiere decir que no tiene horario asignado
                FacesMessage msg = new FacesMessage("Aviso", "El colaborador no tiene horario asignado");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
            else {
                System.out.println("El colaborador ya tiene horario asignado y se desea cambiar");
                current.executeScript("PF('datos2').show();"); //si no esta vacío muestra el dialogo
                //selectAsignacion = new Asignaciones();
            }
        }
    }

    public void changeSchedule(){
        FacesMessage mensaje = null;
        PrimeFaces current = PrimeFaces.current();

        Asignaciones asignaChage = asignacionesService.buscarHorario(selectAsignacion.getColaborador());
        System.out.println("Jornada: "+ jornada.getDescripcion());
            try{
                asignacionesService.updateAsignacion(selectAsignacion);
                current.executeScript("PF('datos2').hide();");

                FacesMessage msg = new FacesMessage("Aviso", "Horario cambiado correctamente.");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } catch (Exception e) {
                System.out.println("No se puedo cambiar el horario");
            } finally {
                selectAsignacion = new Asignaciones();
                asignacion = new Asignaciones();
                jornada = new Jornadas();
            }
    }

    public void create()
    {
        FacesMessage mensaje = null;
        PrimeFaces current = PrimeFaces.current();

        System.out.println("Nombre: "+selectAsignacion.getColaborador().getNombre());
        System.out.println("Descanso: "+asignacion.getDiaDescanso());
        System.out.println("Horario: "+ asignacion.getHorario().getPk_idhorario());
            try {
                asignacion.setColaborador(selectAsignacion.getColaborador());
                asignacionesService.createAsignacion(asignacion);
                current.executeScript("PF('datos').hide();");
                System.out.println("Horario asignado");

                FacesMessage msg = new FacesMessage("Aviso", "Asignación realizada correctamente.");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } catch (Exception e) {
            } finally {
                selectAsignacion = new Asignaciones();
                asignacion = new Asignaciones();
                jornada = new Jornadas();
            }
    }
    public void createDescanso()
    {
        FacesMessage mensaje = null;
        PrimeFaces current = PrimeFaces.current();
        try {
                descansos.setDuracion(Time.valueOf(tiempoDescanso));
            descansos.setColaborador(selectAsignacion.getColaborador());
            descansoServices.createDescansos(descansos);
            current.executeScript("PF('datos3').hide();");
            FacesMessage msg = new FacesMessage("Aviso", "Asignación realizada correctamente.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (Exception e) {
            System.out.println(e.getCause());
        } finally {
            selectAsignacion = new Asignaciones();
            descansos = new Tipodedescansos();
        }
    }

    public void colaboradorSelected()
    {
        PrimeFaces current = PrimeFaces.current();

        if(selectAsignacion.getColaborador()== null) {
            FacesMessage msg = new FacesMessage("Aviso", "Debe seleccionar un colaborador");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            current.executeScript("PF('datos3').show();"); //si no esta vacío muestra el dialogo
        }
    }
}
