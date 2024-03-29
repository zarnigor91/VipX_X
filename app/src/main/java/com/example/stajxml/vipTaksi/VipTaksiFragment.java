package com.example.stajxml.vipTaksi;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stajxml.R;
import com.example.stajxml.app.App;
import com.example.stajxml.app.LocaleHelper;
import com.example.stajxml.sort.SortPriceDown;
import com.example.stajxml.sort.SortedDownSmall;
import com.example.stajxml.sort.SortedDownWide;
import com.example.stajxml.sort.SortedPriceUp;
import com.example.stajxml.sort.SortedSmall;
import com.example.stajxml.sort.SortedUpSmall;
import com.example.stajxml.sort.SortedUpWide;
import com.example.stajxml.sort.SortedWide;
import com.example.stajxml.tarif.TarifFragment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;


public class VipTaksiFragment extends Fragment implements SearchView.OnCloseListener {
    private static int a = 0;
    private RecyclerView rvtaksi;
    private ImageView imSwap, imageLogo;
    private Button zagruzitVse;
    private RadioGroup radioGroupSena, radioGroupBole;
    private ArrayList<ModelVipTaksi> listGrid;
    private VipTaxiAdapter adapterVip;
    private SearchView searchView = null;
    private   int count=0;

    private SearchView.OnQueryTextListener queryTextListener;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.vip_taksi_xml, container, false);
        rvtaksi = view.findViewById(R.id.rvVip);
        imSwap = view.findViewById(R.id.btSwap);
        zagruzitVse = view.findViewById(R.id.zagEshyo);
        imageLogo = view.findViewById(R.id.image_logo);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listGrid = loadModelVipTaxiFromAsset();
        adapterVip = new VipTaxiAdapter(listGrid, getContext(), new VipTaxiAdapter.ItemClickListener() {
            @Override
            public void onItemClick(ModelVipTaksi modelVipTaksi) {   //vertical recycler
                TarifFragment tarifFragment = new TarifFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.enter_right_to_left, R.anim.exit_left_to_right,
                        R.anim.enter_left_to_right, R.anim.exit_left_to_right)
                        .replace(R.id.for_fragments, tarifFragment, "FRAGMENT_TARIF")
                        .addToBackStack(null);
                transaction.commit();
            }
        });


        rvtaksi.setLayoutManager(new GridLayoutManager(getContext(), 2));   // vertical recyclerView
        rvtaksi.setAdapter(adapterVip);
        zagruzitVse.setOnClickListener(new View.OnClickListener() {  //qo`shimcha yuklash
            @Override
            public void onClick(View v) {
                adapterVip.update1(listGrid);
            }
        });
        imSwap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {  // dialog chaqirish tugmasi

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(view.getContext());
                View dialogView = LayoutInflater.from(view.getContext()).inflate(R.layout.dialog_xml, null, false);
                ImageView close = dialogView.findViewById(R.id.close);
                Button btnClose = dialogView.findViewById(R.id.otmena);
                Button btPrimenit = dialogView.findViewById(R.id.primenit);
                alertDialog.setView(dialogView);
                final AlertDialog dialog = alertDialog.create();
count++;
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
                btnClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                radioGroupSena = dialogView.findViewById(R.id.radiogroup_sena);
                radioGroupBole = dialogView.findViewById(R.id.radiogroup_bol);

                btPrimenit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) { //  sort  and  go to update VipTaksiFragment

                        int id = radioGroupSena.getCheckedRadioButtonId();

                        int id1 = radioGroupBole.getCheckedRadioButtonId();

                        if (id == R.id.senaUbivayu && id1 == R.id.radioBole) {
                            Collections.sort(listGrid, new SortedDownWide());
                            adapterVip.notifyDataSetChanged();
                        } else if (id == R.id.senaUbivayu && id1 == R.id.radioMene) {
                            Collections.sort(listGrid, new SortedDownSmall());
                            adapterVip.notifyDataSetChanged();
                        } else if (id == R.id.senaVozras && id1 == R.id.radioMene) {
                            Collections.sort(listGrid, new SortedUpSmall());
                            adapterVip.notifyDataSetChanged();

                        } else if (id == R.id.senaVozras && id1 == R.id.radioBole) {
                            Collections.sort(listGrid, new SortedUpWide());
                            adapterVip.notifyDataSetChanged();
                        } else if (id == R.id.senaVozras) {
                            Collections.sort(listGrid, new SortedPriceUp());
                            adapterVip.notifyDataSetChanged();

                        } else if (id == R.id.senaUbivayu) {
                            Collections.sort(listGrid, new SortPriceDown());
                            adapterVip.notifyDataSetChanged();
                        } else if (id == R.id.radioMene) {
                            Collections.sort(listGrid, new SortedSmall());
                            adapterVip.notifyDataSetChanged();
                        } else if (id == R.id.radioBole) {
                            Collections.sort(listGrid, new SortedWide());
                            adapterVip.notifyDataSetChanged();
                        }
                        dialog.dismiss();


                    }
                });

            }
        });

        rvtaksi.setAdapter(adapterVip);

    }


    private ArrayList<ModelVipTaksi> loadModelVipTaxiFromAsset() { //Parse from json
        InputStream in = null;

        try {
            if (LocaleHelper.getLanguage(App.getInstance()).equals("uz"))
                in = getActivity().getAssets().open("vip_taxi_uz.json");
            else
                in = getActivity().getAssets().open("vip_taxi.json");
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


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {   // search
        menu.clear();
        inflater.inflate(R.menu.main2, menu);
        MenuItem searchItem = menu.findItem(R.id.search);

        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();

        }
        if (searchView != null) {
            queryTextListener = new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextChange(String newText) {
                    Log.i("onQueryTextChange", newText);
                    adapterVip.searchWith(newText);
                    return true;
                }

                @Override
                public boolean onQueryTextSubmit(String query) {
                    Log.i("onQueryTextSubmit", query);
                    adapterVip.searchWith(query);
                    return true;
                }
            };
            searchView.setOnQueryTextListener(queryTextListener);
        }
        super.onCreateOptionsMenu(menu, inflater);
    }

//    @Override
//    public void onDetach() {
//        super.onDetach();
//        getActivity().getSupportFragmentManager().popBackStack("0", FragmentManager.POP_BACK_STACK_INCLUSIVE);
//
//    }

    @Override
    public boolean onClose() {
        imageLogo.setVisibility(View.VISIBLE);
        return false;
    }


}
