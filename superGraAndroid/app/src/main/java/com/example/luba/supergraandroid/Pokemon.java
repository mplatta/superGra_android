package com.example.luba.supergraandroid;

import java.util.UUID;

public class Pokemon {

    private String Nazwa;
    private String Opis;
    private int Foto;

    private Integer Siła;
    private Integer Szybkość;
    private String Klasa;
    private Integer Zycie;

    public Pokemon() {
    }

    public Pokemon(String nazwa, String opis, int foto) {
        Nazwa = nazwa;
        Opis = opis;
        Foto = foto;
    }

    public Pokemon(String nazwa, String opis, int foto, int sila, int szybkosc, String klasa, int zycie) {
        Nazwa = nazwa;
        Opis = opis;
        Foto = foto;
        Siła = sila;
        Szybkość = szybkosc;
        Klasa = klasa;
        Zycie = zycie;
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

    public Integer getSiła() {
        return Siła;
    }

    public void setSiła(Integer siła) {
        Siła = siła;
    }

    public Integer getSzybkość() {
        return Szybkość;
    }

    public void setSzybkość(Integer szybkość) {
        Szybkość = szybkość;
    }

    public String getKlasa() {
        return Klasa;
    }

    public void setKlasa(String klasa) {
        Klasa = klasa;
    }

    public Integer getZycie() {
        return Zycie;
    }

    public void setZycie(Integer zycie) {
        Zycie = zycie;
    }
}
