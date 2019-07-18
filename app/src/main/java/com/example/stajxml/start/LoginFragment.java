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
import androidx.fragment.app.FragmentTransaction;

import com.example.stajxml.app.App;
import com.example.stajxml.HomeActivity;
import com.example.stajxml.app.LocaleHelper;
import com.example.stajxml.app.Prefs;
import com.example.stajxml.R;

public class LoginFragment extends Fragment {
    TextView uz, rus, Reg, bezreg, wellcome, infoText, login, prodoljit;
    Context context;
    private EditText editLogin;
    private ConstraintLayout prodol;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getInstance().updateRes();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.log_in_fragment, container, false);
        uz = view.findViewById(R.id.tvUz);
        rus = view.findViewById(R.id.tvRus);
        Reg = view.findViewById(R.id.tvReg);
        bezreg = view.findViewById(R.id.bz);

        wellcome = view.findViewById(R.id.textWelcome);
        infoText = view.findViewById(R.id.text);
        login = view.findViewById(R.id.textLogin);
        prodoljit = view.findViewById(R.id.prodoljit);
        editLogin = view.findViewById(R.id.login);

        context = getContext();
        uz.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        Reg.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);

        Reg.setTextColor(getResources().getColor(R.color.colorYellow));

        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setResourceString();
        checkLenguage();
        prodol = view.findViewById(R.id.prodoljit1);
        prodol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Prefs.instance(App.preferences).getLogin().equals(editLogin.getText().toString().trim())) {
                    ParolFragment newFragment = new ParolFragment();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.setCustomAnimations(R.anim.enter_right_to_left, R.anim.exit_left_to_right,
                            R.anim.enter_left_to_right, R.anim.exit_left_to_right)
                            .replace(R.id.continer, newFragment)
                            .addToBackStack(null)
                            .commit();
                } else {
                    login.setError("XATO kiritildi");
                    Toast.makeText(context, "Login xato", Toast.LENGTH_SHORT).show();
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
                RegistratsionFragment registratsionFragment = new RegistratsionFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.continer, registratsionFragment)
                        .addToBackStack(null)
                        .setCustomAnimations(R.anim.abc_slide_in_top, R.anim.abc_slide_out_top) // Top Fragment Animation
                        .show(registratsionFragment)
                        .setCustomAnimations(R.anim.abc_slide_in_bottom, R.anim.abc_slide_out_bottom) // Bottom Fragment Animation
                        .show(registratsionFragment)
                        .commit();

            }
        });


    }

    private void setResourceString() {
        wellcome.setText(context.getResources().getString(R.string.dobro_pajalovat));
        infoText.setText(context.getResources().getString(R.string.arenda_avto));
        login.setText(context.getResources().getString(R.string.login));
        prodoljit.setText(context.getResources().getString(R.string.prodoljit));
        bezreg.setText(context.getResources().getString(R.string.bez_avto));
        Reg.setText(context.getResources().getString(R.string.register));
        uz.setText(context.getResources().getString(R.string.uzbek));
        rus.setText(context.getResources().getString(R.string.rus));

    }

    void uzSlect() {
        uz.setTextColor(getResources().getColor(R.color.colorYellow));
        uz.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        rus.setTextColor(getResources().getColor(R.color.colorGrey));

        context = LocaleHelper.setLocale(getContext(), "uz");
        App.getInstance().updateRes();
        setResourceString();
    }

    void rusSlect() {
        uz.setTextColor(getResources().getColor(R.color.colorGrey));
        rus.setTextColor(getResources().getColor(R.color.colorYellow));
        rus.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);

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
