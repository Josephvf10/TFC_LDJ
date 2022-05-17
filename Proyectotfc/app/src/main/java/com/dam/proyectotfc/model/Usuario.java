package com.dam.proyectotfc.model;

import java.util.List;

public class Usuario {
    private String nombreCompleto;
    private String telefono;
    private String email;
    private List listaJugados;
    private List listaMedias;
    private List listaCompletados;
    private List listaOlvidados;

    public Usuario() {}

    public Usuario(String nombreCompleto, String telefono, String email, List listaJugados, List listaCompletados, List listaMedias, List listaOlvidados) {
        this.nombreCompleto = nombreCompleto;
        this.telefono = telefono;
        this.email = email;
        this.listaJugados = listaJugados;
        this.listaCompletados = listaCompletados;
        this.listaMedias = listaMedias;
        this.listaOlvidados = listaOlvidados;
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

    public List getListaJugados() {
        return listaJugados;
    }

    public void setListaJugados(List listaJugados) {
        this.listaJugados = listaJugados;
    }

    public List getListaCompletados() {
        return listaCompletados;
    }

    public void setListaCompletados(List listaCompletados) {
        this.listaCompletados = listaCompletados;
    }

    public List getListaMedias() {
        return listaMedias;
    }

    public void setListaMedias(List listaMedias) {
        this.listaMedias = listaMedias;
    }

    public List getListaOlvidados() {
        return listaOlvidados;
    }

    public void setListaOlvidados(List listaOlvidados) {
        this.listaOlvidados = listaOlvidados;
    }
}
