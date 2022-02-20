package com.example.atividade122;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button yourbutton = (Button) findViewById(R.id.button2);

        yourbutton.setOnClickListener(
                v -> {

                    EditText textEdit = findViewById(R.id.editTextTextPersonName);
                    Intent inte = new Intent(MainActivity.this, MainActivity2.class);
                    String text=textEdit.getText().toString();
                    if (text.isEmpty())
                        inte.putExtra("message", getString(R.string.men));
                    else
                        inte.putExtra("message", text);
                        MainActivity.this.startActivity(inte);

                });


    }
}