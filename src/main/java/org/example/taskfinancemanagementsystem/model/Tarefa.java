package org.example.taskfinancemanagementsystem.model;

import jakarta.persistence.*;
import java.util.List;
import java.time.LocalDate;


public class Tarefa {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String descricao;
    private LocalDate prazo;
    private String prioridade;
    private String status;

    @ManyToMany
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao () {
        return descricao;
    }

    public void setDescricao (String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getPrazo () {
        return prazo;
    }

    public void setPrazo (LocalDate prazo) {
        this.prazo = prazo;
    }

    public String getPrioridade () {
        return prioridade;
    }

    public void setPrioridade (String prioridade) {
        this.prioridade = prioridade;
    }

    public String getStatus () {
        return status;
    }

    public void setStatus (String status) {
        this.status = status;
    }


}
