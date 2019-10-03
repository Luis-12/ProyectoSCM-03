package s.c.m.beans;


import org.primefaces.PrimeFaces;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import s.c.m.entities.Colaborador;
import s.c.m.entities.Departamento;
import s.c.m.entities.Puesto;
import s.c.m.services.ColaboradorService;
import s.c.m.services.DepartamentoService;


import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@ManagedBean
@Scope("session")
public class ColaboradorBean {
    @Autowired
    ColaboradorService colaboradorService;
    private Colaborador colaborador = new Colaborador();
    private Colaborador colaborador1 = new Colaborador();
    private Colaborador colaboradorlogueado = new Colaborador();
    private Colaborador selectcolaborador=new Colaborador();
    private Departamento departamento = new Departamento();
    private Puesto puesto = new Puesto();
    private List<Colaborador> colaboradores;
    private  boolean loggedIn;
    @Autowired
    DepartamentoService departamentoService;
    private Date fecha;


    @PostConstruct
    public String init()
    {
        Colaborador miC = new Colaborador();
        colaboradores = colaboradorService.getAllColaboradoresActivos();
        return "colaboradorList.xhtml";
    }


    public Date getFecha()
    {
        Calendar c = Calendar.getInstance();
        return c.getTime();
    }

    public void setFecha(Date fecha)
    {
        this.fecha = fecha;
    }


    public Colaborador getColaborador()
    {
        return colaborador;
    }

    public void setColaborador(Colaborador colaborador)
    {
        this.colaborador = colaborador;
    }

    public Colaborador getSelectcolaborador()
    {
        return selectcolaborador;
    }

    public void setSelectcolaborador(Colaborador selectcolaborador)
    {
        this.selectcolaborador = selectcolaborador;
    }

    public Departamento getDepartamento()
    {
        return departamento;
    }

    public void setDepartamento(Departamento departamento)
    {
        this.departamento = departamento;
    }

    public Puesto getPuesto()
    {
        return puesto;
    }

    public void setPuesto(Puesto puesto)
    {
        this.puesto = puesto;
    }

    public List<Colaborador> getColaboradores()
    {
        return colaboradores;
    }

    public void setColaboradores(List<Colaborador> colaboradores)
    {
        this.colaboradores = colaboradores;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public Colaborador getColaboradorlogueado() {
        return colaboradorlogueado;
    }

    public void setColaboradorlogueado(Colaborador colaboradorlogueado) {
        this.colaboradorlogueado = colaboradorlogueado;
    }

    public String doLogin() {
            colaborador1 = colaboradorService.findColaborador(colaboradorlogueado.getPk_idColaborador());
            String dbUsername = colaborador1.getPk_idColaborador();
            String dbPassword = colaborador1.getClave();

            if (colaboradorlogueado.getPk_idColaborador().equals(dbUsername) && colaboradorlogueado.getClave().equals(dbPassword)) {
                colaboradorlogueado.setNombre(colaborador1.getNombre());
                loggedIn = true;
                return "/administracion/MantenimientoColaborador.xhtml?faces-redirect=true";
            }

            FacesMessage msg = new FacesMessage("Login error!", "ERROR MSG");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            FacesContext.getCurrentInstance().addMessage(null, msg);

            return "/login.xhtml";
    }



    public String doLogout() {
        // Set the paremeter indicating that user is logged in to false
        loggedIn = false;

        FacesMessage msg = new FacesMessage("Salio", "INFO");
        msg.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext.getCurrentInstance().addMessage(null, msg);
        //  return navigationBean.redirectToLogin();
        return "/login.xhtml?faces-redirect=true";
    }



    public void create()
    {
        FacesMessage mensaje= null;
        boolean existeColaborador = false;
        //System.out.println(colaborador.getPk_idColaborador());
        for(Colaborador c: colaboradores){
            if(colaborador.getPk_idColaborador().equals(c.getPk_idColaborador())){
                existeColaborador = true;
                break;
            }else{
                existeColaborador = false;
            }
        }
        if(!existeColaborador){
            try{
                System.out.println("No existe el colaborador");
                colaboradorService.createColaborador(colaborador);
                mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Colaborador insertado correctamente.");
                colaboradores = colaboradorService.getAllColaboradoresActivos();
            }catch (Exception e){
            } finally {
                colaborador = new Colaborador();
            }
        }else if(existeColaborador) {
            System.out.println("Si existe el colaborador con ese id");
            mensaje = new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Ya existe un colaborador con ese id pruebe nuevamente.");
            colaborador = new Colaborador();
        }
        FacesContext.getCurrentInstance().addMessage(null, mensaje);
        PrimeFaces.current().ajax().addCallbackParam("existeColaborador", existeColaborador);
    }

    public void checkDepartamento()
    {
        PrimeFaces current = PrimeFaces.current();

        if (departamentoService.getAllDepartamentosActivos().size()!=0) {
            current.executeScript("PF('dlAC').show();"); //si no esta vacio muestra el dialogo
            //onclick="PF('dlAC').show();"
        } else {
            addMessage("Aviso", "No puede agregar colaboradores si no existe al menos un departamento."); //si esta vacio muetra este mensaje
        }
    }
    public void checkSelection()
    { //para verifiacar si el objeto selectcolaborador esta vacio
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
            current.executeScript("PF('dlEC').show();"); //si no esta vacio muestra el dialogo
        }

    }

    public void delete()
    {
        colaboradorService.deleteColaborador(selectcolaborador);
        addMessage("Aviso", "Registro eliminado correctamente.");
        colaboradores = colaboradorService.getAllColaboradoresActivos();
        System.out.println("Eliminado");
    }

    public void update()
    {
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



    public void find()
    {
        String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("ColaboradorIdBusqueda");
        colaboradores.clear();
        colaboradores.add(colaboradorService.findColaborador(id));
    }

    public void close()
    {
        colaborador = new Colaborador();
    }

    public void addMessage(String summary, String detail)
    {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void onRowSelectedColaborador(SelectEvent event)
    {
        FacesMessage msg = new FacesMessage("Colaborador Seleccionado",((Colaborador) event.getObject()).getNombre());
        FacesContext.getCurrentInstance().addMessage("asdasdasdasd",msg);
    }

    public void onRowUnselectColaborador(SelectEvent event)
    {
        FacesMessage msg = new FacesMessage("Colaborador selecionado",((Colaborador) event.getObject()).getNombre());
        FacesContext.getCurrentInstance().addMessage("dasdasdasdas",msg);
    }


    public void onRowEdit(RowEditEvent event)
    {
        colaboradorService.updateColaborador(((Colaborador) event.getObject()));
        System.out.println(((Colaborador) event.getObject()).getNombre());
        FacesMessage msg = new FacesMessage("Colaborador Editado", ((Colaborador) event.getObject()).getPk_idColaborador());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent event)
    {
        FacesMessage msg = new FacesMessage("Actualización Cancelada", ((Colaborador) event.getObject()).getPk_idColaborador());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowDelete(RowEditEvent event)
    {
        colaboradorService.deleteColaborador((Colaborador) event.getObject());
        FacesMessage msg = new FacesMessage("Colaborador eliminado", ((Colaborador) event.getObject()).getPk_idColaborador());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        colaboradores = colaboradorService.getAllColaboradoresActivos();
    }
}
