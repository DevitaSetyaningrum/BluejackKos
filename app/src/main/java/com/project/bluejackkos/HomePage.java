package com.project.bluejackkos;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.zip.Inflater;

public class HomePage extends AppCompatActivity {

    RecyclerView kosList;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

    String currId;

    void init(){
        currId = getIntent().getStringExtra("currentuserid");

        kosList = findViewById(R.id.kosList);
        layoutManager = new LinearLayoutManager(this);
        kosList.setLayoutManager(layoutManager);

        adapter = new Adapter(Helper.dataKos, this, currId);
        kosList.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("Kost List");
        setContentView(R.layout.kostlist_form);

        init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.bookTransaction:{
                Intent bookTrx = new Intent(HomePage.this, TransactionDetails.class);
                bookTrx.putExtra("currentuserid", currId);
                startActivity(bookTrx);
                break;
            }
            case R.id.exit:{
                finish();
                break;
            }
        }
        return true;
    }
}
