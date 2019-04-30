package com.example.raul.listadetarefas.helper;

import com.example.raul.listadetarefas.model.Tarefa;

import java.util.List;

public interface ITarefaDAO {

    public boolean salvarTarefa(Tarefa tarefa);
    public boolean editarTarefa(Tarefa tarefa);
    public boolean excluirTarefa(Tarefa tarefa);
    public List<Tarefa> listar();

}
