package s.c.m.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import s.c.m.entities.Colaborador;
import s.c.m.entities.Departamento;
import s.c.m.entities.Puesto;
import s.c.m.services.ColaboradorService;
import s.c.m.services.DepartamentoService;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.List;

@Component
@ManagedBean
public class ColaboradorBean {
    @Autowired
    ColaboradorService colaboradorService;
    private Colaborador colaborador = new Colaborador();
    private Departamento departamento = new Departamento();
    private Puesto puesto = new Puesto();
    private List<Colaborador> colaboradores;

    @PostConstruct
    public String init() {
        colaboradores = colaboradorService.getAllColaboradoresActivos();
        return "colaboradorList.xhtml";
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public Puesto getPuesto() {
        return puesto;
    }

    public void setPuesto(Puesto puesto) {
        this.puesto = puesto;
    }

    public List<Colaborador> getColaboradores() {
        return colaboradores;
    }

    public void setColaboradores(List<Colaborador> colaboradores) {
        this.colaboradores = colaboradores;
    }

    public void create() {
        try{
            colaboradorService.createColaborador(colaborador);
            addMessage("Aviso", "Registro insertado correctamente.");
            colaboradores = colaboradorService.getAllColaboradoresActivos();
        }catch (Exception e){
        } finally {
            colaborador = new Colaborador();
        }
    }

    public void delete(){
        String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("ColaboradorId");
        colaboradorService.deleteColaborador(colaboradorService.findColaborador(id));
        addMessage("Aviso", "Registro eliminado correctamente.");
        colaboradores = colaboradorService.getAllColaboradoresActivos();
    }

    public void update(){
        try{
            colaboradorService.updateColaborador(colaborador);
            addMessage("Aviso", "Registro modificado correctamente.");
            colaboradores = colaboradorService.getAllColaboradoresActivos();
        }catch (Exception e){
        } finally {
            colaborador = new Colaborador();
        }
    }

    public String carga(){//Aca se carga la persona y se redirecciona a la ventana update
        String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("ColaboradorId");
        colaborador=colaboradorService.findColaborador(id);
        return "colaboradorUpdate.xhtml";
    }

    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }


}
