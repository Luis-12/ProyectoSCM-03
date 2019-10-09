package s.c.m.beans;


import org.apache.commons.codec.digest.DigestUtils;
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
import java.io.IOException;
import java.text.ParseException;
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
    private Colaborador colaboradorClave = new Colaborador();
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

    public Colaborador getColaborador1() {
        return colaborador1;
    }

    public void setColaborador1(Colaborador colaborador1) {
        this.colaborador1 = colaborador1;
    }

    public Colaborador getColaboradorClave() {
        return colaboradorClave;
    }

    public void setColaboradorClave(Colaborador colaboradorClave) {
        this.colaboradorClave = colaboradorClave;
    }


    public boolean validaVence(Date f){
        Date fechaVence = f;
        Date fechaActual = new Date();
        String fechaVenceString = null;
        String fechaActualString = null;

        int fechaV=0;
        int fechaA=0;

        int mesA;
        String mesA2 = null;
        int mesV;
        String mesV2 = null;

        int diaA;
        String diaA2 = null;
        int diaV;
        String diaV2 = null;


        int yearA;
        int yearV;
        Boolean vencio = false;

        mesV=fechaVence.getMonth() +1;
        diaV=fechaVence.getDate();
        yearV=fechaVence.getYear()+1900;
        if(mesV<10){
            mesV2 = "0"+ mesV;
        }else{
            mesV2 =""+mesV;
        }
        if(diaV<10){
            diaV2 = "0"+ diaV;
        }else{
            diaV2 =""+ diaV;
        }
        fechaVenceString = yearV+""+mesV2+""+diaV2;
        fechaV = Integer.parseInt(fechaVenceString);
        //System.out.println("FECHA DE VENCIMIENTO"+fechaV);

        mesA=fechaActual.getMonth()+1;
        diaA=fechaActual.getDate();
        yearA=fechaActual.getYear()+1900;
        if(mesA<10){
            mesA2 = "0"+ mesA;
        }else{
            mesA2 =""+mesA;
        }
        if(diaA<10){
            diaA2 = "0"+ diaA;
        }else{
            diaA2 =""+ diaA;
        }
        fechaActualString = yearA+""+mesA2+""+diaA2;
        fechaA = Integer.parseInt(fechaActualString);
        //System.out.println("FECHA ACTUAL:"+fechaA);

        if(fechaA >= fechaV){
            vencio = true;
            System.out.println("AL USUARIO SE LE VENCIO LA CONTRASEÑA");
            addMessage("Aviso","La contraseña vencio cambiela");
        }else {
            vencio = false;
            System.out.println("AL USUARIO SE LE SIGUE VIGENTE LA CONTRASEÑA");
        }
        return vencio;//Si es true quiere decir que vencio
    }

    public String doLogin() throws IOException {
        PrimeFaces current = PrimeFaces.current();
            colaborador1 = colaboradorService.findColaborador(colaboradorlogueado.getPk_idColaborador());
            String dbUsername = colaborador1.getPk_idColaborador();
            String dbPassword = colaborador1.getClave();
        FacesContext context = FacesContext.getCurrentInstance();
        colaboradorClave.setPk_idColaborador(colaboradorlogueado.getPk_idColaborador());
        colaboradorClave.setClave(colaboradorlogueado.getClave());


        if (colaboradorlogueado.getPk_idColaborador().equals(dbUsername)
                    && colaboradorlogueado.getClave().equals(dbPassword)
                    && colaborador1.getPuesto().getDescripcion().equals("Jefatura")
                    &&colaborador1.getDepartamento().getNombre().equals("Recursos Humanos")) {
                colaboradorlogueado.setNombre(colaborador1.getNombre());
                colaboradorlogueado.setPuesto(colaborador1.getPuesto());
                colaboradorlogueado.setDepartamento(colaborador1.getDepartamento());
                if(validaVence(colaborador1.getFechaVencimiento())){
                    current.executeScript("PF('dlCC').show();");
                }else{
                    loggedIn = true;
                    return "/administracion/MantenimientoColaborador.xhtml?faces-redirect=true";
                }

            }
        if (colaboradorlogueado.getPk_idColaborador().equals(dbUsername) && colaboradorlogueado.getClave().equals(dbPassword)&& colaborador1.getPuesto().getDescripcion().equals("Colaborador")) {
            colaboradorlogueado.setNombre(colaborador1.getNombre());
            colaboradorlogueado.setPuesto(colaborador1.getPuesto());
            colaboradorlogueado.setDepartamento(colaborador1.getDepartamento());
            if(validaVence(colaborador1.getFechaVencimiento())){
                //addMessage("AVISO","Cambie la constraseña");
                current.executeScript("PF('dlCC').show();");
            }else{
            loggedIn = true;
                return "/colaboradores/SolicitudVacaciones.xhtml?faces-redirect=true";
            }
        }

        FacesMessage msg = new FacesMessage("Aviso", "El usuario o contraseña son incorrectos");
        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return null;
    }
    public boolean validaClave(){
        boolean diferente = false;
        if(colaborador1.getClave().equals(colaboradorlogueado.getClave())){//Si son iguales retorna false por ende no puede cambiar la clave
            addMessage("Aviso","La nueva clave no puede ser igual a la anterior");
            diferente = false;
        }else{
            diferente = true;
        }
        return diferente;//Si es true quiere decir que son diferentes por lo tanto pueden hacer el cambio
    }


    public boolean validarContrasena(String usuario,String contrasena)
    {
        usuario.toLowerCase();
        contrasena.toLowerCase();
        for(int i=0;(i+2)<contrasena.length();i++)
        if(contrasena.indexOf(usuario.substring(i,i+3))!=-1)
            return false;
        return true;
    }
    public String cambioClave() throws ParseException, IOException {

        if(validaClave()){//Si validaClave retorna true se puede cambiar la clave
            if (validarContrasena(colaborador1.getNombre(), colaboradorlogueado.getClave())==true) {

                colaborador1.setClave(colaboradorlogueado.getClave());
                System.out.println("NUEVA CLAVE:" + colaborador1.getClave());
                colaboradorService.actualizaClave(colaborador1);//Aca le paso el colaborador ya con la nueva clave para que en el service con esta funcion lo updatee en la base con la nueva clave
                Colaborador c = colaboradorService.findColaborador(colaborador1.getPk_idColaborador());
                System.out.println("La nueva clave es" + c.getClave());
                return doLogin();
            }
            else {
                FacesContext.getCurrentInstance().addMessage("contraseñaCC", new FacesMessage("No puede incuir su nombre"));
                return null;
            }
        }else{
            return null;
        }
    }

    public String error() {
        return "/login.xhtml";
    }

    public String doLogout() {
        loggedIn = false;

        FacesMessage msg = new FacesMessage("Salio", "Ha salido con exito");
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
                colaborador.setJustificacion("NA");
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
