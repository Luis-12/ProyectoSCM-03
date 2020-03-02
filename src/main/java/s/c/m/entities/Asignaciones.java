package s.c.m.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "asignaciones")


public class Asignaciones implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private int pk_idAsignacion;

    @OneToOne
    @JoinColumn(name = "fk_idColaborador",nullable = false)
    private Colaborador colaborador;
    @OneToOne
    @JoinColumn(name = "fk_idHorario",nullable = false)
    private Horarios horario;

    private String diadescanso;

    private String segundodiades;

    public String getSegundodiades() {
        return segundodiades;
    }

    public void setSegundodiades(String segundodiades) {
        this.segundodiades = segundodiades;
    }

    public String getDiadescanso() {
        return diadescanso;
    }

    public void setDiadescanso(String diadescanso) {
        this.diadescanso = diadescanso;
    }


    public int getPk_idAsignacion() {
        return pk_idAsignacion;
    }

    public void setPk_idAsignacion(int pk_idAsignacion) {
        this.pk_idAsignacion = pk_idAsignacion;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    public Horarios getHorario() {
        return horario;
    }

    public void setHorario(Horarios horario) {
        this.horario = horario;
    }

    public String getDiaDescanso() {
        return diadescanso;
    }

}
