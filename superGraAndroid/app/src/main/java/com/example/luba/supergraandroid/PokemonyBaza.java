package com.example.luba.supergraandroid;

import java.util.ArrayList;
import java.util.List;

public class PokemonyBaza {

    private List<Pokemon> mPokemon;

    private PokemonyBaza() {
        mPokemon = new ArrayList<>();

        mPokemon.add(new Pokemon("Jigglypuff", "Słodki i miły, jednak jeśli go zdenerwujesz...", R.drawable.jigglypuff254));
        mPokemon.add(new Pokemon("Diglett", "Nazywany \"krecim Pokemonem\", choć z wyglądu przypomina dżdżownicę", R.drawable.diglett254));
        mPokemon.add(new Pokemon("Magikarp", "Zwykła śnięta ryba. Nie stanowi zagrożenia", R.drawable.magikarp254));
        mPokemon.add(new Pokemon("Snorlax", "Śpią przez większość swojego życia. Budzą się jedynie po to, by zaspokoić głód", R.drawable.snorlax));
        mPokemon.add(new Pokemon("Bulbazaur", "Na jego grzbiecie znajduje się charakterystyczna, sporych rozmiarów bulwa", R.drawable.bulbazaur));
        mPokemon.add(new Pokemon("Miau", "Uwielbia błyszczące przedmioty. Nocami samotnie wędruje szukając takowych", R.drawable.meow));

    }

    private static final PokemonyBaza ourInstance = new PokemonyBaza();

    public static PokemonyBaza getInstance() {
        return ourInstance;
    }

    public List<Pokemon> getPokemons(){
        return mPokemon;
    }

}
