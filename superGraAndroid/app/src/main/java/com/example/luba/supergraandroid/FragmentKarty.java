package com.example.luba.supergraandroid;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class FragmentKarty extends Fragment {

    View v;
    private RecyclerView myrecycleview;
    private List<Pokemon> lstPokemon;

    public FragmentKarty() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_karty, container, false);
        myrecycleview = (RecyclerView) v.findViewById(R.id.rv_KartyRecycleView);
        myrecycleview.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL));
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(getContext(), lstPokemon);
        myrecycleview.setLayoutManager(new LinearLayoutManager(getActivity()));
        myrecycleview.setAdapter(recyclerViewAdapter);
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        lstPokemon = new ArrayList<>();
        lstPokemon.add(new Pokemon("Jigglypuff", "Słodki i miły, jednak jeśli go zdenerwujesz...", R.drawable.jigglypuff254));
        lstPokemon.add(new Pokemon("Diglett", "Nazywany \"krecim Pokemonem\", choć z wyglądu przypomina dżdżownicę", R.drawable.diglett254));
        lstPokemon.add(new Pokemon("Magikarp", "Zwykła śnięta ryba. Nie stanowi zagrożenia", R.drawable.magikarp254));
        lstPokemon.add(new Pokemon("Snorlax", "Śpią przez większość swojego życia. Budzą się jedynie po to, by zaspokoić głód", R.drawable.snorlax));
        lstPokemon.add(new Pokemon("Bulbazaur", "Na jego grzbiecie znajduje się charakterystyczna, sporych rozmiarów bulwa", R.drawable.bulbazaur));
        lstPokemon.add(new Pokemon("Miau", "Uwielbia błyszczące przedmioty. Nocami samotnie wędruje szukając takowych", R.drawable.meow));

    }

}
