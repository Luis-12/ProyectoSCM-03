package s.c.m.entities;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "asignaciondescansos")
public class AsignacionDescansos implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private int pk_idasigdescansos;

    @OneToOne
    @JoinColumn(name = "fk_idcolaborador",nullable = false)
    private Colaborador colaborador;
    @OneToOne
    @JoinColumn(name = "fk_iddescanso",nullable = false)
    private Descansos descanso;

    public AsignacionDescansos() {
    }

    public int getPk_idasigdescansos() {
        return pk_idasigdescansos;
    }

    public void setPk_idasigdescansos(int pk_idasigdescansos) {
        this.pk_idasigdescansos = pk_idasigdescansos;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    public Descansos getDescanso() {
        return descanso;
    }

    public void setDescanso(Descansos descanso) {
        this.descanso = descanso;
    }
}
