package s.c.m.beans;

import org.hibernate.sql.Select;
import org.primefaces.PrimeFaces;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
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
    private Departamento selectDepartamento = new Departamento();
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

    public Departamento getSelectDepartamento() {
        return selectDepartamento;
    }

    public void setSelectDepartamento(Departamento selectDepartamento) {
        this.selectDepartamento = selectDepartamento;
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
    public void checkSelection(){
        PrimeFaces current = PrimeFaces.current();

        if(selectDepartamento==null){
            addMessage("Aviso", "Debe seleccionar un Departamento"); //si esta vacío muestra este mensaje
        }else {
            current.executeScript("PF('dlUC').show();"); //si no esta vacío muestra el dialogo
        }
    }


    public  void showconfirm()
    {
        PrimeFaces current = PrimeFaces.current();

        if (selectDepartamento==null) {
            addMessage("Aviso", "Debe Seleccionar un Departamento."); //si esta vacio muetra este mensaje
        } else {
            current.executeScript("PF('dlCFD').show();"); //si no esta vacio muestra el dialogo
        }

    }




    public void delete(){
        departamentoService.deleteDepartamento(selectDepartamento);
        addMessage("Aviso", "Registro eliminado correctamente.");
        departamentos = departamentoService.getAllDepartamentosActivos();
        System.out.println("Eliminado");
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
    public void update(){
        try{
            System.out.println("El departamento actualizado es" +selectDepartamento.getNombre());
            departamentoService.updateDepartamento(selectDepartamento);
            addMessage("Aviso", "Registro modificado correctamente");
            departamentos = departamentoService.getAllDepartamentosActivos();
        }catch (Exception e){}finally {
            departamento = new Departamento();

        }
    }


    public void find(){
        String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("DepartamentoIdBusqueda");
        departamentos.clear();
        departamentos.add(departamentoService.findDepartamento(id));
    }

    public  void onRowSelectedDepartamento(SelectEvent event){
        FacesMessage msg = new FacesMessage("Departamento Seleccionado", ((Departamento) event.getObject()).getNombre());
        FacesContext.getCurrentInstance().addMessage("asdasdasdasd",msg);
    }

    public void onRowUnselectColaborador(SelectEvent event){
        FacesMessage msg = new FacesMessage("Departamento Seleccionado",((Departamento) event.getObject()).getNombre());
        FacesContext.getCurrentInstance().addMessage("dasdasdasdas",msg);
    }

    public void onRowEdit(RowEditEvent event){
        departamentoService.updateDepartamento(((Departamento) event.getObject()));
        System.out.println(((Departamento) event.getObject()).getNombre());
        FacesMessage msg = new FacesMessage("Departamento Editado", ((Departamento) event.getObject()).getPk_codDepartamento());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    public void  onRowCancel(RowEditEvent event){
        FacesMessage msg = new FacesMessage("Actualización Cancelada", ((Departamento) event.getObject()).getPk_codDepartamento());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    public  void  onRowDelete(RowEditEvent event) {
        departamentoService.deleteDepartamento((Departamento) event.getObject());
        FacesMessage msg = new FacesMessage("Departamento eliminado", ((Departamento) event.getObject()).getPk_codDepartamento());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        departamentos = departamentoService.getAllDepartamentosActivos();
    }

    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}














