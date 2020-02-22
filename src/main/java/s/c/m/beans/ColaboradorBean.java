package s.c.m.beans;


import org.omg.CORBA.NO_IMPLEMENT;
import org.omg.CORBA.PUBLIC_MEMBER;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.util.Calendar.YEAR;


@ManagedBean
@Scope("session")
public class ColaboradorBean {
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
    private String base64Image="";
    private MarcaLaboradas marcaLa = new MarcaLaboradas();

    private Vacaciones vacaciones = new Vacaciones();
    private Vacaciones vacaciones1 = new Vacaciones();


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

    private MarcaLaboradas marcaLaboradas = new MarcaLaboradas();
    public boolean botonEntrada = true;
    public boolean botonSalida = true;
    public boolean botonDesSali = true;


    @PostConstruct
    public String init() {
        Colaborador miC = new Colaborador();
        return "colaboradorList.xhtml";
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

    public boolean validaVence(Date f) {
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

        System.out.println("El mes de vencimiento es: " + mesV);
        System.out.println("El dia de vencimiento es: " + diaV);
        System.out.println("El anio de vencimiento es:" + yearV);

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
        fechaVenceString = yearV + "" + mesV2 + "" + diaV2;
        fechaV = Integer.parseInt(fechaVenceString);
        //System.out.println("FECHA DE VENCIMIENTO"+fechaV);

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
        fechaActualString = yearA + "" + mesA2 + "" + diaA2;
        fechaA = Integer.parseInt(fechaActualString);
        //System.out.println("FECHA ACTUAL:"+fechaA);

        if (fechaA >= fechaV) {
            vencio = true;
            System.out.println("AL USUARIO SE LE VENCIÓ LA CONTRASEÑA");
            addMessage("Aviso", "La contraseña venció, ¡debe cambiarla!");
        } else {
            vencio = false;
            System.out.println("AL USUARIO SE LE SIGUE VIGENTE LA CONTRASEÑA");
        }
        return vencio;//Si es true quiere decir que vencio
    }

    public void construyeMenuDinamico(String rol, String nombreDept) {
        model = generaMenu.construyeMenuPorRol(rol, nombreDept);
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
                    && colaborador1.getPuesto().getDescripcion().equals("Gerencia")
                    && colaborador1.getDepartamento().getNombre().equals("Recursos Humanos")) {
                colaboradorlogueado.setNombre(colaborador1.getNombre());
                colaboradorlogueado.setPuesto(colaborador1.getPuesto());
                colaboradorlogueado.setDepartamento(colaborador1.getDepartamento());
                diasDisponibles(colaborador1);
                vacaciones1 = vacacionesService.diasDisponibles(colaborador1);

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
                diasDisponibles(colaborador1);
                vacaciones1 = vacacionesService.diasDisponibles(colaborador1);

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
                diasDisponibles(colaborador1);
                vacaciones1 = vacacionesService.diasDisponibles(colaborador1);

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
                    || colaborador1.getPuesto().getDescripcion().equals("Jefatura")
                    || colaborador1.getPuesto().getDescripcion().equals("Supervisor")
                    || colaborador1.getPuesto().getDescripcion().equals("Analista"))) {
                colaboradorlogueado.setNombre(colaborador1.getNombre());
                colaboradorlogueado.setPuesto(colaborador1.getPuesto());
                colaboradorlogueado.setDepartamento(colaborador1.getDepartamento());
                diasDisponibles(colaborador1);
                vacaciones1 = vacacionesService.diasDisponibles(colaborador1);

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
        } else {
            addMessage("Aviso", "El usuario ingresado no se encuetra registrado en el sistema");
            return null;
        }
    }


    public boolean validaClave() {
        boolean diferente = false;
        if (colaborador1.getClave().equals(colaboradorlogueado.getClave())) {//Si son iguales retorna false por ende no puede cambiar la clave
            FacesContext.getCurrentInstance().addMessage("contraseñaCCC",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "La nueva clave no puede ser igual a la anterior", "La nueva clave no puede ser igual a la anterior"));

            diferente = false;
        } else {
            diferente = true;
        }
        return diferente;//Si es true quiere decir que son diferentes por lo tanto pueden hacer el cambio
    }


    public boolean validarContrasena(String usuario, String contrasena) {
        for (int i = 0; (i + 2) < usuario.length(); i++)
            if (contrasena.indexOf(usuario.substring(i, i + 3)) != -1) {
                return false;
            }
        return true;
    }

    public String cambioClave() throws Exception {

        if (validaClave()) {//Si validaClave retorna true se puede cambiar la clave
            if (validarContrasena(colaborador1.getNombre(), colaboradorlogueado.getClave()) == true) {

                colaborador1.setClave(colaboradorlogueado.getClave());
                System.out.println("NUEVA CLAVE:" + colaborador1.getClave());
                colaboradorService.actualizaClave(colaborador1);//Aca le paso el colaborador ya con la nueva clave para que en el service con esta funcion lo updatee en la base con la nueva clave
                Colaborador c = colaboradorService.findColaborador(colaborador1.getPk_idColaborador());
                System.out.println("La nueva clave es" + c.getClave());
                return doLogin();
            } else {
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

    public String doLogout() {
        loggedIn = false;

        FacesMessage msg = new FacesMessage("Salió", "Ha salido con éxito");
        msg.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return "/login.xhtml?faces-redirect=true";
    }


    public void create() {
        PrimeFaces current = PrimeFaces.current();
        FacesMessage mensaje = null;
        boolean existeColaborador = false;

        for (Colaborador c : colaboradores) {
            if (colaborador.getPk_idColaborador().equals(c.getPk_idColaborador())) {
                existeColaborador = true;
                break;
            } else {
                existeColaborador = false;
            }
        }
        if (!existeColaborador) {
            if (((colaboradorService.findColaboradorEncargado(colaborador.getDepartamento(), puestoService.findIdPuesto("Gerencia")) == null)
                    && //Si no encuetra un gerente Gerencia
                    (colaboradorService.findColaboradorEncargado(colaborador.getDepartamento(), puestoService.findIdPuesto("Jefatura")) == null))//Y si no encuentra un jefe pasa directo a agregar Jefatura
                    ||
                    ((!colaborador.getPuesto().getDescripcion().equals("Gerencia"))//Si el puesto es distinto de Gerencia
                            &&
                            (!colaborador.getPuesto().getDescripcion().equals("Jefatura")))//Y si el puesto es distinto de Jefatura pasa directo a agregar
            ) {
                try {
                    System.out.println("No existe el colaborador");
                    colaboradorService.createColaborador(colaborador);
                    current.executeScript("PF('dlAC').hide();");
                    current.ajax().update("form:tablaColaborador");
                    mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Colaborador insertado correctamente.");
                    colaboradores = colaboradorService.getAllColaboradoresActivos(colaboradorlogueado);
                } catch (Exception e) {
                } finally {
                    colaborador = new Colaborador();
                }
            } else {
                mensaje = new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "El departamento " + colaborador.getDepartamento().getNombre() + " ya tiene un Gerente o Jefe Asignado");
                System.out.println("Ya existe un JEFE PARA ESTE DEPARTAMENTO");
            }
        } else if (existeColaborador) {
            System.out.println("Si existe el colaborador con esa cédula");
            mensaje = new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Ya existe un colaborador con esa cédula, pruebe nuevamente.");
            colaborador = new Colaborador();
        }
        FacesContext.getCurrentInstance().addMessage(null, mensaje); //CON MI VALIDACION DE QUE NO SE ASIGNE DOS JEFES A UN DEPT , SE ME CAE POR ESTA LINEA NO SE NI LO QUE HACE
        PrimeFaces.current().ajax().addCallbackParam("existeColaborador", existeColaborador);
    }

    public void checkDepartamento() {
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

            current.executeScript("PF('dlUC').show();"); //si no esta vacio muestra el dialogo
        }
    }

    public void showconfirm() {
        PrimeFaces current = PrimeFaces.current();

        if (selectcolaborador == null) {
            addMessage("Aviso", "Debe Seleccionar un Colaborador."); //si esta vacio muetra este mensaje
        } else {
            current.executeScript("PF('dlEC').show();"); //si no esta vacio muestra el dialogo
        }

    }

    public void delete() {
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
                try {
                    System.out.println("El nombre actualizado es:" + selectcolaborador.getNombre());
                    colaboradorService.updateColaborador(selectcolaborador);
                    current.executeScript("PF('dlUC').hide();");
                    current.ajax().update("form:tablaColaborador");
                    addMessage("Aviso", "Colaborador actualizado correctamente.");
                    colaboradores = colaboradorService.getAllColaboradoresActivos(colaboradorlogueado);
                } catch (Exception e) {
                } finally {
                    selectcolaborador = new Colaborador();
                }
            } else {
                addMessage("Aviso", "El departamento " + selectcolaborador.getDepartamento().getNombre() + " ya tiene un Gerente o Jefe Asignado");
                //mensaje = new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "El departamento " + selectcolaborador.getDepartamento().getNombre() +" ya tiene un Gerente o Jefe Asignado");
                System.out.println("Ya existe un JEFE PARA ESTE DEPARTAMENTO");
            }
        } else {
            System.out.println("El nombre actualizado es:" + selectcolaborador.getNombre());
            colaboradorService.updateColaborador(selectcolaborador);
            current.executeScript("PF('dlUC').hide();");
            current.ajax().update("form:tablaColaborador");
            addMessage("Aviso", "Colaborador actualizado correctamente.");
            colaboradores = colaboradorService.getAllColaboradoresActivos(colaboradorlogueado);
        }
    }

    public void findColaboradorMarca() throws Exception {//Funcion que valida si el colaborador se encuentra en la base de datos
        colaboradorMarca = colaboradorService.findColaborador(colaboradorMarca.getPk_idColaborador());

        if (colaboradorMarca == null) {
            addMessage("Aviso", "No se encuentra el colaborador");
        } else//Si es asi se pasa a validar que tenga horario asignado
        {
            habilitarAccion();
        }
    }

    public void listaMarcasPorJornada() {
        marcasJornadasList = new ArrayList<MarcasJornada>();

        PrimeFaces current = PrimeFaces.current();
        MarcaLaboradas mLaborada = new MarcaLaboradas();
        mLaborada = marcaLaboradaService.buscaMarcaPorColaboradoYEstado(colaboradorMarca, "Entrada");//Saco marca de entrada

        if (mLaborada == null) {
            System.out.println("No ha marcado entrada");
        } else {//Lleno objeto con hora entrada y la descripcion de entrada
            if (mLaborada.getHoraEntrada() != null) {//Si solo a marcado entrada
                MarcasJornada miMJ = new MarcasJornada();
                System.out.println("ENTRO A IF SIN MARCA SALIDA");

                //Ingreso la entrada
                miMJ.setMarca(mLaborada.getHoraEntrada());
                miMJ.setDescripcion("Entrada");
                marcasJornadasList.add(miMJ);
                miMJ = new MarcasJornada();

                List<MarcaDescansos> marcaDescansosList = marcaDescansoService.buscarMarcaPorMarcaLab(mLaborada);
                if (!marcaDescansosList.isEmpty()) {//Si la marca descansos no esta vacia
                    for (MarcaDescansos md : marcaDescansosList) {
                        if (md.getHoraFin()==null) {//Si no ha finalizado el descanso
                            miMJ.setMarca(md.getHoraInicio());
                            miMJ.setDescripcion("Inicio "+md.getDescansos().getDescripcion());
                            marcasJornadasList.add(miMJ);//Solo agrega el inicio de descanso
                            miMJ = new MarcasJornada();

                        } else {//Si el descanso si finalizo es decir el campo getHoraFin tiene algo se carga
                            miMJ.setMarca(md.getHoraInicio());
                            miMJ.setDescripcion("Inicio "+md.getDescansos().getDescripcion());
                            marcasJornadasList.add(miMJ);//el inicio
                            miMJ = new MarcasJornada();

                            miMJ.setMarca(md.getHoraFin());
                            miMJ.setDescripcion("Finalizo "+md.getDescansos().getDescripcion());
                            marcasJornadasList.add(miMJ);//el fin
                            miMJ = new MarcasJornada();
                        }
                    }
                }
            }
        }

        for (MarcasJornada mj : marcasJornadasList) {
            System.out.println("Marca: " + mj.getMarca() + " Descripcion: " + mj.getDescripcion());
        }

        //Por aca debe poner la linea para que se actualice la tabla cada vez que se marque algo
        current.ajax().update("bot:marcaJornadaTabla");
        //marcasJornadasList = new ArrayList<MarcasJornada>();
    }

    public void listaMarcasPorJornadaFinalizada(MarcaLaboradas mLaborada) {
        marcasJornadasList = new ArrayList<MarcasJornada>();
        PrimeFaces current = PrimeFaces.current();
        if (mLaborada.getHoraSalida() != null) {//Si tambien marco salida
            MarcasJornada miMJ = new MarcasJornada();
            System.out.println("ENTRO A IF CON MARCA SALIDA");

            miMJ.setMarca(mLaborada.getHoraEntrada());
            miMJ.setDescripcion("Entrada");
            marcasJornadasList.add(miMJ);
            miMJ = new MarcasJornada();

            List<MarcaDescansos> marcaDescansosList = marcaDescansoService.buscarMarcaPorMarcaLab(mLaborada);
            if (!marcaDescansosList.isEmpty()) {//Si la marca descansos no esta vacia
                for (MarcaDescansos md : marcaDescansosList) {
                    if (md.getHoraFin()==null) {//Si no ha finalizado el descanso
                        miMJ.setMarca(md.getHoraInicio());
                        miMJ.setDescripcion("Inicio "+md.getDescansos().getDescripcion());
                        marcasJornadasList.add(miMJ);//Solo agrega el inicio de descanso
                        miMJ = new MarcasJornada();

                    } else {//Si el descanso si finalizo es decir el campo getHoraFin tiene algo se carga
                        miMJ.setMarca(md.getHoraInicio());
                        miMJ.setDescripcion("Inicio "+md.getDescansos().getDescripcion());
                        marcasJornadasList.add(miMJ);//el inicio
                        miMJ = new MarcasJornada();

                        miMJ.setMarca(md.getHoraFin());
                        miMJ.setDescripcion("Finalizo "+md.getDescansos().getDescripcion());
                        marcasJornadasList.add(miMJ);//el fin
                        miMJ = new MarcasJornada();
                    }
                }
            }
            miMJ.setMarca(mLaborada.getHoraSalida());
            miMJ.setDescripcion("Salida");
            marcasJornadasList.add(miMJ);
        }

        for (MarcasJornada mj : marcasJornadasList) {
            System.out.println("Marca: " + mj.getMarca() + " Descripcion: " + mj.getDescripcion());
        }
        //Por aca debe poner la linea para que se actualice la tabla cada vez que se marque algo
        current.ajax().update("bot:marcaJornadaTabla");
        //marcasJornadasList = new ArrayList<MarcasJornada>();
    }

    public void habilitarAccion()//Funcion que valida si el colaborador al mostrar el qr tiene un horario asignado
    {
        Calendar c1 = Calendar.getInstance();
        PrimeFaces current = PrimeFaces.current();
        createDirectory();
        asignaciones = asignacionesServices.buscarHorario(colaboradorMarca);//Busca si exite una asignacion con el id del colaborador
        if (asignaciones == null)//Si no tiene horario asignado se muestra el mensaje
        {
            addMessage("Aviso", "El colaborador no tiene horario asignado");
            colaboradorMarca = new Colaborador();
        } else {//Si tiene horario asignado

            //ACA SE INVOCARA LA FUNCION PARA BUSCAR LAS MARCAS DEL COLABORADOR
            listaMarcasPorJornada();

            Date date = new Date();
            MarcaLaboradas marcaLa = marcaLaboradaService.buscaMarcaPorColaboradoYEstado(colaboradorMarca, "Entrada");//Se busca si ya marco la entrada por medio de la fecha del dia
            if (marcaLa == null) {//si no encuentra la marca con el colaborador y el estado Entrada se habilita el boton de entrada
                if (!asignaciones.getDiadescanso().equals(verificaDiaLibre(c1.get(Calendar.DAY_OF_WEEK)))) {//Pero primero valida que no sea el dia libre del cola
                    botonEntrada = false;
                    current.ajax().update("bot:ent");
                } else {//Si el dia de hoy y el dia de descanso son iguales se muestra el mensaje de que es dia libre
                    addMessage("Aviso", "" + colaboradorMarca.getNombre() + " es su día libre");
                    botonEntrada = true;
                    current.ajax().update("bot:ent");//Se vuelven a bloquear los botones
                    colaboradorMarca = new Colaborador();
                    current.ajax().update("info:nom");// se limpia el nombre
                    current.ajax().update("info:ced");
                }
            } else {//Si la encuentra se habilita el boton de salida y descanso
                botonSalida = false;
                validacionMarcaDes();
                current.ajax().update("bot:sali");
            }
        }
    }

    public String verificaDiaLibre(int a)//Funcion que verifica segun el dia por numero el dia en string
    {
        String day = null;
        switch (a) {
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

    public void marcaEntrada() {//funcion para el boton marcar Entrada
        PrimeFaces current2 = PrimeFaces.current();
        Calendar c1 = Calendar.getInstance();
        c2 = Calendar.getInstance();
        c1.setTime(asignaciones.getHorario().getHoraentrada());
        c1.set(YEAR, c2.get(YEAR));
        c1.set(Calendar.MONTH, c2.get(Calendar.MONTH));
        c1.set(Calendar.DAY_OF_MONTH, c2.get(Calendar.DAY_OF_MONTH));//se carga la fecha actual
        if (c1.compareTo(c2) == 1) //  antes de entrada madrugo el colaborador
        {
            long resultado = (Math.abs(c1.getTimeInMillis() - c2.getTimeInMillis()) / (1000 * 60));
            if (resultado <= 15) {//Valida si ya puede marcar
                mensaje = "marca de la entrada";
                current2.ajax().update("msj");
                marcaEn();//Aca se llama la funcion para realizar la marca en la base de datos
            } else {//Si no se le avisa que aun no puede marcar por que es muy temprano
                mensaje= "Es muy temprano, no le correponde realizar la marca";
                current2.ajax().update("msj");


                /////////////////LINEAS PRUEBA
                //marcaEn();//Aca se llama la funcion para realizar la marca en la base de datos
                //addMessage("Aviso", "Pero igual marco para probar");
                ///////////////////////////////

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
                mensaje= "marca tarde";//Como es tarde pero todavia es valido que marque puede justificar su tardia
                current2.ajax().update("msj");
                current2.executeScript("PF('just').show();");//
            } else {//Si es demasiado tarde se muestra el mensaje de que ya no puede marca
                mensaje="Demasiado tarde, no puede realizar marca, tiempo limite agotado";
                current2.ajax().update("msj");
                /////////////////LINEAS PRUEBA
                //marcaEn();//Aca se llama la funcion para realizar la marca en la base de datos
                //addMessage("Aviso", "Pero igual marco para probar");
                /////////////////////////////

                botonEntrada = true;
                current2.ajax().update("bot:ent");//Se vuelven a bloquear los botones
                colaboradorMarca = new Colaborador();
                current2.ajax().update("info:nom");// se limpia el nombre
                current2.ajax().update("info:ced");
            }
        }
    }


    public void diasDisponibles(Colaborador colaborador) {

        PrimeFaces current2 = PrimeFaces.current();
        Calendar c1 = Calendar.getInstance();
        c2 = Calendar.getInstance();

        //  c2.set(YEAR, 2023);
        //  c2.set(Calendar.MONTH, 10);
        //  c2.set(Calendar.DAY_OF_MONTH, 15);

        c1.setTime(colaborador.getFechaInicioLaboral());

        vacaciones.setColaborador(colaborador);
        int anios = c2.get(YEAR) - c1.get(YEAR);
        if (c1.get(Calendar.MONTH) > c2.get(Calendar.MONTH) ||
                (c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH) && c1.get(Calendar.DATE) > c2.get(Calendar.DATE))) {
            anios--;
        }//funcion para calcular los annos de trabajo en la empresa


        if (anios==0&&vacacionesService.diasDisponibles(colaborador)==null){

            vacaciones.setDiasdisponibles(0);
            vacacionesService.createVacaciones(vacaciones);
        }

        else if ((c1.get(Calendar.MONTH)+1)==c2.get(Calendar.MONTH)&&c1.get(Calendar.DAY_OF_MONTH)==c2.get(Calendar.DAY_OF_MONTH)){

            if(anios>=1&&anios<=3){
                vacaciones.setDiasdisponibles(12);
                vacacionesService.updateVacaciones(vacaciones);
            }
            else if(anios>3&&anios<=5){
                vacaciones.setDiasdisponibles(15);
                vacacionesService.updateVacaciones(vacaciones);
            }
            else if(anios>5){
                vacaciones.setDiasdisponibles(18);
                vacacionesService.updateVacaciones(vacaciones);
            }

        }
        else {
            vacaciones.setDiasdisponibles(0);
            vacacionesService.createVacaciones(vacaciones);
        }

    }

    public void reset() {
        PrimeFaces current = PrimeFaces.current();
        mensaje="";
        colaboradorMarca=new Colaborador();
        marcasJornadasList = new ArrayList<>();
        asignacionDescansos = new AsignacionDescansos();
        marcaLa = new MarcaLaboradas();
        botonEntrada=true;
        botonSalida=true;
        botonDesSali=true;
        current.ajax().update("bot:ent");//Se desabilita el boton de descanso y el de salida
        current.ajax().update("bot:des");//Se desabilita el boton de descanso y el de salida
        current.ajax().update("bot:sali");
        current.ajax().update("info:nom");
        current.ajax().update("info:ced");
        current.ajax().update("msj");
        current.ajax().update("bot:marcaJornadaTabla");

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
        marcaLaboradaService.crearMarcaLaborada(marcaLaboradas);//y por ultimo se agrega la marca de entrada a la base de datos
        listaMarcasPorJornada();
        saveImage();
        marcaLaboradas = new MarcaLaboradas();
        colaboradorMarca = new Colaborador();
        botonEntrada = true;
        current.ajax().update("bot:ent");//Se vuelven a bloquear los botones
        current.ajax().update("info:nom");// se limpia el nombre
        current.ajax().update("info:ced");//y cedula del colaborador que marca
    }

    public void marcaTarde()//funcion que se dispara con el boton de el formulario just
    {
        PrimeFaces current = PrimeFaces.current();
        marcaEn();//se marca en la base de datos la entrada aunque sea tarde
        current.executeScript("PF('just').hide();");// y se enconde el form
        mensaje="Marca realizada con exito";
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

        System.out.println("Fecha de marca Entrada:" + fechaME);
        System.out.println("Fecha de marca Salida:" + fechaMS);
        if (idH == 11) {
            //System.out.println("Entro al if para horarios de 00:00:00");
            if ((c1.before(c2) && fechaME == fechaMS) || (c1.after(c2) && fechaME <= fechaMS)) //  antes de salida madrugo el colaborador para salir c1.compareTo(c2) < 0
            {
                if (c1.get(Calendar.HOUR_OF_DAY) == 0 && c1.get(Calendar.HOUR_OF_DAY) == 0 && c1.get(Calendar.HOUR_OF_DAY) == 0) {
                    c1.set(Calendar.HOUR_OF_DAY, 24);
                    c1.set(Calendar.MINUTE, 00);
                    c1.set(Calendar.SECOND, 00);
                    //System.out.println("Entro aqui al compara hora con 00:00:00");
                }

                long resultado = (Math.abs(c1.getTimeInMillis() - c2.getTimeInMillis()) / (1000 * 60));
                if (resultado <= 15) {
                    mensaje="Marca de la salida";
                    current.ajax().update("msj");
                    marcaSal();
                } else {//Si no es que salio demasiado mas temprano por ello debe justificar

                    mensaje="Es antes de su hora de salida, justifique ";
                    current.ajax().update("msj");
                    current.executeScript("PF('just2').show();");
                }
            } else if (fechaME < fechaMS)// salio tarde el colaborador hizo mas tiempo champion
            {
                Calendar c3 = c1;
                long resultado = (Math.abs(c3.getTimeInMillis() - c2.getTimeInMillis()) / (1000 * 60));
                if (resultado >= 30) {//Aca esta dando que si esta saliendo 30 minutos o mas tarde de la hora de salida pida justificacion de tiempo extra
                    mensaje="Marco salida mas tarde según su horario realizo Tiempo Extra";
                    current.ajax().update("msj");
                    current.executeScript("PF('just3').show();");
                } else {//Si no es mayor o igual el tiempo de media hora extra se depliega el siguiente mensaje

                    mensaje="Marco salida mas tarde, sin embargo no se toma como Tiempo Extra";
                    current.ajax().update("msj");
                    marcaSal();
                }
            }
        } else if (idH == 12 || idH == 14) {
            System.out.println("Entro al if para horarios de DOS DIAS");
            if ((c1.before(c2) && fechaME == fechaMS) || (c1.after(c2) && fechaME == fechaMS)) //  antes de salida madrugo el colaborador para salir c1.compareTo(c2) < 0
            {
                long resultado = (Math.abs(c1.getTimeInMillis() - c2.getTimeInMillis()) / (1000 * 60));
                if (resultado <= 15 && fechaME < fechaMS) {//Valida si ya puede marcar la salida a la hora establecida por horario
                    mensaje="Marca de la salida";
                    current.ajax().update("msj");
                    marcaSal();
                } else {//Si no es que salio demasiado mas temprano por ello debe justificar

                    mensaje="Es antes de su hora de salida, justifique";
                    current.ajax().update("msj");
                    current.executeScript("PF('just2').show();");
                }
            } else if (c1.after(c2) || fechaME < fechaMS)// salio tarde el colaborador hizo mas tiempo champion
            {
                Calendar c3 = c1;
                long resultado = (Math.abs(c3.getTimeInMillis() - c2.getTimeInMillis()) / (1000 * 60));
                if (resultado >= 30) {//Aca esta dando que si esta saliendo 30 minutos o mas tarde de la hora de salida pida justificacion de tiempo extra
                    mensaje="Marcó salida más tarde, según su horario realizó Tiempo Extra";
                    current.ajax().update("msj");
                    current.executeScript("PF('just3').show();");//Se despliega el dialogo con el campo para que justifique por que hizo Tiempo extra
                } else {//Si no es mayor o igual el tiempo de media hora extra se depliega el siguiente mensaje
                    mensaje="Marcó salida más tarde, sin embargo, no se toma como Tiempo Extra";
                    current.ajax().update("msj");
                    marcaSal();
                }
            }
        } else {
            System.out.println("Entro al if para horarios de un Dia");
            if (c1.compareTo(c2) == 1 && fechaME >= fechaMS) //  antes de salida madrugo el colaborador para salir
            {
                long resultado = (Math.abs(c1.getTimeInMillis() - c2.getTimeInMillis()) / (1000 * 60));
                if (resultado <= 15) {//Valida si ya puede marcar la salida a la hora establecida por horario
                    mensaje="Se realiza la marca de salida";
                    current.ajax().update("msj");
                    marcaSal();//Aca se llama la funcion para realizar la marca de salida en la base de datos
                } else {//Si no es que salio demasiado mas temprano por ello debe justificar
                    mensaje="Es antes de su hora de salida, Justifique";
                    current.ajax().update("msj");
                    current.executeScript("PF('just2').show();");
                }
            } else // salio tarde el colaborador hizo mas tiempo champion
            {
                Calendar c3 = c1;
                long resultado = (Math.abs(c3.getTimeInMillis() - c2.getTimeInMillis()) / (1000 * 60));
                if (resultado >= 30 || fechaME < fechaMS) {//Aca esta dando que si esta saliendo 30 minutos o mas tarde de la hora de salida pida justificacion de tiempo extra
                    mensaje="Marcó salida más tarde según su horario realizo Tiempo Extra";
                    current.ajax().update("msj");
                    current.executeScript("PF('just3').show();");//Se despliega el dialogo con el campo para que justifique por que hizo Tiempo extra
                } else {//Si no es mayor o igual el tiempo de media hora extra se depliega el siguiente mensaje
                    mensaje="Marcó salida más tarde, sin embargo no se toma como Tiempo Extra";
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
        marcaLaboradaService.actualizarMarcaLaborada(marcaLaboradas);//y por ultimo se agrega la marca de entrada a la base de datos

        listaMarcasPorJornadaFinalizada(marcaLaboradas);
        saveImage();
        marcaLaboradas = new MarcaLaboradas();
        colaboradorMarca = new Colaborador();
        botonSalida = true;
        justST = null;
        justTE = null;
        current.ajax().update("bot:des");//Se desabilita el boton de descanso y el de salida
        current.ajax().update("bot:sali");
        current.ajax().update("info:nom");// se limpia el nombre
        current.ajax().update("info:ced");//y cedula del colaborador que marca
    }

    public void marcaSalidaAntes()//funcion que se dispara con el boton de el formulario just2
    {
        PrimeFaces current = PrimeFaces.current();
        marcaSal();//se marca en la base de datos la entrada aunque sea tarde
        current.executeScript("PF('just2').hide();");// y se enconde el form
        mensaje="Marca salida prematura realizada con exito";
        current.ajax().update("msj");
        justST = null;
    }


    public void marcaTiempoExtra()//funcion que se dispara con el boton de el formulario just3
    {
        PrimeFaces current = PrimeFaces.current();
        marcaSal();//se marca en la base de datos la entrada aunque sea tarde
        current.executeScript("PF('just3').hide();");// y se enconde el form
        mensaje="Marca de tiempo extra realizada con exito";
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

    public void marcaIniDes() {
        MarcaLaboradas marcaLa = marcaLaboradaService.buscaMarcaPorColaboradoYEstado(colaboradorMarca, "Entrada");
        MarcaDescansos marcaDescansos = new MarcaDescansos();
        marcaDescansos.setColaborador(colaboradorMarca);
        marcaDescansos.setMarcaLaboradas(marcaLa);
        marcaDescansos.setDescansos(asignacionDescansos.getDescanso());
        Time time = new Time(now.get(Calendar.HOUR_OF_DAY), now.get(Calendar.MINUTE), now.get(Calendar.SECOND));
        marcaDescansos.setHoraInicio(time);
        marcaDescansoService.crearMarcaDescanso(marcaDescansos);
        saveImage();
        listaMarcasPorJornada();
    }

    public void marcaFindes() {
        MarcaDescansos marcaDescansos = marcaDescansoService.buscarMdescanso(asignacionDescansos.getDescanso(), marcaLa);
        Time time = new Time(now.get(Calendar.HOUR_OF_DAY), now.get(Calendar.MINUTE), now.get(Calendar.SECOND));
        marcaDescansos.setHoraFin(time);
        marcaDescansoService.actualizarMarcaDescanso(marcaDescansos);
        saveImage();
        listaMarcasPorJornada();
    }

    public void validacionMarcaDes()
    {

        List<AsignacionDescansos> asignacionesList=asignacionDescansosService.buscarDescansosAsignadosPorColaborador(colaboradorMarca);//numero de descansos que tiene el colaborador
        marcaLa = marcaLaboradaService.buscaMarcaPorColaboradoYEstado(colaboradorMarca,"Entrada");//para sacar el id de la marca que esta asociada a la marca de descanso por dia
        List<MarcaDescansos> marcaDescansosList = marcaDescansoService.buscarMarcaPorMarcaLab(marcaLa);
        PrimeFaces current = PrimeFaces.current();
        boolean encontrado=false;
        Calendar calendar=Calendar.getInstance();
        Calendar calendar2=Calendar.getInstance();
        now=Calendar.getInstance();
        for (AsignacionDescansos asignacionDescansos1 : asignacionesList)
        {
            String time=asignacionDescansos1.getDescanso().getHorainicio().toString();
            String[] timeArrayInicio = time.split(":");
            String time2=asignacionDescansos1.getDescanso().getHorafinalizacion().toString();
            String[] timeArrayFin = time2.split(":");
            if(timeArrayFin[0].equals("01")){timeArrayFin[0]="24";timeArrayFin[1]="59";}
            calendar.set(Calendar.HOUR_OF_DAY,Integer.parseInt(timeArrayInicio[0]));
            calendar.set(Calendar.MINUTE,Integer.parseInt(timeArrayInicio[1]));
            calendar.set(Calendar.SECOND,Integer.parseInt(timeArrayInicio[2]));
            calendar2.set(Calendar.HOUR_OF_DAY,Integer.parseInt(timeArrayFin[0]));
            calendar2.set(Calendar.MINUTE,Integer.parseInt(timeArrayFin[1]));
            calendar2.set(Calendar.SECOND,Integer.parseInt(timeArrayFin[2]));
            if(now.get(Calendar.HOUR)==0)
            {
                now.set(Calendar.HOUR_OF_DAY,24);
            }
            if((now.compareTo(calendar) >= 0) && ( now.compareTo(calendar2) <= 0)){
                asignacionDescansos=asignacionDescansos1;
                if(marcaDescansoService.buscarMdescanso(asignacionDescansos1.getDescanso(),marcaLa)==null)
                {
                    botonDesSali=false;
                    variable="Inicio Descanso";
                }
                else if(marcaDescansoService.buscarMdescanso(asignacionDescansos1.getDescanso(),marcaLa).getHoraFin()==null) //hacer debug con lo que agrego pablo ya que no pasa de aqui
                {
                    botonDesSali=false;
                    variable="Fin Descanso";
                }
                encontrado=true;
                current.ajax().update("bot");
                break;
            }
        }
        if(encontrado==false)
        {
            mensaje="No puede marcar descanso";
            current.ajax().update("msj");

        }
    }

    public void marcarDescanso() {
        if (variable.equals("Inicio Descanso")) {
            PrimeFaces current = PrimeFaces.current();
            mensaje="Marca descanso";
            current.ajax().update("msj");
            marcaIniDes();
            limpia();
        } else {
            marcaFindes();
            limpia();
            PrimeFaces current = PrimeFaces.current();
            mensaje="Marca fin del descanso";
            current.ajax().update("msj");

        }
    }

    public void limpia() {
        PrimeFaces current = PrimeFaces.current();
        colaboradorMarca = new Colaborador();
        asignacionDescansos = new AsignacionDescansos();
        marcaLa = new MarcaLaboradas();
        botonSalida = true;
        botonDesSali = true;
        variable = "Descanso";
        mensaje="";
        current.ajax().update("msj");
        current.ajax().update("bot:des");//Se desabilita el boton de descanso y el de salida
        current.ajax().update("bot:sali");
        current.ajax().update("info:nom");// se limpia el nombre
        current.ajax().update("info:ced");//y cedula del colaborador que marca
    }
    public void resetMsj()
    {
        PrimeFaces current = PrimeFaces.current();
        mensaje="";
        current.ajax().update("msj");
    }

    public void createDirectory() {
        Calendar can= Calendar.getInstance();
        String date=""+(can.get(Calendar.MONTH)+1 )+"-"+can.get(Calendar.DATE)+"-"+can.get(YEAR);
        File file = new File("C:\\"+date);
        if (!file.exists()) {
            file.mkdir();
        }
    }

    public void saveImage()
    {

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
        Calendar can= Calendar.getInstance();
        String date=""+(can.get(Calendar.MONTH)+1 )+"-"+can.get(Calendar.DATE)+"-"+can.get(YEAR);
        //convert base64 string to binary data
        byte[] data = DatatypeConverter.parseBase64Binary(strings[1]);
        String path = "C:\\"+date+"\\"+colaboradorMarca.getPk_idColaborador()+"_"+can.get(Calendar.HOUR)+"_"+can.get(Calendar.MINUTE)+"_"+can.get(Calendar.SECOND)+"."+ extension;
        File file = new File(path);
        try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file))) {
            outputStream.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
