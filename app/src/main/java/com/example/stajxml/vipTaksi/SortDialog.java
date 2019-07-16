package com.example.stajxml.vipTaksi;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stajxml.R;
import com.example.stajxml.Sorted;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;

public class SortDialog extends DialogFragment {
    public static int a = 0;
    VipTaxiAdapter adapter;
    private ArrayList<ModelVipTaksi> list;
    private RecyclerView rvsort;
    private ArrayList<ModelVipTaksi> modelVipTaksis;
    private ArrayList<ModelVipTaksi> modelVipTaksis1;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.sort_xml, container, false);
        rvsort = view.findViewById(R.id.rvsort);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        list = loadModelVipTaxiFromAsset();
        sortType(a);
        final VipTaxiAdapter adapterVip = new VipTaxiAdapter(list, getContext(), new VipTaxiAdapter.ItemClickListener() {
            @Override
            public void onItemClick(ModelVipTaksi modelVipTaksi) {

            }
        });
        rvsort.setLayoutManager(new GridLayoutManager(getContext(), 2));
        rvsort.setAdapter(adapterVip);

    }

    public void sortType(int a) {

        if (a == 6) {
            Collections.sort(list, new Sorted(){
                @Override
                public int compare(ModelVipTaksi o1, ModelVipTaksi o2) {
                    return o1.getPrice()- o2.getPrice();
                }
            });
        }

        Log.d("TTT", "Sorted");
    }

    private ArrayList<ModelVipTaksi> loadModelVipTaxiFromAsset() {
        try {
            InputStream in = getActivity().getAssets().open("vip_taxi.json");
            StringBuilder builder = new StringBuilder();
            Gson gson = new Gson();
            byte[] buffer = new byte[1024];
            int offset;
            while ((offset = in.read(buffer)) != -1) {
                builder.append(new String(buffer, 0, offset));
            }
            ArrayList<ModelVipTaksi> models = gson.fromJson(builder.toString(),
                    new TypeToken<ArrayList<ModelVipTaksi>>() {
                    }.getType());
            return models;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

}
