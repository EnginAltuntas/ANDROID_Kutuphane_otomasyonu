package com.example.staj1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.staj1.veritabanıClasslar.turler;

import java.util.ArrayList;

public class kitap_ekle extends AppCompatActivity {

    private Button KekleButon;
    private Spinner Sliste;
    private EditText Kyazar;
    private EditText Kisim;
    private VeritabaniYardimcisi vt;
    private String tur_isim;
    private int turId;

    private ArrayList<String> turAd = new ArrayList<>();
    private ArrayAdapter<String> turadiAdaptoru;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitap_ekle);

        vt = new VeritabaniYardimcisi(kitap_ekle.this);
        KekleButon = findViewById(R.id.KekleButon);
        Sliste = findViewById(R.id.Sliste);
        Kyazar = findViewById(R.id.Kyazar);
        Kisim = findViewById(R.id.Kisim);

        ArrayList<turler> gelentur = new veritabaniSorgular().tur(vt);
        for (turler t : gelentur) {
            turAd.add(t.getTur_ad());
        }
        turadiAdaptoru = new ArrayAdapter<>(this
                , android.R.layout.simple_list_item_1
                , android.R.id.text1
                , turAd);
        Sliste.setAdapter(turadiAdaptoru);

        Sliste.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                tur_isim = turAd.get(i);
                i=i+1;
                turId=i;
                Log.e("tur",""+tur_isim+" "+turId);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        KekleButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = vt.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("kitap_ad",Kisim.getText().toString().trim());
                values.put("kitap_durum",0);
                values.put("tur_id",turId);
                values.put("yazar_ad",Kyazar.getText().toString().trim());

                long sonuc = db.insertOrThrow("Kitap_bilgi",null,values);
                db.close();
                if(sonuc == -1)
                {
                    Toast.makeText(kitap_ekle.this,"Hata! Kitap eklenemedi!",Toast.LENGTH_LONG).show();
                }
                else
                {
                    finish();
                    startActivity(getIntent());
                    Toast.makeText(kitap_ekle.this,"Kitap ekleme başarılı.",Toast.LENGTH_LONG).show();
                }
            }
        });





    }
}