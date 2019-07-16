package com.example.stajxml.search;


import com.example.stajxml.vipTaksi.ModelVipTaksi;

import java.util.ArrayList;

public interface IFilter {
    ArrayList<ModelVipTaksi> search(String text);
}
