package s.c.m.beans;

import org.primefaces.PrimeFaces;
import org.primefaces.model.menu.MenuModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import s.c.m.components.MenuView;
import s.c.m.entities.Colaborador;
import s.c.m.entities.Departamento;
import s.c.m.entities.Puesto;
import s.c.m.services.ColaboradorService;
import s.c.m.services.DepartamentoService;
import s.c.m.services.PuestoService;


import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.Calendar;
import java.util.Date;
import java.util.List;




@ManagedBean
@Scope("session")
public class ColaboradorBean {
    @Autowired
    ColaboradorService colaboradorService;
    private Colaborador colaborador = new Colaborador();
    private Colaborador colaboradorMarca=new Colaborador();
    private Colaborador colaborador1 = new Colaborador();
    private Colaborador colaboradorlogueado = new Colaborador();
    private Colaborador selectcolaborador=new Colaborador();
    private Colaborador colaboradorClave = new Colaborador();
    private Departamento departamento = new Departamento();
    private Puesto puesto = new Puesto();

    @Autowired
    private PuestoService puestoService;
    private List<Colaborador> colaboradores;
    private  boolean loggedIn;
    private MenuView generaMenu = new MenuView();
    private MenuModel model;

    @Autowired
    DepartamentoService departamentoService;
    private Date fecha;


    @PostConstruct
    public String init()
    {
        Colaborador miC = new Colaborador();
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
    public Colaborador getColaboradorMarca() {
        return colaboradorMarca;
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

    public MenuView getGeneraMenu() {
        return generaMenu;
    }

    public void setGeneraMenu(MenuView generaMenu) {
        this.generaMenu = generaMenu;
    }

    public MenuModel getModel() {
        return model;
    }

    public void setModel(MenuModel model) {
        this.model = model;
    }

    public boolean validaVence(Date f){
        Date fechaVence = f;
        Date fechaActual = new Date();
        String fechaVenceString = null;
        String fechaActualString = null;

        Calendar fechaActual2 = Calendar.getInstance();

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

        System.out.println("El mes de vencimiento es: " + mesV);
        System.out.println("El dia de vencimiento es: " + diaV);
        System.out.println("El anio de vencimiento es:" + yearV);

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

        mesA=fechaActual2.get(Calendar.MONTH) +1;
        diaA=fechaActual2.get(Calendar.DAY_OF_MONTH);
        yearA=fechaActual2.get(Calendar.YEAR);

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
            System.out.println("AL USUARIO SE LE VENCIÓ LA CONTRASEÑA");
            addMessage("Aviso","La contraseña venció, ¡debe cambiarla!");
        }else {
            vencio = false;
            System.out.println("AL USUARIO SE LE SIGUE VIGENTE LA CONTRASEÑA");
        }
        return vencio;//Si es true quiere decir que vencio
    }

    public void construyeMenuDinamico(String rol,String nombreDept){
        model=generaMenu.construyeMenuPorRol(rol,nombreDept);
    }

    public String doLogin() throws Exception {
        PrimeFaces current = PrimeFaces.current();
        colaborador1 = colaboradorService.findColaborador(colaboradorlogueado.getPk_idColaborador());

        if (colaborador1 != null && colaborador1.getEstado().equals("Activo")) {//IF que valida que el usuario ingresado existe o no
            String dbUsername = colaborador1.getPk_idColaborador();
            String dbPassword = colaborador1.getClave();
            FacesContext context = FacesContext.getCurrentInstance();
            colaboradorClave.setPk_idColaborador(colaboradorlogueado.getPk_idColaborador());
            colaboradorClave.setClave(colaboradorlogueado.getClave());

            if (colaboradorlogueado.getPk_idColaborador().equals(dbUsername)
                    && colaboradorlogueado.getClave().equals(dbPassword)
                    && colaborador1.getPuesto().getDescripcion().equals("Gerencia")
                    && colaborador1.getDepartamento().getNombre().equals("Recursos Humanos")) {
                colaboradorlogueado.setNombre(colaborador1.getNombre());
                colaboradorlogueado.setPuesto(colaborador1.getPuesto());
                colaboradorlogueado.setDepartamento(colaborador1.getDepartamento());
                if (validaVence(colaborador1.getFechaVencimiento())) {
                    current.executeScript("PF('dlCC').show();");
                } else {
                    construyeMenuDinamico(colaborador1.getPuesto().getDescripcion(), colaborador1.getDepartamento().getNombre());
                    loggedIn = true;
                    colaboradores = colaboradorService.getAllColaboradoresActivos(colaboradorlogueado);
                    return "/administracion/MantenimientoColaborador.xhtml?faces-redirect=true";
                }

            }
            if ((colaboradorlogueado.getPk_idColaborador().equals(dbUsername)
                    && colaboradorlogueado.getClave().equals(dbPassword)
                    && colaborador1.getPuesto().getDescripcion().equals("Gerencia")
                    && (!colaborador1.getDepartamento().getNombre().equals("Recursos Humanos")))) {//IF DE CUANDO ES JEFE PERO NO DE RH
                colaboradorlogueado.setNombre(colaborador1.getNombre());
                colaboradorlogueado.setPuesto(colaborador1.getPuesto());
                colaboradorlogueado.setDepartamento(colaborador1.getDepartamento());
                if (validaVence(colaborador1.getFechaVencimiento())) {
                    current.executeScript("PF('dlCC').show();");
                } else {
                    construyeMenuDinamico(colaborador1.getPuesto().getDescripcion(), colaborador1.getDepartamento().getNombre());
                    loggedIn = true;
                    colaboradores = colaboradorService.getAllColaboradoresActivos(colaboradorlogueado);
                    return "/administracion/ListaSolicitud.xhtml?faces-redirect=true";
                }

            }
            if (colaboradorlogueado.getPk_idColaborador().equals(dbUsername) && colaboradorlogueado.getClave().equals(dbPassword) && colaborador1.getPuesto().getDescripcion().equals("Colaborador")) {
                colaboradorlogueado.setNombre(colaborador1.getNombre());
                colaboradorlogueado.setPuesto(colaborador1.getPuesto());
                colaboradorlogueado.setDepartamento(colaborador1.getDepartamento());
                if (validaVence(colaborador1.getFechaVencimiento())) {
                    current.executeScript("PF('dlCC').show();");
                } else {
                    construyeMenuDinamico(colaborador1.getPuesto().getDescripcion(), colaborador1.getDepartamento().getNombre());
                    loggedIn = true;
                    colaboradores = colaboradorService.getAllColaboradoresActivos(colaboradorlogueado);
                    return "/administracion/SolicitudVacaciones.xhtml?faces-redirect=true";
                }
            }
            if (colaboradorlogueado.getPk_idColaborador().equals(dbUsername) && colaboradorlogueado.getClave().equals(dbPassword)
                    && (colaborador1.getPuesto().getDescripcion().equals("Dirección Corporativa")
                    ||colaborador1.getPuesto().getDescripcion().equals("Jefatura")
                    ||colaborador1.getPuesto().getDescripcion().equals("Supervisor")
                    ||colaborador1.getPuesto().getDescripcion().equals("Analista"))) {
                colaboradorlogueado.setNombre(colaborador1.getNombre());
                colaboradorlogueado.setPuesto(colaborador1.getPuesto());
                colaboradorlogueado.setDepartamento(colaborador1.getDepartamento());
                if (validaVence(colaborador1.getFechaVencimiento())) {
                    current.executeScript("PF('dlCC').show();");
                } else {
                    construyeMenuDinamico(colaborador1.getPuesto().getDescripcion(), colaborador1.getDepartamento().getNombre());
                    loggedIn = true;
                    colaboradores = colaboradorService.getAllColaboradoresActivos(colaboradorlogueado);


                    return "/administracion/ListaSolicitud.xhtml?faces-redirect=true";
                }
            }
            FacesMessage msg = new FacesMessage("Aviso", "El usuario o contraseña son incorrectos");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return null;
        }else {
            addMessage("Aviso", "El usuario ingresado no se encuetra registrado en el sistema");
            return null;
        }
    }



    public boolean validaClave(){
        boolean diferente = false;
        if(colaborador1.getClave().equals(colaboradorlogueado.getClave())){//Si son iguales retorna false por ende no puede cambiar la clave
            FacesContext.getCurrentInstance().addMessage("contraseñaCCC",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "La nueva clave no puede ser igual a la anterior","La nueva clave no puede ser igual a la anterior"));

            diferente = false;
        }else{
            diferente = true;
        }
        return diferente;//Si es true quiere decir que son diferentes por lo tanto pueden hacer el cambio
    }


