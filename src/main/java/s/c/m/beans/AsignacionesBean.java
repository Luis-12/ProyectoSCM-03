package s.c.m.beans;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import s.c.m.entities.*;
import s.c.m.services.*;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;

@Component
@ManagedBean
public class AsignacionesBean {
    //Controlador que conecta el front end con el backend en las asignaciones.
    @Autowired
    AsignacionesServices asignacionesService;

    @Autowired
    JornadaService jornadaService;

    @Autowired
    HorarioService horarioService;

    @Autowired
    AsignacionDescansosService asignacionDescansosService;

    @Autowired
    DescansosService descansosService;


    private Colaborador colaborador = new Colaborador();
    private Horarios horario = new Horarios();
    private Jornadas jornada = new Jornadas();
    private String descanso;
    private String tiempoDescanso;
    private List<Asignaciones> asignaciones = new ArrayList<>();
    private List<AsignacionDescansos> asignacionesDescansos = new ArrayList<>();
    private List<Descansos> listDescansos = new ArrayList<Descansos>();
    public boolean habilitar = true;//Parametros para habilitar y deshabilitar combos de segundo dia de descanso
    public boolean habilitar2 = true;

    private List<Jornadas> jornadas;
    private List<Horarios> horarios;
    private List<Horarios> horariosTemp;
    private List<Descansos> descansosPorColaborador = new ArrayList<>();

    private Asignaciones asignacion = new Asignaciones();
    private Asignaciones selectAsignacion = new Asignaciones();
    private AsignacionDescansos asignacionDescansos = new AsignacionDescansos();


    @PostConstruct
    public void init() {
        jornadas = jornadaService.getAllJornadas();
        horarios = horarioService.getAllHorarios();

    }

    public void handleJornadaChange() {//Funcion para cargar combobox de horarios por jornada seleccionada
        if (this.jornada != null && this.jornada.getPk_idjornada() != null) {
            this.horariosTemp = new ArrayList<>();
            this.horariosTemp = horarioService.findHorariosPorJornada(jornada);//Se invoca la funcion del service que encuentra los horarios por jornada
        }
    }

    public String getTiempoDescanso() {
        return tiempoDescanso;
    }

    public void setTiempoDescanso(String tiempoDescanso) {
        this.tiempoDescanso = tiempoDescanso;
    }

    public List<Descansos> getListDescansos() {
        return listDescansos;
    }

    public void setListDescansos(List<Descansos> listDescansos) {
        this.listDescansos = listDescansos;
    }

    public List<Descansos> getDescansosPorColaborador() {
        return descansosPorColaborador;
    }

    public void setDescansosPorColaborador(List<Descansos> descansosPorColaborador) {
        this.descansosPorColaborador = descansosPorColaborador;
    }

    public boolean isHabilitar() {
        return habilitar;
    }

    public boolean isHabilitar2() {
        return habilitar2;
    }

    public DescansosService getDescansosService() {
        return descansosService;
    }

    public void setDescansosService(DescansosService descansosService) {
        this.descansosService = descansosService;
    }

    public AsignacionesServices getAsignacionesService() {
        return asignacionesService;
    }

    public void setAsignacionesService(AsignacionesServices asignacionesService) {
        this.asignacionesService = asignacionesService;
    }

    public List<AsignacionDescansos> getAsignacionesDescansos() {
        return asignacionesDescansos;
    }

    public void setAsignacionesDescansos(List<AsignacionDescansos> asignacionesDescansos) {
        this.asignacionesDescansos = asignacionesDescansos;
    }

