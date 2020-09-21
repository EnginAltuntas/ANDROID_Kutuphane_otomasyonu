package com.example.staj1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private Button uye_ol;
    private Button yonlendir;
    private EditText kullaniciAdi;
    private EditText sifre;
    private VeritabaniYardimcisi vt;
    private TextView bir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        kullaniciAdi = findViewById(R.id.kullaniciAdi);
        sifre = findViewById(R.id.sifre);
        uye_ol = findViewById(R.id.uye_ol);
        yonlendir = findViewById(R.id.yonlendir);
        bir = findViewById(R.id.bir);

        vt = new VeritabaniYardimcisi(this);

        veritabani();
        yonlendir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int sonuc = new veritabaniSorgular().KullaniciKontrol(vt,kullaniciAdi.getText().toString().trim(),sifre.getText().toString().trim());
                if(sonuc==0)
                {
                    Intent sayfa = new Intent(MainActivity.this,OgrenciEkrani1.class);
                    startActivity(sayfa);
                }
                else if(sonuc==1){
                    Intent sayfa = new Intent(MainActivity.this,Gorevli1.class);
                    startActivity(sayfa);
                }
                else if(sonuc == 2)
                {
                    Toast.makeText(MainActivity.this,"Kullanıcı adi veya şifre yanlış girildi!",Toast.LENGTH_LONG).show();
                }
            }
        });

        uye_ol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sayfa = new Intent(MainActivity.this,kayit.class);
                startActivity(sayfa);
            }
        });


    }

    public void veritabani(){
        DatabaseCopyHelper databaseCopyHelper = new DatabaseCopyHelper(this);
        try {
            databaseCopyHelper.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        databaseCopyHelper.openDataBase();
    }
}
