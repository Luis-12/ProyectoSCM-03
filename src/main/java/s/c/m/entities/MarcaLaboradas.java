package s.c.m.entities;



import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

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

    private Timestamp horaEntrada;

    private Timestamp horaSalida;

    private String descripcion;

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

    public Timestamp getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(Timestamp horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public Timestamp getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(Timestamp horaSalida) {
        this.horaSalida = horaSalida;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
