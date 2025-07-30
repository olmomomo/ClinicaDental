/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

/**
 *
 * @author ayele
 */
public class Dentista {
    private int id;
    private String nombre;
    private String turno; // "Matutino" o "Vespertino"
    private String especialidad;
    private int idUsuario;
    private String apellidoP;
    private String apellidoM;

    // Constructor vacío
    public Dentista() {
    }

    // Constructor con parámetros
    public Dentista(int id, String nombre, String apellidoP, String apellidoM, String turno, String especialidad) {
        this.id = id;
        this.nombre = nombre;
        this.apellidoM=apellidoM;
        this.apellidoP=apellidoP;                   
        this.turno = turno;
        this.especialidad = especialidad;
    }

    // Agrega estos métodos nuevos
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    // Getters
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
    
    public void setApellidoP(String apellidoP) {
        this.apellidoP = apellidoP;
    }

    public void setApellidoM(String apellidoM) {
        this.apellidoM = apellidoM;
    }

    public String getTurno() {
        return turno;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    @Override
public String toString() {
    return nombre + " (" + especialidad + " - " + turno + ")";
}

}

