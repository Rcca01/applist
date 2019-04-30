package com.example.raul.listadetarefas.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.raul.listadetarefas.R;
import com.example.raul.listadetarefas.model.Tarefa;

import java.util.List;

public class AdapterTarefa extends RecyclerView.Adapter<AdapterTarefa.MyViewHolder> {

    List<Tarefa> listaTarefa;

    public AdapterTarefa(List<Tarefa> lista) {
        this.listaTarefa = lista;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_tarefa,viewGroup,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Tarefa tarefa = listaTarefa.get(i);
        myViewHolder.tvTarefa.setText(tarefa.getNomeTarefa());
        myViewHolder.tvDescricao.setText(tarefa.getDescricaoTarefa());
        myViewHolder.tvSolicitante.setText(tarefa.getSolicitanteTarefa());
        myViewHolder.tvDataRegistro.setText(tarefa.getDataRegistroTarefa());
        myViewHolder.tvDataPrevisto.setText(tarefa.getDataPrevistaEntregaTarefa());
    }

    @Override
    public int getItemCount() {
        return listaTarefa.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tvTarefa;
        TextView tvDescricao;
        TextView tvSolicitante;
        TextView tvDataRegistro;
        TextView tvDataPrevisto;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTarefa = itemView.findViewById(R.id.tvTarefa);
            tvDescricao = itemView.findViewById(R.id.tvDescricao);
            tvSolicitante = itemView.findViewById(R.id.tvSolicitante);
            tvDataRegistro = itemView.findViewById(R.id.tvDataRegistro);
            tvDataPrevisto = itemView.findViewById(R.id.tvDataPrevisto);
        }
    }

}
