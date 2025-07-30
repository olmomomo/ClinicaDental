/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import java.math.BigDecimal;

/**
 *
 * @author ayele
 */
public class Procedimiento {
    private int id;
    private String nombre;
    private String descripcion;
    private BigDecimal costo;
    private int idServicio;

    // Constructor con todos los campos
    public Procedimiento(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Procedimiento() {
        // Constructor vacío si lo necesitas
    }

    public int getId() {
        return id;
    }

    public void setIdProcedimiento(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getCosto() {
        return costo;
    }

    public void setCosto(BigDecimal costo) {
        this.costo = costo;
    }

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    @Override
    public String toString() {
        return id + " | " + nombre;
    }
}
