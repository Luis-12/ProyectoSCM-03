package s.c.m.beans;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.BaseFont;
import org.primefaces.PrimeFaces;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.HorizontalBarChartModel;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.optionconfig.legend.Legend;
import org.primefaces.model.charts.optionconfig.legend.LegendLabel;
import org.primefaces.model.charts.optionconfig.title.Title;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import s.c.m.entities.*;
import s.c.m.services.*;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Calendar.YEAR;

@Component
@ManagedBean
public class ReporteBean {

    Calendar c2;
    List<Colaborador> colaboradorDepartamento;
    private VacacionesPorColaborador vacacionesPorColaborador = new VacacionesPorColaborador();
    @Autowired
    ColaboradorService colaboradorService;

    //PARAMETROS PARA HACER REPORTE DETALLADO CONFORME A COLABORADOR ESPECIFICO
    private Date fechaInicioR;
    private Date fechaFinalR;
    private  Departamento departamentoReporte;
    private String cedulaReporte;
    private Colaborador colaboradorR;
    List<MarcaLaboradas> marcaLaboradasPorCYR;
    List<MarcaDescansos> marcaDescansosPorCYR;
    List<MarcaLaboradas> marcaLaboradasTardias;
    List<ReporteLlegadasTardias> llegadasTardias;
    List<AsignacionDescansos> descansosCantidad;
    private List<ReporteColaboradorH> reporteColaboradorHorarioList = new ArrayList<>();
    private List<ReporteColaboradorDetallado> reporteColaboradorDetalladosList = new ArrayList<>();
    private Asignaciones asignacionesDelColaReporte;
    private AsignacionDescansos asignacionDescansos;
    private BarChartModel barModel=null;

    private HorizontalBarChartModel modelD=null;


    @Autowired
    VacacionesPorColaboradorService vacacionesPorColaboradorService;

    @Autowired
    AsignacionesServices asignacionesServices;

    @Autowired
    AsignacionDescansosService asignacionDescansosService;

    @Autowired
    MarcaDescansoService marcaDescansoService;
    @Autowired
    MarcaLaboradaService marcaLaboradaService;

    public String limpiaObjetos(){
        System.out.println("SE ESTANNNN LIMPIANDO LOS OBJETOOOOOOS");
        this.cedulaReporte = "";
        fechaInicioR = null;
        fechaFinalR = null;
        this.reporteColaboradorDetalladosList = new ArrayList<>();
        return "InformeColaborador.xhtml";
    }

    public Colaborador getColaboradorR() {
        return colaboradorR;
    }
    public BarChartModel getBarModel() {
        return barModel;
    }

    public HorizontalBarChartModel getModelD() {
        return modelD;
    }

    public void setModelD(HorizontalBarChartModel modelD) {
        this.modelD = modelD;
    }

    public List<ReporteLlegadasTardias> getLlegadasTardias() {
        return llegadasTardias;
    }

    public void setLlegadasTardias(List<ReporteLlegadasTardias> llegadasTardias) {
        this.llegadasTardias = llegadasTardias;
    }

    public List<ReporteColaboradorH> getReporteColaboradorHorarioList() {
        return reporteColaboradorHorarioList;
    }
    public void setReporteColaboradorHorarioList(List<ReporteColaboradorH> reporteColaboradorHorarioList) {
        this.reporteColaboradorHorarioList = reporteColaboradorHorarioList;
    }

    public void setBarModel(BarChartModel barModel) {
        this.barModel = barModel;
    }
    public List<ReporteColaboradorDetallado> getReporteColaboradorDetalladosList() {
        return reporteColaboradorDetalladosList;
    }
    public void setColaboradorR(Colaborador colaboradorR) {
        this.colaboradorR = colaboradorR;
    }
    public void setReporteColaboradorDetalladosList(List<ReporteColaboradorDetallado> reporteColaboradorDetalladosList) {
        this.reporteColaboradorDetalladosList = reporteColaboradorDetalladosList;
    }
    public VacacionesPorColaborador getVacacionesPorColaborador() {
        return vacacionesPorColaborador;
    }

    public void setVacacionesPorColaborador(VacacionesPorColaborador vacacionesPorColaborador) {
        this.vacacionesPorColaborador = vacacionesPorColaborador;
    }
    public Date getFechaInicioR() {
        return fechaInicioR;
    }

    public void setFechaInicioR(Date fechaInicioR) {
        this.fechaInicioR = fechaInicioR;
    }

    public Date getFechaFinalR() {
        return fechaFinalR;
    }

    public void setFechaFinalR(Date fechaFinalR) {
        this.fechaFinalR = fechaFinalR;
    }

    public String getCedulaReporte() {
        return cedulaReporte;
    }

    public void setCedulaReporte(String cedulaReporte) {
        this.cedulaReporte = cedulaReporte;
    }

    public Departamento getDepartamentoReporte() {
        return departamentoReporte;
    }

    public List<Colaborador> getColaboradorDepartamento() {
        return colaboradorDepartamento;
    }



    public void setColaboradorDepartamento(List<Colaborador> colaboradorDepartamento) {
        this.colaboradorDepartamento = colaboradorDepartamento;
    }

    public void setDepartamentoReporte(Departamento departamentoReporte) {
        this.departamentoReporte = departamentoReporte;
    }

