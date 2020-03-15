package s.c.m.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "vacacionesPorColaborador")
public class VacacionesPorColaborador implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pk_idvacacionesporcolaborador;

    @OneToOne
    @JoinColumn(name = "fk_idcolaborador", nullable = false)
    private Colaborador colaborador;

    private int diasdisponibles;
    private int diasdisfrutados;
    private Date fechaasignada;

    public VacacionesPorColaborador(Colaborador colaborador, int diasdisponibles, int diasdisfrutados, Date fechaasignada) {
        this.colaborador = colaborador;
        this.diasdisponibles = diasdisponibles;
        this.diasdisfrutados = diasdisfrutados;
        this.fechaasignada = fechaasignada;
    }

    public VacacionesPorColaborador() {
    }

    public int getPk_idvacacionesporcolaborador() {
        return pk_idvacacionesporcolaborador;
    }

    public void setPk_idvacacionesporcolaborador(int pk_idvacacionesporcolaborador) {
        this.pk_idvacacionesporcolaborador = pk_idvacacionesporcolaborador;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    public int getDiasdisponibles() {
        return diasdisponibles;
    }

    public void setDiasdisponibles(int diasdisponibles) {
        this.diasdisponibles = diasdisponibles;
    }

    public int getDiasdisfrutados() {
        return diasdisfrutados;
    }

    public void setDiasdisfrutados(int diasdisfrutados) {
        this.diasdisfrutados = diasdisfrutados;
    }

    public Date getFechaasignada() {
        return fechaasignada;
    }

    public void setFechaasignada(Date fechaasignada) {
        this.fechaasignada = fechaasignada;
    }
}
