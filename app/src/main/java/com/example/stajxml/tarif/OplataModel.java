package com.example.stajxml.tarif;

import java.util.ArrayList;

public class OplataModel {
    private String adress;
    private String narx;

    public OplataModel(String adress, String narx) {
        this.adress = adress;
        this.narx = narx;
    }

    public String getAdress() {
        return adress;
    }

    public String getNarx() {
        return narx;
    }
    public  static ArrayList<OplataModel> genOplata(){
        String [] adress=new String[9];
       adress[0]= "Самарканд (1 день)";
       adress[1]="Самарканд-Бухоро(2 день)";
       adress[2]="Самарканд-Бухоро(3 день)";
       adress[3]="Самарканд-Бухоро-Xiva(5 день)";
       adress[4]="Фаргона(1 день)";
       adress[5]="Наманган(1 день)";
       adress[6]="Кокон(1 день)";
       adress[7]="Андижон(1 день)";
       adress[8]="Поездку по долине(2 день";

       String [] narx=new String[9];
       narx[0]="170 000";
        narx[1]="170 000";
        narx[2]="170 000";
        narx[3]="170 000";
        narx[4]="170 000";
        narx[5]="170 000";
        narx[6]="170 000";
        narx[7]="170 000";
        narx[8]="170 000";
       ArrayList<OplataModel> list=new ArrayList<>();
        for (int i = 0; i <9 ; i++) {
            list.add(new OplataModel(adress[i],narx[i]));
        }
        return list;
    }
}
