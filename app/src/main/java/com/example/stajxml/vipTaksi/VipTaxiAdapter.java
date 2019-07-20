package com.example.stajxml.vipTaksi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stajxml.R;
import com.example.stajxml.RjexNumber;
import com.example.stajxml.app.App;
import com.example.stajxml.app.LocaleHelper;
import com.example.stajxml.search.FilterImpl;
import com.example.stajxml.search.IFilter;

import java.util.ArrayList;
import java.util.List;

public class VipTaxiAdapter extends RecyclerView.Adapter<VipTaxiAdapter.VH> {
    private ArrayList<ModelVipTaksi> list;
    private ArrayList<ModelVipTaksi> newList;
//    private ArrayList<ModelVipTaksi> sortList;
    private LayoutInflater inflater;
    private Context context;
    private IFilter filter;


    private ItemClickListener itemClickListener;


    public VipTaxiAdapter(ArrayList<ModelVipTaksi> list, Context context, ItemClickListener listener) {
        this.list = list;
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.itemClickListener = listener;
        newList = (ArrayList<ModelVipTaksi>) list.clone();
        newList.addAll(list);
        filter = new FilterImpl(list);
//        sortList=list;

    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VH(inflater.inflate(R.layout.item_vip_taxi, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        ModelVipTaksi modelTaksi = list.get(position);
        holder.bind(modelTaksi, itemClickListener);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void searchWith(String newText) {
        ArrayList<ModelVipTaksi> newList = filter.search(newText);
        DiffCallback callback = new DiffCallback(list, newList);
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(callback);
        list.clear();
        list.addAll(newList);
        result.dispatchUpdatesTo(this);
        notifyDataSetChanged();
    }

    public void update1(List<ModelVipTaksi> newList1) {
        list.addAll(newList1);
        list.addAll(newList1);
        notifyDataSetChanged();

    }

    public interface ItemClickListener {
        void onItemClick(ModelVipTaksi modelVipTaksi);

    }

    class VH extends RecyclerView.ViewHolder {

        ImageView carImage;
        TextView status;
        TextView price;
        TextView name;
        TextView time;
        TextView ot;
        TextView dan;


        public VH(@NonNull final View itemView) {
            super(itemView);
            carImage = itemView.findViewById(R.id.car_image);
            name = itemView.findViewById(R.id.car_name);
            price = itemView.findViewById(R.id.car_price);
            ot = itemView.findViewById(R.id.tvOt);
            status = itemView.findViewById(R.id.car_status);
            time = itemView.findViewById(R.id.car_time);
            dan=itemView.findViewById(R.id.dan);


        }

        public void bind(final ModelVipTaksi modelTaksi, final ItemClickListener listener) {
            if(modelTaksi.getCarImage() == 1) {
                carImage.setImageResource(R.drawable.elantra);
            }
            else if(modelTaksi.getCarImage() == 2) {
                carImage.setImageResource(R.drawable.prado1);
            }
            else
            if(modelTaksi.getCarImage() == 3) {
                carImage.setImageResource(R.drawable.hyundai);
            }
            else if(modelTaksi.getCarImage() == 4) {
                carImage.setImageResource(R.drawable.prado2);
            }
            else
            if(modelTaksi.getCarImage() == 5) {
                carImage.setImageResource(R.drawable.hyundai2);
            }
            else if(modelTaksi.getCarImage() == 6) {
                carImage.setImageResource(R.drawable.prado3);
            }
            status.setText(modelTaksi.getStatus());
            name.setText(modelTaksi.getName());
            price.setText(RjexNumber.IntToString(modelTaksi.getPrice()) + "");
            time.setText(modelTaksi.getTime());
            if (LocaleHelper.getLanguage(App.getInstance()).equals("uz")) {
                ot.setVisibility(View.GONE);
                if (modelTaksi.getStatus().equals("bo`sh")) {
                    status.setBackground(context.getResources().getDrawable(R.drawable.status_background1));

                } else {
                    status.setBackground(context.getResources().getDrawable(R.drawable.status_background2));
                }

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onItemClick(modelTaksi);
                    }
                });
            } else {
                     dan.setVisibility(View.GONE);
                if (modelTaksi.getStatus().equals("свободен")) {
                    status.setBackground(context.getResources().getDrawable(R.drawable.status_background1));
                } else {
                    status.setBackground(context.getResources().getDrawable(R.drawable.status_background2));
                }
//                dan.setVisibility(View.INVISIBLE);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onItemClick(modelTaksi);
                    }
                });
            }
        }


    }

}
