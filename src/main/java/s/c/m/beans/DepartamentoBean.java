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
import java.util.Optional;


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

    public void delete(){
        String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("DepartamentoId");
        Optional<Departamento> departamentoOptional=departamentoService.findDepartamentoById(id);
        if(departamentoOptional.isPresent())
            departamento=departamentoOptional.get();
        departamentoService.deleteDepartamento(departamento);
        departamentos=departamentoService.getAllDepartamentosActivos();
        departamento = new Departamento();
    }


    public String buscar(){
        String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("DepartamentoId");
        Optional<Departamento> departamentoOptional=departamentoService.findDepartamentoById(id);
        if(departamentoOptional.isPresent()) {
            departamento = departamentoOptional.get();

            //PrimeFaces.current().executeScript("PF('dlA').show();");
        }
        departamentos=departamentoService.getAllDepartamentosActivos();
        return "MantenimientoDepartamento";

    }

    public void delete1(){
        //String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("ColaboradorId");
        //System.out.println("El id del colaborador que se desea borrar es "+id);
        //Colaborador miC = colaboradorService.findColaborador(id);
        //miC.toString();
        departamentoService.deleteDepartamento(departamento);
        addMessage("Aviso", "Registro eliminado correctamente.");
        departamentos = departamentoService.getAllDepartamentosActivos();
    }

    public void create(){
        try{
            departamentoService.createDepartamento(departamento);
            addMessage("Aviso", "Departamento creado correctamente!");
            departamentos=departamentoService.getAllDepartamentosActivos();
        }catch (Exception e){
        }finally {
            departamento = new Departamento();
        }
    }


    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}