    public boolean validarContrasena(String usuario,String contrasena)
    {
        for(int i=0;(i+2)<usuario.length();i++)
        if(contrasena.indexOf(usuario.substring(i,i+3))!=-1) { return false;
        }
        return true;
    }
    public String cambioClave() throws Exception {

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
                FacesContext.getCurrentInstance().addMessage("contraseñaCCC",
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "No puede usar su nombre o apellidos","No puede usar su nombre o apellidos"));
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

        FacesMessage msg = new FacesMessage("Salió", "Ha salido con éxito");
        msg.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return "/login.xhtml?faces-redirect=true";
    }


    public void create()
    {
        PrimeFaces current = PrimeFaces.current();
        FacesMessage mensaje= null;
        boolean existeColaborador = false;

        for(Colaborador c: colaboradores){
            if(colaborador.getPk_idColaborador().equals(c.getPk_idColaborador())){
                existeColaborador = true;
                break;
            }else{
                existeColaborador = false;
            }
        }
        if(!existeColaborador){
            if(     ((colaboradorService.findColaboradorEncargado(colaborador.getDepartamento(), puestoService.findIdPuesto("Gerencia"))==null)
                    && //Si no encuetra un gerente Gerencia
                    (colaboradorService.findColaboradorEncargado(colaborador.getDepartamento(), puestoService.findIdPuesto("Jefatura"))==null))//Y si no encuentra un jefe pasa directo a agregar Jefatura
                    ||
                    ((!colaborador.getPuesto().getDescripcion().equals("Gerencia"))//Si el puesto es distinto de Gerencia
                    &&
                    (!colaborador.getPuesto().getDescripcion().equals("Jefatura")))//Y si el puesto es distinto de Jefatura pasa directo a agregar
               ){
            try{
                System.out.println("No existe el colaborador");
                colaboradorService.createColaborador(colaborador);
                current.executeScript("PF('dlAC').hide();");
                current.ajax().update("form:tablaColaborador");
                mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Colaborador insertado correctamente.");
                colaboradores = colaboradorService.getAllColaboradoresActivos(colaboradorlogueado);
            }catch (Exception e){
            } finally {
                colaborador = new Colaborador();
            }
            }else{
                mensaje = new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "El departamento " + colaborador.getDepartamento().getNombre() +" ya tiene un Gerente o Jefe Asignado");
                System.out.println("Ya existe un JEFE PARA ESTE DEPARTAMENTO");
            }
        }else if(existeColaborador) {
            System.out.println("Si existe el colaborador con esa cédula");
            mensaje = new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Ya existe un colaborador con esa cédula, pruebe nuevamente.");
            colaborador = new Colaborador();
        }
        FacesContext.getCurrentInstance().addMessage(null, mensaje); //CON MI VALIDACION DE QUE NO SE ASIGNE DOS JEFES A UN DEPT , SE ME CAE POR ESTA LINEA NO SE NI LO QUE HACE
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
        PrimeFaces current = PrimeFaces.current();
        colaboradorService.deleteColaborador(selectcolaborador);
        current.ajax().update("form:tablaColaborador");
        addMessage("Aviso", "Colaborador desactivado correctamente.");
        colaboradores = colaboradorService.getAllColaboradoresActivos(colaboradorlogueado);
        System.out.println("Eliminado");
    }

    public void update() throws Exception {
        Colaborador colaboradorEvalua = colaboradorService.findColaborador(selectcolaborador.getPk_idColaborador());
        PrimeFaces current = PrimeFaces.current();
        FacesMessage mensaje= null;
        if((!colaboradorEvalua.getPuesto().getDescripcion().equals(selectcolaborador.getPuesto().getDescripcion()))//Si es diferente el puesto o el departamento
                || (!colaboradorEvalua.getDepartamento().getNombre().equals(selectcolaborador.getDepartamento().getNombre()))){

        if(     ((colaboradorService.findColaboradorEncargado(selectcolaborador.getDepartamento(), puestoService.findIdPuesto("Gerencia"))==null)
                && //Si no encuetra un gerente Gerencia
                (colaboradorService.findColaboradorEncargado(selectcolaborador.getDepartamento(), puestoService.findIdPuesto("Jefatura"))==null))//Y si no encuentra un jefe pasa directo a agregar Jefatura
                ||
                ((!selectcolaborador.getPuesto().getDescripcion().equals("Gerencia"))//Si el puesto es distinto de Gerencia
                        && (!selectcolaborador.getPuesto().getDescripcion().equals("Jefatura")))//Y si el puesto es distinto de Jefatura pasa directo a agregar
        )
        {
            try{
                System.out.println("El nombre actualizado es:"+selectcolaborador.getNombre());
                colaboradorService.updateColaborador(selectcolaborador);
                current.executeScript("PF('dlUC').hide();");
                current.ajax().update("form:tablaColaborador");
                addMessage("Aviso", "Colaborador actualizado correctamente.");
                colaboradores = colaboradorService.getAllColaboradoresActivos(colaboradorlogueado);
            }catch (Exception e){
            } finally {
                selectcolaborador = new Colaborador();
            }
        }else{
            addMessage("Aviso","El departamento " + selectcolaborador.getDepartamento().getNombre() +" ya tiene un Gerente o Jefe Asignado");
            //mensaje = new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "El departamento " + selectcolaborador.getDepartamento().getNombre() +" ya tiene un Gerente o Jefe Asignado");
            System.out.println("Ya existe un JEFE PARA ESTE DEPARTAMENTO");
        }
    }else{
            System.out.println("El nombre actualizado es:"+selectcolaborador.getNombre());
            colaboradorService.updateColaborador(selectcolaborador);
            current.executeScript("PF('dlUC').hide();");
            current.ajax().update("form:tablaColaborador");
            addMessage("Aviso", "Colaborador actualizado correctamente.");
            colaboradores = colaboradorService.getAllColaboradoresActivos(colaboradorlogueado);
    }
    }

     public void findColaboradorMarca() throws Exception {
        colaboradorMarca=colaboradorService.findColaborador(colaboradorMarca.getPk_idColaborador());
     }

    public void find() throws Exception {
        String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("ColaboradorIdBusqueda");
        colaboradores.clear();
        colaboradores.add(colaboradorService.findColaborador(id));
    }

    public void close()
    {
        colaborador = new Colaborador();
    }

    public void close2()
    {
        selectcolaborador = new Colaborador();
        colaboradores = colaboradorService.getAllColaboradoresActivos(colaboradorlogueado);
    }

    public void addMessage(String summary, String detail)
    {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

}
