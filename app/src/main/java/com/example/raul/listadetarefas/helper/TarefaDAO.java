package com.example.raul.listadetarefas.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.raul.listadetarefas.model.Tarefa;

import java.util.ArrayList;
import java.util.List;

public class TarefaDAO implements ITarefaDAO {
    private SQLiteDatabase escrever;
    private SQLiteDatabase ler;

    public TarefaDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        escrever = dbHelper.getWritableDatabase();
        ler = dbHelper.getReadableDatabase();
    }

    @Override
    public boolean salvarTarefa(Tarefa tarefa) {
        ContentValues cv = new ContentValues();

        cv.put("nome",tarefa.getNomeTarefa());
        cv.put("descricao",tarefa.getDescricaoTarefa());
        cv.put("solicitante",tarefa.getSolicitanteTarefa());
        cv.put("data_registro",tarefa.getDataRegistroTarefa());
        cv.put("data_prevista",tarefa.getDataPrevistaEntregaTarefa());

        try {
            escrever.insert(DbHelper.TABELA_TAREFAS, null, cv);
            Log.i("db","Tarefa adicionada com sucesso");
        }catch (Exception e ){
            Log.e("bd", e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean editarTarefa(Tarefa tarefa) {
        ContentValues cv = new ContentValues();

        cv.put("nome",tarefa.getNomeTarefa());
        cv.put("descricao",tarefa.getDescricaoTarefa());
        cv.put("solicitante",tarefa.getSolicitanteTarefa());
        cv.put("data_prevista",tarefa.getDataPrevistaEntregaTarefa());

        try {
            String[] args = {tarefa.getId().toString()};
            escrever.update(DbHelper.TABELA_TAREFAS, cv, "id=?", args );
            Log.i("INFO", "Tarefa atualizada com sucesso!");
        }catch (Exception e){
            Log.e("INFO", "Erro ao atualizada tarefa " + e.getMessage() );
            return false;
        }

        return true;
    }

    @Override
    public boolean excluirTarefa(Tarefa tarefa) {
        try {
            String[] args = { tarefa.getId().toString() };
            escrever.delete(DbHelper.TABELA_TAREFAS, "id=?", args );
            Log.i("INFO", "Tarefa removida com sucesso!");
        }catch (Exception e){
            Log.e("INFO", "Erro ao remover tarefa " + e.getMessage() );
            return false;
        }

        return true;
    }

    @Override
    public List<Tarefa> listar() {
        List<Tarefa> listaTarefa = new ArrayList<>();
        String sql = "SELECT * FROM "+DbHelper.TABELA_TAREFAS+" ;";
        Cursor c = ler.rawQuery(sql,null);
        while (c.moveToNext()){
            Tarefa tarefa = new Tarefa();
            tarefa.setId(c.getLong(c.getColumnIndex("id")));
            tarefa.setNomeTarefa(c.getString(c.getColumnIndex("nome")));
            tarefa.setDescricaoTarefa(c.getString(c.getColumnIndex("descricao")));
            tarefa.setSolicitanteTarefa(c.getString(c.getColumnIndex("solicitante")));
            tarefa.setDataRegistroTarefa(c.getString(c.getColumnIndex("data_registro")));
            tarefa.setDataPrevistaEntregaTarefa(c.getString(c.getColumnIndex("data_prevista")));
            listaTarefa.add(tarefa);
        }
        return listaTarefa;
    }
}
