package com.example.trabfinal.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabfinal.R;
import com.example.trabfinal.model.ServicoResponse;

import java.util.List;

public class ServicoAdapter extends RecyclerView.Adapter<ServicoAdapter.ServicoViewHolder> {

    private List<ServicoResponse> servicos;
    private OnServicoClickListener onServicoClickListener;

    public ServicoAdapter(List<ServicoResponse> servicos, OnServicoClickListener onServicoClickListener) {
        this.servicos = servicos;
        this.onServicoClickListener = onServicoClickListener;
    }

    public void updateServicos(List<ServicoResponse> servicos) {
        this.servicos = servicos;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ServicoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_servico, parent, false);
        return new ServicoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServicoViewHolder holder, int position) {
        ServicoResponse servico = servicos.get(position);
        holder.bind(servico);

        holder.itemView.setOnClickListener(v -> {
            if (onServicoClickListener != null) {
                onServicoClickListener.onServicoClick(servico);
            }
        });
    }

    @Override
    public int getItemCount() {
        return servicos == null ? 0 : servicos.size();
    }

    static class ServicoViewHolder extends RecyclerView.ViewHolder {

        private final TextView nomeServico;
        private final TextView valorServico;
        private final TextView especialidadesServico;

        public ServicoViewHolder(@NonNull View itemView) {
            super(itemView);

            nomeServico = itemView.findViewById(R.id.nome_servico);
            valorServico = itemView.findViewById(R.id.valor_servico);
            especialidadesServico = itemView.findViewById(R.id.especialidades_servico);
        }

        public void bind(ServicoResponse servico) {
            // Exibe os valores no card
            nomeServico.setText(servico.getServico().getDescricao());
            valorServico.setText("R$ " + servico.getServico().getValor());

            StringBuilder especialidadesString = new StringBuilder();
            for (int i = 0; i < servico.getEspecialidade().size(); i++) {
                especialidadesString.append(servico.getEspecialidade().get(i).getNome());
                if (i < servico.getEspecialidade().size() - 1) {
                    especialidadesString.append(", ");
                }
            }
            especialidadesServico.setText(especialidadesString.toString());
        }
    }

    public interface OnServicoClickListener {
        void onServicoClick(ServicoResponse servico);
    }
}
