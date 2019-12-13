package s.c.m.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "asignaciones")
public class Asignaciones {

    private String fk_idcolaborador;
    private int fk_idhorario;
    private String diadescanso;

    public Asignaciones() {
    }

    public String getFk_idcolaborador() {
        return fk_idcolaborador;
    }

    public void setFk_idcolaborador(String fk_idcolaborador) {
        this.fk_idcolaborador = fk_idcolaborador;
    }

    public int getFk_idhorario() {
        return fk_idhorario;
    }

    public void setFk_idhorario(int fk_idhorario) {
        this.fk_idhorario = fk_idhorario;
    }

    public String getDiadescanso() {
        return diadescanso;
    }

    public void setDiadescanso(String diadescanso) {
        this.diadescanso = diadescanso;
    }
}
