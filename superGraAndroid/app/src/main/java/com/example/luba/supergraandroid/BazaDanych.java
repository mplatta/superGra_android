package com.example.luba.supergraandroid;

import android.provider.BaseColumns;

public class BazaDanych {

    private BazaDanych(){};

    public static final class BazaDanychPokemon implements BaseColumns{
        public static final String TABLE_NAME = "Pokemon";
        public static final String COLUMN_NAZWA = "Nazwa";
        public static final String COLUMN_OPIS = "Opis";
        public static final String  COLUMN_SILA = "Sila";
        public static final String  COLUMN_SZYBKOSC = "Szybkosc";
        public static final String  COLUMN_ZYWIOL = "Zywiol";
        public static final String  COLUMN_ZYCIE = "Zycie";
        public static final String  COLUMN_TIMESTAMP = "timestamp";
    }

}
