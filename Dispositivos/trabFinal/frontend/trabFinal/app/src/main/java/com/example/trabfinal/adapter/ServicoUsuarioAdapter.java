package com.example.trabfinal.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabfinal.DetalhesContratoActivity;
import com.example.trabfinal.R;
import com.example.trabfinal.model.ServicoUsuarioResponse;

import java.util.List;

public class ServicoUsuarioAdapter extends RecyclerView.Adapter<ServicoUsuarioAdapter.ServicoUsuarioViewHolder> {

    private final Context context;
    private final List<ServicoUsuarioResponse> contratos;

    public ServicoUsuarioAdapter(Context context, List<ServicoUsuarioResponse> contratos) {
        this.context = context;
        this.contratos = contratos;
    }

    @NonNull
    @Override
    public ServicoUsuarioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_contrato, parent, false);
        return new ServicoUsuarioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServicoUsuarioViewHolder holder, int position) {
        ServicoUsuarioResponse contrato = contratos.get(position);

        holder.nomeServico.setText("Serviço: " + contrato.getServico().getDescricao());
        holder.valorContrato.setText("Valor: R$ " + contrato.getValor());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetalhesContratoActivity.class);
            intent.putExtra("CONTRATO", contrato);
            context.startActivity(intent);
        });
    }


    @Override
    public int getItemCount() {
        return contratos.size();
    }

    static class ServicoUsuarioViewHolder extends RecyclerView.ViewHolder {
        TextView nomeServico, valorContrato;

        public ServicoUsuarioViewHolder(@NonNull View itemView) {
            super(itemView);
            nomeServico = itemView.findViewById(R.id.nome_servico_contrato);
            valorContrato = itemView.findViewById(R.id.valor_contrato);
        }
    }
}
