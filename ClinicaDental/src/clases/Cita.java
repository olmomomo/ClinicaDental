/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;


import java.util.Date;

public class Cita {
    private int idCita;
    private int idPaciente;
    private int idDentista;
    private Date fecha;
    private String hora;
    private String estado; // si lo usas

    // Constructor vacío
    public Cita() {
    }

    // Constructor con parámetros (el orden y tipos según tu necesidad)
    public Cita(int idCita, Date fecha, String hora, int idDentista) {
        this.idCita = idCita;
        this.fecha = fecha;
        this.hora = hora;
        this.idDentista = idDentista;
    }

    // Getters
    public int getIdCita() {
        return idCita;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public int getIdDentista() {
        return idDentista;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    public String getEstado() {
        return estado;
    }

    // Setters
    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public void setIdDentista(int idDentista) {
        this.idDentista = idDentista;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
