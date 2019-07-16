package com.example.stajxml.tarif;

import java.util.ArrayList;

public class TarifModel {
    private String tarif;
    private String place;
    private ArrayList<NarxModel> listNarx;

    public TarifModel(String tarif, String place, ArrayList<NarxModel> listNarx) {
        this.tarif = tarif;
        this.place = place;
        this.listNarx = listNarx;
    }

    public String getTarif() {
        return tarif;
    }

    public String getPlace() {
        return place;
    }

    public ArrayList<NarxModel> getListNarx() {
        return listNarx;
    }


}
