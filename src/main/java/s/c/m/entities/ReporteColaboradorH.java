package s.c.m.entities;

public class ReporteColaboradorH {

    private String cedula;
    private String nombre;
    private String horario;
    private int cantidadDescansos;
    private String diaDescanso;
    private String dia2;

    public ReporteColaboradorH() {
    }

    public ReporteColaboradorH(String cedula, String nombre, String horario, int cantidadDescansos, String diaDescanso, String dia2) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.horario = horario;
        this.cantidadDescansos = cantidadDescansos;
        this.diaDescanso = diaDescanso;
        this.dia2 = dia2;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidadDescansos() {
        return cantidadDescansos;
    }

    public void setCantidadDescansos(int cantidadDescansos) {
        this.cantidadDescansos = cantidadDescansos;
    }

    public String getDiaDescanso() {
        return diaDescanso;
    }

    public void setDiaDescanso(String diaDescanso) {
        this.diaDescanso = diaDescanso;
    }

    public String getDia2() {
        return dia2;
    }

    public void setDia2(String dia2) {
        this.dia2 = dia2;
    }
}
