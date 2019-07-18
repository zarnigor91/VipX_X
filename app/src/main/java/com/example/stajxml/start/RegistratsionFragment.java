package com.example.stajxml.start;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.stajxml.app.App;
import com.example.stajxml.app.Prefs;
import com.example.stajxml.R;


public class RegistratsionFragment extends Fragment {
    private EditText login_input, password_input, name_input, surName_input, telNumber_input,
            question_input, answer_input, pass2_input;
    private String login, password, name, surName, telNumber, question, answer, pass2;
    private Button RegisterButton;
    private Spinner spinner ;
    private int  index = 0;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.registration, container, false);
        RegisterButton = view.findViewById(R.id.registr_bt);
        login_input = view.findViewById(R.id.tie_login);
        password_input = view.findViewById(R.id.tie_pass1);
        name_input = view.findViewById(R.id.tie_name);
        surName_input = view.findViewById(R.id.tie_surname);
        pass2_input = view.findViewById(R.id.tie_pass2);
        telNumber_input = view.findViewById(R.id.tie_tel);
        answer_input = view.findViewById(R.id.tie_answer);
        spinner=view.findViewById(R.id.spinner_ques);
        return view;

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        spinnerSetData();
        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registred();


            }


        });
    }

    private void registred() {     // registration
        String [] questions = {
                "Sizning birinchi moshinangiz?",
                "Uy hayvoningizni ismi?",
                "Sevimli kitobingiz",
                "Siz yoqtirgan dasturlash tili"
        };

        login = login_input.getText().toString();
        password = password_input.getText().toString();
        name = name_input.getText().toString();
        surName = surName_input.getText().toString();
         question = questions[index];
        pass2 = pass2_input.getText().toString();
        telNumber = telNumber_input.getText().toString();
        answer = answer_input.getText().toString();
        if (!name.isEmpty() && !surName.isEmpty() && !telNumber.isEmpty() &&
                !answer.isEmpty() && !password.isEmpty() &&
                pass2.equals(pass2) && !login.isEmpty()
        ) {

            Prefs.instance(App.preferences).setLogin(login);         // Send to sharedpreference
            Prefs.instance(App.preferences).setPass(password);
            Prefs.instance(App.preferences).setReg(true);
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.continer, new LoginFragment())
                    .addToBackStack(null)
                    .commit();
        } else if (!validation()) {
            Toast.makeText(getActivity(), "sucessfull", Toast.LENGTH_LONG).show();
        }
    }




    private void spinnerSetData() {
        ArrayAdapter adapter = new ArrayAdapter(getContext(), R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                index=position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private boolean validation() {    // check for isEmpty

        boolean valid = true;
        if (name.isEmpty() || name_input.getText().length() < 4) {
            name_input.setError("empty name");

            valid = false;
        }
        if (surName.isEmpty()) {
            surName_input.setError("empty surName");
            valid = false;
        }
        if (telNumber.isEmpty() || telNumber_input.getText().length() != 13) {
            telNumber_input.setError("incorrect number");
            valid = false;
        }
        if (answer.isEmpty()) {
            answer_input.setError("empty answer");
            valid = false;

        }

        if (login.isEmpty() || login_input.getText().length() < 4) {
            login_input.setError("invalid login");

            valid = false;
        }
        if (password.isEmpty() || password_input.getText().length() < 5) {
            login_input.setError("invalid password");

            valid = false;
        }
        if (password.isEmpty() && password.equals(pass2) || pass2_input.getText().length() < 5) {
            login_input.setError("invalid password2");

            valid = false;
        }
        return true;
    }


}
