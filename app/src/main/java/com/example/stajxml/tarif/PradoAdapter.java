package com.example.stajxml.tarif;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.stajxml.R;

import java.util.ArrayList;

public class PradoAdapter extends RecyclerView.Adapter<PradoAdapter.Vh> {
    private ArrayList<Integer> list;
    private LayoutInflater inflater;
    private Context context;

    public PradoAdapter(ArrayList<Integer> list, Context context) {
        this.list = list;
        inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public Vh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Vh(LayoutInflater.from(parent.getContext()).inflate(R.layout.moshina_xml, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Vh holder, int position) {

        Glide.with(context)
                .load(list.get(position))
                .into(holder.im);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Vh extends RecyclerView.ViewHolder {
        ImageView im = itemView.findViewById(R.id.imPradoMosh);

        public Vh(@NonNull View itemView) {

            super(itemView);

        }
    }
}
