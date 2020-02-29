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
    public boolean habilitar = true;
    public boolean habilitar2 = true;

    public String getTiempoDescanso() {
        return tiempoDescanso;
    }

    public void setTiempoDescanso(String tiempoDescanso) {
        this.tiempoDescanso = tiempoDescanso;
    }

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

    public void handleJornadaChange() {
        if (this.jornada != null && this.jornada.getPk_idjornada() != null) {
            this.horariosTemp = new ArrayList<>();

            this.horariosTemp = horarioService.findHorariosPorJornada(jornada);
        }
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

    public void listarDescansos() {
        listDescansos.clear();
        PrimeFaces current = PrimeFaces.current();
        asignacionesDescansos = asignacionDescansosService.buscarDescansosAsignadosPorColaborador(selectAsignacion.getColaborador());

        if (asignacionesDescansos.isEmpty()) {
            FacesMessage msg = new FacesMessage("Aviso", "El colaborador no tiene descansos asignados");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            System.out.println("Descripcion de descanso: " + asignacionesDescansos.get(0).getDescanso().getDescripcion());
            for (int i = 0; i < asignacionesDescansos.size(); i++) {//Se va recorriendo la lista de asignaciones
                listDescansos.add(asignacionesDescansos.get(i).getDescanso());//luego se van sacando los descanso que tenga asignados el colaborador
                //Con esta lista de Descansos se podra imprimir los datos de los Descansos que tiene asignados el colaborador en la tabla asignacionDescansos
            }
            System.out.println("Los descansos que tiene asignados el colaborador son: " + asignacionesDescansos.size());
            current.executeScript("PF('dD').show();"); //si no esta vacío muestra el dialogo
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

    public void setAsignacionDescansos(AsignacionDescansos asignacionDescansos) {
        this.asignacionDescansos = asignacionDescansos;
    }

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

    public void setAsignacionDescansosService(AsignacionDescansosService asignacionDescansosService) {
        this.asignacionDescansosService = asignacionDescansosService;
    }

    public void setSelectAsignacion(Asignaciones selectAsignacion) {
        this.selectAsignacion = selectAsignacion;
    }

    public void checkSelection() {
        PrimeFaces current = PrimeFaces.current();
        //habilitar = true;
        //current.ajax().update("horaio:descanso3");
        disable();

        if (selectAsignacion.getColaborador() == null) {
            FacesMessage msg = new FacesMessage("Aviso", "Debe seleccionar un colaborador");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            if (asignacionesService.buscarHorario(selectAsignacion.getColaborador()) == null) {
                current.executeScript("PF('datos').show();"); //si no esta vacío muestra el dialogo
            } else {
                FacesMessage msg = new FacesMessage("Aviso", "¡Ya tiene un horario asignado!");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        }
    }

    public void checkSelectionD() {
        PrimeFaces current = PrimeFaces.current();

        if (selectAsignacion.getColaborador() == null) {
            FacesMessage msg = new FacesMessage("Aviso", "Debe seleccionar un colaborador");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else if (asignacionDescansosService.buscarDescansosAsignadosPorColaborador(selectAsignacion.getColaborador()) == null) {
            FacesMessage msg = new FacesMessage("Aviso", "El colaborador no tiene descansos asignados");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            listarDescansos();
        }
    }

    public void checkSelectionC() {
        PrimeFaces current = PrimeFaces.current();

        if (selectAsignacion.getColaborador() == null) {
            FacesMessage msg = new FacesMessage("Aviso", "Debe seleccionar un colaborador");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else if (asignacionesService.buscarHorario(selectAsignacion.getColaborador()) == null) {
            FacesMessage msg = new FacesMessage("Aviso", "El colaborador no tiene horario asignado");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            buscaHorarioAsignado();
            current.executeScript("PF('dH').show();"); //si no esta vacío muestra el dialogo
        }
    }

    public void buscaHorarioAsignado() {
        Horarios horarioAux = new Horarios();
        String diaDescanso = null;
        String diaDescanso2 = null;

        if (asignacionesService.buscarHorario(selectAsignacion.getColaborador()) != null) {
            selectAsignacion = asignacionesService.buscarHorario(selectAsignacion.getColaborador());
            if (selectAsignacion.getDiaDescanso().equals("LU")) {
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
            selectAsignacion.setDiadescanso(diaDescanso);
            if (selectAsignacion.getSegundodiades() == null) {//Si el segundo dia de descanso esta vacio
                selectAsignacion.setSegundodiades("N/A");//Cargue el texto no aplica
            } else {
                if (selectAsignacion.getSegundodiades().equals("LU")) {
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
                }
                selectAsignacion.setSegundodiades(diaDescanso2);
            }
        } else {
            System.out.println("No se encontro el horario");
        }
    }


    public void convertirDia() {
        String diaDescanso = null;

        if (selectAsignacion.getDiaDescanso().equals("Lunes")) {
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
        } else {
            diaDescanso = selectAsignacion.getDiaDescanso();
        }
        selectAsignacion.setDiadescanso(diaDescanso);

    }

    public void checkSelectionScheduleChange() {
        PrimeFaces current = PrimeFaces.current();
        disable2();

        if (selectAsignacion.getColaborador() == null) {
            FacesMessage msg = new FacesMessage("Aviso", "Debe seleccionar un colaborador");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            if (asignacionesService.buscarHorario(selectAsignacion.getColaborador()) == null) {//Quiere decir que no tiene horario asignado
                FacesMessage msg = new FacesMessage("Aviso", "El colaborador no tiene horario asignado");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else {
                buscaHorarioAsignado();
                System.out.println("El colaborador ya tiene horario asignado y se desea cambiar");
                current.executeScript("PF('datos2').show();"); //si no esta vacío muestra el dialogo
                //selectAsignacion = new Asignaciones();
            }
        }
    }

    @Transactional
    public void changeSchedule() {
        descansosPorColaborador.clear();
        FacesMessage mensaje = null;
        PrimeFaces current = PrimeFaces.current();

        Asignaciones asignaChage = asignacionesService.buscarHorario(selectAsignacion.getColaborador());
        try {
            //Se procede a actualizar el nuevo horario asignado en la base
            convertirDia();
            if (selectAsignacion.getSegundodiades().equals("Lunes")
                    || selectAsignacion.getSegundodiades().equals("Martes")
                    || selectAsignacion.getSegundodiades().equals("Miércoles")
                    || selectAsignacion.getSegundodiades().equals("Jueves")
                    || selectAsignacion.getSegundodiades().equals("Viernes")
                    || selectAsignacion.getSegundodiades().equals("Sábado")
                    || selectAsignacion.getSegundodiades().equals("Domingo")) {
                selectAsignacion.setSegundodiades("N/A");
            }
            asignacionesService.updateAsignacion(selectAsignacion);//Aca se actualiza la asignacion del horario
            //System.out.println("Id colaborador: " + selectAsignacion.getColaborador().getPk_idColaborador());
            //Seguidamente se procede a eliminar los descanso que tenia asignados el colaborador segun el horario anterior
            asignacionDescansosService.deletePorColaborador(selectAsignacion.getColaborador());

            //Y por ultimo volver a asignar los descanso por el nuevo horario
            descansosPorColaborador = descansosService.buscarDescansosPorHorario(selectAsignacion.getHorario());//Aca se llena una lista de descansos
            for (int i = 0; i < descansosPorColaborador.size(); i++) {//Ciclo for para asignar los descansos al colaborador por el horario
                asignacionDescansos.setColaborador(selectAsignacion.getColaborador());
                asignacionDescansos.setDescanso(descansosPorColaborador.get(i));
                asignacionDescansosService.updateAsignacionDescanso(asignacionDescansos);
                asignacionDescansos = new AsignacionDescansos();
            }
            current.executeScript("PF('datos2').hide();");

            FacesMessage msg = new FacesMessage("Aviso", "¡Horario actualizado correctamente!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (Exception e) {
            System.out.println("No se puedo actualizar el horario");
        } finally {
            selectAsignacion = new Asignaciones();
            asignacion = new Asignaciones();
            jornada = new Jornadas();
            asignacionDescansos = new AsignacionDescansos();
            descansosPorColaborador.clear();
        }
    }

    public void create() {
        descansosPorColaborador.clear();
        FacesMessage mensaje = null;
        PrimeFaces current = PrimeFaces.current();

        if (jornada != null) {
            try {
                //Insersion de asignacion de horario en la tabla asignaciones
                asignacion.setColaborador(selectAsignacion.getColaborador());
                if (selectAsignacion.getSegundodiades() == null) {
                    selectAsignacion.setSegundodiades("N/A");
                }
                asignacionesService.createAsignacion(asignacion);//Aca se agrega la nueva asignacion de horario

                //Ahora se procede a hacer la insercion a la base de datos de las asignaciones de descansos
                descansosPorColaborador = descansosService.buscarDescansosPorHorario(asignacion.getHorario());//Aca se llena una lista de descansos
                System.out.println("Los descansos que tiene pertenecen al horario son: " + descansosPorColaborador.size());
                for (int i = 0; i < descansosPorColaborador.size(); i++) {//Ciclo for para asignar los descansos al colaborador por el horario
                    asignacionDescansos.setColaborador(selectAsignacion.getColaborador());
                    asignacionDescansos.setDescanso(descansosPorColaborador.get(i));
                    asignacionDescansosService.createAsignacionDescanso(asignacionDescansos);
                    asignacionDescansos = new AsignacionDescansos();
                }
                current.executeScript("PF('datos').hide();");
                System.out.println("Horario asignado");

                FacesMessage msg = new FacesMessage("Aviso", "¡Asignación realizada correctamente!");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } catch (Exception e) {
            } finally {
                selectAsignacion = new Asignaciones();
                asignacion = new Asignaciones();
                jornada = new Jornadas();
                asignacionDescansos = new AsignacionDescansos();
                descansosPorColaborador.clear();
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
