package s.c.m.beans;


import org.primefaces.PrimeFaces;
import org.primefaces.model.menu.MenuModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import s.c.m.components.MenuView;
import s.c.m.entities.*;
import s.c.m.services.*;


import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


import static java.util.Calendar.YEAR;


@ManagedBean
@Scope("session")
public class ColaboradorBean {
    //Controlador que conecta el front end con el backend para IO de colaborador.
    @Autowired
    ColaboradorService colaboradorService;
    private Colaborador colaborador = new Colaborador();
    private Colaborador colaboradorMarca = new Colaborador();

    private Colaborador colaborador1 = new Colaborador();
    private Colaborador colaboradorlogueado = new Colaborador();
    private Colaborador selectcolaborador = new Colaborador();
    private Colaborador colaboradorClave = new Colaborador();
    private Departamento departamento = new Departamento();
    private Puesto puesto = new Puesto();
    private Asignaciones asignaciones = new Asignaciones();
    Calendar c2;
    private String base64Image = "";
    private MarcaLaboradas marcaLa = new MarcaLaboradas();

    private Vacaciones vacaciones = new Vacaciones();
    private Vacaciones vacaciones1 = new Vacaciones();
    private VacacionesPorColaborador vacacionesPorColaborador = new VacacionesPorColaborador();


    @Autowired
    private PuestoService puestoService;
    private List<Colaborador> colaboradores;
    private List<MarcasJornada> marcasJornadasList = new ArrayList<MarcasJornada>();
    private boolean loggedIn;
    private String justST;
    private String justTE;
    private MenuView generaMenu = new MenuView();
    private MenuModel model;
    Calendar now;
    String variable = "Descanso";
    private AsignacionDescansos asignacionDescansos = new AsignacionDescansos();
    public String mensaje;

    @Autowired
    AsignacionesServices asignacionesServices;

    @Autowired
    MarcaDescansoService marcaDescansoService;

    @Autowired
    AsignacionDescansosService asignacionDescansosService;

    @Autowired
    DepartamentoService departamentoService;
    private Date fecha;
    @Autowired
    MarcaLaboradaService marcaLaboradaService;

    @Autowired
    VacacionesService vacacionesService;

    @Autowired
    VacacionesPorColaboradorService vacacionesPorColaboradorService;

    private MarcaLaboradas marcaLaboradas = new MarcaLaboradas();
    public boolean botonEntrada = true;
    public boolean botonSalida = true;
    public boolean botonDesSali = true;


    @PostConstruct
    public String init() {
        Colaborador miC = new Colaborador();
        return "colaboradorList.xhtml";
    }

    public VacacionesPorColaborador getVacacionesPorColaborador() {
        return vacacionesPorColaborador;
    }

    public void setVacacionesPorColaborador(VacacionesPorColaborador vacacionesPorColaborador) {
        this.vacacionesPorColaborador = vacacionesPorColaborador;
    }

    public List<MarcasJornada> getMarcasJornadasList() {
        return marcasJornadasList;
    }

    public void setMarcasJornadasList(List<MarcasJornada> marcasJornadasList) {
        this.marcasJornadasList = marcasJornadasList;
    }

    public Vacaciones getVacaciones() {
        return vacaciones;
    }

    public void setVacaciones(Vacaciones vacaciones) {
        this.vacaciones = vacaciones;
    }

    public Vacaciones getVacaciones1() {
        return vacaciones1;
    }

    public void setVacaciones1(Vacaciones vacaciones1) {
        this.vacaciones1 = vacaciones1;
    }

    public String getVariable() {
        return variable;
    }

    public String getJustTE() {
        return justTE;
    }

    public void setJustTE(String justTE) {
        this.justTE = justTE;
    }

    public Date getFecha() {
        Calendar c = Calendar.getInstance();
        return c.getTime();
    }

