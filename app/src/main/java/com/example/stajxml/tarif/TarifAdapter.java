package com.example.stajxml.tarif;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stajxml.R;

import java.util.ArrayList;

public class TarifAdapter extends RecyclerView.Adapter<TarifAdapter.VH> {
    ArrayList<TarifModel> list;
    private LayoutInflater inflater;
    private Context context;

    public TarifAdapter(ArrayList<TarifModel> list, Context context) {
        inflater = LayoutInflater.from(context);
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VH(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tarif, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        TarifModel model = list.get(position);
        holder.bind(model);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class VH extends RecyclerView.ViewHolder {
        TextView tarif;
        TextView den;
        RecyclerView recyclerView;

        public VH(@NonNull View itemView) {
            super(itemView);
            tarif = itemView.findViewById(R.id.tarf);
            den = itemView.findViewById(R.id.den);
            recyclerView = itemView.findViewById(R.id.list_type_tarif);
        }

        void bind(TarifModel model) {
            tarif.setText(model.getTarif());

            if (!model.getPlace().equals("")) {
                den.setText(model.getPlace());
            } else {
                den.setVisibility(View.GONE);
            }


            NarxAdapter adapter = new NarxAdapter(model.getListNarx(), context);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(adapter);
        }
    }
}
