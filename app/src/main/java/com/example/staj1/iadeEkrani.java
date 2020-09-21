package com.example.staj1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class iadeEkrani extends AppCompatActivity {

    private EditText iadeNumara;
    private EditText iadeKitapAd;
    private Button Kaydet;
    private VeritabaniYardimcisi vt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iade_ekrani);

        iadeNumara = findViewById(R.id.iadeNumara);
        iadeKitapAd = findViewById(R.id.iadeKitapAd);
        Kaydet = findViewById(R.id.Kaydet);
        vt = new VeritabaniYardimcisi(this);

        Kaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               String gelenMsj = new veritabaniSorgular().iadeEt(vt,iadeNumara.getText().toString().trim(),iadeKitapAd.getText().toString().trim());
                Toast.makeText(iadeEkrani.this,""+gelenMsj,Toast.LENGTH_LONG).show();

            }
        });

    }
}