    public void listarDescansos() {//Funcion para listar descansos por el colaborador
        listDescansos.clear();
        PrimeFaces current = PrimeFaces.current();
        //Aca se cargan las asignaciones de descansos que tiene el colaborador
        asignacionesDescansos = asignacionDescansosService.buscarDescansosAsignadosPorColaborador(selectAsignacion.getColaborador());

        if (asignacionesDescansos.isEmpty()) {//Si no encuentra nada quiere decir que el colaborador no tiene descansos asignados
            FacesMessage msg = new FacesMessage("Aviso", "El colaborador no tiene descansos asignados");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {//Si encuentra descansos asignados los enlista para imprimirlos en la tabla
            for (int i = 0; i < asignacionesDescansos.size(); i++) {//Se va recorriendo la lista de asignaciones
                listDescansos.add(asignacionesDescansos.get(i).getDescanso());//luego se van sacando los descanso que tenga asignados el colaborador
                //Con esta lista de Descansos se podra imprimir los datos de los Descansos que tiene asignados el colaborador en la tabla asignacionDescansos
            }
            current.executeScript("PF('dD').show();"); //se muestra el dialogo con los descansos activados
        }
    }

    public List<Asignaciones> getAsignaciones() {
        return asignaciones;
    }

    public void setAsignaciones(List<Asignaciones> asignaciones) {
        this.asignaciones = asignaciones;
    }

    public Asignaciones getAsignacion() {
        return asignacion;
    }

    public void setAsignacion(Asignaciones asignacion) {
        this.asignacion = asignacion;
    }

    public AsignacionDescansos getAsignacionDescansos() {
        return asignacionDescansos;
    }

    public void setAsignacionDescansos(AsignacionDescansos asignacionDescansos) { this.asignacionDescansos = asignacionDescansos; }

    public Horarios getHorario() {
        return horario;
    }

    public void setHorario(Horarios horario) {
        this.horario = horario;
    }

    public String getDescanso() {
        return descanso;
    }

    public void setDescanso(String descanso) {
        this.descanso = descanso;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    public Jornadas getJornada() {
        return jornada;
    }

    public void setJornada(Jornadas jornada) {
        this.jornada = jornada;
    }

    public JornadaService getJornadaService() {
        return jornadaService;
    }

    public void setJornadaService(JornadaService jornadaService) {
        this.jornadaService = jornadaService;
    }

    public List<Jornadas> getJornadas() {
        return jornadas;
    }

    public void setJornadas(List<Jornadas> jornadas) {
        this.jornadas = jornadas;
    }

    public HorarioService getHorarioService() {
        return horarioService;
    }

    public void setHorarioService(HorarioService horarioService) {
        this.horarioService = horarioService;
    }

    public List<Horarios> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<Horarios> horarios) {
        this.horarios = horarios;
    }

    public List<Horarios> getHorariosTemp() {
        return horariosTemp;
    }

    public void setHorariosTemp(List<Horarios> horariosTemp) {
        this.horariosTemp = horariosTemp;
    }

    public Asignaciones getSelectAsignacion() {
        return selectAsignacion;
    }

    public AsignacionDescansosService getAsignacionDescansosService() {
        return asignacionDescansosService;
    }

    public void setAsignacionDescansosService(AsignacionDescansosService asignacionDescansosService) { this.asignacionDescansosService = asignacionDescansosService; }

    public void setSelectAsignacion(Asignaciones selectAsignacion) {
        this.selectAsignacion = selectAsignacion;
    }

    public void checkSelection() {//Funcion para validar si selecciono un colaborador desde el form
        PrimeFaces current = PrimeFaces.current();
        disable();//Se desactiva el combo de segundo dia libre

        if (selectAsignacion.getColaborador() == null) {//Si el objeto esta vacio quiere decir que no selecciono ningun colaborador
            FacesMessage msg = new FacesMessage("Aviso", "Debe seleccionar un colaborador");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {//Si lo selecciono
            if (asignacionesService.buscarHorario(selectAsignacion.getColaborador()) == null) {//Y no tiene horario asignado
                current.executeScript("PF('datos').show();"); //Muestra el dialogo con el form para asignarle uno
            } else {//Sino quiere decir que ya tiene horario asignado
                FacesMessage msg = new FacesMessage("Aviso", "¡Ya tiene un horario asignado!");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        }
    }

    public void checkSelectionD() {//Funcion para validar que se puedan consultar descanso asignados
        PrimeFaces current = PrimeFaces.current();

        if (selectAsignacion.getColaborador() == null) {//Se valida que se haya seleccionado un colaborador
            FacesMessage msg = new FacesMessage("Aviso", "Debe seleccionar un colaborador");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else if (asignacionDescansosService.buscarDescansosAsignadosPorColaborador(selectAsignacion.getColaborador()) == null) {
            //Si se selecciono uno se busca si tiene o no descansos asignados
            FacesMessage msg = new FacesMessage("Aviso", "El colaborador no tiene descansos asignados");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {//Si tiene descansos asignados
            listarDescansos();//Se listan los mismo
        }
    }

    public void checkSelectionC() {//Funcion para consultar horario asignado
        PrimeFaces current = PrimeFaces.current();

        if (selectAsignacion.getColaborador() == null) {//Se valida si el colaborador fue seleccionado
            FacesMessage msg = new FacesMessage("Aviso", "Debe seleccionar un colaborador");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else if (asignacionesService.buscarHorario(selectAsignacion.getColaborador()) == null) {
            //Si se selecciono un colaborador se busca si tiene horarios asignados
            FacesMessage msg = new FacesMessage("Aviso", "El colaborador no tiene horario asignado");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {//Si tiene horario asignado
            buscaHorarioAsignado();//Se lista el mismo
            current.executeScript("PF('dH').show();"); //Y se muestra en el dialogo
        }
    }

    public void buscaHorarioAsignado() {//Funcion para cargar horario en objeto para imprimirlo en la tabla de consulta
        Horarios horarioAux = new Horarios();
        String diaDescanso = null;
        String diaDescanso2 = null;

        if (asignacionesService.buscarHorario(selectAsignacion.getColaborador()) != null) {//Se valida si se encuentra el horario
            selectAsignacion = asignacionesService.buscarHorario(selectAsignacion.getColaborador());//De ser asi se guarda
            if (selectAsignacion.getDiaDescanso().equals("LU")) {//Y se hace un cambio de formato para le dia para mostrarlo en la tabla de manera mas legible
                diaDescanso = "Lunes";
            } else if (selectAsignacion.getDiaDescanso().equals("MA")) {
                diaDescanso = "Martes";
            } else if (selectAsignacion.getDiaDescanso().equals("MI")) {
                diaDescanso = "Miércoles";
            } else if (selectAsignacion.getDiaDescanso().equals("JU")) {
                diaDescanso = "Jueves";
            } else if (selectAsignacion.getDiaDescanso().equals("VI")) {
                diaDescanso = "Viernes";
            } else if (selectAsignacion.getDiaDescanso().equals("SA")) {
                diaDescanso = "Sábado";
            } else if (selectAsignacion.getDiaDescanso().equals("DO")) {
                diaDescanso = "Domingo";
            }
            selectAsignacion.setDiadescanso(diaDescanso);//Y por ultimo se carga el dia de descanso 1
            if (selectAsignacion.getSegundodiades() == null) {//Si el segundo dia de descanso esta vacio
                selectAsignacion.setSegundodiades("N/A");//Se carga el texto no aplica
            } else {//Si tiene algo en el segundo dia
                if (selectAsignacion.getSegundodiades().equals("LU")) {//Se hace el cambio de formato a uno mas legible
                    diaDescanso2 = "Lunes";
                } else if (selectAsignacion.getSegundodiades().equals("MA")) {
                    diaDescanso2 = "Martes";
                } else if (selectAsignacion.getSegundodiades().equals("MI")) {
                    diaDescanso2 = "Miércoles";
                } else if (selectAsignacion.getSegundodiades().equals("JU")) {
                    diaDescanso2 = "Jueves";
                } else if (selectAsignacion.getSegundodiades().equals("VI")) {
                    diaDescanso2 = "Viernes";
                } else if (selectAsignacion.getSegundodiades().equals("SA")) {
                    diaDescanso2 = "Sábado";
                } else if (selectAsignacion.getSegundodiades().equals("DO")) {
                    diaDescanso2 = "Domingo";
                }else if (selectAsignacion.getSegundodiades().equals("N/A")) {
                    diaDescanso2 = "N/A";
                }
                selectAsignacion.setSegundodiades(diaDescanso2);//Y por ultimo se carga en el objeto el segundo dia de descanso
            }
        } else {
            System.out.println("No se encontro el horario");
        }
    }


    public void convertirDia() {//Funcion para convertir el dia de formato mas legible a el que se va a guardar en la base
        String diaDescanso = null;

        if (selectAsignacion.getDiaDescanso().equals("Lunes")) {//Se valida segun el dia lo que se va guardar
            diaDescanso = "LU";
        } else if (selectAsignacion.getDiaDescanso().equals("Martes")) {
            diaDescanso = "MA";
        } else if (selectAsignacion.getDiaDescanso().equals("Miércoles")) {
            diaDescanso = "MI";
        } else if (selectAsignacion.getDiaDescanso().equals("Jueves")) {
            diaDescanso = "JU";
        } else if (selectAsignacion.getDiaDescanso().equals("Viernes")) {
            diaDescanso = "VI";
        } else if (selectAsignacion.getDiaDescanso().equals("Sábado")) {
            diaDescanso = "SA";
        } else if (selectAsignacion.getDiaDescanso().equals("Domingo")) {
            diaDescanso = "DO";
        } else if (selectAsignacion.getDiaDescanso() == null) {
            diaDescanso = "N/A";
        }else {
            diaDescanso = selectAsignacion.getDiaDescanso();
        }
        selectAsignacion.setDiadescanso(diaDescanso);//Y se carga el dia de descanso con el formato correcto para la base
    }

    public void checkSelectionScheduleChange() {//Funcion para validar si se puede cambiar el horario
        PrimeFaces current = PrimeFaces.current();
        disable2();//Se bloquea el combo de segundo dia

        if (selectAsignacion.getColaborador() == null) {//Se valida si selecciono el colaborador
            FacesMessage msg = new FacesMessage("Aviso", "Debe seleccionar un colaborador");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {//Si se selecciono un colaborador
            if (asignacionesService.buscarHorario(selectAsignacion.getColaborador()) == null) {//Se valida si tiene o no un horario asignado
                FacesMessage msg = new FacesMessage("Aviso", "El colaborador no tiene horario asignado");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else {//Si tiene horario asignado
                buscaHorarioAsignado();//Se busca el mismo
                current.executeScript("PF('datos2').show();"); //se muestra el dialogo con el form para actualizar el horario asignado
            }
        }
    }

    public void actualizarHorario()
    {
        asignacionesService.updateAsignacion(selectAsignacion);//Aca se actualiza la asignacion del horario
        //Seguidamente se procede a eliminar los descanso que tenia asignados el colaborador segun el horario anterior
        asignacionDescansosService.deletePorColaborador(selectAsignacion.getColaborador());

        asignacionesService.updateAsignacion(selectAsignacion);//Aca se actualiza la asignacion del horario
        //Seguidamente se procede a eliminar los descanso que tenia asignados el colaborador segun el horario anterior
        asignacionDescansosService.deletePorColaborador(selectAsignacion.getColaborador());

        //Y por ultimo volver a asignar los descanso por el nuevo horario
        descansosPorColaborador = descansosService.buscarDescansosPorHorario(selectAsignacion.getHorario());//Aca se llena una lista de descansos
        for (int i = 0; i < descansosPorColaborador.size(); i++) {//Ciclo for para asignar los descansos al colaborador por el horario
            asignacionDescansos.setColaborador(selectAsignacion.getColaborador());
            asignacionDescansos.setDescanso(descansosPorColaborador.get(i));
            asignacionDescansosService.updateAsignacionDescanso(asignacionDescansos);//Aca se van asignado los descansos nuevamente al colaborador
            asignacionDescansos = new AsignacionDescansos();
        }
        selectAsignacion = new Asignaciones();
        asignacion = new Asignaciones();
        jornada = new Jornadas();
        asignacionDescansos = new AsignacionDescansos();
        descansosPorColaborador.clear();
    }

    @Transactional
    public void changeSchedule() {//Funcion para actualizar el horario en la base de datos con sus descansos
        descansosPorColaborador.clear();
        FacesMessage mensaje = null;
        PrimeFaces current = PrimeFaces.current();

        Asignaciones asignaChage = asignacionesService.buscarHorario(selectAsignacion.getColaborador());//Se busca el horario que tiene asignado el colaborador actualmente
        try {
            //Se procede a actualizar el nuevo horario asignado en la base
            convertirDia();
            if (selectAsignacion.getSegundodiades().equals("Lunes")
                    || selectAsignacion.getSegundodiades().equals("Martes")
                    || selectAsignacion.getSegundodiades().equals("Miércoles")
                    || selectAsignacion.getSegundodiades().equals("Jueves")
                    || selectAsignacion.getSegundodiades().equals("Viernes")
                    || selectAsignacion.getSegundodiades().equals("Sábado")
                    || selectAsignacion.getSegundodiades().equals("Domingo")
                    || selectAsignacion.getSegundodiades().equals("")) {
                //Si el combo retorna un texto de dia
                selectAsignacion.setSegundodiades("N/A");//Se carga segundo dia con N/A
            }if(selectAsignacion.getDiadescanso().equals(selectAsignacion.getSegundodiades())){
                addMessage("Warning","Los días de desconso no pueden ser los mismos");
            }else {
                actualizarHorario();
                //Y por ultimo volver a asignar los descanso por el nuevo horario
                current.executeScript("PF('datos2').hide();");//Se esconde el form

                FacesMessage msg = new FacesMessage("Aviso", "¡Horario actualizado correctamente!");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } catch (Exception e) {
            System.out.println("No se puedo actualizar el horario");
        }
    }

    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public  void addDescanso(){
        //Ahora se procede a hacer la insercion a la base de datos de las asignaciones de descansos
        descansosPorColaborador = descansosService.buscarDescansosPorHorario(asignacion.getHorario());//Aca se llena una lista de descansos
        System.out.println("Los descansos que tiene pertenecen al horario son: " + descansosPorColaborador.size());
        for (int i = 0; i < descansosPorColaborador.size(); i++) {//Ciclo for para asignar los descansos al colaborador por el horario
            asignacionDescansos.setColaborador(selectAsignacion.getColaborador());
            asignacionDescansos.setDescanso(descansosPorColaborador.get(i));
            asignacionDescansosService.createAsignacionDescanso(asignacionDescansos);//Aca se agrega el descanso al colaborador
            asignacionDescansos = new AsignacionDescansos();
        }
        selectAsignacion = new Asignaciones();
        asignacion = new Asignaciones();
        jornada = new Jornadas();
        asignacionDescansos = new AsignacionDescansos();
        descansosPorColaborador.clear();
    }

    public void create() {//Funcion para asignar los horarios y descansos al colaborador
        descansosPorColaborador.clear();
        FacesMessage mensaje = null;
        PrimeFaces current = PrimeFaces.current();

        if (jornada != null) {//Primero debe seleccionar una jornada
            try {
                //Insersion de asignacion de horario en la tabla asignaciones
                asignacion.setColaborador(selectAsignacion.getColaborador());
                if (asignacion.getSegundodiades() == null) {//Si el segundo dia es null
                    selectAsignacion.setSegundodiades("N/A");//Se llena el campo segundo dia con N/A
                    asignacion.setSegundodiades("N/A");//Se llena el campo segundo dia con N/A
                    asignacionesService.createAsignacion(asignacion);//Aca se agrega la nueva asignacion de horario
                    addDescanso();
                    current.executeScript("PF('datos').hide();");//Se esconde el formulario
                }else
                {
                    if(asignacion.getDiadescanso().equals(asignacion.getSegundodiades())){
                       addMessage("Warning","Los días de desconso no pueden ser los mismos");
                    }else
                    {
                        asignacionesService.createAsignacion(asignacion);//Aca se agrega la nueva asignacion de horario
                        addDescanso();
                        current.executeScript("PF('datos').hide();");//Se esconde el formulario
                        System.out.println("Horario asignado");
                        FacesMessage msg = new FacesMessage("Aviso", "¡Asignación realizada correctamente!");
                        FacesContext.getCurrentInstance().addMessage(null, msg);
                    }
                }
            } catch (Exception e) {
            }
        } else {
            FacesMessage msg = new FacesMessage("Aviso", "Debe seleccionar una jornada.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void enable()//para habilitar el segundo día de descanso
    {
        PrimeFaces current = PrimeFaces.current();
        habilitar = false;
        current.ajax().update("horaio:descanso3");
    }

    public void enable2()//para habilitar el segundo día de descanso
    {
        PrimeFaces current = PrimeFaces.current();
        habilitar2 = false;
        current.ajax().update("horaio2:descanso4");
    }

    public void disable()//para deshabilitar el segundo día de descanso
    {
        PrimeFaces current = PrimeFaces.current();
        habilitar = true;
        current.ajax().update("horaio:descanso3");
    }

    public void disable2()//para deshabilitar el segundo día de descanso
    {
        PrimeFaces current = PrimeFaces.current();
        habilitar2 = true;
        current.ajax().update("horaio2:descanso4");
    }

}
