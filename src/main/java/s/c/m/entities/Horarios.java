package s.c.m.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Time;

@Entity
@Table(name = "horarios")
public class Horarios implements Serializable {

    @Id
    private int pk_idhorario;
    private String fk_idjornada;
    private Time horaentrada;
    private Time horasalida;

    public Horarios() { }

    public int getPk_idhorario() {
        return pk_idhorario;
    }

    public void setPk_idhorario(int pk_idhorario) {
        this.pk_idhorario = pk_idhorario;
    }

    public String getFk_idjornada() {
        return fk_idjornada;
    }

    public void setFk_idjornada(String fk_idjornada) {
        this.fk_idjornada = fk_idjornada;
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
