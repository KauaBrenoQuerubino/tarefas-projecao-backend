package com.api.tarefas.Model;


public class Curso {

    private String id;

    private String nome;

    private String duracao;

    private Usuario usuario;

    public Curso(String id, String nome, String duracao, Usuario usuario) {
        this.id = id;
        this.nome = nome;
        this.duracao = duracao;
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Curso(){

    }

    public Curso(String id, String nome, String duracao) {
        this.id = id;
        this.nome = nome;
        this.duracao = duracao;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
