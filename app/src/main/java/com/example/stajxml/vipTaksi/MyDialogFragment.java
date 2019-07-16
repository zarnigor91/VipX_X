package com.example.stajxml.vipTaksi;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.stajxml.R;

public class MyDialogFragment extends DialogFragment {
    ImageView close;
    Button otmemena;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.dialog_xml,container,false);
        close=view.findViewById(R.id.close);
        otmemena=view.findViewById(R.id.otmena);
        return  view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        final AlertDialog alertDialog = builder.create();
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              alertDialog.dismiss();
            }
        });
        otmemena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }
}
