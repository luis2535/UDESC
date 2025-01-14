package com.example.trabfinal.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabfinal.R;
import com.example.trabfinal.api.ApiClient;
import com.example.trabfinal.api.ServicoUsuarioApi;
import com.example.trabfinal.model.ServicoUsuarioRequest;
import com.example.trabfinal.model.ServicoComentarioResponse;

import java.math.BigDecimal;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServicoComentarioAdapter extends RecyclerView.Adapter<ServicoComentarioAdapter.ComentarioViewHolder> {

    private List<ServicoComentarioResponse> comentarios;
    private Context context;
    private ServicoUsuarioApi servicoUsuarioApi;
    private int servicoId;

    public ServicoComentarioAdapter(Context context, List<ServicoComentarioResponse> comentarios, int servicoId) {
        this.context = context;
        this.comentarios = comentarios;
        this.servicoId = servicoId;
        this.servicoUsuarioApi = ApiClient.createServicoUsuarioApiService();

        Log.d("ServicoComentarioAdapter", "Recebido SERVICO_ID no Adapter: " + servicoId);
    }


    public void updateComentarios(List<ServicoComentarioResponse> novosComentarios) {
        this.comentarios = novosComentarios;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ComentarioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comentario, parent, false);
        return new ComentarioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ComentarioViewHolder holder, int position) {
        ServicoComentarioResponse comentario = comentarios.get(position);

        holder.nomeUsuario.setText(comentario.getUsuario().getNome());
        holder.textoComentario.setText(comentario.getComentario());
        holder.orcamentoComentario.setText("R$ " + comentario.getOrcamento() + " - " + comentario.getComentarioOrcamento());

        holder.contratarBtn.setOnClickListener(v -> contratarServico(comentario));
    }

    @Override
    public int getItemCount() {
        return comentarios.size();
    }

    private void contratarServico(ServicoComentarioResponse comentario) {
        Log.d("ServicoComentarioAdapter", "Usando SERVICO_ID no POST: " + servicoId);

        ServicoUsuarioRequest request = new ServicoUsuarioRequest();
        request.setUsuarioId(comentario.getUsuario().getId());
        request.setServicoId(servicoId); // Verificar se está usando o valor correto
        request.setValor(comentario.getOrcamento());

        servicoUsuarioApi.contratarServico(request).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(context, "Serviço contratado com sucesso!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Erro ao contratar serviço.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(context, "Falha na comunicação: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }



    static class ComentarioViewHolder extends RecyclerView.ViewHolder {
        TextView nomeUsuario, textoComentario, orcamentoComentario;
        Button contratarBtn;

        public ComentarioViewHolder(@NonNull View itemView) {
            super(itemView);
            nomeUsuario = itemView.findViewById(R.id.nome_usuario_comentario);
            textoComentario = itemView.findViewById(R.id.texto_comentario);
            orcamentoComentario = itemView.findViewById(R.id.orcamento_comentario);
            contratarBtn = itemView.findViewById(R.id.contratar_btn);
        }
    }
}
