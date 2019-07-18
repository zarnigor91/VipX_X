package com.example.stajxml.taksi;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.stajxml.app.App;
import com.example.stajxml.app.LocaleHelper;
import com.example.stajxml.R;
import com.example.stajxml.puteshest.CubeTransformer;
import com.example.stajxml.puteshest.PuteshestAdapter;
import com.example.stajxml.tarif.TarifFragment;
import com.example.stajxml.vipTaksi.ModelVipTaksi;
import com.example.stajxml.vipTaksi.VipTaksiFragment;
import com.example.stajxml.vipTaksi.VipTaxiAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class FragmentTaksi extends Fragment {
    private static final Integer[] IMAGES = {R.drawable.advert, R.drawable.advert2, R.drawable.advert3, R.drawable.advert4};
    private static ViewPager mPager;
    RecyclerView horizontalList;
    RecyclerView gridList;
    boolean issBack = true;
    ArrayList<ModelVipTaksi> listGrid;
    SearchViewListener listener;
    private Button zagruzitVse;
    private TaksiAdapter adapter;
    private VipTaxiAdapter adapterVip;
    private ArrayList<Integer> ImagesArray = new ArrayList<>();
    private PuteshestAdapter puteshestAdapter;

    public void setListener(SearchViewListener listener) {
        this.listener = listener;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.asosiy_xml, container, false);
        horizontalList = view.findViewById(R.id.im_rv_Vip);
        gridList = view.findViewById(R.id.rvM);
        zagruzitVse = view.findViewById(R.id.zagVse);
        mPager = view.findViewById(R.id.viewPager);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        ArrayList<ModelTaksi> listHorizontal = loadModelTaxiFromAsset();

        adapter = new TaksiAdapter(listHorizontal, getContext(), new TaksiAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                adapter.setChecked(pos);

            }
        });
        horizontalList.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        horizontalList.setAdapter(adapter);  // HORIZONTAL recylerview

        listGrid = loadModelVipTaxiFromAsset();
        adapterVip = new VipTaxiAdapter(listGrid, getContext(), new VipTaxiAdapter.ItemClickListener() {
            @Override
            public void onItemClick(ModelVipTaksi modelVipTaksi) {
                TarifFragment tarifFragment = new TarifFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.enter_right_to_left, R.anim.exit_left_to_right,
                        R.anim.enter_left_to_right, R.anim.exit_left_to_right)
                        .replace(R.id.for_fragments, tarifFragment, "FRAGMENT_TARIF")
                        .addToBackStack(null);
                transaction.commit();
            }
        });
        gridList.setLayoutManager(new GridLayoutManager(getContext(), 2)); // vertical recycler view load item

        gridList.setAdapter(adapterVip);
        zagruzitVse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapterVip.update(listGrid);
                adapterVip.update1(listGrid);     // upload again
                Toast.makeText(getActivity(), "click", Toast.LENGTH_SHORT).show();

            }
        });


        TextView vseTaksi = view.findViewById(R.id.tvVsetak);
        vseTaksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {                    //  vse taksi load
                VipTaksiFragment newFragment = new VipTaksiFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.enter_right_to_left, R.anim.exit_left_to_right,
                        R.anim.enter_left_to_right, R.anim.exit_left_to_right)
                        .add(R.id.for_fragments, newFragment, "FRAGMENT_VIP")
                   .addToBackStack(null)
               .commit();
            }
        });
        init();

        puteshestAdapter.setListener(new PuteshestAdapter.ItemClickListener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(getContext(), "Item clicked " + position, Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void init() { // viewPager load
        for (int i = 0; i < IMAGES.length; i++)
            ImagesArray.add(IMAGES[i]);
        puteshestAdapter = new PuteshestAdapter(ImagesArray, getActivity());  // viewPager load with animation
        mPager.setPageTransformer(true, new CubeTransformer());
        mPager.setAdapter(puteshestAdapter);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {    // search
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();

        inflater.inflate(R.menu.main2, menu);
        MenuItem item = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) item.getActionView();

        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(1);
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                adapterVip.searchWith(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Toast.makeText(getActivity(), "" + newText, Toast.LENGTH_SHORT).show();
                Log.d("grghtg", newText);
                adapterVip.searchWith(newText);
                return true;
            }

        });
    }

    private ArrayList<ModelVipTaksi> loadModelVipTaxiFromAsset() {  // Parse from json
        InputStream in = null;
        try {
            if (LocaleHelper.getLanguage(App.getInstance()).equals("uz")) {
                in = getActivity().getAssets().open("vip_taxi_uz.json");
            } else {
                in = getActivity().getAssets().open("vip_taxi.json");
            }
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

    private ArrayList<ModelTaksi> loadModelTaxiFromAsset() {
        InputStream in = null;

        try {
            if (LocaleHelper.getLanguage(App.getInstance()).equals("uz")) {
                in = getActivity().getAssets().open("taxi_uz.json");
            } else in = getActivity().getAssets().open("taxi.json");
            StringBuilder builder = new StringBuilder();
            Gson gson = new Gson();
            byte[] buffer = new byte[1024];
            int offset;
            while ((offset = in.read(buffer)) != -1) {
                builder.append(new String(buffer, 0, offset));
            }
            ArrayList<ModelTaksi> models = gson.fromJson(builder.toString(),
                    new TypeToken<ArrayList<ModelTaksi>>() {
                    }.getType());
            return models;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }


    public interface SearchViewListener {
        void onClick(int positon);
    }


}