package com.example.raul.listadetarefas.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class DbHelper extends SQLiteOpenHelper {

    public static int VERSION = 1;
    public static String TABELA_TAREFAS = "tarefas";
    public static String NOME_DB = "DB_TAREFAS";

    public DbHelper(Context context) {
        super(context, NOME_DB, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TABELA_TAREFAS
                + " (id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT NOT NULL, " +
                "descricao TEXT NOT NULL, solicitante TEXT NOT NULL ," +
                "data_registro TEXT NOT NULL, data_prevista TEXT NOT NULL) ";
        try {
            db.execSQL(sql);
            Log.i("BD","Banco de dados criado");
        }catch (Exception e){
            Log.i("BD",e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + TABELA_TAREFAS + " ;";
        try {
            db.execSQL(sql);
            Log.i("BD","Banco de dados criado");
        }catch (Exception e){
            Log.i("BD",e.getMessage());
        }
    }
}
