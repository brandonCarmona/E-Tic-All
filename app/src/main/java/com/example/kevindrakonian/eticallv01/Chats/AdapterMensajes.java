package com.example.kevindrakonian.eticallv01.Chats;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.kevindrakonian.eticallv01.Entidades.MensajeEntity;
import com.example.kevindrakonian.eticallv01.Entidades.MensajeRecibirEntity;
import com.example.kevindrakonian.eticallv01.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;

public class AdapterMensajes extends RecyclerView.Adapter <HolderMensaje> {

    private List<MensajeRecibirEntity> listamensajes = new ArrayList<>();
    private Context c;

    public AdapterMensajes(Context c) {
        this.c = c;
    }

    public void addMensaje(MensajeRecibirEntity me){
     listamensajes.add(me);
     notifyItemInserted(listamensajes.size());
    }

    @Override
    public HolderMensaje onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(c).inflate(R.layout.card_view_mensajes,parent,false);
        return new HolderMensaje(v);
    }

    @Override
    public void onBindViewHolder(HolderMensaje holder, int position) {
        holder.getNombre().setText(listamensajes.get(position).getNombre());
        holder.getMensaje().setText(listamensajes.get(position).getMensaje());

        if (listamensajes.get(position).getType().equals("2")){
            holder.getFotoMensaje().setVisibility(View.VISIBLE);
            holder.getMensaje().setVisibility(View.VISIBLE);
            Glide.with(c).load(listamensajes.get(position).getFoto_envia()).into(holder.getFotoMensaje());
        }else if (listamensajes.get(position).getType().equals("1")){
            holder.getFotoMensaje().setVisibility(View.GONE);
            holder.getMensaje().setVisibility(View.VISIBLE);
        }

        long condigoHora = listamensajes.get(position).getHora();
        Date d = new Date(condigoHora);
        SimpleDateFormat ho = new SimpleDateFormat("hh:mm:ss a");
        holder.getHora().setText(ho.format(d));
    }

    @Override
    public int getItemCount() {
        return listamensajes.size();
    }
}
