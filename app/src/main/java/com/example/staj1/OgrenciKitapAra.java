package com.example.staj1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

import com.example.staj1.veritabanıClasslar.Kitap_bilgi;

import java.util.ArrayList;

public class OgrenciKitapAra extends AppCompatActivity {

    private Button arama;
    private EditText kitapIsmı;
    private GridView kitap;
    private VeritabaniYardimcisi vt;
    private ArrayList<String> k_bilgi= new ArrayList<>();

    private ArrayAdapter<String> adepter;

    private ArrayList<Kitap_bilgi> liste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ogrenci_kitap_ara);

        kitapIsmı = findViewById(R.id.kitapIsmı);
        kitap = findViewById(R.id.kitap_bilgi);
        arama = findViewById(R.id.arama);

        vt = new VeritabaniYardimcisi(this);

        arama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String arananKelime = kitapIsmı.getText().toString();
                Log.e("aranan kelime: ",""+arananKelime);

                k_bilgi = new ArrayList<>();

                liste = new veritabaniSorgular().kitapAra(vt,arananKelime);

                for (Kitap_bilgi k : liste) {

                    if(k.getKitap_durum()==0)
                    {
                        k_bilgi.add(k.getKitap_ad());
                        k_bilgi.add(k.getYazar_ad());
                        k_bilgi.add("mevcut");
                    }
                    else
                    {
                        k_bilgi.add(k.getKitap_ad());
                        k_bilgi.add(k.getYazar_ad());
                        k_bilgi.add("mevcut değil");
                    }
                }

                adepter = new ArrayAdapter<>(OgrenciKitapAra.this
                        , android.R.layout.simple_list_item_1
                        , android.R.id.text1
                        , k_bilgi);
                kitap.setAdapter(adepter);

            }
        });




    }
}
