package com.example.luba.supergraandroid;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.android.gms.vision.text.Line;

import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

public class ListaKart extends AppCompatActivity {

    private LinearLayout tabLayout;
    private RecyclerView myrecycleview;
    private List<Pokemon> lstPokemon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        lstPokemon = PokemonyBaza.getInstance().getPokemons();

        setContentView(R.layout.activity_lista_kart);
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

        myrecycleview = (RecyclerView) findViewById(R.id.rv_KartyRecycleView);
        myrecycleview.setLayoutManager(new LinearLayoutManager(this));

        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(this, lstPokemon);
        //myrecycleview.setLayoutManager(new LinearLayoutManager(getActivity()));
        myrecycleview.setAdapter(recyclerViewAdapter);


    }

    public void createPokemon(View view){
        Intent intent = new Intent(this, DodajKarte.class);
        startActivity(intent);
    }
}
