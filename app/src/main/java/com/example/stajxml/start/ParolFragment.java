package com.example.stajxml.start;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.example.stajxml.app.App;
import com.example.stajxml.HomeActivity;
import com.example.stajxml.app.LocaleHelper;
import com.example.stajxml.app.Prefs;
import com.example.stajxml.R;

public class ParolFragment extends Fragment implements View.OnClickListener {

    private EditText etParol;
    TextView uz, rus, Reg, bezreg, wellcome, infoText, parol, voyti;
    Context context;
    private ConstraintLayout prodol;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getInstance().updateRes();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.parol_xml, container, false);
        prodol = view.findViewById(R.id.prodoljit2);
        etParol = view.findViewById(R.id.etParol);
        uz = view.findViewById(R.id.tvUz);
        rus = view.findViewById(R.id.tvRus);
        Reg = view.findViewById(R.id.tvReg);
        bezreg = view.findViewById(R.id.bz);

        wellcome = view.findViewById(R.id.textWelcome);
        infoText = view.findViewById(R.id.text);
        parol = view.findViewById(R.id.textParol);
        voyti = view.findViewById(R.id.voyti);
        etParol = view.findViewById(R.id.etParol);

        context = getContext();
        uz.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        Reg.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);

        Reg.setTextColor(getResources().getColor(R.color.colorYellow));



        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        uz = view.findViewById(R.id.tvUz);
        rus = view.findViewById(R.id.tvRus);
        Reg = view.findViewById(R.id.tvReg);
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



        uz.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        Reg.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        prodol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Prefs.instance(App.preferences).getPass().equals(etParol.getText().toString().trim())) {
                    Intent intent = new Intent(getContext(), HomeActivity.class);
                    //intent.putExtra();
                    startActivity(intent);
                    getActivity().finish();
                } else {
                    etParol.setError("XATO kiritildi");
                    Toast.makeText(getContext(), "Parol xato", Toast.LENGTH_SHORT).show();
                }
            }
        });
        bezreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), HomeActivity.class);
                //intent.putExtra();
                startActivity(intent);
                getActivity().finish();
            }
        });

        checkLenguage();
    }

    @Override
    public void onClick(View v) {
    }


    private void setResourceString() {
        wellcome.setText(context.getResources().getString(R.string.dobro_pajalovat));
        infoText.setText(context.getResources().getString(R.string.arenda_avto));
        parol.setText(context.getResources().getString(R.string.password));
        voyti.setText(context.getResources().getString(R.string.voyti));
        bezreg.setText(context.getResources().getString(R.string.bez_avto));
        Reg.setText(context.getResources().getString(R.string.register));
        uz.setText(context.getResources().getString(R.string.uzbek));
        rus.setText(context.getResources().getString(R.string.rus));

    }
    void uzSlect() {

        uz.setTextColor(getResources().getColor(R.color.colorGrey));
        rus.setTextColor(getResources().getColor(R.color.colorYellow));
        Reg.setTextColor(getResources().getColor(R.color.colorYellow));

        context = LocaleHelper.setLocale(getContext(), "uz");
        App.getInstance().updateRes();
        setResourceString();
    }

    void rusSlect() {
        uz.setTextColor(getResources().getColor(R.color.colorYellow));
        rus.setTextColor(getResources().getColor(R.color.colorGrey));
        rus.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        Reg.setTextColor(getResources().getColor(R.color.colorYellow));

        context = LocaleHelper.setLocale(getContext(), "ru");
        App.getInstance().updateRes();
        setResourceString();
    }


    void checkLenguage() {
        if (LocaleHelper.getLanguage(App.getInstance()).equals("uz")) {
            uz.setTextColor(getResources().getColor(R.color.colorYellow));
            uz.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
            rus.setTextColor(getResources().getColor(R.color.colorGrey));
        } else {
            uz.setTextColor(getResources().getColor(R.color.colorGrey));
            rus.setTextColor(getResources().getColor(R.color.colorYellow));
            rus.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        }
    }

}
