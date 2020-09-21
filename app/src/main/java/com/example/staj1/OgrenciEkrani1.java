package com.example.staj1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OgrenciEkrani1 extends AppCompatActivity {

    private Button kitapListele;
    private  Button kitapAra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ogrenci_ekrani1);

        kitapListele = findViewById(R.id.kitapListele);
        kitapAra = findViewById(R.id.kitapAra);


        kitapListele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sayfa = new Intent(OgrenciEkrani1.this,TumKitaplar.class);
                startActivity(sayfa);
            }
        });

        kitapAra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sayfa = new Intent(OgrenciEkrani1.this,OgrenciKitapAra.class);
                startActivity(sayfa);
            }
        });



    }
}
