package s.c.m.entities;



import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.sql.Time;

@Entity
@Table(name = "marcaslaboradas")
public class MarcaLaboradas implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private int pk_idMarcasLaboradas;

    @OneToOne
    @JoinColumn(name = "fk_idcolaborador",nullable = false)
    private Colaborador colaborador;

    private Time horaEntrada;

    private Time horaSalida;

    private Date fechaMarca;

    private String descripcion;

    private String estado;

    private String justSalidaTemprana;

    private String justTiempoExtra;

    public int getPk_idMarcasLaboradas() {
        return pk_idMarcasLaboradas;
    }

    public void setPk_idMarcasLaboradas(int pk_idMarcasLaboradas) {
        this.pk_idMarcasLaboradas = pk_idMarcasLaboradas;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    public Time getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(Time horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public Time getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(Time horaSalida) {
        this.horaSalida = horaSalida;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaMarca() {
        return fechaMarca;
    }

    public void setFechaMarca(Date fechaMarca) {
        this.fechaMarca = fechaMarca;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getJustSalidaTemprana() {
        return justSalidaTemprana;
    }

    public void setJustSalidaTemprana(String justSalidaTemprana) {
        this.justSalidaTemprana = justSalidaTemprana;
    }

    public String getJustTiempoExtra() {
        return justTiempoExtra;
    }

    public void setJustTiempoExtra(String justTiempoExtra) {
        this.justTiempoExtra = justTiempoExtra;
    }
}
