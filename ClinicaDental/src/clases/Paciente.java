/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

/**
 *
 * @author ayele
 */
public class Paciente {
    
    private int idPaciente;
    private int idUsuario;
    private String nombreCompleto; // Agregado

    public Paciente(int idPaciente, int idUsuario) {
        this.idPaciente = idPaciente;
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombreCompleto;
    }

    public void setNombre(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    @Override
    public String toString() {
        return nombreCompleto; // Útil si los cargas a un JComboBox
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public int getIdPaciente() {
        return idPaciente;
    }
}

