package com.example.stajxml.tarif;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stajxml.R;
import com.example.stajxml.RjexNumber;

import java.util.ArrayList;

public class NarxAdapter extends RecyclerView.Adapter<NarxAdapter.VH> {
    private ArrayList<NarxModel> list;
    private Context context;

    public NarxAdapter(ArrayList<NarxModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public NarxAdapter.VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VH(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_narx, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NarxAdapter.VH holder, int position) {
        NarxModel model = list.get(position);
        holder.bind(model);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class VH extends RecyclerView.ViewHolder {

        TextView vaqt;
        TextView oplata;
        TextView soat;
        TextView narx;
        LinearLayout background;

        public VH(@NonNull View itemView) {
            super(itemView);
            vaqt = itemView.findViewById(R.id.vaqt);
            oplata = itemView.findViewById(R.id.oplata);
            soat = itemView.findViewById(R.id.soat);
            narx = itemView.findViewById(R.id.narx);
            background = itemView.findViewById(R.id.yashil_back);
        }

        void bind(NarxModel model) {

            vaqt.setText(model.getVaqt());
            oplata.setText(RjexNumber.StringToString(model.getOplata()));

            if (model.getNarxniKorinishi()) {
                soat.setText(model.getTime());
                narx.setText(RjexNumber.StringToString(model.getNarx()));

            } else {
                background.setVisibility(View.INVISIBLE);
            }
        }
    }
}
