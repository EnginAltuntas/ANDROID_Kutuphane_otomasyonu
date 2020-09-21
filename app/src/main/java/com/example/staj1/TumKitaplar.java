package com.example.staj1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;

import com.example.staj1.veritabanıClasslar.Kitap_bilgi;
import com.example.staj1.veritabanıClasslar.turler;

import java.util.ArrayList;

public class TumKitaplar extends AppCompatActivity {

    private Spinner spinner;
    private VeritabaniYardimcisi vt;
    private GridView kitap_ad;

    private ArrayList<String> turAd = new ArrayList<>();
    private ArrayList<Integer> turId = new ArrayList<>();
    private ArrayList<String> kitapAd = new ArrayList<>();

    private ArrayAdapter<String> turadiAdaptoru;
    private ArrayAdapter<String> veriAdaptoru;

    private int SecilenTurId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tum_kitaplar);

        spinner = findViewById(R.id.spinner);
        kitap_ad = findViewById(R.id.kitap_ad);

        vt = new VeritabaniYardimcisi(this);

        ArrayList<turler> gelentur = new veritabaniSorgular().tur(vt);
        for (turler t : gelentur) {
            turId.add(t.getTur_id());
            turAd.add(t.getTur_ad());
        }
        turadiAdaptoru = new ArrayAdapter<>(this
                , android.R.layout.simple_list_item_1
                , android.R.id.text1
                , turAd);
        spinner.setAdapter(turadiAdaptoru);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                SecilenTurId = i;
                SecilenTurId++;

                kitapAd = new ArrayList<>();

                ArrayList<Kitap_bilgi> gelen = new veritabaniSorgular().tumKitaplar(vt, SecilenTurId);

                for (Kitap_bilgi k : gelen) {

                    if (k.getKitap_durum() == 0){
                        kitapAd.add(k.getKitap_ad());
                        kitapAd.add(k.getYazar_ad());
                        kitapAd.add("muvcut");
                    }
                    else{
                        kitapAd.add(k.getKitap_ad());
                        kitapAd.add(k.getYazar_ad());
                        kitapAd.add("mevcut değil");
                    }
                }
                veriAdaptoru = new ArrayAdapter<>(TumKitaplar.this, android.R.layout.simple_list_item_1, android.R.id.text1, kitapAd);
                kitap_ad.setAdapter(veriAdaptoru);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

}