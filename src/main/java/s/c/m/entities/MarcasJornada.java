package s.c.m.entities;

import java.sql.Time;

public class MarcasJornada {

    private Time marca;
    private String descripcion;

    public MarcasJornada(Time marca, String descripcion) {
        this.marca = marca;
        this.descripcion = descripcion;
    }

    public MarcasJornada() {
    }

    public Time getMarca() {
        return marca;
    }

    public void setMarca(Time marca) {
        this.marca = marca;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
