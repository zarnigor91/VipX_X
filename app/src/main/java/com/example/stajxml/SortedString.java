package com.example.stajxml;

import com.example.stajxml.vipTaksi.ModelVipTaksi;

import java.util.Comparator;

public class SortedString implements Comparator<ModelVipTaksi> {


    @Override
    public int compare(ModelVipTaksi o1, ModelVipTaksi o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
