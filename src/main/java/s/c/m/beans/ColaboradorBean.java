package s.c.m.beans;


import org.primefaces.PrimeFaces;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import s.c.m.entities.Colaborador;
import s.c.m.entities.Departamento;
import s.c.m.entities.Puesto;
import s.c.m.services.ColaboradorService;


import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.List;

//@Component
@ManagedBean
public class ColaboradorBean {
    @Autowired
    ColaboradorService colaboradorService;
    private Colaborador colaborador = new Colaborador();
    private Colaborador selectcolaborador=new Colaborador();
    private Departamento departamento = new Departamento();
    private Puesto puesto = new Puesto();
    private List<Colaborador> colaboradores;
    private  boolean disable;


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

    public Colaborador getSelectcolaborador() {
        return selectcolaborador;
    }

    public void setSelectcolaborador(Colaborador selectcolaborador) {
        this.selectcolaborador = selectcolaborador;
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
        /*String id = colaborador.getPk_idColaborador();
        boolean existeColaborador = false;
        if(colaboradorService.findColaborador(id).getPk_idColaborador().equals(colaborador.getPk_idColaborador())){
            existeColaborador = true;
            addMessage("Aviso", "Ya existe un colaborador con esa identificación.");
        }else {
            existeColaborador = false;
            try{
                colaboradorService.createColaborador(colaborador);
                addMessage("Aviso", "Registro insertado correctamente.");
                colaboradores = colaboradorService.getAllColaboradoresActivos();
            }catch (Exception e){
            } finally {
                colaborador = new Colaborador();
            }
        }*/
        try{
            colaboradorService.createColaborador(colaborador);
            addMessage("Aviso", "Registro insertado correctamente.");
            colaboradores = colaboradorService.getAllColaboradoresActivos();
        }catch (Exception e){
        } finally {
            colaborador = new Colaborador();
        }
    }

    public void checkSelection() { //para verifiacar si el objeto selectcoalborador esta vacio
        PrimeFaces current = PrimeFaces.current();

        if (selectcolaborador==null) {
            addMessage("Aviso", "Debe Seleccionar un Colaborador."); //si esta vacio muetra este mensaje
        } else {
            current.executeScript("PF('dlUC').show();"); //si no esta vacio muestra el dialogo
        }
    }

    public  void showconfirm()
    {
        PrimeFaces current = PrimeFaces.current();

        if (selectcolaborador==null) {
            addMessage("Aviso", "Debe Seleccionar un Colaborador."); //si esta vacio muetra este mensaje
        } else {
            current.executeScript("PF('dlCFD').show();"); //si no esta vacio muestra el dialogo
        }

    }

    public void delete(){
        colaboradorService.deleteColaborador(selectcolaborador);
        addMessage("Aviso", "Registro eliminado correctamente.");
        colaboradores = colaboradorService.getAllColaboradoresActivos();
        System.out.println("Eliminado");
    }

    public void update(){
        try{
            System.out.println("El nombre a actualizado es:"+selectcolaborador.getNombre());
            colaboradorService.updateColaborador(selectcolaborador);
            addMessage("Aviso", "Registro modificado correctamente.");
            colaboradores = colaboradorService.getAllColaboradoresActivos();
        }catch (Exception e){
        } finally {
            colaborador = new Colaborador();
        }
    }

    public void find(){
        String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("ColaboradorIdBusqueda");
        colaboradores.clear();
        colaboradores.add(colaboradorService.findColaborador(id));
    }


    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void onRowSelectedColaborador(SelectEvent event){
        FacesMessage msg = new FacesMessage("Colaborador Seleccionado",((Colaborador) event.getObject()).getNombre());
        FacesContext.getCurrentInstance().addMessage("asdasdasdasd",msg);
    }

    public void onRowUnselectColaborador(SelectEvent event){
        FacesMessage msg = new FacesMessage("Colaborador selecionado",((Colaborador) event.getObject()).getNombre());
        FacesContext.getCurrentInstance().addMessage("dasdasdasdas",msg);
    }


    public void onRowEdit(RowEditEvent event) {
        colaboradorService.updateColaborador(((Colaborador) event.getObject()));
        System.out.println(((Colaborador) event.getObject()).getNombre());
        FacesMessage msg = new FacesMessage("Colaborador Editado", ((Colaborador) event.getObject()).getPk_idColaborador());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Actualización Cancelada", ((Colaborador) event.getObject()).getPk_idColaborador());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowDelete(RowEditEvent event) {
        colaboradorService.deleteColaborador((Colaborador) event.getObject());
        FacesMessage msg = new FacesMessage("Colaborador eliminado", ((Colaborador) event.getObject()).getPk_idColaborador());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        colaboradores = colaboradorService.getAllColaboradoresActivos();
    }
}
