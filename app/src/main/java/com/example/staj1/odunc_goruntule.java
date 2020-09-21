package com.example.staj1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import com.example.staj1.veritabanıClasslar.odunc_bilgi;

import java.util.ArrayList;

public class odunc_goruntule extends AppCompatActivity {

    private GridView oduncListesi;
    private VeritabaniYardimcisi vt;
    private ArrayList<String> liste = new ArrayList<>();

    private ArrayAdapter<String> adaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_odunc_goruntule);

        oduncListesi = findViewById(R.id.oduncListesi);

        vt = new VeritabaniYardimcisi(this);

        ArrayList<String> ivjj  = new veritabaniSorgular().oduncGoruntule(vt);


        adaptor = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,android.R.id.text1,ivjj);
        oduncListesi.setAdapter(adaptor);

    }
}
