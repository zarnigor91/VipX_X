package com.example.stajxml.search;


import com.example.stajxml.vipTaksi.ModelVipTaksi;

import java.util.ArrayList;

public class FilterImpl implements IFilter {
    private ArrayList<ModelVipTaksi> originList;
    private ArrayList<ModelVipTaksi> tempList;

    public FilterImpl(ArrayList<ModelVipTaksi> tempList) {
        originList = (ArrayList<ModelVipTaksi>) tempList.clone();
    }

    @Override
    public ArrayList<ModelVipTaksi> search(String text) {
        tempList = new ArrayList<>();
        if (text.isEmpty()) {
            tempList.addAll(originList);
        } else {
            for (ModelVipTaksi model : originList) {
                if (model.getName().toLowerCase().contains(text.toLowerCase())) {
                    tempList.add(model);
                }
            }
        }
        return tempList;
    }
}
