package com.project.bluejackkos;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.myViewHolder> {

    Context ctx;
    ArrayList<Transaction> transactions;

    public TransactionAdapter(Context context, ArrayList<Transaction> transactions){
        this.ctx = context;
        this.transactions = transactions;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(ctx).inflate(R.layout.list_transaction, parent, false);
        return new myViewHolder(v);
    }
    
    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {

        Transaction transactionDetail = transactions.get(position);

        holder.thumbnail.setImageResource(transactionDetail.getKosThumbnail());
        holder.kosName.setText(transactionDetail.getKosName());
        holder.kosFasilitas.setText(transactionDetail.getKosFacility());
        holder.hargaKos.setText("Rp. " + String.valueOf(transactionDetail.getKosPrice()));
        holder.tanggalBookingan.setText(transactionDetail.getBookingDate());
        holder.bookingId.setText(transactionDetail.getBookingId());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder deleteM = new AlertDialog.Builder(ctx);
                deleteM.setMessage("Are you sure want to cancel?");
                deleteM.setCancelable(true);

                deleteM.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                deleteM.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog popUp = deleteM.create();
                deleteM.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder{

        ImageView thumbnail;
        TextView kosName;
        TextView bookingId;
        TextView tanggalBookingan;
        TextView kosFasilitas;
        TextView hargaKos;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            thumbnail = itemView.findViewById(R.id.koshumbnail);
            bookingId = itemView.findViewById(R.id.bookingId);
            kosName = itemView.findViewById(R.id.kosName);
            tanggalBookingan = itemView.findViewById(R.id.tanggalBooking);
            kosFasilitas = itemView.findViewById(R.id.kosFacilities);
            hargaKos = itemView.findViewById(R.id.hargaKosnya);
        }
    }

}
