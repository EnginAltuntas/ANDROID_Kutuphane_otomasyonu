package com.example.staj1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Gorevli1 extends AppCompatActivity {

    private Button oduncVer;
    private Button kitap_ekle;
    private Button iadeAl;
    private Button oduncEkrani;
    private Button o_listesi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gorevli1);

        oduncVer = findViewById(R.id.oduncVer);
        iadeAl = findViewById(R.id.iadeAl);
        oduncEkrani = findViewById(R.id.oduncEkrani);
        o_listesi = findViewById(R.id.o_listesi);
        kitap_ekle = findViewById(R.id.kitap_ekle);

        kitap_ekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sayfa = new Intent(Gorevli1.this,kitap_ekle.class);
                startActivity(sayfa);

            }
        });

        oduncEkrani.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sayfa = new Intent(Gorevli1.this,odunc_goruntule.class);
                startActivity(sayfa);

            }
        });
        o_listesi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sayfa = new Intent(Gorevli1.this,o_liste.class);
                startActivity(sayfa);
            }
        });

        oduncVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sayfa = new Intent(Gorevli1.this,OduncEkrani.class);
                startActivity(sayfa);

            }
        });

        iadeAl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sayfa = new Intent(Gorevli1.this,iadeEkrani.class);
                startActivity(sayfa);
            }
        });



    }
}
