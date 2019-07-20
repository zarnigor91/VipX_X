package com.example.stajxml.sort;

import com.example.stajxml.vipTaksi.ModelVipTaksi;

import java.util.Comparator;

public class SortPriceDown implements Comparator<ModelVipTaksi> {


    @Override
    public int compare(ModelVipTaksi o1, ModelVipTaksi o2)
    {
        return (o2.getPrice()-o1.getPrice());
    }


}