    public int calculaMesesMinimos(Colaborador c){
        int meses = 0;
        Date fechaInicioL = c.getFechaInicioLaboral();
        Calendar fechaInicio = Calendar.getInstance();
        fechaInicio.setTime(fechaInicioL);//Cargo la fecha de inicio laboral al calendar

        Calendar fechaActual = Calendar.getInstance();

        //Saco los meses de la fecha de inicio y de la actual
        int mesIni = fechaInicio.get(Calendar.MONTH) +1;
        //System.out.println("Mes ini: " + mesIni);
        int mesAct = fechaActual.get(Calendar.MONTH) +1;
        //System.out.println("Mes act: " + mesAct);

        //Saco los dias de la fecha de inicio y de la actual
        int diaIni = fechaInicio.get(Calendar.DAY_OF_MONTH);
        int diaAct = fechaActual.get(Calendar.DAY_OF_MONTH);

        //Saco los years de la fecha de inicio y de la actual
        int yearIni = fechaInicio.get(Calendar.YEAR);
        //System.out.println("Year ini: " + yearIni);
        int yearAct = fechaActual.get(Calendar.YEAR);
        //System.out.println("Year act: " + yearAct);
        int yearDiferencia = yearAct - yearIni;
        if(diaAct >= diaIni && yearIni == yearAct){//Si es el mismo year
            meses = mesAct - mesIni;//Si el dia es mayor o igual se resta normal
        }
        else if(diaAct >= diaIni && yearIni <= yearAct){//Si es un year mayor
            mesAct = mesAct + (yearDiferencia * 12);//Se le suman los meses del year
            meses = mesAct - mesIni;//Si el dia es mayor o igual se resta normal
        }
        else if(diaAct < diaIni && yearIni == yearAct){
            meses = mesAct - mesIni;
            meses--;//Si el dia de entrada no se cumple se resta un mes
        }
        else if(diaAct < diaIni && yearIni <= yearAct){
            mesAct = mesAct + (yearDiferencia * 12);//Se le suman los meses del year
            meses = mesAct - mesIni;
            meses--;//Si el dia de entrada no se cumple se resta un mes
        }

        System.out.println("Meses laborados hasta el momento: "+ meses);
        return meses;
    }


    public double diasDisponibles(Colaborador colaborador) throws ParseException {//Funcion para calcular los dias disponible de vacaciones
 
        VacacionesPorColaborador disponibles = new VacacionesPorColaborador();
        disponibles = vacacionesPorColaboradorService.findVacacionesPorColaborador(colaborador);//Se consulta los dia disponibles
        int anios = calculaAnios(colaborador);//Se consulta los years que este laborando el cola en la empresa
        int diasDisf = disponibles.getDiasdisfrutados();
        double diasLibresTotales = 0.0;

        if(anios == 0){//Si solo ha trabajado meses
            diasLibresTotales = calculaMesesMinimos(colaborador) - diasDisf;//Los meses que tenga laborados cuentan como dias libres
        } 
        else if(anios > 0) {//Si ha trabajado un anio ya se le pueden contar los dias libres
            for (int i = 1; i <= anios; i++) {//Cambio aqui
                if (i == 1 || i == 2) {//IF QUE INCLUYE TAMBIEN EL PRIMER DIA
                    //if (i == 2) {
                    diasLibresTotales = diasLibresTotales + (1 * 12);
                } else if (i == 3 || i == 4) {
                    diasLibresTotales = diasLibresTotales + (1.25 * 12);
                } else if (i >= 5) {
                    diasLibresTotales = diasLibresTotales + (1.50 * 12);
                }
            }
            diasLibresTotales = (diasLibresTotales + CalculaDiasMesesExtra(colaborador)) - diasDisf;
            System.out.println("Dias totales disponibles: " + diasLibresTotales);
        }
            System.out.println("Dias totales disponibles: " + diasLibresTotales);
            disponibles.setDiasdisponibles((int) diasLibresTotales);
            vacacionesPorColaboradorService.updateVacacionesPorColaborador(disponibles);
        //}
        return diasLibresTotales;
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
            if (anio == 1 || anio == 2) {//
                dia = 1;
            } else if (anio == 3 || anio == 4) {
                dia = 1.25;
            } else if (anio >= 5) {
                dia = 1.50;
            }

            if ((c1.get(Calendar.MONTH) + 1) == (c2.get(Calendar.MONTH) + 1)) {//Si no ha pasado el mes de entrada laboral
                mesesExtra = 0;
            } else if ((c1.get(Calendar.MONTH) + 1) > (c2.get(Calendar.MONTH) + 1)) {//Si paso el mes de entrada laboral ya hay mese extra
                mesesExtra = (12 - (c1.get(Calendar.MONTH) + 1)) + (c2.get(Calendar.MONTH) + 1);//Bien creo
                System.out.println("Meses extra: " + mesesExtra);
            }/*else if((c1.get(Calendar.MONTH)+1) < (c2.get(Calendar.MONTH)+1)){
                mesesExtra = (c2.get(Calendar.MONTH)+1);// + (c1.get(Calendar.MONTH)+1);
                System.out.println("Meses extra: " + mesesExtra);
            }*/
            cantidadDiasMesesExtra = mesesExtra * dia;
        }

