package com.example.staj1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class OduncEkrani extends AppCompatActivity {

    private VeritabaniYardimcisi vt;

    private EditText ogrNumara;
    private EditText kAd;
    private EditText tarih;
    private Button kaydet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_odunc_ekrani);

        ogrNumara = findViewById(R.id.ogrNumara);
        kAd = findViewById(R.id.kAd);
        tarih = findViewById(R.id.tarih);
        kaydet = findViewById(R.id.kaydet);


        kaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String num = ogrNumara.getText().toString().trim();

                vt = new VeritabaniYardimcisi(OduncEkrani.this);

                String gelenMsj = new veritabaniSorgular().oduncEkle(vt,num,kAd.getText().toString(),tarih.getText().toString());
                ogrNumara.setText("");
                kAd.setText("");
                tarih.setText("");
                Toast.makeText(OduncEkrani.this,""+gelenMsj,Toast.LENGTH_LONG).show();

            }
        });




    }
}
