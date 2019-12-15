package s.c.m.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;

@Entity
@Table(name = "horarios")
public class Horarios implements Serializable {

    @Id
    private int pk_idhorario;
    @OneToOne
    @JoinColumn(name = "fk_idjornada",nullable = false)
    private Jornadas jornada;

    private Time horaentrada;
    private Time horasalida;

    public Horarios() { }

    public Jornadas getJornada() {
        return jornada;
    }

    public void setJornada(Jornadas jornada) {
        this.jornada = jornada;
    }

    public int getPk_idhorario() {
        return pk_idhorario;
    }

    public void setPk_idhorario(int pk_idhorario) {
        this.pk_idhorario = pk_idhorario;
    }


    public Time getHoraentrada() {
        return horaentrada;
    }

    public void setHoraentrada(Time horaentrada) {
        this.horaentrada = horaentrada;
    }

    public Time getHorasalida() {
        return horasalida;
    }

    public void setHorasalida(Time horasalida) {
        this.horasalida = horasalida;
    }
}
