package s.c.m.entities;

public class ReporteColaboradorDetallado {
    private String cedula;
    private String nombre;
    private String tipoJornada;
    private String horario;
    private double cantHorasLaboradas;
    private int cantLlegadasTardias;
    private double diasDispoVacaciones;

    public ReporteColaboradorDetallado(String cedula, String nombre,
                                       String tipoJornada, String horario,
                                       double cantHorasLaboradas, int cantLlegadasTardias,
                                       double diasDispoVacaciones) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.tipoJornada = tipoJornada;
        this.horario = horario;
        this.cantHorasLaboradas = cantHorasLaboradas;
        this.cantLlegadasTardias = cantLlegadasTardias;
        this.diasDispoVacaciones = diasDispoVacaciones;
    }

    public ReporteColaboradorDetallado() {
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoJornada() {
        return tipoJornada;
    }

    public void setTipoJornada(String tipoJornada) {
        this.tipoJornada = tipoJornada;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public double getCantHorasLaboradas() {
        return cantHorasLaboradas;
    }

    public void setCantHorasLaboradas(double cantHorasLaboradas) {
        this.cantHorasLaboradas = cantHorasLaboradas;
    }

    public int getCantLlegadasTardias() {
        return cantLlegadasTardias;
    }

    public void setCantLlegadasTardias(int cantLlegadasTardias) {
        this.cantLlegadasTardias = cantLlegadasTardias;
    }

    public double getDiasDispoVacaciones() {
        return diasDispoVacaciones;
    }

    public void setDiasDispoVacaciones(double diasDispoVacaciones) {
        this.diasDispoVacaciones = diasDispoVacaciones;
    }
}
