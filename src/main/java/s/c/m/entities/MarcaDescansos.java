package s.c.m.entities;

import javax.persistence.*;
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

    private Timestamp horaFin;

    private Timestamp horaInicio;

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

    public Timestamp getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Timestamp horaFin) {
        this.horaFin = horaFin;
    }

    public Timestamp getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Timestamp horaInicio) {
        this.horaInicio = horaInicio;
    }
}
