package s.c.m.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import s.c.m.entities.Colaborador;
import s.c.m.entities.Vacaciones;
import s.c.m.entities.VacacionesPorColaborador;
import s.c.m.services.ColaboradorService;
import s.c.m.services.VacacionesPorColaboradorService;
import s.c.m.services.VacacionesService;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.criteria.CriteriaBuilder;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;

@Component
@ManagedBean
public class VacacionesBean {

    @Autowired
    VacacionesService vacacionesService;
    @Autowired
    ColaboradorService colaboradorService;
    @Autowired
    VacacionesPorColaboradorService vacacionesPorColaboradorService;

    ColaboradorBean colaboradorBean = new ColaboradorBean();
    Colaborador colaboradorSolicitante = new Colaborador();
    Vacaciones solicitudVac = new Vacaciones();
    VacacionesPorColaborador vacacionesPorColaborador = new VacacionesPorColaborador();
    private List<Vacaciones> vacaciones;
    private Date fecha;

    @PostConstruct
    public void init() {
        Vacaciones miV = new Vacaciones();
        vacaciones = vacacionesService.getAllSolVacaciones();
    }

    public ColaboradorBean getColaboradorBean() {
        return colaboradorBean;
    }

    public void setColaboradorBean(ColaboradorBean colaboradorBean) {
        this.colaboradorBean = colaboradorBean;
    }

    public Colaborador getColaboradorSolicitante() {
        return colaboradorSolicitante;
    }

    public void setColaboradorSolicitante(Colaborador colaboradorSolicitante) {
        this.colaboradorSolicitante = colaboradorSolicitante;
    }

    public Vacaciones getSolicitudVac() {
        return solicitudVac;
    }

    public void setSolicitudVac(Vacaciones solicitudVac) {
        this.solicitudVac = solicitudVac;
    }

    public VacacionesPorColaborador getVacacionesPorColaborador() {
        return vacacionesPorColaborador;
    }

    public void setVacacionesPorColaborador(VacacionesPorColaborador vacacionesPorColaborador) {
        this.vacacionesPorColaborador = vacacionesPorColaborador;
    }

    public List<Vacaciones> getVacaciones() {
        return vacaciones;
    }

    public void setVacaciones(List<Vacaciones> vacaciones) {
        this.vacaciones = vacaciones;
    }

    public Date getFecha() {
        Calendar c = Calendar.getInstance();
        return c.getTime();
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void createSolicitud(String idColaborador) throws Exception {
        FacesMessage mensaje = null;

        colaboradorSolicitante = colaboradorService.findColaborador(idColaborador);
        vacacionesPorColaborador = vacacionesPorColaboradorService.findVacacionesPorColaborador(colaboradorSolicitante);
        Format formateador = new SimpleDateFormat("yyyyMMdd");
        String fechaI = formateador.format(solicitudVac.getFechainicio());
        String fechaF = formateador.format(solicitudVac.getFechafinal());
        int fInicio = Integer.parseInt(fechaI);
        int fFinal = Integer.parseInt(fechaF);

        if (fechaI.equals(fechaF)) {
            mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Las fechas no pueden ser iguales");
        } else {
            if (fInicio >= fFinal) {
                mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Las fecha final no puede ser menor que la de inicio");
            } else {
                int diasSol = CalculaDiasSolicitados();
                int diasDispo = vacacionesPorColaborador.getDiasdisponibles();

                if (diasSol < diasDispo) {//Si los dias solicitados son menores a los disponibles puede realizar la solicitud
                    solicitudVac.setColaborador(colaboradorSolicitante);
                    solicitudVac.setEstado("Pendiente");
                    solicitudVac.setDiasSolicitados(diasSol);
                    solicitudVac.setJustificacion("Justifique la decisión");
                    System.out.println("Colaborador Solicitante desde VB: " + colaboradorSolicitante.getPk_idColaborador());
                    System.out.println("NOMBRE: " + colaboradorSolicitante.getNombre());
                    System.out.println("Fecha inicio: " + solicitudVac.getFechainicio());
                    System.out.println("Fecha final: " + solicitudVac.getFechafinal());
                    System.out.println("Dias Disponibles: " + diasDispo);
                    //Aca despues de cargar los datos en el objeto
                    vacacionesService.createVacaciones(solicitudVac);//Se llama la funcion para agregar la solicitud a la base
                    mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Solicitud realizada correctamente!!!");
                    solicitudVac = new Vacaciones();
                    //Se refresca la tabla
                    vacaciones = vacacionesService.getAllSolVacaciones();
                }else{
                    mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "No puede solicitar mas dias de los que tiene disponibles");
                }
            }
        }

        FacesContext.getCurrentInstance().addMessage(null, mensaje);
    }

    //Falta funcion que calcula los dias que pide el mae
    public int CalculaDiasSolicitados() {
        int totalDiasSolicitados = 0;
        Calendar finicio = Calendar.getInstance();
        Calendar ffinal = Calendar.getInstance();

        finicio.set(YEAR, solicitudVac.getFechainicio().getYear() + 1900);
        finicio.set(Calendar.MONTH, solicitudVac.getFechainicio().getMonth() + 1);
        finicio.set(Calendar.DAY_OF_MONTH, solicitudVac.getFechainicio().getDate());
        finicio.set(Calendar.HOUR,0);
        finicio.set(Calendar.HOUR_OF_DAY,0);
        finicio.set(Calendar.MINUTE,0);
        finicio.set(Calendar.SECOND,0);

        ffinal.set(YEAR, solicitudVac.getFechafinal().getYear() + 1900);
        ffinal.set(Calendar.MONTH, solicitudVac.getFechafinal().getMonth() + 1);
        ffinal.set(Calendar.DAY_OF_MONTH, solicitudVac.getFechafinal().getDate());
        ffinal.set(Calendar.HOUR,0);
        ffinal.set(Calendar.HOUR_OF_DAY,0);
        ffinal.set(Calendar.MINUTE,0);
        ffinal.set(Calendar.SECOND,0);

        Format formateador = new SimpleDateFormat("yyyy-MM-dd");
        String fechaI = formateador.format(finicio.getTime());
        String fechaF = formateador.format(ffinal.getTime());
        System.out.println("Fecha inicio: " + fechaI);
        System.out.println("Fecha final: " + fechaF);

        long iniMS = finicio.getTimeInMillis();
        long finMS = ffinal.getTimeInMillis();

        totalDiasSolicitados = (int) ((Math.abs(finMS-iniMS))/ (1000 * 60 * 60 * 24));//86.400.000
        totalDiasSolicitados++;

        return totalDiasSolicitados;
    }
}
