package com.example.luba.supergraandroid;

public class Pokemon {

    private String Nazwa;
    private String Opis;
    private int Foto;

    public Pokemon() {
    }

    public Pokemon(String nazwa, String opis, int foto) {
        Nazwa = nazwa;
        Opis = opis;
        Foto = foto;
    }


    public String getNazwa() {
        return Nazwa;
    }

    public void setNazwa(String nazwa) {
        Nazwa = nazwa;
    }

    public String getOpis() {
        return Opis;
    }

    public void setOpis(String opis) {
        Opis = opis;
    }

    public int getFoto() {
        return Foto;
    }

    public void setFoto(int foto) {
        Foto = foto;
    }
}
