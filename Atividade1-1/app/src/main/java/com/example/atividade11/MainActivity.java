package com.example.atividade11;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button yourbutton = findViewById(R.id.button2);
        EditText number_one = findViewById(R.id.editTextNumber);
        EditText number_two = findViewById(R.id.editTextNumber2);
        TextView result_text = findViewById(R.id.results);


        yourbutton.setOnClickListener(
                v -> {

                    String field_1 = number_one.getText().toString();
                    String field_2 = number_two.getText().toString();

                    float result = Float.parseFloat(field_1) + Float.parseFloat(field_2);

                    result_text.setText(String.format("Result: %s", result));

                    //result_text.setText(field_1);



                });

    }

}