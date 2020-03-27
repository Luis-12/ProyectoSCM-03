package s.c.m.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

@Entity
@Table(name = "VACACIONES")
public class Vacaciones implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pk_idVacaciones;

    @OneToOne
    @JoinColumn(name = "fk_idColaborador", nullable = false)
    private Colaborador colaborador;

    private Date fechainicio;
    private Date fechafinal;
    private String estado;
    private String justificacion;
    private int diasSolicitados;


    public int getPk_idVacaciones() {
        return pk_idVacaciones;
    }

    public void setPk_idVacaciones(int pk_idVacaciones) {
        this.pk_idVacaciones = pk_idVacaciones;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    public Date getFechainicio() {
        return fechainicio;
    }

    public void setFechainicio(Date fechainicio) {
        this.fechainicio = fechainicio;
    }

    public Date getFechafinal() {
        return fechafinal;
    }

    public void setFechafinal(Date fechafinal) {
        this.fechafinal = fechafinal;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }

    public int getDiasSolicitados() {
        return diasSolicitados;
    }

    public void setDiasSolicitados(int diasSolicitados) {
        this.diasSolicitados = diasSolicitados;
    }
}

