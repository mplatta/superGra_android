package com.example.luba.supergraandroid;

import android.provider.BaseColumns;

class BazaDanych {

    private BazaDanych(){};

    static final class BazaDanychPokemon implements BaseColumns{
        static final String TABLE_NAME = "Pokemon";
        static final String COLUMN_NAZWA = "Nazwa";
        static final String COLUMN_OPIS = "Opis";
        static final String  COLUMN_SILA = "Sila";
        static final String  COLUMN_SZYBKOSC = "Szybkosc";
        static final String  COLUMN_ZYWIOL = "Zywiol";
        static final String  COLUMN_ZYCIE = "Zycie";
        static final String  COLUMN_TIMESTAMP = "timestamp_t";

        static final String CH_TABLE_NAME = "Characters";
        static final String CH_COLUMN_ID = "Id";
        static final String CH_COLUMN_NAME = "Name";
        static final String CH_COLUMN_DESCRIPTION = "Description";
        static final String CH_COLUMN_CLASS = "ChClass";

        static final String SKILL_TABLE_NAME = "Skills";
        static final String SKILL_COLUMN_CH_ID = "ChId";
        static final String SKILL_COLUMN_NAME = "Name";
        static final String SKILL_COLUMN_VALUE = "Value";

        static final String EQ_TABLE_NAME = "Equipment";
        static final String EQ_COLUMN_CH_ID = "ChId";
        static final String EQ_COLUMN_NAME = "NAME";
    }

}
