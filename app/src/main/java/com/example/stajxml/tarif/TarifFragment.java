package com.example.stajxml.tarif;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.example.stajxml.App;
import com.example.stajxml.GravitySnapHelper;
import com.example.stajxml.LocaleHelper;
import com.example.stajxml.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class TarifFragment extends Fragment {
    private RecyclerView rvTarif, recyclerView;
    private int[] imag = new int[]{R.drawable.car_image, R.drawable.car_image2, R.drawable.car_image, R.drawable.car_image2};
    private ArrayList<Integer> carList;
    private PradoAdapter pradoAdapter;
    private Button zakazat;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvTarif = view.findViewById(R.id.tarif_list);

        ArrayList<TarifModel> tarifModels =loadTarifFromAsset() ;

        TarifAdapter adapter = new TarifAdapter(tarifModels, getContext());
        rvTarif.setLayoutManager(new LinearLayoutManager(getContext()));
        rvTarif.setAdapter(adapter);
          zakazat=view.findViewById(R.id.zakazat);


        recyclerView = view.findViewById(R.id.rv_arenda);
        carList = car();
        pradoAdapter = new PradoAdapter(carList, getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(pradoAdapter);

        SnapHelper snapHelper = new GravitySnapHelper(Gravity.START);
        snapHelper.attachToRecyclerView(recyclerView);
        zakazat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ZakazFragment newFragment = new ZakazFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.enter_right_to_left,R.anim.exit_left_to_right,
                        R.anim.enter_left_to_right,R.anim.exit_left_to_right)
                        .replace(R.id.for_fragments, newFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.prado_xml, container, false);

        return view;
    }

    private ArrayList<Integer> car() {

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            list.add(imag[i]);
        }
        return list;
    }
    private ArrayList<TarifModel> loadTarifFromAsset() {
        InputStream in=null;
        try {
            if(LocaleHelper.getLanguage(App.getInstance()).equals("uz"))
         in = getActivity().getAssets().open("prado_tarif_uz.json");
            else
                in = getActivity().getAssets().open("prado_tarif.json");
            StringBuilder builder = new StringBuilder();
            Gson gson=new Gson();
            byte[] buffer = new byte[1024];
            int offset;
            while ((offset = in.read(buffer)) != -1) {
                builder.append(new String(buffer, 0, offset));
            }
            ArrayList<TarifModel> models=gson.fromJson(builder.toString(),
                    new TypeToken<ArrayList<TarifModel>>(){}.getType());
            return models;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

}
