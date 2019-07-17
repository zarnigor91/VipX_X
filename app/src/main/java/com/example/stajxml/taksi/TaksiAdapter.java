package com.example.stajxml.taksi;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stajxml.R;

import java.util.ArrayList;


public class TaksiAdapter extends RecyclerView.Adapter<TaksiAdapter.VH> {
    private ArrayList<ModelTaksi> list;
    private LayoutInflater inflater;
    private OnItemClickListener listener;
    private boolean firstSee = false;
    private int itemPosition = -1;

    private Context context;


    public TaksiAdapter(ArrayList<ModelTaksi> list, Context context, OnItemClickListener listener) {
        this.list = list;
        this.listener = listener;
        inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.row_rv_item, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, final int position) {
        final ModelTaksi modelTaksi = list.get(position);
        holder.holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(position);
            }
        });

        if (position == 0 && !firstSee) {
            holder.imag.setImageResource(modelTaksi.getImage());
            holder.holder.setBackgroundColor(Color.rgb(255, 255, 255));
            firstSee = true;
            modelTaksi.setIscChecked(true);

            itemPosition = 0;
        }

        if (modelTaksi.isChecked()) {     //   taxi load check
            holder.imag.setImageResource(modelTaksi.getImage());
            holder.holder.setBackgroundColor(Color.rgb(255, 255, 255));
            modelTaksi.setIscChecked(true);
            holder.textTaksi.setTextColor(context.getResources().getColor(R.color.colorBlack));
        } else {
            holder.imag.setImageResource(modelTaksi.getImageChecked());
            holder.holder.setBackgroundResource(R.drawable.background_tegma);
            modelTaksi.setIscChecked(false);
            holder.textTaksi.setTextColor(context.getResources().getColor(R.color.colorGrey));
        }

        holder.textTaksi.setText(modelTaksi.getText());

        holder.holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemPosition != -1) {
                    list.get(itemPosition).setIscChecked(false);
                    modelTaksi.setIscChecked(true);
                    notifyDataSetChanged();
                    itemPosition = position;
                }
            }
        });

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setChecked(int pos) {
        list.get(pos).setIscChecked(true);
        notifyItemChanged(pos);
    }


    public interface OnItemClickListener {
        void onItemClick(int pos);
    }

    class VH extends RecyclerView.ViewHolder {

        ImageView imag;
        TextView textTaksi;
        LinearLayout holder;

        public VH(@NonNull View itemView) {
            super(itemView);
            imag = itemView.findViewById(R.id.imtak);
            textTaksi = itemView.findViewById(R.id.taksitv);
            holder = itemView.findViewById(R.id.holder);

        }
    }
}
