package s.c.m.entities;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;

@Entity
@Table(name = "marcasdescansos")
public class MarcaDescansos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private int pk_idMarcaDescanso;

    @OneToOne
    @JoinColumn(name = "fk_idcolaborador",nullable = false)
    private Colaborador colaborador;

    @OneToOne
    @JoinColumn(name = "fk_idmarcaslaboradas",nullable = false)
    private MarcaLaboradas marcaLaboradas;

    @OneToOne
    @JoinColumn(name = "fk_iddescanso",nullable = false)
    private Descansos descansos;

    private Time horaFin;

    private Time horaInicio;

    public int getPk_idMarcaDescanso() {
        return pk_idMarcaDescanso;
    }

    public void setPk_idMarcaDescanso(int pk_idMarcaDescanso) {
        this.pk_idMarcaDescanso = pk_idMarcaDescanso;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    public Time getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Time horaFin) {
        this.horaFin = horaFin;
    }

    public Time getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Time horaInicio) {
        this.horaInicio = horaInicio;
    }

    public MarcaLaboradas getMarcaLaboradas() {
        return marcaLaboradas;
    }

    public void setMarcaLaboradas(MarcaLaboradas marcaLaboradas) {
        this.marcaLaboradas = marcaLaboradas;
    }

    public Descansos getDescansos() {
        return descansos;
    }

    public void setDescansos(Descansos descansos) {
        this.descansos = descansos;
    }
}
