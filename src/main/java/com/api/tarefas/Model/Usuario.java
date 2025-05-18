package com.api.tarefas.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "Usuarios")
public class Usuario {


    @Id
    @Column(name="matricula")
    private int matricula;

    @Column(name = "nome")
    private String nome;


    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
