package com.dam.proyectotfc.model;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String id;
    private String nombreCompleto;
    private String telefono;
    private String email;

    public Usuario() {}

    public Usuario(String nombreCompleto, String telefono, String email) {
        this.nombreCompleto = nombreCompleto;
        this.telefono = telefono;
        this.email = email;
    }

    public Usuario(String id, String nombreCompleto, String telefono, String email) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
        this.telefono = telefono;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getEmail() {
        return email;
    }

}
