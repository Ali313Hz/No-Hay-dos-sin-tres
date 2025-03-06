package com.gestioninvitados.modelo;

/**
 * Clase que representa a un invitado en el sistema
 */
public class Invitado {
    private int id;
    private String nombre;

    // Constructor por defecto
    public Invitado() {
    }

    // Constructor con nombre
    public Invitado(String nombre) {
        this.nombre = nombre;
    }

    // Constructor completo
    public Invitado(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Invitado [id=" + id + ", nombre=" + nombre + "]";
    }
}