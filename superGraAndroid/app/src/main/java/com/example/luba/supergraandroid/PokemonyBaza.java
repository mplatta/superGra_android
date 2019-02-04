package com.example.luba.supergraandroid;

import java.util.ArrayList;
import java.util.List;

public class PokemonyBaza {

    private List<Pokemon> mPokemon;

    private PokemonyBaza() {
        mPokemon = new ArrayList<>();
        for(int i=0; i<10; i++){
            Pokemon poke = new Pokemon();
            poke.setNazwa("aaa"+String.valueOf(i));
            poke.setOpis("bbb"+String.valueOf(i));
            poke.setFoto(R.drawable.nicolas);
            mPokemon.add(poke);
        }
    }

    private static final PokemonyBaza ourInstance = new PokemonyBaza();

    public static PokemonyBaza getInstance() {
        return ourInstance;
    }

    public List<Pokemon> getPokemons(){
        return mPokemon;
    }

}
