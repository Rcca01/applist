package com.example.raul.listadetarefas.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.raul.listadetarefas.R;
import com.example.raul.listadetarefas.adapter.AdapterTarefa;
import com.example.raul.listadetarefas.helper.RecyclerItemClickListener;
import com.example.raul.listadetarefas.helper.TarefaDAO;
import com.example.raul.listadetarefas.model.Tarefa;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerViewListaTarefas;
    AdapterTarefa adapterTarefa;
    List<Tarefa> listaTarefa = new ArrayList<>();
    private Tarefa tarefaSelecionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerViewListaTarefas = findViewById(R.id.recycleViewListaTarefas);
        recyclerViewListaTarefas.addOnItemTouchListener(new RecyclerItemClickListener(
                getApplicationContext(),
                recyclerViewListaTarefas,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        //Recuperar tarefa para edicao
                        Tarefa tarefaSelecionada = listaTarefa.get( position );

                        //Envia tarefa para tela adicionar tarefa
                        Intent intent = new Intent(MainActivity.this, AdicionarTarefaActivity.class);
                        intent.putExtra("tarefaSelecionada", tarefaSelecionada );

                        startActivity( intent );
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        //Recupera tarefa para deletar
                        tarefaSelecionada = listaTarefa.get( position );

                        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);

                        //Configura título e mensagem
                        dialog.setTitle("Confirmar exclusão");
                        dialog.setMessage("Deseja excluir a tarefa: " + tarefaSelecionada.getNomeTarefa() + " ?" );

                        dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                TarefaDAO tarefaDAO = new TarefaDAO(getApplicationContext());
                                if ( tarefaDAO.excluirTarefa(tarefaSelecionada) ){

                                    carregarListaTarefas();
                                    Toast.makeText(getApplicationContext(),
                                            "Sucesso ao excluir tarefa!",
                                            Toast.LENGTH_SHORT).show();

                                }else {
                                    Toast.makeText(getApplicationContext(),
                                            "Erro ao excluir tarefa!",
                                            Toast.LENGTH_SHORT).show();
                                }

                            }
                        });

                        dialog.setNegativeButton("Não", null );

                        //Exibir dialog
                        dialog.create();
                        dialog.show();
                    }

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    }
                }
        ));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AdicionarTarefaActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        this.carregarListaTarefas();
    }

    public void carregarListaTarefas(){

        //Lista de tarefas
        TarefaDAO tarefaDAO = new TarefaDAO(getApplicationContext());
        listaTarefa = tarefaDAO.listar();

        //Configurar adapter
        adapterTarefa = new AdapterTarefa(listaTarefa);

        //Configurar recyclerview
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewListaTarefas.setLayoutManager(layoutManager);
        recyclerViewListaTarefas.setHasFixedSize(true);
        recyclerViewListaTarefas.setAdapter(adapterTarefa);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
