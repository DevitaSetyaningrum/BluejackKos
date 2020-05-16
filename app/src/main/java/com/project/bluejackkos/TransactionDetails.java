package com.project.bluejackkos;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class TransactionDetails extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("Transaction Details");

        String userID = getIntent().getStringExtra("currentuserid");
        ArrayList<Transaction> userTrans = new ArrayList<>();

        for(Transaction trans : Helper.transaction){
            if (trans.getUserId().equals(userID)){
                userTrans.add(trans);
            }
        }

        if(userTrans.size() == 0){
            setContentView(R.layout.booking_kost_empty_form);
        } else {
            setContentView(R.layout.booking_kost_list_form);

            recyclerView = findViewById(R.id.bookingList);
            layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);

            adapter = new TransactionAdapter( this, userTrans);
            recyclerView.setAdapter(adapter);
        }

    }
}
