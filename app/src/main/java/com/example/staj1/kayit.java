package com.example.staj1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class kayit extends AppCompatActivity {
    private EditText k_isim;
    private EditText k_soyisim;
    private EditText k_okulno;
    private EditText k_bolum;
    private EditText k_kadi;
    private EditText k_sifre;
    private EditText k_sifretekrar;
    private Button kayit_ol;
    private VeritabaniYardimcisi vt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayit);

        vt = new VeritabaniYardimcisi(this);
        k_isim = findViewById(R.id.k_isim);
        k_soyisim = findViewById(R.id.k_soyisim);
        k_okulno = findViewById(R.id.k_okulno);
        k_bolum = findViewById(R.id.k_bolum);
        k_kadi = findViewById(R.id.k_kadi);
        k_sifre = findViewById(R.id.k_sifre);
        k_sifretekrar = findViewById(R.id.k_sifretekrar);
        kayit_ol = findViewById(R.id.kayit_ol);

        kayit_ol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!k_sifre.getText().toString().trim().equals(k_sifretekrar.getText().toString().trim()))
                {
                    Toast.makeText(kayit.this,"Şifreler uyuşmamaktadır.Girilen şifrelerin aynı olduğundan emin olun",Toast.LENGTH_LONG).show();
                }
                else
                {
                    int sonuc = new veritabaniSorgular().Kayit(vt,k_isim.getText().toString().trim(),
                            k_soyisim.getText().toString().trim(),
                            k_okulno.getText().toString().trim(),
                            k_bolum.getText().toString().trim(),
                            k_kadi.getText().toString().trim(),
                            k_sifre.getText().toString().trim());

                    if(sonuc ==666)
                        Toast.makeText(kayit.this,"Girilen kullanıcı adı zaten mevcut.Başka bir kullanıcı adı deneyin",Toast.LENGTH_LONG).show();
                    else if(sonuc == 6 || sonuc == 66)
                        Toast.makeText(kayit.this,"Veri tabanına eklemede hata oluştu!",Toast.LENGTH_LONG).show();
                    else if(sonuc == 1)
                        Toast.makeText(kayit.this,"Kayıt Başarılı.",Toast.LENGTH_LONG).show();

                }



            }
        });








    }
}