    public String getBase64Image() {
        return base64Image;
    }

    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }

    public String getJustST() {
        return justST;
    }

    public void setJustST(String justST) {
        this.justST = justST;
    }

    public boolean isBotonEntrada() {
        return botonEntrada;
    }

    public boolean isBotonDesSali() {
        return botonDesSali;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Colaborador getColaboradorMarca() {
        return colaboradorMarca;
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

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
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

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public boolean isBotonSalida() {
        return botonSalida;
    }

    public Colaborador getColaboradorlogueado() {
        return colaboradorlogueado;
    }

    public void setColaboradorlogueado(Colaborador colaboradorlogueado) {
        this.colaboradorlogueado = colaboradorlogueado;
    }

    public MarcaLaboradas getMarcaLaboradas() {
        return marcaLaboradas;
    }

    public void setMarcaLaboradas(MarcaLaboradas marcaLaboradas) {
        this.marcaLaboradas = marcaLaboradas;
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

    public boolean validaVence(Date f) {//Funcion que valida si la clave del usuario ya vencio
        Date fechaVence = f;
        Date fechaActual = new Date();
        String fechaVenceString = null;
        String fechaActualString = null;

        Calendar fechaActual2 = Calendar.getInstance();

        int fechaV = 0;
        int fechaA = 0;

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

        mesV = fechaVence.getMonth() + 1;
        diaV = fechaVence.getDate();
        yearV = fechaVence.getYear() + 1900;

        if (mesV < 10) {
            mesV2 = "0" + mesV;
        } else {
            mesV2 = "" + mesV;
        }
        if (diaV < 10) {
            diaV2 = "0" + diaV;
        } else {
            diaV2 = "" + diaV;
        }
        fechaVenceString = yearV + "" + mesV2 + "" + diaV2;//Se forma un numero con la fecha de vencimiento
        fechaV = Integer.parseInt(fechaVenceString);//Y se pasa a int la fecha de vencimiento formado con la fecha que tenia en la base el colan

        mesA = fechaActual2.get(Calendar.MONTH) + 1;
        diaA = fechaActual2.get(Calendar.DAY_OF_MONTH);
        yearA = fechaActual2.get(YEAR);

        if (mesA < 10) {
            mesA2 = "0" + mesA;
        } else {
            mesA2 = "" + mesA;
        }
        if (diaA < 10) {
            diaA2 = "0" + diaA;
        } else {
            diaA2 = "" + diaA;
        }
        fechaActualString = yearA + "" + mesA2 + "" + diaA2;//Se da formato a la fecha actual
        fechaA = Integer.parseInt(fechaActualString);//Se pasa a int la fecha actual

        if (fechaA >= fechaV) {//Se valida si la fecha actual es mayor o igual a la fecha de vencimiento
            vencio = true;
            System.out.println("AL USUARIO SE LE VENCIÓ LA CONTRASEÑA");
            addMessage("Aviso", "La contraseña venció, ¡debe cambiarla!");//Si es asi quiere decir que vencio
        } else {//De no ser asi la clave sigue vigente
            vencio = false;
            System.out.println("AL USUARIO SE LE SIGUE VIGENTE LA CONTRASEÑA");
        }
        return vencio;//Si es true quiere decir que vencio
    }

    public void construyeMenuDinamico(String rol, String nombreDept) {//Funcion para crear el menu dinamico segun el departamento y rol
        model = generaMenu.construyeMenuPorRol(rol, nombreDept);
    }

    public String doLogin() throws Exception {//Funcion para hacer login
        PrimeFaces current = PrimeFaces.current();
        colaborador1 = colaboradorService.findColaborador(colaboradorlogueado.getPk_idColaborador());//Se encuetra el colaborador

        if (colaborador1 != null && colaborador1.getEstado().equals("Activo")) {//IF que valida que el usuario ingresado existe y esta activo o no
            String dbUsername = colaborador1.getPk_idColaborador();
            String dbPassword = colaborador1.getClave();
            FacesContext context = FacesContext.getCurrentInstance();
            colaboradorClave.setPk_idColaborador(colaboradorlogueado.getPk_idColaborador());
            colaboradorClave.setClave(colaboradorlogueado.getClave());

            if (colaboradorlogueado.getPk_idColaborador().equals(dbUsername)
                    && colaboradorlogueado.getClave().equals(dbPassword)
                    && colaborador1.getPuesto().getDescripcion().equals("Gerencia")
                    && colaborador1.getPuesto().getDescripcion().equals("Gerencia")
                    && colaborador1.getDepartamento().getNombre().equals("Recursos Humanos")) {
                //Se valida si la clave y usuario corresponden y se valida si el puesto es Gerencia y RH
                colaboradorlogueado.setNombre(colaborador1.getNombre());
                colaboradorlogueado.setPuesto(colaborador1.getPuesto());
                colaboradorlogueado.setDepartamento(colaborador1.getDepartamento());

                diasDisponibles(colaborador1);
                vacacionesPorColaborador = vacacionesPorColaboradorService.findVacacionesPorColaborador(colaborador1);

                //vacaciones1 = vacacionesService.diasDisponibles(colaborador1);//Se llena las vacaciones con los dias disponibles del colaborador
                if (validaVence(colaborador1.getFechaVencimiento())) {//Se valida si la fecha de vencimiento del cola que se loguea se vencio
                    current.executeScript("PF('dlCC').show();");//Si es asi se despliega el form para cambiar la clase
                } else {//Si no
                    construyeMenuDinamico(colaborador1.getPuesto().getDescripcion(), colaborador1.getDepartamento().getNombre());//Se contruye el menu dinamico
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

                diasDisponibles(colaborador1);
                vacacionesPorColaborador = vacacionesPorColaboradorService.findVacacionesPorColaborador(colaborador1);
                //vacaciones1 = vacacionesService.diasDisponibles(colaborador1);

                if (validaVence(colaborador1.getFechaVencimiento())) {//Se valida si la clave vencio
                    current.executeScript("PF('dlCC').show();");//Si vencio se muestra el form para cambiar la clave
                } else {//Si no es necesario cambiar la clave
                    construyeMenuDinamico(colaborador1.getPuesto().getDescripcion(), colaborador1.getDepartamento().getNombre());
                    //Se construye el menu dinamico por rol y dept
                    loggedIn = true;//Se loguea
                    colaboradores = colaboradorService.getAllColaboradoresActivos(colaboradorlogueado);//Se cargan todos los cola
                    return "/administracion/ListaSolicitud.xhtml?faces-redirect=true";
                }

            }
            if (colaboradorlogueado.getPk_idColaborador().equals(dbUsername) && colaboradorlogueado.getClave().equals(dbPassword) && colaborador1.getPuesto().getDescripcion().equals("Colaborador")) {
                //Si es que se loguea es un simple colaborador
                colaboradorlogueado.setNombre(colaborador1.getNombre());
                colaboradorlogueado.setPuesto(colaborador1.getPuesto());
                colaboradorlogueado.setDepartamento(colaborador1.getDepartamento());

                diasDisponibles(colaborador1);
                vacacionesPorColaborador = vacacionesPorColaboradorService.findVacacionesPorColaborador(colaborador1);
                //vacaciones1 = vacacionesService.diasDisponibles(colaborador1);

                if (validaVence(colaborador1.getFechaVencimiento())) {//Valida si la clave vencio
                    current.executeScript("PF('dlCC').show();");//Se muestra el dialogo para mostrar el form de cambio de clave
                } else {
                    construyeMenuDinamico(colaborador1.getPuesto().getDescripcion(), colaborador1.getDepartamento().getNombre());
                    //Se construye el menu dinamico por puesto y departamento
                    loggedIn = true;
                    colaboradores = colaboradorService.getAllColaboradoresActivos(colaboradorlogueado);//Se listan todos los colaboradores
                    return "/administracion/SolicitudVacaciones.xhtml?faces-redirect=true";//Se muestra el form de solicitud de vacaciones
                }
            }
            if (colaboradorlogueado.getPk_idColaborador().equals(dbUsername) && colaboradorlogueado.getClave().equals(dbPassword)
                    && (colaborador1.getPuesto().getDescripcion().equals("Dirección Corporativa")
                    || colaborador1.getPuesto().getDescripcion().equals("Jefatura")
                    || colaborador1.getPuesto().getDescripcion().equals("Supervisor")
                    || colaborador1.getPuesto().getDescripcion().equals("Analista"))) {//Se valida por el rol lo que se va a mostrar
                colaboradorlogueado.setNombre(colaborador1.getNombre());
                colaboradorlogueado.setPuesto(colaborador1.getPuesto());
                colaboradorlogueado.setDepartamento(colaborador1.getDepartamento());

                diasDisponibles(colaborador1);
                vacacionesPorColaborador = vacacionesPorColaboradorService.findVacacionesPorColaborador(colaborador1);
                //vacaciones1 = vacacionesService.diasDisponibles(colaborador1);

                if (validaVence(colaborador1.getFechaVencimiento())) {//Valida si la clave ya vencio
                    current.executeScript("PF('dlCC').show();");//Despliega el form de cambio de clave
                } else {
                    construyeMenuDinamico(colaborador1.getPuesto().getDescripcion(), colaborador1.getDepartamento().getNombre());
                    //Se construye el menu dinamico
                    loggedIn = true;//Se de acceso al logueo
                    colaboradores = colaboradorService.getAllColaboradoresActivos(colaboradorlogueado);//Se cargan todos los colaboradores

                    return "/administracion/ListaSolicitud.xhtml?faces-redirect=true";//Se despliega la ventana de lista de solicitudes para estos roles
                }
            }
            FacesMessage msg = new FacesMessage("Aviso", "El usuario o contraseña son incorrectos");
            //Si el usuario y clave no corresponden
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return null;
        } else {
            addMessage("Aviso", "El usuario ingresado no se encuetra registrado en el sistema");
            return null;
        }
    }


    public boolean validaClave() {//Valida que la clave no sea igual a la anterior
        boolean diferente = false;
        if (colaborador1.getClave().equals(colaboradorlogueado.getClave())) {//Si son iguales retorna false por ende no puede cambiar la clave
            FacesContext.getCurrentInstance().addMessage("contraseñaCCC",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "La nueva clave no puede ser igual a la anterior", "La nueva clave no puede ser igual a la anterior"));
            diferente = false;
        } else {//Si es diferente se retorna true para que puede pasar y cambiar
            diferente = true;
        }
        return diferente;//Si es true quiere decir que son diferentes por lo tanto pueden hacer el cambio
    }


    public boolean validarContrasena(String usuario, String contrasena) {//Valida que la clave no sea similar al usuario
        for (int i = 0; (i + 2) < usuario.length(); i++)
            if (contrasena.indexOf(usuario.substring(i, i + 3)) != -1) {
                return false;
            }
        return true;
    }

    public String cambioClave() throws Exception {//Funcion para cambiar la clave del colaborador cuando vensa

        if (validaClave()) {//Si validaClave retorna true se debe cambiar la clave
            if (validarContrasena(colaborador1.getNombre(), colaboradorlogueado.getClave()) == true) {//Valida que si sirva la clave nueva

                colaborador1.setClave(colaboradorlogueado.getClave());
                //System.out.println("NUEVA CLAVE:" + colaborador1.getClave());
                colaboradorService.actualizaClave(colaborador1);//Aca le paso el colaborador ya con la nueva clave para que en el service con esta funcion lo updatee en la base con la nueva clave
                Colaborador c = colaboradorService.findColaborador(colaborador1.getPk_idColaborador());//Aca se actualiza el colaborador con la nueva clave
                //System.out.println("La nueva clave es" + c.getClave());
                return doLogin();
            } else {//Si no quiere decir que la clave no es valida
                FacesContext.getCurrentInstance().addMessage("contraseñaCCC",
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "No puede usar su nombre o apellidos", "No puede usar su nombre o apellidos"));
                return null;
            }
        } else {
            return null;
        }
    }

    public String error() {
        return "/login.xhtml";
    }

    public String doLogout() {//Funcion para desloguearse
        loggedIn = false;

        FacesMessage msg = new FacesMessage("Salió", "Ha salido con éxito");
        msg.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return "/login.xhtml?faces-redirect=true";//Se vuelve a la pagina de logueo
    }


    public void create() {//Funcion para agregar colaborador a la base
        PrimeFaces current = PrimeFaces.current();
        FacesMessage mensaje = null;
        boolean existeColaborador = false;//Parametro para validar si el colaborador existe

        for (Colaborador c : colaboradores) {//Se busca en la lista de colaboradores activos si el colaborador se encuentra
            if (colaborador.getPk_idColaborador().equals(c.getPk_idColaborador())) {
                existeColaborador = true;//Si existe se pone la bandera true
                break;
            } else {
                existeColaborador = false;
            }
        }
        if (!existeColaborador) {//Si el colaborador no existe se puede agregar el colaborador
            if (((colaboradorService.findColaboradorEncargado(colaborador.getDepartamento(), puestoService.findIdPuesto("Gerencia")) == null)
                    && //Si no encuetra un gerente Gerencia
                    (colaboradorService.findColaboradorEncargado(colaborador.getDepartamento(), puestoService.findIdPuesto("Jefatura")) == null))//Y si no encuentra un jefe pasa directo a agregar Jefatura
                    ||
                    ((!colaborador.getPuesto().getDescripcion().equals("Gerencia"))//Si el puesto es distinto de Gerencia
                            &&
                            (!colaborador.getPuesto().getDescripcion().equals("Jefatura")))//Y si el puesto es distinto de Jefatura pasa directo a agregar
            ) {//Se valida si no existe un jefe de departamento y si el que se esta agregando no es un jefe de dept
                try {
                    //System.out.println("No existe el colaborador");
                    colaboradorService.createColaborador(colaborador);//Aca se agrega el colaborador a la base

                    //SE AGREGA A LA BASE EL REGISTRO DE VACACIONES POR COLABORADOR
                    vacacionesPorColaborador.setColaborador(colaborador);
                    vacacionesPorColaborador.setDiasdisponibles(0);
                    vacacionesPorColaborador.setDiasdisfrutados(0);
                    vacacionesPorColaborador.setFechaasignada(colaborador.getFechaInicioLaboral());
                    vacacionesPorColaboradorService.createVacacionesPorColaborador(vacacionesPorColaborador);

                    current.executeScript("PF('dlAC').hide();");
                    current.ajax().update("form:tablaColaborador");
                    mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Ingreso de colaborador exitoso.");
                    colaboradores = colaboradorService.getAllColaboradoresActivos(colaboradorlogueado);//Se refresca la lista de colaboradores

                } catch (Exception e) {
                } finally {
                    colaborador = new Colaborador();
                    vacacionesPorColaborador = new VacacionesPorColaborador();
                }
            } else {//Si no se avisa que ya hay un jefe existente
                mensaje = new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "El departamento " + colaborador.getDepartamento().getNombre() + " ya tiene un Gerente o Jefe Asignado");
                System.out.println("Ya existe un JEFE PARA ESTE DEPARTAMENTO");
            }
        } else if (existeColaborador) {//Si ya se econtro un colaborador con ese id
            //System.out.println("Si existe el colaborador con esa cédula");
            mensaje = new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Ya existe un colaborador con esa cédula, pruebe nuevamente.");
            colaborador = new Colaborador();
        }
        FacesContext.getCurrentInstance().addMessage(null, mensaje); //CON MI VALIDACION DE QUE NO SE ASIGNE DOS JEFES A UN DEPT , SE ME CAE POR ESTA LINEA NO SE NI LO QUE HACE
        PrimeFaces.current().ajax().addCallbackParam("existeColaborador", existeColaborador);
    }

    public void checkDepartamento() {//Se valida existe un dept al cual se pueden agregar colaboradores
        PrimeFaces current = PrimeFaces.current();

        if (departamentoService.getAllDepartamentosActivos().size() != 0) {
            current.executeScript("PF('dlAC').show();"); //si no esta vacio muestra el dialogo
            //onclick="PF('dlAC').show();"
        } else {
            addMessage("Aviso", "No puede agregar colaboradores si no existe al menos un departamento."); //si esta vacio muetra este mensaje
        }
    }

    public void checkSelection() { //para verifiacar si el objeto selectcolaborador esta vacio
        PrimeFaces current = PrimeFaces.current();

        if (selectcolaborador == null) {
            addMessage("Aviso", "Debe Seleccionar un Colaborador."); //si esta vacio muetra este mensaje
        } else {

            current.executeScript("PF('dlUC').show();"); //si no esta vacio muestra el dialogo para actualizar colaborador
        }
    }

    public void showconfirm() {//Funcion para mostrar confirmacion de desactivar el colaborador
        PrimeFaces current = PrimeFaces.current();

        if (selectcolaborador == null) {
            addMessage("Aviso", "Debe Seleccionar un Colaborador."); //si esta vacio muetra este mensaje
        } else {
            current.executeScript("PF('dlEC').show();"); //si no esta vacio muestra el dialogo de confirmacion
        }

    }

    public void delete() {//Funcion para desactivar el colaborador de la base
        PrimeFaces current = PrimeFaces.current();
        colaboradorService.deleteColaborador(selectcolaborador);
        current.ajax().update("form:tablaColaborador");
        addMessage("Aviso", "Colaborador desactivado correctamente.");
        colaboradores = colaboradorService.getAllColaboradoresActivos(colaboradorlogueado);
        //System.out.println("Eliminado");
    }

    public void update() throws Exception {//Funcion para actualizar colaborador
        Colaborador colaboradorEvalua = colaboradorService.findColaborador(selectcolaborador.getPk_idColaborador());//Se busca el colaborador a actualizar
        PrimeFaces current = PrimeFaces.current();
        FacesMessage mensaje = null;
        if ((!colaboradorEvalua.getPuesto().getDescripcion().equals(selectcolaborador.getPuesto().getDescripcion()))//Si es diferente el puesto o el departamento
                || (!colaboradorEvalua.getDepartamento().getNombre().equals(selectcolaborador.getDepartamento().getNombre()))) {

            if (((colaboradorService.findColaboradorEncargado(selectcolaborador.getDepartamento(), puestoService.findIdPuesto("Gerencia")) == null)
                    && //Si no encuetra un gerente Gerencia
                    (colaboradorService.findColaboradorEncargado(selectcolaborador.getDepartamento(), puestoService.findIdPuesto("Jefatura")) == null))//Y si no encuentra un jefe pasa directo a agregar Jefatura
                    ||
                    ((!selectcolaborador.getPuesto().getDescripcion().equals("Gerencia"))//Si el puesto es distinto de Gerencia
                            && (!selectcolaborador.getPuesto().getDescripcion().equals("Jefatura")))//Y si el puesto es distinto de Jefatura pasa directo a agregar
            ) {
                try {//Se procede a actualizar el colaborador
                    System.out.println("El nombre actualizado es:" + selectcolaborador.getNombre());
                    colaboradorService.updateColaborador(selectcolaborador);//Aca se actualiza en la base de datos
                    current.executeScript("PF('dlUC').hide();");
                    current.ajax().update("form:tablaColaborador");
                    addMessage("Aviso", "Colaborador actualizado correctamente.");
                    colaboradores = colaboradorService.getAllColaboradoresActivos(colaboradorlogueado);//Se cargan los colaboradores por jefe logueado
                } catch (Exception e) {
                } finally {
                    selectcolaborador = new Colaborador();
                }
            } else {//Si se trata de agregar un jefe y esta ya esta agregado se muestra el siguiente mensaje
                addMessage("Aviso", "El departamento " + selectcolaborador.getDepartamento().getNombre() + " ya tiene un Gerente o Jefe Asignado");
                //mensaje = new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "El departamento " + selectcolaborador.getDepartamento().getNombre() +" ya tiene un Gerente o Jefe Asignado");
                System.out.println("Ya existe un JEFE PARA ESTE DEPARTAMENTO");
            }
        } else {//Si no si se puede actualizar el colaborador
            System.out.println("El nombre actualizado es:" + selectcolaborador.getNombre());
            colaboradorService.updateColaborador(selectcolaborador);//Se actualizar el colaborador en la base de datos
            current.executeScript("PF('dlUC').hide();");
            current.ajax().update("form:tablaColaborador");
            addMessage("Aviso", "Colaborador actualizado correctamente.");
            colaboradores = colaboradorService.getAllColaboradoresActivos(colaboradorlogueado);//Se cargan los colaboradores por jefe logueado
        }
    }

    public void findColaboradorMarca() throws Exception {//Funcion que valida si el colaborador se encuentra en la base de datos
        colaboradorMarca = colaboradorService.findColaborador(colaboradorMarca.getPk_idColaborador());
        PrimeFaces current = PrimeFaces.current();

        if (colaboradorMarca == null) {//Se valida si el colaborador existe
            addMessage("Aviso", "No se encuentra el colaborador");
            mensaje = "No se encuentra el colaborador";
            current.ajax().update("msj");

        } else//Si es asi se pasa a validar que tenga horario asignado
        {
            habilitarAccion();
        }
    }

    public void listaMarcasPorJornada() {//Funcion para listar las marcas que tiene el colaborador en la jornada
        marcasJornadasList = new ArrayList<MarcasJornada>();

        PrimeFaces current = PrimeFaces.current();
        MarcaLaboradas mLaborada = new MarcaLaboradas();
        mLaborada = marcaLaboradaService.buscaMarcaPorColaboradoYEstado(colaboradorMarca, "Entrada");//Saco marca de entrada

        if (mLaborada == null) {//Si no ha marcado entro aca
            //System.out.println("No ha marcado entrada");
        } else {//Lleno objeto con hora entrada y la descripcion de entrada
            if (mLaborada.getHoraEntrada() != null) {//Si solo a marcado entrada
                MarcasJornada miMJ = new MarcasJornada();
                System.out.println("ENTRO A IF SIN MARCA SALIDA");

                //Ingreso la entrada
                miMJ.setMarca(mLaborada.getHoraEntrada());//Se agrega el objeto de entrada
                miMJ.setDescripcion("Entrada");
                marcasJornadasList.add(miMJ);//Se agrega a la lista a mostrar
                miMJ = new MarcasJornada();

                List<MarcaDescansos> marcaDescansosList = marcaDescansoService.buscarMarcaPorMarcaLab(mLaborada);//Se cargan los descansos
                //Se buscan la marcas por descansos
                if (!marcaDescansosList.isEmpty()) {//Si la marca descansos no esta vacia
                    for (MarcaDescansos md : marcaDescansosList) {//Se listan las marcas de descanso que tenga
                        if (md.getHoraFin() == null) {//Si no ha finalizado el descanso
                            miMJ.setMarca(md.getHoraInicio());
                            miMJ.setDescripcion("Inicio " + md.getDescansos().getDescripcion());
                            marcasJornadasList.add(miMJ);//Solo agrega el inicio de descanso
                            miMJ = new MarcasJornada();
                        } else {//Si el descanso si finalizo es decir el campo getHoraFin tiene algo se carga
                            miMJ.setMarca(md.getHoraInicio());
                            miMJ.setDescripcion("Inicio " + md.getDescansos().getDescripcion());
                            marcasJornadasList.add(miMJ);//el inicio
                            miMJ = new MarcasJornada();

                            miMJ.setMarca(md.getHoraFin());
                            miMJ.setDescripcion("Finalizo " + md.getDescansos().getDescripcion());
                            marcasJornadasList.add(miMJ);//el fin
                            miMJ = new MarcasJornada();
                        }
                    }
                }
            }
            current.ajax().update("f1:marcaJornadaTabla");//Se actualiza la tabla
        }

    }

    public void listaMarcasPorJornadaFinalizada(MarcaLaboradas mLaborada) {//Funcion para listar marcas de jornada hasta el final
        marcasJornadasList = new ArrayList<MarcasJornada>();
        PrimeFaces current = PrimeFaces.current();
        if (mLaborada.getHoraSalida() != null) {//Si tambien marco salida
            MarcasJornada miMJ = new MarcasJornada();
            miMJ.setMarca(mLaborada.getHoraEntrada());
            miMJ.setDescripcion("Entrada");
            marcasJornadasList.add(miMJ);//Se lista la marca de entrada
            miMJ = new MarcasJornada();

            List<MarcaDescansos> marcaDescansosList = marcaDescansoService.buscarMarcaPorMarcaLab(mLaborada);//Se buscan las marcas de descansos
            if (!marcaDescansosList.isEmpty()) {//Si la marca descansos no esta vacia
                for (MarcaDescansos md : marcaDescansosList) {
                    if (md.getHoraFin() == null) {//Si no ha finalizado el descanso
                        miMJ.setMarca(md.getHoraInicio());
                        miMJ.setDescripcion("Inicio " + md.getDescansos().getDescripcion());
                        marcasJornadasList.add(miMJ);//Solo agrega el inicio de descanso
                        miMJ = new MarcasJornada();

                    } else {//Si el descanso si finalizo es decir el campo getHoraFin tiene algo se carga
                        miMJ.setMarca(md.getHoraInicio());
                        miMJ.setDescripcion("Inicio " + md.getDescansos().getDescripcion());
                        marcasJornadasList.add(miMJ);//Se agrega el inicio de descanso
                        miMJ = new MarcasJornada();

                        miMJ.setMarca(md.getHoraFin());
                        miMJ.setDescripcion("Finalizo " + md.getDescansos().getDescripcion());
                        marcasJornadasList.add(miMJ);//y el fin del descanso
                        miMJ = new MarcasJornada();
                    }
                }
            }
            miMJ.setMarca(mLaborada.getHoraSalida());
            miMJ.setDescripcion("Salida");
            marcasJornadasList.add(miMJ);//Y por ultimo se carga la marca de salida
        }
        current.ajax().update("f1:marcaJornadaTabla");//Se actualiza la tabla
    }


    void validaSalida() {//valida si el dia anterior marco la salida
        MarcaLaboradas m = marcaLaboradaService.buscaMarcaSinSalida(colaboradorMarca);
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        try{
        c1.setTime(m.getFechaMarca());
        c1.set(Calendar.HOUR_OF_DAY, 0);
        c1.set(Calendar.MINUTE, 0);
        c1.set(Calendar.SECOND, 0);
        c1.set(Calendar.MILLISECOND, 0);
        c2.set(Calendar.HOUR_OF_DAY, 0);
        c2.set(Calendar.MINUTE, 0);
        c2.set(Calendar.SECOND, 0);
        c2.set(Calendar.MILLISECOND, 0);
        System.out.println(c1.getTime());
        System.out.println(c2.getTime());
        System.out.println(c1.compareTo(c2));
        }catch (NullPointerException e){}

        if (c1.compareTo(c2)==0) {
        } else {
            try {
                // m.setHoraSalida(asignacionesServices.buscarHorario(colaboradorMarca).getHorario().getHorasalida());
                m.setEstado("Finalizado");
                marcaLaboradaService.updateMarcaLaborada(m);
            } catch (NullPointerException e) {

            }finally {
                botonSalida=true;
                botonDesSali=true;
                botonEntrada=true;
            }
        }

    }


    public void habilitarAccion()//Funcion que valida si el colaborador al mostrar el qr tiene un horario asignado
    {
        Calendar c1 = Calendar.getInstance();
        PrimeFaces current = PrimeFaces.current();
        createDirectory();
        asignaciones = asignacionesServices.buscarHorario(colaboradorMarca);//Busca si exite una asignacion con el id del colaborador
        if (asignaciones == null)//Si no tiene horario asignado se muestra el mensaje
        {
            mensaje = "El colaborador no tiene horario asignado";
            current.ajax().update("msj");

            colaboradorMarca = new Colaborador();

        } else {//Si tiene horario asignado

            //ACA SE INVOCARA LA FUNCION PARA BUSCAR LAS MARCAS DEL COLABORADOR
            listaMarcasPorJornada();
            Date date = new Date();
            java.sql.Date fechahoy = new java.sql.Date(date.getTime());
            validaSalida();
            MarcaLaboradas marcaLa = marcaLaboradaService.buscaMarcaPorColaboradoYEstado(colaboradorMarca, "Entrada");//Se busca si ya marco la entrada por medio de la fecha del dia

            if (marcaLaboradaService.validaMarcaDia(colaboradorMarca, fechahoy) != null && marcaLaboradaService.validaMarcaDia(colaboradorMarca, fechahoy).getEstado().equals("Finalizado")) {
                mensaje = "Ya realizo sus marcas del dia de hoy";
                current.ajax().update("msj");
            } else {
                if (marcaLa == null) {//si no encuentra la marca con el colaborador y el estado Entrada se habilita el boton de entrada
                    if ((!asignaciones.getDiadescanso().equals(verificaDiaLibre(c1.get(Calendar.DAY_OF_WEEK)))
                            && !asignaciones.getSegundodiades().equals(verificaDiaLibre(c1.get(Calendar.DAY_OF_WEEK))))//Si no es el primer ni el segundo dia de descanso
                            || (!asignaciones.getDiadescanso().equals(verificaDiaLibre(c1.get(Calendar.DAY_OF_WEEK)))//O si no es el primer y es el segundo dia N/A
                            && asignaciones.getSegundodiades().equals("N/A"))) {//Pero primero valida que no sea el dia libre del colaborador
                       mensaje=null;
                        current.ajax().update("msj");
                        botonEntrada = false;
                        current.ajax().update("bot");//Puede realizar la marca
                    } else {
                        //Si el dia de hoy y el dia de descanso son iguales se muestra el mensaje de que es dia libre
                        mensaje = colaboradorMarca.getNombre() + " es su dia libre";
                        current.ajax().update("msj");
                        botonEntrada = true;
                        current.ajax().update("bot:ent");//Se vuelven a bloquear los botones
                        colaboradorMarca = new Colaborador();
                        current.ajax().update("info:nom");// se limpia el nombre
                        current.ajax().update("info:ced");
                    }
                } else {//Si la encuentra se habilita el boton de salida y descanso por que quiere decir que ya habia marcado entrada
                    botonSalida = false;
                    validacionMarcaDes();
                    current.ajax().update("bot:sali");
                }
            }
        }
    }

    public String verificaDiaLibre(int a)//Funcion que verifica segun el dia por numero el dia en string
    {
        String day = null;
        switch (a) {//Se hace un cambio de formato segun el numero del dia a letras
            case 1:
                day = "DO";
                break;
            case 2:
                day = "LU";
                break;
            case 3:
                day = "MA";
                break;
            case 4:
                day = "MI";
                break;
            case 5:
                day = "JU";
                break;
            case 6:
                day = "VI";
                break;
            case 7:
                day = "SA";
                break;
        }
        return day;
    }

    public void marcaEntrada() {//funcion para el boton marcar Entrada para guardar marca en la base de datos
        PrimeFaces current2 = PrimeFaces.current();
        Calendar c1 = Calendar.getInstance();
        c2 = Calendar.getInstance();
        c1.setTime(asignaciones.getHorario().getHoraentrada());
        c1.set(YEAR, c2.get(YEAR));
        c1.set(Calendar.MONTH, c2.get(Calendar.MONTH));
        c1.set(Calendar.DAY_OF_MONTH, c2.get(Calendar.DAY_OF_MONTH));//se carga la fecha actual
        if (c1.compareTo(c2) == 1) //Valida si es antes de entrada el colaborador llego antes
        {
            long resultado = (Math.abs(c1.getTimeInMillis() - c2.getTimeInMillis()) / (1000 * 60));
            if (resultado <= 15) {//Valida si ya puede marcar se dan 15 minutos de marca antes
                mensaje = "Marca de la entrada";
                current2.ajax().update("msj");
                marcaEn();//Aca se llama la funcion para realizar la marca en la base de datos
            } else {//Si no se le avisa que aun no puede marcar por que es muy temprano
                mensaje = "Es muy temprano, no le correponde realizar la marca";
                current2.ajax().update("msj");
                botonEntrada = true;
                current2.ajax().update("bot:ent");//Se vuelven a bloquear los botones
                colaboradorMarca = new Colaborador();
                current2.ajax().update("info:nom");// se limpia el nombre
                current2.ajax().update("info:ced");
            }
        } else //  despues de entrada llego tarde el colaborador
        {
            Calendar c3 = c1;
            c3.add(Calendar.HOUR, 1);
            long resultado = (Math.abs(c3.getTimeInMillis() - c2.getTimeInMillis()) / (1000 * 60));
            if (resultado <= 60) {//Aca se esta dando un tiempo de colchon para realizar la marca tarde despues del tiempo de un 1 de la hora de entrada segun horario
                mensaje = "Marca tarde";//Como es tarde pero todavia es valido que marque puede justificar su tardia
                current2.ajax().update("msj");
                current2.executeScript("PF('just').show();");//Se despliega form para justifica llegada tardia
            } else {//Si es demasiado tarde se muestra el mensaje de que ya no puede marca
                mensaje = "Demasiado tarde, no puede realizar marca, tiempo limite agotado";
                current2.ajax().update("msj");
                botonEntrada = true;
                current2.ajax().update("bot:ent");//Se vuelven a bloquear los botones
                colaboradorMarca = new Colaborador();
                current2.ajax().update("info:nom");// se limpia el nombre
                current2.ajax().update("info:ced");
            }
        }
    }


    public void diasDisponibles(Colaborador colaborador) throws ParseException {//Funcion para calcular los dias disponible de vacaciones

        VacacionesPorColaborador disponibles = new VacacionesPorColaborador();
        disponibles = vacacionesPorColaboradorService.findVacacionesPorColaborador(colaborador);//Se consulta los dia disponibles
        int anios = calculaAnios(colaborador);//Se consulta los years que este laborando el cola en la empresa
        //int meses = 0;
        int diasDisf = disponibles.getDiasdisfrutados();
        double diasLibresTotales = 0.0;

        if (anios != 0) {//Si ha trabajado un anio ya se le pueden contar los dias libres
            for (int i = 1; i <= anios; i++) {
                //if (i == 1 || i == 2) {
                if (i == 2) {
                    diasLibresTotales = diasLibresTotales + (1 * 12);
                } else if (i == 3 || i == 4) {
                    diasLibresTotales = diasLibresTotales + (1.25 * 12);
                } else if (i >= 5) {
                    diasLibresTotales = diasLibresTotales + (1.50 * 12);
                }
            }
            //PrimeFaces current2 = PrimeFaces.current();
            diasLibresTotales = (diasLibresTotales + CalculaDiasMesesExtra(colaborador)) - diasDisf;
            System.out.println("Dias totales disponibles: " + diasLibresTotales);

            disponibles.setDiasdisponibles((int) diasLibresTotales);
            vacacionesPorColaboradorService.updateVacacionesPorColaborador(disponibles);

            /*if (disponibles == null) {
                //vacaciones.setDiasdisponibles(0);
                vacacionesService.createVacaciones(vacaciones);
                vacaciones = new Vacaciones();
                asignaDiasVacaciones(anios);
            } else {
                asignaDiasVacaciones(anios);
                vacaciones = new Vacaciones();
            }*/
        }
    }

    public int CalculaDiasMesesExtra(Colaborador colaborador) throws ParseException {
        int anio = calculaAnios(colaborador);
        double dia = 1;
        double cantidadDiasMesesExtra = 0.0;
        int mesesExtra = 0;

        Calendar c1 = Calendar.getInstance();
        c1.setTime(colaborador.getFechaInicioLaboral());
        c2 = Calendar.getInstance();//FechaActual
        if (anio != 0) {
            if (anio == 1 || anio == 2) {
                dia = 1;
            } else if (anio == 3 || anio == 4) {
                dia = 1.25;
            } else if (anio >= 5) {
                dia = 1.50;
            }

            if((c1.get(Calendar.MONTH)+1) == (c2.get(Calendar.MONTH)+1)){//Si no ha pasado el mes de entrada laboral
                mesesExtra = 0;
            }else if((c1.get(Calendar.MONTH)+1) > (c2.get(Calendar.MONTH)+1)){//Si paso el mes de entrada laboral ya hay mese extra
                mesesExtra = (12-(c1.get(Calendar.MONTH)+1)) + (c2.get(Calendar.MONTH)+1);//Bien creo
                System.out.println("Meses extra: " + mesesExtra);
            }/*else if((c1.get(Calendar.MONTH)+1) < (c2.get(Calendar.MONTH)+1)){
                mesesExtra = (c2.get(Calendar.MONTH)+1);// + (c1.get(Calendar.MONTH)+1);
                System.out.println("Meses extra: " + mesesExtra);
            }*/
            cantidadDiasMesesExtra = mesesExtra * dia;
        }

        return (int) cantidadDiasMesesExtra;
    }

    public int calculaAnios(Colaborador colaborador) throws ParseException {//Funcion para calcular la cantidad de year que lleva el colaborador laborando
 /*       Calendar c1 = Calendar.getInstance();
        c2 = Calendar.getInstance();
        c1.setTime(colaborador.getFechaInicioLaboral());
        vacaciones.setColaborador(colaborador);
        int anios = c2.get(YEAR) - c1.get(YEAR);
        if (c1.get(Calendar.MONTH + 1) > c2.get(Calendar.MONTH) || (c1.get(Calendar.MONTH + 1) == c2.get(Calendar.MONTH)
                        && c1.get(Calendar.DATE) > c2.get(Calendar.DATE))) {
            anios--;
        }//funcion para calcular los annos de trabajo en la empresa
 */
        vacacionesPorColaborador = vacacionesPorColaboradorService.findVacacionesPorColaborador(colaborador);
        Calendar cFechaDeUltimoCalculo = Calendar.getInstance();
        //cFechaDeUltimoCalculo.setTime(vacacionesPorColaborador.getFechaasignada());
        cFechaDeUltimoCalculo.setTime(colaborador.getFechaInicioLaboral());
        c2 = Calendar.getInstance();//FechaActual
        int anios = c2.get(YEAR) - cFechaDeUltimoCalculo.get(YEAR);

        if (cFechaDeUltimoCalculo.get(Calendar.MONTH) + 1 >= c2.get(Calendar.MONTH) + 1) {//Si no se a pasado o cumplido la fecha de actualizacion no se suma anio
            if ((cFechaDeUltimoCalculo.get(Calendar.MONTH) + 1 > c2.get(Calendar.MONTH) + 1)
                    || (cFechaDeUltimoCalculo.get(Calendar.MONTH) + 1 == c2.get(Calendar.MONTH) + 1
                    && cFechaDeUltimoCalculo.get(Calendar.DATE) > c2.get(Calendar.DATE))) {
                anios--;
            }
        }
        System.out.println("Cantidad de anios laborador: " + anios);

        return anios;
    }


    public void asignaDiasVacaciones(int anios) {//Funcion que calcula la cantidad de dias disponibles segun los anio que lleva laborando

        if (anios == 1 || anios == 2 || anios == 3) {//dependiendo de la cantidad de anios se le asigna los dias de vacaciones
            //vacaciones.setDiasdisponibles(12);
            vacacionesService.updateVacaciones(vacaciones);
        } else if (anios == 3 || anios == 4 || anios == 5) {
            //vacaciones.setDiasdisponibles(15);
            vacacionesService.updateVacaciones(vacaciones);
        } else if (anios > 5) {
            //vacaciones.setDiasdisponibles(18);
            vacacionesService.updateVacaciones(vacaciones);
        }
    }

    public void reset() {//Funcion para limpiar botones , tabla y forms de la pantalla de inicio
        PrimeFaces current = PrimeFaces.current();
        mensaje = "";
        colaboradorMarca = new Colaborador();
        marcasJornadasList = new ArrayList<>();
        asignacionDescansos = new AsignacionDescansos();
        marcaLa = new MarcaLaboradas();
        botonEntrada = true;
        botonSalida = true;
        botonDesSali = true;
        current.ajax().update("bot:ent");//Se desabilita el boton de descanso y el de salida
        current.ajax().update("bot:des");//Se desabilita el boton de descanso y el de salida
        current.ajax().update("bot:sali");
        current.ajax().update("info:nom");
        current.ajax().update("info:ced");
        current.ajax().update("msj");
        current.ajax().update("f1:marcaJornadaTabla");

    }

    public void marcaEn()//Funcion para realizar marca en la base de datos
    {
        PrimeFaces current = PrimeFaces.current();
        //Se prepara el objeto de marca
        marcaLaboradas.setColaborador(colaboradorMarca);//Se carga el colaborador que realiza la marca
        Date date = new Date();//Se saca fecha actual
        Time time = new Time(c2.get(Calendar.HOUR_OF_DAY), c2.get(Calendar.MINUTE), c2.get(Calendar.SECOND));//se carga la hora, min, seg en que se realizo la marca
        marcaLaboradas.setHoraEntrada(time);//se guarda el tiempo de la marca
        marcaLaboradas.setJustSalidaTemprana("N/A");
        marcaLaboradas.setJustTiempoExtra("N/A");
        marcaLaboradas.setEstado("Entrada");//Para saber que es una marca de entrada a la hora de marcar la salida
        marcaLaboradas.setFechaMarca(date);//se carga la fecha de la marca que es la fecha del sistema
        marcaLaboradaService.createMarcaLaborada(marcaLaboradas);//y por ultimo se agrega la marca de entrada a la base de datos
        listaMarcasPorJornada();
        saveImage();
        marcaLaboradas = new MarcaLaboradas();
        colaboradorMarca = new Colaborador();
        botonEntrada = true;
        current.ajax().update("bot:ent");//Se vuelven a bloquear los botones
        current.ajax().update("info:ced");//y cedula del colaborador que marca

    }

    public void marcaTarde()//funcion que se dispara con el boton de el formulario just
    {
        PrimeFaces current = PrimeFaces.current();
        marcaEn();//se marca en la base de datos la entrada aunque sea tarde
        current.executeScript("PF('just').hide();");// y se enconde el form
        mensaje = "Marca realizada con exito";
        current.ajax().update("msj");
    }

    public void marcaSalida() {//funcion para el boton marcar Salida
        Calendar c1 = Calendar.getInstance();
        c2 = Calendar.getInstance();
        c1.setTime(asignaciones.getHorario().getHorasalida());//c1 se convierte en horaSalida
        c1.set(YEAR, c2.get(YEAR));
        c1.set(Calendar.MONTH, c2.get(Calendar.MONTH));
        c1.set(Calendar.DAY_OF_MONTH, c2.get(Calendar.DAY_OF_MONTH));//se carga la fecha actual
        PrimeFaces current = PrimeFaces.current();
        marcaLaboradas = marcaLaboradaService.buscaMarcaPorColaboradoYEstado(colaboradorMarca, "Entrada");//Saco la marca de entrada
        Date fechaDeMarcaEntrada = new Date();
        fechaDeMarcaEntrada = marcaLaboradas.getFechaMarca();
        String timeStampFMS = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String timeStampFME = new SimpleDateFormat("yyyyMMdd").format(fechaDeMarcaEntrada);
        int fechaME = Integer.parseInt(timeStampFME);//Paso la fecha de marca de entrada a int
        int fechaMS = Integer.parseInt(timeStampFMS);//Paso la fecha de marca de salida a int
        int idH = asignaciones.getHorario().getPk_idhorario();
        if (idH == 11) {
            if ((c1.before(c2) && fechaME == fechaMS) || (c1.after(c2) && fechaME <= fechaMS)) //  antes de salida madrugo el colaborador para salir c1.compareTo(c2) < 0
            {
                if (c1.get(Calendar.HOUR_OF_DAY) == 0 && c1.get(Calendar.HOUR_OF_DAY) == 0 && c1.get(Calendar.HOUR_OF_DAY) == 0) {
                    c1.set(Calendar.HOUR_OF_DAY, 24);
                    c1.set(Calendar.MINUTE, 00);
                    c1.set(Calendar.SECOND, 00);
                }

                long resultado = (Math.abs(c1.getTimeInMillis() - c2.getTimeInMillis()) / (1000 * 60));
                if (resultado <= 15) {
                    mensaje = "Marca de la salida";
                    current.ajax().update("msj");
                    marcaSal();
                } else {//Si no es que salio demasiado mas temprano por ello debe justificar
                    mensaje = "Es antes de su hora de salida, justifique ";
                    current.ajax().update("msj");
                    current.executeScript("PF('just2').show();");
                }
            } else if (fechaME < fechaMS)// salio tarde el colaborador hizo mas tiempo
            {
                Calendar c3 = c1;
                long resultado = (Math.abs(c3.getTimeInMillis() - c2.getTimeInMillis()) / (1000 * 60));
                if (resultado >= 30) {//Aca esta dando que si esta saliendo 30 minutos o mas tarde de la hora de salida pida justificacion de tiempo extra
                    mensaje = "Marco salida mas tarde según su horario realizo Tiempo Extra";
                    current.ajax().update("msj");
                    current.executeScript("PF('just3').show();");//Se despliega form de que justifique que realizo tiempo extra
                } else {//Si no es mayor o igual el tiempo de media hora extra se depliega el siguiente mensaje
                    mensaje = "Marco salida mas tarde, sin embargo no se toma como Tiempo Extra";
                    current.ajax().update("msj");
                    marcaSal();//Aca se marca la salida en la base
                }
            }
        } else if (idH == 12 || idH == 14) {//Validacion de salida para horarios de dos dias
            System.out.println("Entro al if para horarios de DOS DIAS");
            if ((c1.before(c2) && fechaME == fechaMS) || (c1.after(c2) && fechaME == fechaMS)) //  antes de salida madrugo el colaborador para salir c1.compareTo(c2) < 0
            {
                long resultado = (Math.abs(c1.getTimeInMillis() - c2.getTimeInMillis()) / (1000 * 60));
                if (resultado <= 15 && fechaME < fechaMS) {//Valida si ya puede marcar la salida a la hora establecida por horario
                    mensaje = "Marca de la salida";
                    current.ajax().update("msj");
                    marcaSal();
                } else {//Si no es que salio demasiado mas temprano por ello debe justificar

                    mensaje = "Es antes de su hora de salida, justifique";
                    current.ajax().update("msj");
                    current.executeScript("PF('just2').show();");//Se despliega form para que justifique por que sale antes
                }
            } else if (c1.after(c2) || fechaME < fechaMS)// salio tarde el colaborador hizo mas tiempo champion
            {
                Calendar c3 = c1;
                long resultado = (Math.abs(c3.getTimeInMillis() - c2.getTimeInMillis()) / (1000 * 60));
                if (resultado >= 30) {//Aca esta dando que si esta saliendo 30 minutos o mas tarde de la hora de salida pida justificacion de tiempo extra
                    mensaje = "Marcó salida más tarde, según su horario realizó Tiempo Extra";
                    current.ajax().update("msj");
                    current.executeScript("PF('just3').show();");//Se despliega el dialogo con el campo para que justifique por que hizo Tiempo extra
                } else {//Si no es mayor o igual el tiempo de media hora extra se depliega el siguiente mensaje
                    mensaje = "Marcó salida más tarde, sin embargo, no se toma como Tiempo Extra";
                    current.ajax().update("msj");
                    marcaSal();//Se marca sin justificar tiempo extra
                }
            }
        } else {
            System.out.println("Entro al if para horarios de un Dia");
            if (c1.compareTo(c2) == 1 && fechaME >= fechaMS) //antes de salida madrugo el colaborador para salir
            {
                long resultado = (Math.abs(c1.getTimeInMillis() - c2.getTimeInMillis()) / (1000 * 60));
                if (resultado <= 15) {//Valida si ya puede marcar la salida a la hora establecida por horario
                    mensaje = "Se realiza la marca de salida";
                    current.ajax().update("msj");
                    marcaSal();//Aca se llama la funcion para realizar la marca de salida en la base de datos
                } else {//Si no es que salio demasiado mas temprano por ello debe justificar
                    mensaje = "Es antes de su hora de salida, Justifique";
                    current.ajax().update("msj");
                    current.executeScript("PF('just2').show();");
                }
            } else // salio tarde el colaborador hizo mas tiempo champion
            {
                Calendar c3 = c1;
                long resultado = (Math.abs(c3.getTimeInMillis() - c2.getTimeInMillis()) / (1000 * 60));
                if (resultado >= 30 || fechaME < fechaMS) {//Aca esta dando que si esta saliendo 30 minutos o mas tarde de la hora de salida pida justificacion de tiempo extra
                    mensaje = "Marcó salida más tarde según su horario realizo Tiempo Extra";
                    current.ajax().update("msj");
                    current.executeScript("PF('just3').show();");//Se despliega el dialogo con el campo para que justifique por que hizo Tiempo extra
                } else {//Si no es mayor o igual el tiempo de media hora extra se depliega el siguiente mensaje
                    mensaje = "Marcó salida más tarde, sin embargo no se toma como Tiempo Extra";
                    current.ajax().update("msj");
                    marcaSal();
                }
            }
        }
        marcaLaboradas = new MarcaLaboradas();
    }


    public void marcaSal()//Funcion para realizar marca de salida en la base de datos
    {
        PrimeFaces current = PrimeFaces.current();
        //Se prepara el objeto de marcalaboradas de salida
        marcaLaboradas = marcaLaboradaService.buscaMarcaPorColaboradoYEstado(colaboradorMarca, "Entrada");//Se busca la marca de entrada que tenia el colaborador por el estado
        Time time = new Time(c2.get(Calendar.HOUR_OF_DAY), c2.get(Calendar.MINUTE), c2.get(Calendar.SECOND));//se carga la hora, min, seg en que se realizo la marca de salida
        marcaLaboradas.setHoraSalida(time);//se guarda el tiempo de la marca
        marcaLaboradas.setJustSalidaTemprana(justST);
        marcaLaboradas.setJustTiempoExtra(justTE);
        marcaLaboradas.setEstado("Finalizado");
        marcaLaboradaService.updateMarcaLaborada(marcaLaboradas);//y por ultimo se agrega la marca de entrada a la base de datos

        listaMarcasPorJornadaFinalizada(marcaLaboradas);
        saveImage();
        marcasJornadasList = new ArrayList<>();
        marcaLaboradas = new MarcaLaboradas();
        colaboradorMarca = new Colaborador();
        botonSalida = true;
        justST = null;
        justTE = null;
        current.ajax().update("bot:des");//Se desabilita el boton de descanso y el de salida
        current.ajax().update("bot:sali");
        current.ajax().update("info:nom");// se limpia el nombre
        current.ajax().update("info:ced");//y cedula del colaborador que marca
        current.ajax().update("f1:marcaJornadaTabla");
    }

    public void marcaSalidaAntes()//funcion que se dispara con el boton de el formulario just2
    {
        PrimeFaces current = PrimeFaces.current();
        marcaSal();//se marca en la base de datos la entrada aunque sea tarde
        current.executeScript("PF('just2').hide();");//y se enconde el form
        mensaje = "Marca salida prematura realizada con exito";
        current.ajax().update("msj");
        justST = null;
    }


    public void marcaTiempoExtra()//funcion que se dispara con el boton de el formulario just3
    {
        PrimeFaces current = PrimeFaces.current();
        marcaSal();//se marca en la base de datos la entrada aunque sea tarde
        current.executeScript("PF('just3').hide();");// y se enconde el form
        mensaje = "Marca de tiempo extra realizada con exito";
        current.ajax().update("msj");
        justTE = null;
    }

    public void find() throws Exception {
        String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("ColaboradorIdBusqueda");
        colaboradores.clear();
        colaboradores.add(colaboradorService.findColaborador(id));
    }

    public void close() {
        colaborador = new Colaborador();
    }

    public void close2() {
        selectcolaborador = new Colaborador();
        colaboradores = colaboradorService.getAllColaboradoresActivos(colaboradorlogueado);
    }

    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void marcaIniDes() {//Funcion para marcar en la base el inicio de un descanso
        MarcaLaboradas marcaLa = marcaLaboradaService.buscaMarcaPorColaboradoYEstado(colaboradorMarca, "Entrada");
        MarcaDescansos marcaDescansos = new MarcaDescansos();
        marcaDescansos.setColaborador(colaboradorMarca);
        marcaDescansos.setMarcaLaboradas(marcaLa);
        marcaDescansos.setDescansos(asignacionDescansos.getDescanso());
        Time time = new Time(now.get(Calendar.HOUR_OF_DAY), now.get(Calendar.MINUTE), now.get(Calendar.SECOND));
        marcaDescansos.setHoraInicio(time);
        marcaDescansoService.crearMarcaDescanso(marcaDescansos);
        saveImage();//Funcion para guardar imagen
        listaMarcasPorJornada();
    }

    public void marcaFindes() {//Funcion para marcar en la base el fin de descanso
        MarcaDescansos marcaDescansos = marcaDescansoService.buscarMdescanso(asignacionDescansos.getDescanso(), marcaLa);
        Time time = new Time(now.get(Calendar.HOUR_OF_DAY), now.get(Calendar.MINUTE), now.get(Calendar.SECOND));
        marcaDescansos.setHoraFin(time);
        marcaDescansoService.updateMarcaDescanso(marcaDescansos);
        saveImage();//Funcion para guardar imagen
        listaMarcasPorJornada();
    }

    public void validacionMarcaDes() {//Funcion para validar si puede marca descanso inicio y fin

        List<AsignacionDescansos> asignacionesList = asignacionDescansosService.buscarDescansosAsignadosPorColaborador(colaboradorMarca);//numero de descansos que tiene el colaborador
        marcaLa = marcaLaboradaService.buscaMarcaPorColaboradoYEstado(colaboradorMarca, "Entrada");//para sacar el id de la marca que esta asociada a la marca de descanso por dia
        List<MarcaDescansos> marcaDescansosList = marcaDescansoService.buscarMarcaPorMarcaLab(marcaLa);
        PrimeFaces current = PrimeFaces.current();
        boolean encontrado = false;
        Calendar calendar = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        now = Calendar.getInstance();
        for (AsignacionDescansos asignacionDescansos1 : asignacionesList) {
            String time = asignacionDescansos1.getDescanso().getHorainicio().toString();
            String[] timeArrayInicio = time.split(":");
            String time2 = asignacionDescansos1.getDescanso().getHorafinalizacion().toString();
            String[] timeArrayFin = time2.split(":");
            if (timeArrayFin[0].equals("01")) {
                timeArrayFin[0] = "24";
                timeArrayFin[1] = "59";
            }
            calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeArrayInicio[0]));
            calendar.set(Calendar.MINUTE, Integer.parseInt(timeArrayInicio[1]));
            calendar.set(Calendar.SECOND, Integer.parseInt(timeArrayInicio[2]));
            calendar2.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeArrayFin[0]));
            calendar2.set(Calendar.MINUTE, Integer.parseInt(timeArrayFin[1]));
            calendar2.set(Calendar.SECOND, Integer.parseInt(timeArrayFin[2]));

            if (now.get(Calendar.HOUR_OF_DAY) == 0) {
                now.set(Calendar.HOUR_OF_DAY, 24);
            }


            long resultado = (Math.abs(calendar2.getTimeInMillis() - now.getTimeInMillis()) / (1000 * 60));
            if (((now.compareTo(calendar) >= 0) && (now.compareTo(calendar2) <= 0)) || resultado <= 15) {
                asignacionDescansos = asignacionDescansos1;
                if (marcaDescansoService.buscarMdescanso(asignacionDescansos1.getDescanso(), marcaLa) == null) {
                    botonDesSali = false;
                    variable = "Inicio Descanso";
                } else if (marcaDescansoService.buscarMdescanso(asignacionDescansos1.getDescanso(), marcaLa).getHoraFin() == null) //hacer debug con lo que agrego pablo ya que no pasa de aqui
                {
                    botonDesSali = false;
                    variable = "Fin Descanso";
                }
                encontrado = true;
                current.ajax().update("bot");
                break;
            }
        }
        if (encontrado == false) {
            mensaje = "No puede marcar descanso";
            current.ajax().update("msj");

        }
    }

    public void marcaDescanso() {//Funcion para manejo de botones en el front end
        if (variable.equals("Inicio Descanso")) {
            PrimeFaces current = PrimeFaces.current();
            mensaje = "Marca descanso";
            current.ajax().update("msj");
            marcaIniDes();//Se llama la funcion para marcar inicio
            reset();
        } else {
            marcaFindes();//Se marca fin de descanso
            reset();
            PrimeFaces current = PrimeFaces.current();
            mensaje = "Marca fin del descanso";
            current.ajax().update("msj");

        }
    }


    public void resetMsj() {
        PrimeFaces current = PrimeFaces.current();
        mensaje = "";
        marcasJornadasList = new ArrayList<>();
        current.ajax().update("f1:marcaJornadaTabla");
        current.ajax().update("msj");
        current.ajax().update("info:nom");// se limpia el nombre
    }

    public void createDirectory() {//Funcion para crear direcctorio para guarda la imagen
        Calendar can = Calendar.getInstance();
        String date = "" + (can.get(Calendar.MONTH) + 1) + "-" + can.get(Calendar.DATE) + "-" + can.get(YEAR);
        File file = new File("C:\\" + date);
        if (!file.exists()) {
            file.mkdir();//Aca se crea el dir
        }
    }

    public void saveImage() {//Funcion para tomar la foto

        String[] strings = base64Image.split(",");
        String extension;
        switch (strings[0]) {//check image's extension
            case "data:image/jpeg;base64":
                extension = "jpeg";
                break;
            case "data:image/png;base64":
                extension = "png";
                break;
            default://should write cases for more images types
                extension = "jpg";
                break;
        }
        Calendar can = Calendar.getInstance();
        String date = "" + (can.get(Calendar.MONTH) + 1) + "-" + can.get(Calendar.DATE) + "-" + can.get(YEAR);
        //Se le da el nombre de el dia y la hora al archivo de imagen que se guarda
        //convert base64 string to binary data
        byte[] data = DatatypeConverter.parseBase64Binary(strings[1]);
        String path = "C:\\" + date + "\\" + colaboradorMarca.getPk_idColaborador() + "_" + can.get(Calendar.HOUR) + "_" + can.get(Calendar.MINUTE) + "_" + can.get(Calendar.SECOND) + "." + extension;
        File file = new File(path);
        try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file))) {
            outputStream.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
