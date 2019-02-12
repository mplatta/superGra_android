package com.example.luba.supergraandroid;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class BazaDanychHelper extends SQLiteOpenHelper {

    static final String DATABASE_NAME = "BazaDanych.db";
    static final int DATABASE_VERSION = 1;

    BazaDanychHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String CREATE_BAZA_DANYCH = "CREATE TABLE " + BazaDanych.BazaDanychPokemon.TABLE_NAME
                + " (" + BazaDanych.BazaDanychPokemon._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + BazaDanych.BazaDanychPokemon.COLUMN_NAZWA + " TEXT NOT NULL, "
                + BazaDanych.BazaDanychPokemon.COLUMN_OPIS + " TEXT NOT NULL, "
                + BazaDanych.BazaDanychPokemon.COLUMN_SILA + " INTEGER NOT NULL, "
                + BazaDanych.BazaDanychPokemon.COLUMN_SZYBKOSC + " INTEGER NOT NULL, "
                + BazaDanych.BazaDanychPokemon.COLUMN_ZYWIOL + " TEXT NOT NULL, "
                + BazaDanych.BazaDanychPokemon.COLUMN_ZYCIE + " INTEGER NOT NULL, "
                + BazaDanych.BazaDanychPokemon.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP);";

        final String CHARACTERS_TABLE = "CREATE TABLE " + BazaDanych.BazaDanychPokemon.CH_TABLE_NAME
                + " (" +BazaDanych.BazaDanychPokemon.CH_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + BazaDanych.BazaDanychPokemon.CH_COLUMN_NAME + " VARCHAR(30) NOT NULL UNIQUE, "
                + BazaDanych.BazaDanychPokemon.CH_COLUMN_DESCRIPTION + " TEXT NOT NULL, "
                + BazaDanych.BazaDanychPokemon.CH_COLUMN_CLASS + " TEXT NOT NULL, "
                + BazaDanych.BazaDanychPokemon.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP);";

        final String SKILLS_TABLE = "CREATE TABLE " + BazaDanych.BazaDanychPokemon.SKILL_TABLE_NAME
                + " (" + BazaDanych.BazaDanychPokemon.SKILL_COLUMN_CH_ID + " INTEGER NOT NULL, "
                + BazaDanych.BazaDanychPokemon.SKILL_COLUMN_NAME + " TEXT NOT NULL, "
                + BazaDanych.BazaDanychPokemon.SKILL_COLUMN_VALUE + " INTEGER NOT NULL, "
                + "FOREIGN KEY(" + BazaDanych.BazaDanychPokemon.SKILL_COLUMN_CH_ID + ") "
                + "REFERENCES " + BazaDanych.BazaDanychPokemon.CH_TABLE_NAME + "("
                + BazaDanych.BazaDanychPokemon.CH_COLUMN_ID + "), "
                + "PRIMARY KEY(" + BazaDanych.BazaDanychPokemon.SKILL_COLUMN_CH_ID + ", "
                + BazaDanych.BazaDanychPokemon.SKILL_COLUMN_NAME + "));";

        final String EQ_TABLE = "CREATE TABLE " + BazaDanych.BazaDanychPokemon.EQ_TABLE_NAME
                + " (" + BazaDanych.BazaDanychPokemon.EQ_COLUMN_CH_ID + " INTEGER NOT NULL, "
                + BazaDanych.BazaDanychPokemon.EQ_COLUMN_NAME + " TEXT NOT NULL, "
                + "FOREIGN KEY(" + BazaDanych.BazaDanychPokemon.EQ_COLUMN_CH_ID + ") "
                + "REFERENCES " + BazaDanych.BazaDanychPokemon.CH_TABLE_NAME + "("
                + BazaDanych.BazaDanychPokemon.CH_COLUMN_ID + "));";

//        db.execSQL(CREATE_BAZA_DANYCH);

        db.execSQL(CHARACTERS_TABLE);
        db.execSQL(SKILLS_TABLE);
        db.execSQL(EQ_TABLE);

        Log.d("ASDASD", "aaaaaa");
        Log.d("ASDASD", CHARACTERS_TABLE);
        Log.d("ASDASD", SKILLS_TABLE);
        Log.d("ASDASD", EQ_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + BazaDanych.BazaDanychPokemon.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + BazaDanych.BazaDanychPokemon.EQ_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + BazaDanych.BazaDanychPokemon.SKILL_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + BazaDanych.BazaDanychPokemon.CH_TABLE_NAME);
    }
}
