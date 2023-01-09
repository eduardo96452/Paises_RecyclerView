package com.example.paises_recyclerview.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.paises_recyclerview.Models.Paises;
import com.example.paises_recyclerview.R;

import java.util.List;

public class PaisesAdaptador extends RecyclerView.Adapter<PaisesAdaptador.RevistaViewHolder> {
    private Context Ctx;
    private List<Paises> lista_pais;

    public PaisesAdaptador(Context mCtx, List<Paises> pais) {
        this.lista_pais = pais;
        Ctx = mCtx;
    }

    @Override
    public PaisesAdaptador.RevistaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(Ctx);
        View view = inflater.inflate(R.layout.lypaises_item, null);
        return new PaisesAdaptador.RevistaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PaisesAdaptador.RevistaViewHolder holder, int position) {
        Paises pais = lista_pais.get(position);
        holder.textViewNpais.setText(pais.getPais());
        holder.textCapital.setText(pais.getCapital());
        Glide.with(Ctx)
                .load(pais.getUrlbandera())
                .into(holder.imagebandera);
    }

    @Override
    public int getItemCount() {
        return lista_pais.size();
    }

    class RevistaViewHolder extends RecyclerView.ViewHolder {
        TextView textViewNpais, textCapital;
        ImageView imagebandera;
        public RevistaViewHolder(View itemView) {
            super(itemView);
            textViewNpais = itemView.findViewById(R.id.txtnombrepais);
            textCapital = itemView.findViewById(R.id.txtcapital);
            imagebandera = itemView.findViewById(R.id.imgbandera);
        }
    }
}
