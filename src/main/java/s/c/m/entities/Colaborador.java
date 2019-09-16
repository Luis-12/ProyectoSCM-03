package s.c.m.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "colaboradores")
public class Colaborador implements Serializable {

    @Id
    private String pk_idColaborador;
    @OneToOne
    @JoinColumn(name = "fk_codDepartamento",nullable = false)
    private Departamento departamento;
    @OneToOne
    @JoinColumn(name = "fk_idPuesto",nullable = false)
    private Puesto puesto;


}
