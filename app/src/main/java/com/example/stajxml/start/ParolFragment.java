package com.example.stajxml.start;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.example.stajxml.App;
import com.example.stajxml.HomeActivity;
import com.example.stajxml.R;

public class ParolFragment extends Fragment implements View.OnClickListener {
    TextView uz, rus, Reg;

    private  ConstraintLayout prodol;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getInstance().updateRes();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.parol_xml,container,false);
        prodol = view.findViewById(R.id.prodoljit2);

        prodol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), HomeActivity.class);
                //intent.putExtra();
                startActivity(intent);
                getActivity().finish();
            }
        });


        return  view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        uz=view.findViewById(R.id.tvUz);
        rus=view.findViewById(R.id.tvRus);
        Reg=view.findViewById(R.id.tvReg);
        uz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uzSlect();
            }
        });
        rus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rusSlect();
            }
        });
        Reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regSlect();
            }
        });


        uz.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        Reg.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
    }

    @Override
    public void onClick(View v) {
    }
    void uzSlect(){
        uz.setTextColor(getResources().getColor(R.color.colorWhite));
        rus.setTextColor(getResources().getColor(R.color.colorYellow));
        Reg.setTextColor(getResources().getColor(R.color.colorYellow));
    }
    void rusSlect(){
        uz.setTextColor(getResources().getColor(R.color.colorYellow));
        rus.setTextColor(getResources().getColor(R.color.colorWhite));
        rus.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        Reg.setTextColor(getResources().getColor(R.color.colorYellow));
    }
    void regSlect(){
        uz.setTextColor(getResources().getColor(R.color.colorYellow));
        rus.setTextColor(getResources().getColor(R.color.colorYellow));
        Reg.setTextColor(getResources().getColor(R.color.colorWhite));
    }
}
