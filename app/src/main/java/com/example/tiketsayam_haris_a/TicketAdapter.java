package com.example.tiketsayam_haris_a;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.MyViewHolder> {
    Context context;
    ArrayList<MyTicket> myTicket;

    public TicketAdapter(Context c, ArrayList<MyTicket> p) {
        context = c;
        myTicket = p;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.itemmyticket, parent, false));
//        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        holder.lokasi.setText(myTicket.get(position).getLokasi());
        holder.nama_wisata.setText(myTicket.get(position).getNama_wisata());
        holder.jumlahTiket.setText(myTicket.get(position).getJumlahTiket() + " Tickets");


        final String namaWisata = myTicket.get(position).getNama_wisata();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotomyticketdetail = new Intent(context, MyticketDetailAct.class);
                gotomyticketdetail.putExtra("namawisata", namaWisata);
                context.startActivity(gotomyticketdetail);
            }
        });

    }

    @Override
    public int getItemCount() {
        return myTicket.size();

//        return 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nama_wisata, lokasi, jumlahTiket;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nama_wisata = itemView.findViewById(R.id.namaWisata);
            lokasi = itemView.findViewById(R.id.lokasiWisata);
            jumlahTiket = itemView.findViewById(R.id.jumlahTicket);

        }
    }

}
