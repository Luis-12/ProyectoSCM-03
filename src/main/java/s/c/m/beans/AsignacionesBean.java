package s.c.m.beans;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import s.c.m.entities.*;
import s.c.m.services.AsignacionesServices;
import s.c.m.services.HorarioService;
import s.c.m.services.JornadaService;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
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


    private Colaborador colaborador = new Colaborador();
    private Horarios horario = new Horarios();
    private Jornadas jornada = new Jornadas();
    private String descanso;
    private List<Asignaciones> asignaciones;

    private List<Jornadas>jornadas;
    private List<Horarios>horarios;
    private List<Horarios>horariosTemp;

    Asignaciones asignacion = new Asignaciones();



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

    public void checkSelection()
    {
        PrimeFaces current = PrimeFaces.current();

         if(asignacion.getColaborador() == null) {
            FacesMessage msg = new FacesMessage("Aviso", "Debe seleccionar un colaborador");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {

             if (asignacionesService.buscarHorario(asignacion.getColaborador())==null) {

                 current.executeScript("PF('datos').show();"); //si no esta vacío muestra el dialogo
             }

             else {
                 FacesMessage msg = new FacesMessage("Aviso", "Ya tiene un horario asignado");
                 FacesContext.getCurrentInstance().addMessage(null, msg);
                 asignacion = new Asignaciones();

             }

        }
    }

    public void create()
    {
        FacesMessage mensaje = null;
        PrimeFaces current = PrimeFaces.current();

        System.out.println("Nombre: "+asignacion.getColaborador().getNombre());
        System.out.println("Descanso: "+asignacion.getDiaDescanso());
        System.out.println("Horario: "+ asignacion.getHorario().getPk_idhorario());
            try {
                asignacionesService.createAsignacion(asignacion);
                current.executeScript("PF('dlAC').hide();");
            } catch (Exception e) {
            } finally {
                asignacion = new Asignaciones();
            }
        FacesMessage msg = new FacesMessage("Aviso", "Asignacion realizada correctamente");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        asignacion = new Asignaciones();

    }
}
