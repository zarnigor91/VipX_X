package com.example.stajxml.vipTaksi;




import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.DiffUtil;



import java.util.List;

public class DiffCallback extends DiffUtil.Callback {
    private List<ModelVipTaksi> oldList;
    private List<ModelVipTaksi> newList;

    public DiffCallback(List<ModelVipTaksi> oldList, List<ModelVipTaksi> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPoistion, int newItemPoistion) {
       ModelVipTaksi oldItem=oldList.get(oldItemPoistion);
        ModelVipTaksi newItem=newList.get(newItemPoistion);
        return oldItem.getName().equals(newItem.getName());
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean areContentsTheSame(int oldItemPoistion, int newItemPoistion) {
        ModelVipTaksi oldItem=oldList.get(oldItemPoistion);
        ModelVipTaksi newItem=newList.get(newItemPoistion);
        return oldItem.equals(newItem);
    }

    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }

}
