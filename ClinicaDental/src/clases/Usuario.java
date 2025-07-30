/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

/**
 *
 * @author ayele
 */
public class Usuario {
    
    private int idUsuario;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;

    // Constructor, getters, setters
    public Usuario(int idUsuario, String nombre, String apellidoPaterno, String apellidoMaterno) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellidoPaterno=apellidoPaterno;
        this.apellidoMaterno=apellidoMaterno;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }
    
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }
    
}
