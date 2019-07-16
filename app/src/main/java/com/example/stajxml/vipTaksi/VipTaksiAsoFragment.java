package com.example.stajxml.vipTaksi;

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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.stajxml.App;
import com.example.stajxml.IOnclickListener;
import com.example.stajxml.LocaleHelper;
import com.example.stajxml.R;
import com.example.stajxml.sort.SortPriceDown;
import com.example.stajxml.sort.SortedDownSmall;
import com.example.stajxml.sort.SortedDownWide;
import com.example.stajxml.sort.SortedPriceUp;
import com.example.stajxml.sort.SortedSmall;
import com.example.stajxml.sort.SortedUpSmall;
import com.example.stajxml.sort.SortedUpWide;
import com.example.stajxml.sort.SortedWide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;


public class VipTaksiAsoFragment extends Fragment {
    private static int a = 0;
    private static int b = 0;
    private RecyclerView rvtaksi;
    private ImageView imSwap;
    private Button zagruzitVse;
    private RadioGroup radioGroupSena, radioGroupBole;
    private ArrayList<ModelVipTaksi> listGrid;
    private  VipTaxiAdapter adapterVip;
    private IOnclickListener listener;
//   SearchViewListener listener1;

    public void setListener(IOnclickListener listener) {
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
        View view = inflater.inflate(R.layout.vip_taksi_xml, container, false);
        rvtaksi = view.findViewById(R.id.rvVip);
        imSwap = view.findViewById(R.id.btSwap);
        zagruzitVse = view.findViewById(R.id.zagEshyo);



        return view;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
     listGrid = loadModelVipTaxiFromAsset();
       adapterVip = new VipTaxiAdapter(listGrid, getContext(), new VipTaxiAdapter.ItemClickListener() {
            @Override
            public void onItemClick(ModelVipTaksi modelVipTaksi) {

            }
        });  // vertical recyclerView
        rvtaksi.setLayoutManager(new GridLayoutManager(getContext(), 2));
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
                    public void onClick(View v) { //saralash tugamasi

                        int id = radioGroupSena.getCheckedRadioButtonId();

                        int id1 = radioGroupBole.getCheckedRadioButtonId();

                        if (id == R.id.senaUbivayu && id1 == R.id.radioBole) {

                            VipTaksiAsoFragment sortDialog = new VipTaksiAsoFragment();
                            a=1;
                            FragmentTransaction transaction = getFragmentManager().beginTransaction();
                            transaction.setCustomAnimations(R.anim.enter_right_to_left,R.anim.exit_left_to_right,
                                    R.anim.enter_left_to_right,R.anim.exit_left_to_right)
                                    .replace(R.id.for_fragments, sortDialog, null);
                            transaction.addToBackStack(null);
                            transaction.commit();
                        } else if (id == R.id.senaUbivayu && id1 == R.id.radioMene) {
                            VipTaksiAsoFragment sortDialog = new VipTaksiAsoFragment();
                            a=2;
                            FragmentTransaction transaction = getFragmentManager().beginTransaction();
                            transaction.setCustomAnimations(R.anim.enter_right_to_left,R.anim.exit_left_to_right,
                                    R.anim.enter_left_to_right,R.anim.exit_left_to_right)
                                    .replace(R.id.for_fragments, sortDialog, null);
                            transaction.addToBackStack(null);
                            transaction.commit();
                        } else if (id == R.id.senaVozras && id1 == R.id.radioMene) {
                            VipTaksiAsoFragment sortDialog = new VipTaksiAsoFragment();
                            a=3;
                            FragmentTransaction transaction = getFragmentManager().beginTransaction();
                            transaction.setCustomAnimations(R.anim.enter_right_to_left,R.anim.exit_left_to_right,
                                    R.anim.enter_left_to_right,R.anim.exit_left_to_right)
                                    .replace(R.id.for_fragments, sortDialog, null);
                            transaction.addToBackStack(null);
                            transaction.commit();
                        } else if (id == R.id.senaVozras && id1 == R.id.radioBole) {
                            VipTaksiAsoFragment sortDialog = new VipTaksiAsoFragment();
                              a=4;
                            FragmentTransaction transaction = getFragmentManager().beginTransaction();
                            transaction.setCustomAnimations(R.anim.enter_right_to_left,R.anim.exit_left_to_right,
                                    R.anim.enter_left_to_right,R.anim.exit_left_to_right)
                                    .replace(R.id.for_fragments, sortDialog, null);
                            transaction.addToBackStack(null);
                            transaction.commit();
                        } else if (id == R.id.senaVozras) {
                            a=6;
                            VipTaksiAsoFragment sortDialog = new VipTaksiAsoFragment();
                            FragmentTransaction transaction = getFragmentManager().beginTransaction();
                            transaction.setCustomAnimations(R.anim.enter_right_to_left,R.anim.exit_left_to_right,
                                    R.anim.enter_left_to_right,R.anim.exit_left_to_right)
                                    .replace(R.id.for_fragments, sortDialog, null);
                            transaction.addToBackStack(null);
                            transaction.commit();

                        } else if (id == R.id.senaUbivayu) {
                            VipTaksiAsoFragment sortDialog = new VipTaksiAsoFragment();
                            a=5;
                            FragmentTransaction transaction = getFragmentManager().beginTransaction();
                            transaction.setCustomAnimations(R.anim.enter_right_to_left,R.anim.exit_left_to_right,
                                    R.anim.enter_left_to_right,R.anim.exit_left_to_right)
                                    .replace(R.id.for_fragments, sortDialog, null);
                            transaction.addToBackStack(null);
                            transaction.commit();
                        } else if (id == R.id.radioMene) {
                            VipTaksiAsoFragment sortDialog = new VipTaksiAsoFragment();
                            a=7;
                            FragmentTransaction transaction = getFragmentManager().beginTransaction();
                            transaction.setCustomAnimations(R.anim.enter_right_to_left,R.anim.exit_left_to_right,
                                    R.anim.enter_left_to_right,R.anim.exit_left_to_right)
                                    .replace(R.id.for_fragments, sortDialog, null);
                            transaction.addToBackStack(null);
                            transaction.commit();
                        } else if (id == R.id.radioBole) {
                            VipTaksiAsoFragment sortDialog = new VipTaksiAsoFragment();
                            a=8;
                            FragmentTransaction transaction = getFragmentManager().beginTransaction();
                            transaction.setCustomAnimations(R.anim.enter_right_to_left,R.anim.exit_left_to_right,
                                    R.anim.enter_left_to_right,R.anim.exit_left_to_right)
                                    .replace(R.id.for_fragments, sortDialog, null);
                            transaction.addToBackStack(null);
                            transaction.commit();
                        }
                        dialog.dismiss();
                    }
                });

            }
        });
        sortType(a);
        rvtaksi.setAdapter(adapterVip);

    }


    private ArrayList<ModelVipTaksi> loadModelVipTaxiFromAsset() { //jsonni pars qilish
        InputStream in=null;

        try {
            if(LocaleHelper.getLanguage(App.getInstance()).equals("uz"))
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



    public void sortType(int a) {     //saralash funksiyalari


        if (a==1){
            Collections.sort(listGrid, new SortedDownWide());
        }
        else
        if (a==2){
            Collections.sort(listGrid, new SortedDownSmall());
        }
        else
        if (a==3){
            Collections.sort(listGrid, new SortedUpSmall());
        }
        else
        if (a==4){
            Collections.sort(listGrid, new SortedUpWide());
        }
        else
        if (a==5){
            Collections.sort(listGrid, new SortPriceDown());
        }
        if (a == 6) {
            Collections.sort(listGrid, new SortedPriceUp(){
                @Override
                public int compare(ModelVipTaksi o1, ModelVipTaksi o2) {
                    return o1.getPrice()- o2.getPrice();
                }
            });

        }

        else
        if (a==7){
            Collections.sort(listGrid, new SortedSmall());
        }
        else
        if (a==8){
            Collections.sort(listGrid, new SortedWide());
        }
        Log.d("TTT", "SortedPriceUp");
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {  // qidiruv funksiyasi
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();

        inflater.inflate(R.menu.main2, menu);
        MenuItem item = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) item.getActionView();

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                listener.onClick(1);
                return false;
            }
        });

//        searchView.setOnSearchClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                listener.onClick(1);
//            }
//        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                adapterVip.searchWith(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Toast.makeText(getActivity(), "" +newText, Toast.LENGTH_SHORT).show();
                Log.d("grghtg",newText);
                adapterVip.searchWith(newText);
                return true;
            }

        });
    }
//    public interface SearchViewListener{
//        void onClick(int positon);
//    }

}
