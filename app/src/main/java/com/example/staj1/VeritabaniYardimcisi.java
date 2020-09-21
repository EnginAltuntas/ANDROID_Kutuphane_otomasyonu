package com.example.staj1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class VeritabaniYardimcisi extends SQLiteOpenHelper {

    public VeritabaniYardimcisi(@Nullable Context context) {
        super(context, "staj1_kutuphane.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE IF NOT EXISTS Gorevliler (gorevli_id INTEGER PRIMARY KEY AUTOINCREMENT, g_ad TEXT, g_soyad TEXT, kullanici_id INTEGER);");

        db.execSQL("CREATE TABLE IF NOT EXISTS Kitap_bilgi (kitap_id INTEGER PRIMARY KEY AUTOINCREMENT,kitap_ad TEXT,kitap_durum INTEGER,tur_id INTEGER,yazar_ad TEXT);");

        db.execSQL("CREATE TABLE IF NOT EXISTS Kullanicilar (kullanici_id INTEGER PRIMARY KEY AUTOINCREMENT, k_adi TEXT, k_sifre TEXT);");

        db.execSQL("CREATE TABLE IF NOT EXISTS Ogrenciler (ogrenci_id INTEGER PRIMARY KEY AUTOINCREMENT, ogr_ad TEXT, ogr_soyad TEXT, ogr_numara TEXT, ogr_bolum TEXT, kullanici_id INTEGER);");

        db.execSQL("CREATE TABLE IF NOT EXISTS odunc_bilgi (odunc_id INTEGER PRIMARY KEY AUTOINCREMENT, kitap_id INTEGER, ogrenci_id INTEGER, tarih TEXT);");

        db.execSQL("CREATE TABLE IF NOT EXISTS turler (tur_id INTEGER PRIMARY KEY AUTOINCREMENT, tur_ad TEXT);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {


        db.execSQL("DROP TABLE İF EXISTS Gorevliler");
        db.execSQL("DROP TABLE İF EXISTS Kitap_bilgi");
        db.execSQL("DROP TABLE İF EXISTS Kullanicilar");
        db.execSQL("DROP TABLE İF EXISTS Ogrenciler");
        db.execSQL("DROP TABLE İF EXISTS odunc_bilgi");
        db.execSQL("DROP TABLE İF EXISTS turler");
        onCreate(db);


    }
}
