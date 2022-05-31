package com.dam.proyectotfc.model;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String nombreCompleto;
    private String telefono;
    private String email;
    private ArrayList<String> juegosCom;
    private ArrayList<String> juegosJug;
    private ArrayList<String> juegosMed;
    private ArrayList<String> juegosOlv;

    public Usuario() {}

    public Usuario(String nombreCompleto, String telefono, String email) {
        this.nombreCompleto = nombreCompleto;
        this.telefono = telefono;
        this.email = email;
    }

    public Usuario(String nombreCompleto, String telefono, String email, ArrayList<String> juegosCom, ArrayList<String> juegosJug, ArrayList<String> juegosMed, ArrayList<String> juegosOlv) {
        this.nombreCompleto = nombreCompleto;
        this.telefono = telefono;
        this.email = email;
        this.juegosCom = juegosCom;
        this.juegosJug = juegosJug;
        this.juegosMed = juegosMed;
        this.juegosOlv = juegosOlv;
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

    public ArrayList<String> getJuegosCom() {
        return juegosCom;
    }

    public ArrayList<String> getJuegosJug() {
        return juegosJug;
    }

    public ArrayList<String> getJuegosMed() {
        return juegosMed;
    }

    public ArrayList<String> getJuegosOlv() {
        return juegosOlv;
    }
}
