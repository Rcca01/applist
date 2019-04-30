package com.example.raul.listadetarefas.model;

import java.io.Serializable;
import java.util.Date;

public class Tarefa implements Serializable {

    private Long id;
    private String nomeTarefa;
    private String descricaoTarefa;
    private String solicitanteTarefa;
    private int statusTarefa;
    private String dataRegistroTarefa;
    private String dataPrevistaEntregaTarefa;

    public String getSolicitanteTarefa() {
        return solicitanteTarefa;
    }

    public void setSolicitanteTarefa(String solicitanteTarefa) {
        this.solicitanteTarefa = solicitanteTarefa;
    }

    public String getDescricaoTarefa() {
        return descricaoTarefa;
    }

    public void setDescricaoTarefa(String descricaoTarefa) {
        this.descricaoTarefa = descricaoTarefa;
    }

    public String getDataRegistroTarefa() {
        return dataRegistroTarefa;
    }

    public void setDataRegistroTarefa(String dataRegistroTarefa) {
        this.dataRegistroTarefa = dataRegistroTarefa;
    }

    public String getDataPrevistaEntregaTarefa() {
        return dataPrevistaEntregaTarefa;
    }

    public void setDataPrevistaEntregaTarefa(String dataPrevistaEntregaTarefa) {
        this.dataPrevistaEntregaTarefa = dataPrevistaEntregaTarefa;
    }

    public int getStatusTarefa() {
        return statusTarefa;
    }

    public void setStatusTarefa(int statusTarefa) {
        this.statusTarefa = statusTarefa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeTarefa() {
        return nomeTarefa;
    }

    public void setNomeTarefa(String nomeTarefa) {
        this.nomeTarefa = nomeTarefa;
    }
}
