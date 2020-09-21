package com.example.staj1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;


import com.example.staj1.veritabanıClasslar.Ogrenciler;

import java.util.ArrayList;

public class o_liste extends AppCompatActivity {

    private GridView ao_liste;
    private VeritabaniYardimcisi vt;
    private ArrayList<String> liste = new ArrayList<>();
    private ArrayList<Integer> liste1 = new ArrayList<>();
    private ArrayAdapter<String> veriAdaptoru;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_o_liste);

        ao_liste = findViewById(R.id.ao_liste);
        vt = new VeritabaniYardimcisi(this);
        SQLiteDatabase db = vt.getWritableDatabase();

        SQLiteDatabase sdb = vt.getWritableDatabase();
        Cursor k = sdb.rawQuery("SELECT * FROM Ogrenciler",null);

        while (k.moveToNext())
        {
            Ogrenciler ogrenciler = new Ogrenciler(k.getInt(k.getColumnIndex("ogrenci_id"))
                    ,k.getString(k.getColumnIndex("ogr_ad"))
                    ,k.getString(k.getColumnIndex("ogr_soyad"))
                    ,k.getString(k.getColumnIndex("ogr_numara"))
                    ,k.getString(k.getColumnIndex("ogr_bolum"))
                    ,k.getInt(k.getColumnIndex("kullanici_id")));

            liste1.add(0);
            liste1.add(0);
            liste1.add(ogrenciler.getOgrenci_id());
            liste1.add(ogrenciler.getKullanici_id());

            liste.add(ogrenciler.getOgr_ad());
            liste.add(ogrenciler.getOgr_soyad());
            //liste.add(ogrenciler.getOgr_bolum());
            liste.add(ogrenciler.getOgr_numara());
            liste.add("kaydı sil");

        }
        sdb.close();

        veriAdaptoru = new ArrayAdapter<>(o_liste.this, android.R.layout.simple_list_item_1, android.R.id.text1, liste);
        ao_liste.setAdapter(veriAdaptoru);

        ao_liste.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if((i+1)%4==0)
                {

                    AlertDialog.Builder builder = new AlertDialog.Builder(o_liste.this);
                    builder.setTitle("Öğrenci sil")
                            .setCancelable(false)
                            .setMessage(""+liste.get(i-3)+" "+liste.get(i-2)+" isimli öğrencinin kaydını silmek istediğinize emin misiniz? Bu işlem geri alınamaz!")
                            .setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int a) {

                                    SQLiteDatabase db = vt.getWritableDatabase();

                                    db.delete("Ogrenciler","ogr_numara=?",new String[]{liste.get(i-1)});
                                    db.delete("Kullanicilar","kullanici_id=?",new String[]{String.valueOf(liste1.get(i))});
                                    db.delete("odunc_bilgi","ogrenci_id=?",new String[]{String.valueOf(liste1.get(i-1))});
                                    db.close();

                                    //-------------------------------------------------

                                    Toast.makeText(o_liste.this,""+liste.get(i-3)+" "+liste.get(i-2)+" isimli öğrencinin kayıtları silindi.",Toast.LENGTH_LONG).show();
                                    liste.remove(i-3);liste1.remove(i-3);
                                    liste.remove(i-2);liste1.remove(i-2);
                                    liste.remove(i-1);liste1.remove(i-1);
                                    liste.remove(i);liste1.remove(i);

                                    finish();
                                    startActivity(getIntent());
                                    Toast.makeText(o_liste.this,"Kayıt silme başarılı.",Toast.LENGTH_LONG).show();
                                }
                            })
                            .setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            });


                    AlertDialog alert = builder.create();
                    alert.setOnShowListener(arg0 -> {
                        alert.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.colorPrimary));
                        alert.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.red));
                    });
                    alert.show();


                }

            }
        });
    }


}
