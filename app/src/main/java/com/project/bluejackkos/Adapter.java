package com.project.bluejackkos;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;

public class Adapter extends RecyclerView.Adapter<Adapter.myViewHolder> {

    ArrayList<Kos> dataKos;
    Context ctx;
    String currId;

    public Adapter(ArrayList<Kos> dataKos, Context ctx,  String currId) {
        this.dataKos = dataKos;
        this.ctx = ctx;
        this.currId = currId;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(ctx).inflate(R.layout.list_kos,parent, false);
        return new myViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, final int position) {
        Kos kos = Helper.dataKos.get(position);

        Glide.with(ctx).load(kos.getKosThumbnail()).into(holder.kosThumbnail);

        holder.kosThumbnail.setTag(kos.getKosThumbnail());
        holder.kosName.setText(kos.getKosName());
        holder.kosPrice.setText("Rp. " + String.valueOf(kos.getKosPrice()) + ",-");
        holder.kosFacility.setText(kos.getKosFacility());

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent kostdetail = new Intent(ctx, KosDetail.class);
                kostdetail.putExtra("name", position);
                kostdetail.putExtra("currentuserid", currId);
                ctx.startActivity(kostdetail);
            }
        });
    }

    @Override
    public int getItemCount() {
        return Helper.dataKos.size();
    }

    class myViewHolder extends RecyclerView.ViewHolder {

        ImageView kosThumbnail;
        TextView kosName;
        TextView kosPrice;
        TextView kosFacility;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            kosThumbnail = itemView.findViewById(R.id.koshumbnail);
            kosName = itemView.findViewById(R.id.kosName);
            kosPrice = itemView.findViewById(R.id.kosPrice);
            kosFacility = itemView.findViewById(R.id.kosFacility);
        }
    }
}
