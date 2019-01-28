package com.example.luba.supergraandroid;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BazaDanychHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "BazaDanych.db";
    public static final int DATABASE_VERSION = 1;

    public BazaDanychHelper(Context context) {
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

        db.execSQL(CREATE_BAZA_DANYCH);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + BazaDanych.BazaDanychPokemon.TABLE_NAME);
    }
}
