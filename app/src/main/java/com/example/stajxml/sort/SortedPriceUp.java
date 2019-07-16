package com.example.stajxml.sort;

import com.example.stajxml.vipTaksi.ModelVipTaksi;

import java.util.ArrayList;
import java.util.Comparator;

public class SortedPriceUp implements Comparator<ModelVipTaksi> {
    @Override
    public int compare(ModelVipTaksi o1, ModelVipTaksi o2)
    {
        return o1.getPrice() - o1.getPrice();
    }

}
