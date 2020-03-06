package com.example.prova;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;


public class MainActivity extends AppCompatActivity {
    EditText edtCep;
    EditText campo1;
    EditText campo2;
    EditText campo3;
    EditText campo4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtCep = findViewById(R.id.edtCep);


    }


    public void busca(View view) {
        campo1 = findViewById(R.id.campo1);
        campo2 = findViewById(R.id.campo2);
        campo3 = findViewById(R.id.campo3);
        campo4 = findViewById(R.id.campo4);
        try {
            String json = new HttpInfo(edtCep.getText().toString()).execute().get();
            System.out.println(json);

            String[] vet = json.split(",");

            for (int i = 0; i < vet.length; i++) {
                vet[i].replace("\"", "");
            }
            campo1.setText(vet[2]);
            campo2.setText(vet[4]);
            campo3.setText(vet[5]);
            campo4.setText(vet[7]);


        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
