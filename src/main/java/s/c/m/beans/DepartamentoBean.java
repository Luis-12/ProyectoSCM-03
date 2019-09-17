package s.c.m.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import s.c.m.entities.Departamento;
import s.c.m.services.DepartamentoService;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.List;



@Component
@ManagedBean
public class DepartamentoBean {
    @Autowired
    DepartamentoService departamentoService;
    private Departamento departamento = new Departamento();

    private List<Departamento> departamentos;

    @PostConstruct
    public String init() {
        departamentos = departamentoService.getAllDepartamentosActivos();
        return "departamentoList.xhtml";
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public List<Departamento> getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(List<Departamento> departamentos) {
        this.departamentos = departamentos;
    }

    public Departamento obtieneDepartamento(String id)
    {
        if(id == null){
            throw new IllegalArgumentException("no se provee el id");
        }
        for (Departamento d : departamentos){
            if(id.equals(d.getPk_codDepartamento())){
                return d;
            }
        }
        return null;
    }
    public void create(){
        try{
            departamentoService.createDepartamento(departamento);
            addMessage("Aviso", "Departamento creado correctamente!");
            departamentos=departamentoService.getAllDepartamentos();
        }catch (Exception e){
        }finally {
            departamento = new Departamento();
        }
    }
    /* public void create() {
        try{
            personaService.createPersona(persona);
            addMessage("Aviso", "Registro insertado correctamente.");
            personas = personaService.getAllPersonas();
        }catch (Exception e){
        } finally {
            persona = new Persona();
        }
    }*/

    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