        return (int) cantidadDiasMesesExtra;
    }

    public List<Colaborador> colaboradroLaborando()
    {
        return  marcaLaboradaService.coladorLaborando();
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
        System.out.println("Cantidad de anios laborados: " + anios);

        return anios;
    }

    //reporte llegadas tardias
    public void buscarDatosLlegadasTardias() throws Exception {//Funcion para generar y consultar datos de colaborador detallado
        Format formateador = new SimpleDateFormat("yyyyMMdd");
        String fechaI = formateador.format(fechaInicioR);
        String fechaF = formateador.format(fechaFinalR);
        int fInicio = Integer.parseInt(fechaI);
        int fFinal = Integer.parseInt(fechaF);

        colaboradorR=new Colaborador();
        llegadasTardias = new ArrayList<>();
        marcaLaboradasPorCYR=new ArrayList<>();


            if (fInicio >= fFinal) {
                addMessage("Aviso", "La Fecha Final debe ser mayor a la Fecha Inicial");
            } else {
                marcaLaboradasPorCYR = marcaLaboradaService.findLlegadasTardias(fechaInicioR, fechaFinalR);
                if (marcaLaboradasPorCYR.size() != 0) {//Si se encontro alguna marca tardia se hace el reporte
                    for (MarcaLaboradas ml : marcaLaboradasPorCYR) {//Por cada marca laborada

                        if (ml.getDescripcion() != null) {//Si es diferente de null quiere decir que justifico por lo tanto llego tarde
                            ReporteLlegadasTardias miRT = new ReporteLlegadasTardias();
                            Calendar c = Calendar.getInstance();
                            c.setTime(ml.getFechaMarca());
                            c.set(Calendar.HOUR_OF_DAY, ml.getHoraEntrada().getHours());
                            c.set(Calendar.MINUTE, ml.getHoraEntrada().getMinutes());
                            c.set(Calendar.SECOND, ml.getHoraEntrada().getSeconds());
                            colaboradorR = colaboradorService.findColaboradorYEstado(ml.getColaborador().getPk_idColaborador());
                            miRT.setCedula(colaboradorR.getPk_idColaborador());
                            miRT.setNombre(colaboradorR.getNombre());
                            SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                            String fecha = DATE_FORMAT.format(c.getTime());
                            miRT.setFecha(fecha);
                            miRT.setJustificacion(ml.getDescripcion());
                            llegadasTardias.add(miRT);
                        }
                    }
                } else {
                    addMessage("Aviso", "NO se encontraron Marcas Laboradas con ese Rango");
                }
            }
    }



        //reporte colaborador detallado
    public void buscarDatosColaboradorRDetallado() throws Exception {//Funcion para generar y consultar datos de colaborador detallado

        marcaLaboradasPorCYR = new ArrayList<>();
        marcaDescansosPorCYR = new ArrayList<>();
        marcaLaboradasTardias = new ArrayList<>();
        reporteColaboradorDetalladosList = new ArrayList<>();
        List<MarcaDescansos> mdAux = new ArrayList<>();
        ReporteColaboradorDetallado miRC = new ReporteColaboradorDetallado();
        asignacionesDelColaReporte = new Asignaciones();
        double totalHorasLaboradas = 0;
        double totalHorasDescansadas = 0;
        double horasFinal = 0;
        double diasDisponibles = 0.0;

        Format formateador = new SimpleDateFormat("yyyyMMdd");
        String fechaI = formateador.format(fechaInicioR);
        String fechaF = formateador.format(fechaFinalR);
        int fInicio = Integer.parseInt(fechaI);
        int fFinal = Integer.parseInt(fechaF);
        colaboradorR = colaboradorService.findColaboradorYEstado(cedulaReporte);

        if (colaboradorR != null) {//Quiere decir que se encontro el colaborador
            if (fInicio >= fFinal) {
                addMessage("Aviso", "La Fecha Final debe ser mayor a la Fecha Inicial");
            } else {
                marcaLaboradasPorCYR = marcaLaboradaService.findMarcasLaboradasPorRango(fechaInicioR, fechaFinalR, colaboradorR);
                if (marcaLaboradasPorCYR.size() != 0) {//Si se encontro alguna marca se hace el reporte
                    //Aca empiezan los calculos y la magia
                    diasDisponibles = diasDisponibles(colaboradorR);
                    for (MarcaLaboradas ml : marcaLaboradasPorCYR) {//Por cada marca laborada
                        mdAux = marcaDescansoService.buscarMarcaPorMarcaLab(ml);//Busca descansos
                        for (MarcaDescansos md : mdAux) {//For para validar descansos terminados
                            if (md.getHoraFin() != null) {//If para llenar decansos por C y R finalizados
                                marcaDescansosPorCYR.add(md);
                                totalHorasDescansadas = totalHorasDescansadas +
                                        calculaHorasEntreDosTiempos(md.getHoraInicio(), md.getHoraFin());
                            }
                        }
                        totalHorasLaboradas = totalHorasLaboradas +
                                calculaHorasEntreDosTiempos(ml.getHoraEntrada(), ml.getHoraSalida());
                        if (ml.getDescripcion() != null) {//Si es diferente de null quiere decir que justifico por lo tanto llego tarde
                            marcaLaboradasTardias.add(ml);
                        }
                    }
                    //System.out.println("CANTIDAD DE DESCANSO ENCONTRADOS:" + marcaDescansosPorCYR.size());
                    System.out.println("CANTIDAD DE TARDIAS:" + marcaLaboradasTardias.size());
                    System.out.println("Dias Disponibles:" + diasDisponibles);

                    horasFinal = totalHorasLaboradas - totalHorasDescansadas;
                    //Se carga el objeto del reporte para agregarlo a la lista
                    //Se debe buscar tambien la asignacion del horario
                    asignacionesDelColaReporte = asignacionesServices.buscarHorario(colaboradorR);
                    miRC.setCedula(colaboradorR.getPk_idColaborador());
                    miRC.setNombre(colaboradorR.getNombre());
                    miRC.setTipoJornada(asignacionesDelColaReporte.getHorario().getJornada().getDescripcion());
                    miRC.setHorario(" " + asignacionesDelColaReporte.getHorario().getHoraentrada() + " " +
                            asignacionesDelColaReporte.getHorario().getHorasalida() + " ");
                    miRC.setCantHorasLaboradas(horasFinal);
                    miRC.setCantLlegadasTardias(marcaLaboradasTardias.size());
                    miRC.setDiasDispoVacaciones(diasDisponibles);

                    reporteColaboradorDetalladosList.add(miRC);//Se llena la lista de la tabla
                    GraphicColaboradorDetallado();
                    addMessage("Aviso", "Horas Laboradas: " + totalHorasLaboradas +
                            " Horas Descansadas: " + totalHorasDescansadas + " Total H fin: " + horasFinal);
                } else {
                    addMessage("Aviso", "NO se encontraron Marcas Laboradas con ese Rango");
                }
            } 
        } else {
            addMessage("Aviso", "NO se encontró el Colaborador con la cédula: " + cedulaReporte);
        }

    }

    public double calculaHorasEntreDosTiempos(Time tInicio, Time tFinal) {
        double horasTotales = 0.0;
        long iniM = tInicio.getTime();
        long finM=0;
        try {
             finM = tFinal.getTime();
        }catch (Exception e){}

        if(finM!=0) {
            if (finM < iniM) {//Si la fecha de marca fin es menor a la fecha de marca inicio quiere decir que marco el otro dia
                finM = finM + 86400000;//Por lo tanto se le suman los milisegundos de un dia
                horasTotales = (double) ((Math.abs(finM - iniM)) / (1000 * 60 * 60));//Se hace le calculo normal
                System.out.println("Horas Calculadas Para dos dias: " + horasTotales);
            } else {//Si no es menor la fecha fin que la ini
                horasTotales = (double) ((Math.abs(finM - iniM)) / (1000 * 60 * 60));//Se hace le calculo normal
                System.out.println("Horas Calculadas: " + horasTotales);
            }
        }else {
            horasTotales=0.0;
        }

        return horasTotales;
    }


    public void preProcessPDFReporteCD(Object document) throws IOException, BadElementException, DocumentException {
        Format formateador = new SimpleDateFormat("yyyy-MM-dd");
        String fechaI = formateador.format(fechaInicioR);
        String fechaF = formateador.format(fechaFinalR);
        List<MarcaDescansos> auxMD = new ArrayList<>();
        Color azul = new Color(31, 97, 141);
        Color azulC = new Color(46, 134, 193);

        Document pdf = (Document) document;
        pdf.addTitle("Reporte Detallado por Colaborador " + colaboradorR.getNombre()
                + "\n" + colaboradorR.getPk_idColaborador());
        pdf.open();
        pdf.setPageSize(PageSize.A4);

        //Preparo fuentes
        BaseFont bfTitulos = BaseFont.createFont(BaseFont.HELVETICA_BOLDOBLIQUE,BaseFont.WINANSI,BaseFont.EMBEDDED);
        com.lowagie.text.Font fuenteTitulos = new com.lowagie.text.Font(bfTitulos);
        com.lowagie.text.Font informacion = new com.lowagie.text.Font(bfTitulos);
        fuenteTitulos.setSize(14);
        fuenteTitulos.setColor(azul);
        informacion.setColor(Color.BLACK);
        informacion.setSize(14);


        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        String logo = externalContext.getRealPath("") + File.separator + "css" + File.separator + "imagen" + File.separator + "logo1.jpg";

        pdf.add(com.lowagie.text.Image.getInstance(logo));

        Paragraph pTC = new Paragraph("Reporte Detallado del Colaborador: ", fuenteTitulos);
        Paragraph pTC1 = new Paragraph( colaboradorR.getNombre() + "  " + colaboradorR.getPk_idColaborador(),informacion);
        pTC.setAlignment("left");
        pTC.setSpacingBefore(30);
        pTC.setSpacingBefore(20);
        pTC1.setAlignment("center");
        pdf.add(pTC);
        pdf.add(pTC1);

        Paragraph pT = new Paragraph("Datos de Marcas Realizadas en el Rango de: " , fuenteTitulos);
        Paragraph pt1 = new Paragraph(fechaI + " a " + fechaF,informacion);
        pT.setAlignment("left");
        pTC.setSpacingBefore(15);
        pt1.setAlignment("center");
        pdf.add(pT);
        pdf.add(pt1);

        Paragraph titulo = new Paragraph("Información del Horario" , fuenteTitulos);
        titulo.setAlignment("left");
        titulo.setSpacingBefore(10);
        pdf.add(titulo);

        BaseFont bfMarca = BaseFont.createFont(BaseFont.TIMES_ROMAN,BaseFont.WINANSI,BaseFont.EMBEDDED);
        com.lowagie.text.Font marcaft = new com.lowagie.text.Font(bfMarca);
        marcaft.setSize(14);
        marcaft.setColor(Color.BLACK);

        BaseFont bfDes = BaseFont.createFont(BaseFont.TIMES_BOLDITALIC,BaseFont.WINANSI,BaseFont.EMBEDDED);
        com.lowagie.text.Font subt = new com.lowagie.text.Font(bfDes);
        subt.setSize(14);
        subt.setColor(azulC);

        BaseFont bfNot = BaseFont.createFont(BaseFont.TIMES_ROMAN,BaseFont.WINANSI,BaseFont.EMBEDDED);
        com.lowagie.text.Font not = new Font(bfDes);
        not.setSize(14);
        not.setColor(Color.red);

        Paragraph par = new Paragraph("Jornada: " + asignacionesDelColaReporte.getHorario().getJornada().getDescripcion()
                +"\n"+ " Horario: " + asignacionesDelColaReporte.getHorario().getHoraentrada() + " " +
                asignacionesDelColaReporte.getHorario().getHorasalida(),marcaft);
        par.setAlignment("left");
        pdf.add(par);

        Paragraph tk = new Paragraph("Información de las Marcas" , fuenteTitulos);
        tk.setAlignment("left");
        tk.setSpacingBefore(8);
        pdf.add(tk);

        for (MarcaLaboradas ml : marcaLaboradasPorCYR) {//Se hace el proceso de carga en el pdf para cada marca laborada
            Paragraph parML = new Paragraph( "Fecha de la Marca: "+ formateador.format(ml.getFechaMarca()) +"\n Hora de Entrada " + ml.getHoraEntrada()
                    + "\n Hora de Salida: " + ml.getHoraSalida()
                    + "\n Horas Realizadas en Jornada Aprox: " + calculaHorasEntreDosTiempos(ml.getHoraEntrada(),ml.getHoraSalida()), marcaft);
            parML.setAlignment("left");
            parML.setSpacingBefore(1);
            pdf.add(parML);

            for (MarcaDescansos md : marcaDescansosPorCYR) {//Se buscan las marca descanso de la marca laborada en cuestion
                if(md.getMarcaLaboradas().getPk_idMarcasLaboradas() == ml.getPk_idMarcasLaboradas()){
                    auxMD.add(md);//LLeno con los descanso de la marca
                }
            }
            if(auxMD.size() > 0){
                Paragraph parTI = new Paragraph("Descansos Realizados en la Jornada: ",subt);
                parTI.setAlignment("left");
                parTI.setSpacingAfter(5);
                pdf.add(parTI);
            }else{
                Paragraph parND = new Paragraph("    No Registró Descansos",not);
                parND.setAlignment("left");
                parND.setSpacingAfter(5);
                pdf.add(parND);
            }
            for(MarcaDescansos mdDmL : auxMD){//Luego de haber llenado la lista con los descansos de la marca
                //Se ingresan al pdf
                Paragraph parMD = new Paragraph("   Descripción: " + mdDmL.getDescansos().getDescripcion() + "\n"
                        + "    Hora de Inicio: " + mdDmL.getHoraInicio() + "\n"
                        + "    Hora de Finalización: " + mdDmL.getHoraFin() + "\n"
                        + "    Horas Disfrutadas en el Descanso Aprox: " + calculaHorasEntreDosTiempos(mdDmL.getHoraInicio(),mdDmL.getHoraFin()), marcaft);
                parMD.setAlignment("left");
                parMD.setSpacingAfter(5);
                pdf.add(parMD);
            }
            //Se limpia la lista de descansos para que no jale los de la marca laborada anterior
            auxMD.clear();
        }

        Paragraph p = new Paragraph("Marcas de Llegadas Tardías",subt);
        p.setAlignment("left");
        pdf.add(p);
        //System.out.println("CANTIDAD DE LLEGADAS TARDIAS PDF: " + marcaLaboradasTardias);
        for (MarcaLaboradas mt : marcaLaboradasTardias) {
            Paragraph p3 = new Paragraph("Fecha de Marca Tardía: "+ formateador.format(mt.getFechaMarca())+"\n"
                    + "Justificación: "+ mt.getDescripcion(), marcaft);
            p3.setAlignment("left");
            p3.setSpacingAfter(5);
            pdf.add(p3);
        }

        Paragraph pR = new Paragraph("Resumen de la Tabla",fuenteTitulos);
        pR.setAlignment("center");
        pR.setSpacingAfter(50);
        pR.setSpacingAfter(10);
        pdf.add(pR);
    }


    public void preProcessPDFReporteTardias(Object document) throws IOException, BadElementException, DocumentException {
        Format formateador = new SimpleDateFormat("yyyy-MM-dd");
        String fechaI = formateador.format(fechaInicioR);
        String fechaF = formateador.format(fechaFinalR);
        List<MarcaDescansos> auxMD = new ArrayList<>();
        Color azul = new Color(31, 97, 141);
        Color azulC = new Color(46, 134, 193);

        Document pdf = (Document) document;
        pdf.addTitle("Reporte llegadas tardias");
        pdf.open();
        pdf.setPageSize(PageSize.A4);

        //Preparo fuentes
        BaseFont bfTitulos = BaseFont.createFont(BaseFont.HELVETICA_BOLDOBLIQUE,BaseFont.WINANSI,BaseFont.EMBEDDED);
        com.lowagie.text.Font fuenteTitulos = new com.lowagie.text.Font(bfTitulos);
        com.lowagie.text.Font informacion = new com.lowagie.text.Font(bfTitulos);
        fuenteTitulos.setSize(14);
        fuenteTitulos.setColor(azul);
        informacion.setColor(Color.BLACK);
        informacion.setSize(14);


        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        String logo = externalContext.getRealPath("") + File.separator + "css" + File.separator + "imagen" + File.separator + "logo1.jpg";

        pdf.add(com.lowagie.text.Image.getInstance(logo));

        Paragraph pTC = new Paragraph("Reporte llegadas tardías ", fuenteTitulos);
        pTC.setAlignment("center");
        pTC.setSpacingBefore(30);
        pTC.setSpacingAfter(20);
        pdf.add(pTC);

        Paragraph pT = new Paragraph("Datos de llegadas tardías en el Rango de: " , fuenteTitulos);
        Paragraph pt1 = new Paragraph(fechaI + " a " + fechaF,informacion);
        pT.setAlignment("center");
        pTC.setSpacingBefore(15);
        pt1.setAlignment("center");
        pt1.setSpacingAfter(20);

        pdf.add(pT);
        pdf.add(pt1);


        BaseFont bfMarca = BaseFont.createFont(BaseFont.TIMES_ROMAN,BaseFont.WINANSI,BaseFont.EMBEDDED);
        com.lowagie.text.Font marcaft = new com.lowagie.text.Font(bfMarca);
        marcaft.setSize(14);
        marcaft.setColor(Color.BLACK);

        BaseFont bfDes = BaseFont.createFont(BaseFont.TIMES_BOLDITALIC,BaseFont.WINANSI,BaseFont.EMBEDDED);
        com.lowagie.text.Font subt = new com.lowagie.text.Font(bfDes);
        subt.setSize(14);
        subt.setColor(azulC);

        BaseFont bfNot = BaseFont.createFont(BaseFont.TIMES_ROMAN,BaseFont.WINANSI,BaseFont.EMBEDDED);
        com.lowagie.text.Font not = new Font(bfDes);
        not.setSize(14);
        not.setColor(Color.red);
    }

    public void GraphicColaboradorDetallado(){ //métodos del grafico colaborador detallado


        ReporteColaboradorDetallado reporte=reporteColaboradorDetalladosList.get(0);

        barModel = new BarChartModel();
        ChartData data = new ChartData();

        BarChartDataSet barDataSet = new BarChartDataSet();
        barDataSet.setLabel("Colaborador Detallado");

        List<Number> values = new ArrayList<>();
        values.add(reporte.getCantHorasLaboradas());
        values.add(reporte.getCantLlegadasTardias());
        values.add(reporte.getDiasDispoVacaciones());
        barDataSet.setData(values);

        List<String> bgColor = new ArrayList<>();
        bgColor.add("rgba(255, 99, 132, 0.2)");
        bgColor.add("rgba(54, 162, 235, 0.2)");
        bgColor.add("rgba(255, 205, 86, 0.2)");

        barDataSet.setBackgroundColor(bgColor);

        List<String> borderColor = new ArrayList<>();
        borderColor.add("rgb(255, 99, 132)");
        borderColor.add("rgb(54, 162, 235)");
        borderColor.add("rgb(255, 205, 86)");
        barDataSet.setBorderColor(borderColor);
        barDataSet.setBorderWidth(1);

        data.addChartDataSet(barDataSet);

        List<String> labels = new ArrayList<>();
        labels.add("Horas Laboradas");
        labels.add("Llegadas Tardías");
        labels.add("Vacaciones Disponibles");
        data.setLabels(labels);

        //Data
        barModel.setData(data);

        //Options
        BarChartOptions options = new BarChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        CartesianLinearTicks ticks = new CartesianLinearTicks();
        ticks.setBeginAtZero(true);
        linearAxes.setTicks(ticks);
        cScales.addYAxesData(linearAxes);
        options.setScales(cScales);


        Legend legend = new Legend();
        legend.setDisplay(true);
        legend.setPosition("top");
        LegendLabel legendLabels = new LegendLabel();
        legendLabels.setFontStyle("Bold");
        legendLabels.setFontColor("#0c0b0b");
        legendLabels.setFontSize(14);
        legend.setLabels(legendLabels);
        options.setLegend(legend);

        barModel.setOptions(options);

    }

    int ausencias(String id){
        List<ReporteLlegadasTardias> result = llegadasTardias.stream()
                .filter(item -> item.getCedula().equals(id))
                .collect(Collectors.toList());
        return result.size();
    }


    public void buscarDatosColaboradorDetalladoPorDepartamento() throws Exception {//Funcion para generar y consultar datos de colaborador detallado
        marcaLaboradasPorCYR = new ArrayList<>();
        marcaDescansosPorCYR = new ArrayList<>();
        marcaLaboradasTardias = new ArrayList<>();
        reporteColaboradorDetalladosList = new ArrayList<>();
        List<MarcaDescansos> mdAux = new ArrayList<>();
        ReporteColaboradorDetallado miRC;

            asignacionesDelColaReporte = new Asignaciones();
        double totalHorasLaboradas = 0;
        double totalHorasDescansadas = 0;
        double horasFinal = 0;
        double diasDisponibles = 0.0;

        Format formateador = new SimpleDateFormat("yyyyMMdd");
        String fechaI = formateador.format(fechaInicioR);
        String fechaF = formateador.format(fechaFinalR);
        int fInicio = Integer.parseInt(fechaI);
        int fFinal = Integer.parseInt(fechaF);
        colaboradorDepartamento = colaboradorService.findColaboradorDepartamento(departamentoReporte.getPk_idDepartamento());

        if (colaboradorDepartamento.size() != 0) {//Quiere decir que se encontro el colaborador
            if (fInicio >= fFinal) {
                addMessage("Aviso", "La Fecha Final debe ser mayor a la Fecha Inicial");
            }
            else {
                for(Colaborador colaborador : colaboradorDepartamento){
                    marcaLaboradasPorCYR = marcaLaboradaService.findMarcasLaboradasPorRango(fechaInicioR, fechaFinalR, colaborador);
                    if (marcaLaboradasPorCYR.size() != 0) {//Si se encontro alguna marca se hace el reporte
                        //Aca empiezan los calculos y la magia
                        diasDisponibles = diasDisponibles(colaborador);
                        for (MarcaLaboradas ml : marcaLaboradasPorCYR) {//Por cada marca laborada
                            mdAux = marcaDescansoService.buscarMarcaPorMarcaLab(ml);//Busca descansos
                            for (MarcaDescansos md : mdAux) {//For para validar descansos terminados
                                if (md.getHoraFin() != null) {//If para llenar decansos por C y R finalizados
                                    marcaDescansosPorCYR.add(md);
                                    totalHorasDescansadas = totalHorasDescansadas +
                                            calculaHorasEntreDosTiempos(md.getHoraInicio(), md.getHoraFin());
                                }
                            }
                            totalHorasLaboradas = totalHorasLaboradas +
                                    calculaHorasEntreDosTiempos(ml.getHoraEntrada(), ml.getHoraSalida());
                            if (ml.getDescripcion() != null) {//Si es diferente de null quiere decir que justifico por lo tanto llego tarde
                                marcaLaboradasTardias.add(ml);
                            }
                        }
                        horasFinal = totalHorasLaboradas - totalHorasDescansadas;
                        asignacionesDelColaReporte = asignacionesServices.buscarHorario(colaborador);
                        miRC = new ReporteColaboradorDetallado();
                        miRC.setCedula(colaborador.getPk_idColaborador());
                        miRC.setNombre(colaborador.getNombre());
                        miRC.setTipoJornada(asignacionesDelColaReporte.getHorario().getJornada().getDescripcion());
                        miRC.setHorario(" " + asignacionesDelColaReporte.getHorario().getHoraentrada() + " " +
                                asignacionesDelColaReporte.getHorario().getHorasalida() + " ");
                        miRC.setCantHorasLaboradas(horasFinal);
                        miRC.setCantLlegadasTardias(marcaLaboradasTardias.size());
                        miRC.setDiasDispoVacaciones(diasDisponibles);

                        reporteColaboradorDetalladosList.add(miRC);//Se llena la lista de la tabla
                        GraphicColaboradorxDepartamento();
                    }
                }

            }

        }

    }

    public void GraphicColaboradorxDepartamento(){

        modelD = new HorizontalBarChartModel();
        for(ReporteColaboradorDetallado miRC : reporteColaboradorDetalladosList){
            ChartSeries horas= new ChartSeries();
            ChartSeries tardias = new ChartSeries();
            ChartSeries vacaciones = new ChartSeries();
            horas.setLabel("Horas Laboradas");
            tardias.setLabel("Marcas Tardías");
            vacaciones.setLabel("Vacaciones Disponibles");
            horas.set(miRC.getCedula(), miRC.getCantHorasLaboradas());
            tardias.set(miRC.getCedula(), miRC.getCantLlegadasTardias());
            vacaciones.set(miRC.getCedula(), miRC.getDiasDispoVacaciones());
            modelD.addSeries(horas);
            modelD.addSeries(tardias);
            modelD.addSeries(vacaciones);
        }

        modelD.setTitle("Gráfica");
        modelD.setLegendPosition("e");
        modelD.setStacked(true);
        Axis xAxis = modelD.getAxis(AxisType.X);
        Axis yAxis = modelD.getAxis(AxisType.Y);
        yAxis.setLabel("Cedula");

    }

    public void buscarDatosHorarioPorDepartamento() throws Exception {
        ReporteColaboradorH miRC;
        reporteColaboradorHorarioList = new ArrayList<>();
        asignacionesDelColaReporte = new Asignaciones();
        asignacionDescansos = new AsignacionDescansos();
        colaboradorDepartamento = colaboradorService.findColaboradorDepartamento(departamentoReporte.getPk_idDepartamento());

        if (colaboradorDepartamento.size() != 0) {

            for(Colaborador colaborador : colaboradorDepartamento){
                asignacionesDelColaReporte = asignacionesServices.buscarHorario(colaborador);
                descansosCantidad = asignacionDescansosService.buscarDescansosAsignadosPorColaborador(colaborador);
                convertirDia();
                convertirDia2();
                miRC= new ReporteColaboradorH();
                miRC.setCedula(colaborador.getPk_idColaborador());
                miRC.setNombre(colaborador.getNombre());
                miRC.setHorario(" " + asignacionesDelColaReporte.getHorario().getHoraentrada() + " " +
                        asignacionesDelColaReporte.getHorario().getHorasalida() + " ");
                miRC.setCantidadDescansos(descansosCantidad.size());
                miRC.setDiaDescanso(asignacionesDelColaReporte.getDiaDescanso());
                miRC.setDia2(asignacionesDelColaReporte.getSegundodiades());

                reporteColaboradorHorarioList.add(miRC);//Se llena la lista de la tabla
            }
        }else {
            addMessage("Aviso", "NO se encontraron colaboradores en el departamento seleccionado");
        }
    }

    public void convertirDia() {//Convierte el formato del día
        String diaDescanso = null;

        if (asignacionesDelColaReporte.getDiaDescanso().equals("LU")) {
            diaDescanso = "Lunes";
        } else if (asignacionesDelColaReporte.getDiaDescanso().equals("MA")) {
            diaDescanso = "Martes";
        } else if (asignacionesDelColaReporte.getDiaDescanso().equals("MI")) {
            diaDescanso = "Miércoles";
        } else if (asignacionesDelColaReporte.getDiaDescanso().equals("JU")) {
            diaDescanso = "Jueves";
        } else if (asignacionesDelColaReporte.getDiaDescanso().equals("VI")) {
            diaDescanso = "Viernes";
        } else if (asignacionesDelColaReporte.getDiaDescanso().equals("SA")) {
            diaDescanso = "Sábado";
        } else if (asignacionesDelColaReporte.getDiaDescanso().equals("DO")) {
            diaDescanso = "Domingo";
        } else {
            diaDescanso = asignacionesDelColaReporte.getDiaDescanso();
        }
        asignacionesDelColaReporte.setDiadescanso(diaDescanso);
    }
    public void convertirDia2() {//Convierte el formato del día
        String diaDescanso2 = null;

        if (asignacionesDelColaReporte.getSegundodiades().equals("LU")) {
            diaDescanso2 = "Lunes";
        } else if (asignacionesDelColaReporte.getSegundodiades().equals("MA")) {
            diaDescanso2 = "Martes";
        } else if (asignacionesDelColaReporte.getSegundodiades().equals("MI")) {
            diaDescanso2 = "Miércoles";
        } else if (asignacionesDelColaReporte.getSegundodiades().equals("JU")) {
            diaDescanso2 = "Jueves";
        } else if (asignacionesDelColaReporte.getSegundodiades().equals("VI")) {
            diaDescanso2 = "Viernes";
        } else if (asignacionesDelColaReporte.getSegundodiades().equals("SA")) {
            diaDescanso2 = "Sábado";
        } else if (asignacionesDelColaReporte.getSegundodiades().equals("DO")) {
            diaDescanso2 = "Domingo";
        } else {
            diaDescanso2 = asignacionesDelColaReporte.getSegundodiades();
        }
        asignacionesDelColaReporte.setSegundodiades(diaDescanso2);
    }


    public void preProcessPDFReporteHorarios(Object document) throws IOException, BadElementException, DocumentException {

        Color azul = new Color(31, 97, 141);
        Color azulC = new Color(46, 134, 193);

        Document pdf = (Document) document;
        pdf.addTitle("Reporte Horarios por Departamento" + departamentoReporte.getNombre());
        pdf.open();
        pdf.setPageSize(PageSize.A4);

        //Preparo fuentes
        BaseFont bfTitulos = BaseFont.createFont(BaseFont.HELVETICA_BOLDOBLIQUE,BaseFont.WINANSI,BaseFont.EMBEDDED);
        com.lowagie.text.Font fuenteTitulos = new com.lowagie.text.Font(bfTitulos);
        com.lowagie.text.Font informacion = new com.lowagie.text.Font(bfTitulos);
        fuenteTitulos.setSize(14);
        fuenteTitulos.setColor(azul);
        informacion.setColor(Color.BLACK);
        informacion.setSize(14);

        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        String logo = externalContext.getRealPath("") + File.separator + "css" + File.separator + "imagen" + File.separator + "logo1.jpg";

        pdf.add(com.lowagie.text.Image.getInstance(logo));

        Paragraph pTC = new Paragraph("Reporte Horarios Del Departamento " + departamentoReporte.getNombre(), fuenteTitulos);
        pTC.setAlignment("center");
        pTC.setSpacingBefore(30);
        pTC.setSpacingAfter(20);
        pdf.add(pTC);


        BaseFont bfDes = BaseFont.createFont(BaseFont.TIMES_BOLDITALIC,BaseFont.WINANSI,BaseFont.EMBEDDED);
        com.lowagie.text.Font subt = new com.lowagie.text.Font(bfDes);
        subt.setSize(14);
        subt.setColor(azulC);

        BaseFont bfNot = BaseFont.createFont(BaseFont.TIMES_ROMAN,BaseFont.WINANSI,BaseFont.EMBEDDED);
        com.lowagie.text.Font not = new Font(bfDes);
        not.setSize(14);
        not.setColor(Color.red);
    }




//-------------------------------------------------------------------------------------------------------

    public  void resetlist()
    {
        PrimeFaces current = PrimeFaces.current();
        reporteColaboradorDetalladosList=new ArrayList<>();
        current.ajax().update("formRCDetallado:tablaReporteDetallado");
    }

    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

}
