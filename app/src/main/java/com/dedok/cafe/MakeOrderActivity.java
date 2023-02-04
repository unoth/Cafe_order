package com.dedok.cafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MakeOrderActivity extends AppCompatActivity {

    private static final String EXTRA_USER_NAME = "userName";

    private TextView textViewGreetings;
    private RadioGroup radioGroupDrinks;
    private RadioButton radioButtonTea;
    private RadioButton radioButtonCoffee;
    private TextView textViewAdditives;
    private CheckBox checkboxSugar;
    private CheckBox checkboxMilk;
    private CheckBox checkboxLemon;
    private Spinner spinnerTea;
    private Spinner spinnerCoffee;
    private Button buttonOrder;
    private String userName;
    private String drink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_order);
        initViews();
        setupUserName();

        radioGroupDrinks.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                if (id == radioButtonTea.getId()) {
                    onUserChoseTea();

                } else if (id == radioButtonCoffee.getId()) {
                    onUserChoseCoffee();

                }
            }
        });
        radioButtonTea.setChecked(true);

        buttonOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onUserMadeOrder();
            }
        });
    }

    private void onUserMadeOrder() {
        ArrayList<String> additives = new ArrayList<>();
        if (checkboxSugar.isChecked()) {
            additives.add(checkboxSugar.getText().toString());
        } else if (checkboxMilk.isChecked()) {
            additives.add(checkboxMilk.getText().toString());
        } else if (radioButtonTea.isChecked() && checkboxLemon.isChecked()) {
            additives.add(checkboxLemon.getText().toString());
        }
    }


    private void onUserChoseTea() {
        drink = getString(R.string.tea);
        String additives = getString(R.string.additives, drink);
        textViewAdditives.setText(additives);
        checkboxLemon.setVisibility(View.VISIBLE);
        spinnerTea.setVisibility(View.VISIBLE);
        spinnerCoffee.setVisibility(View.INVISIBLE);

    }

    private void onUserChoseCoffee() {
        drink = getString(R.string.coffee);
        String additives = getString(R.string.additives, drink);
        textViewAdditives.setText(additives);
        checkboxLemon.setVisibility(View.INVISIBLE);
        spinnerTea.setVisibility(View.INVISIBLE);
        spinnerCoffee.setVisibility(View.VISIBLE);
    }

    public static Intent newIntent(Context context, String userName) {
        Intent intent = new Intent(context, MakeOrderActivity.class);
        intent.putExtra(EXTRA_USER_NAME, userName);
        return intent;
    }

    private void setupUserName() {
        userName = getIntent().getStringExtra(EXTRA_USER_NAME);
        String greetings = getString(R.string.greetings, userName);
        textViewGreetings.setText(greetings);
    }


    private void initViews() {
        textViewGreetings = findViewById(R.id.textViewGreetings);
        radioGroupDrinks = findViewById(R.id.radioGroupDrinks);
        radioButtonTea = findViewById(R.id.radioButtonTea);
        radioButtonCoffee = findViewById(R.id.radioButtonCoffee);
        textViewAdditives = findViewById(R.id.textViewAdditives);
        checkboxSugar = findViewById(R.id.checkboxSugar);
        checkboxMilk = findViewById(R.id.checkboxMilk);
        checkboxLemon = findViewById(R.id.checkboxLemon);
        spinnerTea = findViewById(R.id.spinnerTea);
        spinnerCoffee = findViewById(R.id.spinnerCoffee);
        buttonOrder = findViewById(R.id.buttonOrder);
    }

}