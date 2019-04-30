package com.example.raul.listadetarefas.activity;

import android.app.DatePickerDialog;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.raul.listadetarefas.R;
import com.example.raul.listadetarefas.adapter.AdapterTarefa;
import com.example.raul.listadetarefas.helper.TarefaDAO;
import com.example.raul.listadetarefas.model.Tarefa;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AdicionarTarefaActivity extends AppCompatActivity {

    TextInputEditText editTarefa;
    TextInputEditText editDescricao;
    TextInputEditText editSolicitante;
    TextInputEditText editDataPrevisto;
    Calendar dataRegistro = Calendar.getInstance();
    Calendar dataPrevisto = Calendar.getInstance();
    private Tarefa tarefaAtual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_tarefa);

        editTarefa = findViewById(R.id.editTarefa);
        editDescricao = findViewById(R.id.editDescricao);
        editSolicitante = findViewById(R.id.editSolicitante);
        editDataPrevisto = findViewById(R.id.editDataPrevisto);

        //Recuperar tarefa, caso seja edição
        tarefaAtual = (Tarefa) getIntent().getSerializableExtra("tarefaSelecionada");

        //Configurar tarefa na caixa de texto
        if ( tarefaAtual != null ){
            editTarefa.setText( tarefaAtual.getNomeTarefa() );
            editDescricao.setText( tarefaAtual.getDescricaoTarefa() );
            editSolicitante.setText( tarefaAtual.getSolicitanteTarefa() );
            editDataPrevisto.setText( tarefaAtual.getDataPrevistaEntregaTarefa() );
        }

        editDataPrevisto.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                new DatePickerDialog(AdicionarTarefaActivity.this,date,dataRegistro
                        .get(Calendar.YEAR), dataRegistro.get(Calendar.MONTH),
                        dataRegistro.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_salvar_tarefa, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch ( item.getItemId() ){
            case R.id.salvarTarefa :
                //Executa açao para o item salvar

                TarefaDAO tarefaDAO = new TarefaDAO( getApplicationContext() );

                if ( tarefaAtual != null ){//edicao

                    String nomeTarefa = editTarefa.getText().toString();
                    String descricaoTarefa = editDescricao.getText().toString();
                    String solicitanteTarefa = editSolicitante.getText().toString();
                    String dataPrevistoTarefa = editDataPrevisto.getText().toString();

                    if ( !nomeTarefa.isEmpty() ){

                        Tarefa tarefa = new Tarefa();
                        tarefa.setNomeTarefa( nomeTarefa );
                        tarefa.setDescricaoTarefa( descricaoTarefa );
                        tarefa.setSolicitanteTarefa( solicitanteTarefa );
                        tarefa.setDataPrevistaEntregaTarefa( dataPrevistoTarefa );
                        tarefa.setId( tarefaAtual.getId() );

                        //atualizar no banco de dados
                        if ( tarefaDAO.editarTarefa( tarefa ) ){
                            finish();
                            Toast.makeText(getApplicationContext(),
                                    "Sucesso ao atualizar tarefa!",
                                    Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getApplicationContext(),
                                    "Erro ao atualizar tarefa!",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }

                }else {//salvar
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt","BR"));

                    String nomeTarefa = editTarefa.getText().toString();
                    String descricaoTarefa = editDescricao.getText().toString();
                    String solicitanteTarefa = editSolicitante.getText().toString();
                    String dataRegistroTarefa = sdf.format(Calendar.getInstance().getTime());
                    String dataPrevistoTarefa = editDataPrevisto.getText().toString();

                    if ( !nomeTarefa.isEmpty() ){
                        Tarefa tarefa = new Tarefa();
                        tarefa.setNomeTarefa( nomeTarefa );
                        tarefa.setDescricaoTarefa( descricaoTarefa );
                        tarefa.setSolicitanteTarefa( solicitanteTarefa );
                        tarefa.setDataRegistroTarefa( dataRegistroTarefa );
                        tarefa.setDataPrevistaEntregaTarefa( dataPrevistoTarefa );

                        if ( tarefaDAO.salvarTarefa( tarefa ) ){
                            finish();
                            Toast.makeText(getApplicationContext(),
                                    "Sucesso ao salvar tarefa!",
                                    Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getApplicationContext(),
                                    "Erro ao salvar tarefa!",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            break;
        }

        return super.onOptionsItemSelected(item);
    }

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            dataPrevisto.set(Calendar.YEAR, year);
            dataPrevisto.set(Calendar.MONTH, month);
            dataPrevisto.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabelPrevisto();
        }
    };

    private void updateLabelPrevisto(){
        String myFormat = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, new Locale("pt","BR"));
        editDataPrevisto.setText(sdf.format(dataPrevisto.getTime()));
    }
}
