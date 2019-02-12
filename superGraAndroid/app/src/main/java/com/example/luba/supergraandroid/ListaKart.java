package com.example.luba.supergraandroid;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import java.util.List;

public class ListaKart extends AppCompatActivity {

    private RecyclerView myrecycleview;

    private SQLiteDatabase mDatabase;
    private RecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_kart);

        BazaDanychHelper dbHelper = new BazaDanychHelper(this);
        mDatabase = dbHelper.getWritableDatabase();

        myrecycleview = (RecyclerView) findViewById(R.id.rv_KartyRecycleView);
        myrecycleview.setLayoutManager(new LinearLayoutManager(this));

        recyclerViewAdapter = new RecyclerViewAdapter(this, getAllItems());
        myrecycleview.setAdapter(recyclerViewAdapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("ListaKart");


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createPokemon(view);
            }
        });

    }

    private Cursor getAllItems() {
        return mDatabase.rawQuery("SELECT " + BazaDanych.BazaDanychPokemon.CH_COLUMN_NAME + ", " +
                BazaDanych.BazaDanychPokemon.CH_COLUMN_DESCRIPTION + ", " +
                BazaDanych.BazaDanychPokemon.CH_COLUMN_CLASS + ", " +
                BazaDanych.BazaDanychPokemon.CH_COLUMN_ID +
                " FROM " +
                BazaDanych.BazaDanychPokemon.CH_TABLE_NAME, null);
    }

    public void createPokemon(View view){
        Intent intent = new Intent(this, DodajKarte.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        recyclerViewAdapter.swapCursor(getAllItems());
    }
}
