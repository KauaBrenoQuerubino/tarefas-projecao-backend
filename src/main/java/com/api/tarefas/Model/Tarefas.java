package com.api.tarefas.Model;

import jakarta.persistence.*;

import java.util.Date;

public class Tarefas {

    private String id;

    private String titulo;

    private String descricao;

    private String limite;

    private String status;

    private Usuario usuario;

    private Disciplina disciplina;


    public Tarefas() {

    }

    public Tarefas(String id, String titulo, String descricao, String limite, String status, Usuario usuario, Disciplina disciplina) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.limite = limite;
        this.status = status;
        this.disciplina = disciplina;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getLimite() {
        return limite;
    }

    public void setLimite(String limite) {
        this.limite = limite;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
