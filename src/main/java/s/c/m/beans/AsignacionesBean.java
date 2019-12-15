package s.c.m.beans;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import s.c.m.entities.*;
import s.c.m.services.AsignacionesServices;

import javax.annotation.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.List;

@Component
@ManagedBean
public class AsignacionesBean {
    @Autowired
    AsignacionesServices asignacionesService;


    private Colaborador colaborador = new Colaborador();
    private Horarios horario = new Horarios();
    private String descanso;

    private List<Asignaciones> asignaciones;

    Asignaciones asignacion = new Asignaciones();



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

    public void create()
    {
        FacesMessage mensaje = null;
        PrimeFaces current = PrimeFaces.current();

            try {
                asignacionesService.createAsignacion(asignacion);
                current.executeScript("PF('dlAC').hide();");
            } catch (Exception e) {
            } finally {
                asignacion = new Asignaciones();
            }
        FacesMessage msg = new FacesMessage("Aviso", "Asignacion realizada correctamente");
        FacesContext.getCurrentInstance().addMessage(null, msg);


    }
}
