package com.example.stajxml.tarif;


public class NarxModel {
    private String vaqt;
    private String oplata;
    private String narx;
    private String time;
    private Boolean narxniKorinishi;

    public NarxModel(String vaqt, String oplata, String narx, String time, Boolean value) {
        this.vaqt = vaqt;
        this.oplata = oplata;
        this.narx = narx;
        this.time = time;
        this.narxniKorinishi = value;
    }

    public String getVaqt() {
        return vaqt;
    }

    public String getOplata() {
        return oplata;
    }

    public String getNarx() {
        return narx;
    }

    public String getTime() {
        return time;
    }

    public Boolean getNarxniKorinishi() {
        return narxniKorinishi;
    }
}


