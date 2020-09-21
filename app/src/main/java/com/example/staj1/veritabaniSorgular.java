package com.example.staj1;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.staj1.veritabanıClasslar.Gorevliler;
import com.example.staj1.veritabanıClasslar.Kitap_bilgi;
import com.example.staj1.veritabanıClasslar.Kullanicilar;
import com.example.staj1.veritabanıClasslar.Ogrenciler;
import com.example.staj1.veritabanıClasslar.odunc_bilgi;
import com.example.staj1.veritabanıClasslar.turler;

import java.util.ArrayList;

public class veritabaniSorgular {

    public int KullaniciKontrol(VeritabaniYardimcisi vt, String kullanici_adi, String sifre) {

        SQLiteDatabase dbx = vt.getWritableDatabase();
        Cursor c = dbx.rawQuery("SELECT * FROM Kullanicilar", null);
     //   Log.e("**** kullanici adi",""+kullanici_adi);
     //   Log.e("**** sifre",""+sifre);

        while (c.moveToNext()) {
          //  Log.e("movetoNextBaşı","Girildi");
            Kullanicilar kullanicilar = new Kullanicilar(c.getInt(c.getColumnIndex("kullanici_id"))
                    , c.getString(c.getColumnIndex("k_adi"))
                    , c.getString(c.getColumnIndex("k_sifre")));
          //  Log.e("-----------------ad",""+kullanicilar.getK_adi());
         //   Log.e("----------------sifre",""+kullanicilar.getK_sifre());
            if (kullanicilar.getK_adi().equals(kullanici_adi) && kullanicilar.getK_sifre().equals(sifre) ) {
              //  Log.e("DOĞRULAMA*****","DOĞRULAMA SAĞLANDI");
                int kullanici_id = kullanicilar.getKullanici_id();
              //  Log.e("----------------------",""+kullanici_id);
                Cursor c1 = dbx.rawQuery("SELECT * FROM Gorevliler", null);
                while (c1.moveToNext()) {
                    Gorevliler gorevliler = new Gorevliler(c1.getInt(c1.getColumnIndex("gorevli_id"))
                            , c1.getString(c1.getColumnIndex("g_ad"))
                            , c1.getString(c1.getColumnIndex("g_soyad"))
                            , c1.getInt(c1.getColumnIndex("kullanici_id")));
                    if (gorevliler.getKullanici_id() == kullanici_id) {
                       // c.close();
                        dbx.close();
                        return 1; // bu kullanıcı görevli sayfasına yönelndirilecek
                    }

                }
                    Cursor c2 = dbx.rawQuery("SELECT * FROM Ogrenciler", null);
                    while (c2.moveToNext()) {
                        Ogrenciler ogrenciler = new Ogrenciler(c2.getInt(c2.getColumnIndex("ogrenci_id"))
                                , c2.getString(c2.getColumnIndex("ogr_ad"))
                                , c2.getString(c2.getColumnIndex("ogr_soyad"))
                                , c2.getString(c2.getColumnIndex("ogr_numara"))
                                , c2.getString(c2.getColumnIndex("ogr_bolum"))
                                , c2.getInt(c2.getColumnIndex("kullanici_id")));
                        if(ogrenciler.getKullanici_id()==kullanici_id){
                           // c.close();
                            dbx.close();
                            return 0; // bu kullanıcı öğrenciler sayfasına yönlendirilecek
                        }
                    }

            }
        }
     //   c.close();
        dbx.close();
        return 2; // Kullanıcı adı veya şifre yanlış

        // Kullanıcılar tablosuna gidip kullanıcı_adı ve şifre kontrolü yapılacak.
        // Eşleşme olursa öğrenciler ve görevliler tablosuna gidip kullanıcı id si eşleşen bulunacak
        // Eşleşmeye göre öğrenci veya görevli ekranına gönderme yapılacak.
    }

    public int Kayit(VeritabaniYardimcisi vt, String k_isim, String k_soyisim, String k_okulno, String k_bolum, String k_kadi, String k_sifre)
    {
        SQLiteDatabase sdb = vt.getWritableDatabase();
        Cursor k = sdb.rawQuery("SELECT * FROM Kullanicilar",null);
        while (k.moveToNext())
        {
            Kullanicilar kullanicilar= new Kullanicilar(k.getInt(k.getColumnIndex("kullanici_id"))
                    ,k.getString(k.getColumnIndex("k_adi"))
                    ,k.getString(k.getColumnIndex("k_sifre")));

            if(kullanicilar.getK_adi().equals(k_kadi))
            {
                return 666;
            }

        }



        SQLiteDatabase dbb = vt.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("k_adi",k_kadi);
        values.put("k_sifre",k_sifre);

        long sonuc = dbb.insertOrThrow("Kullanicilar",null,values);
        dbb.close();
        if(sonuc == -1) return 6;
        else{
            SQLiteDatabase dbx = vt.getWritableDatabase();
            Cursor c = dbx.rawQuery("SELECT * FROM Kullanicilar",null);

            while (c.moveToNext())
            {
                Kullanicilar kullanicilar= new Kullanicilar(c.getInt(c.getColumnIndex("kullanici_id"))
                        ,c.getString(c.getColumnIndex("k_adi"))
                        ,c.getString(c.getColumnIndex("k_sifre")));

                if(kullanicilar.getK_adi().equals(k_kadi))
                {

                    SQLiteDatabase sql = vt.getWritableDatabase();
                    ContentValues deger = new ContentValues();
                    deger.put("ogr_ad",k_isim);
                    deger.put("ogr_soyad",k_soyisim);
                    deger.put("ogr_numara",k_okulno);
                    deger.put("ogr_bolum",k_bolum);
                    deger.put("kullanici_id",kullanicilar.getKullanici_id());

                    long s = sql.insertOrThrow("Ogrenciler",null,deger);
                    sql.close();
                    if(s == -1) return 66;

                }
            }
        }
        return 1;
    }


    public ArrayList<Kitap_bilgi> tumKitaplar(VeritabaniYardimcisi vt, int ti)
    {
        ArrayList<Kitap_bilgi> listele = new ArrayList<>();
        SQLiteDatabase dbx = vt.getWritableDatabase();


        Cursor c = dbx.rawQuery("SELECT * FROM Kitap_bilgi WHERE tur_id="+ti,null);

     //   Log.e("*-*ti",""+ti);
        int sayac =0;

        while (c.moveToNext())
        {
            Kitap_bilgi kitap_bilgi= new Kitap_bilgi(c.getInt(c.getColumnIndex("kitap_id"))
                    ,c.getString(c.getColumnIndex("kitap_ad"))
                    ,c.getInt(c.getColumnIndex("kitap_durum"))
                    ,c.getInt(c.getColumnIndex("tur_id"))
                    ,c.getString(c.getColumnIndex("yazar_ad")));
 //           if(kitap_bilgi.getTur_id()==ti)
    //        {

                Log.e("?turid",""+ti);
                Log.e("?turad",""+kitap_bilgi.getKitap_ad());
                Log.e("?durum",""+kitap_bilgi.getKitap_durum());
                Log.e("?yazar",""+kitap_bilgi.getYazar_ad());
                listele.add(kitap_bilgi);
                sayac++;
          //  }


        }
        c.close();
        dbx.close();
        Log.e("sayac:",""+sayac);
        return listele;

    }

    public ArrayList<turler> tur(VeritabaniYardimcisi vt)
    {
        ArrayList<turler> listele = new ArrayList<>();
        SQLiteDatabase dbx = vt.getWritableDatabase();

        Cursor c = dbx.rawQuery("SELECT * FROM turler",null);

        while (c.moveToNext())
        {
            turler turler = new turler(c.getInt(c.getColumnIndex("tur_id"))
                    ,c.getString(c.getColumnIndex("tur_ad")));
            listele.add(turler);
        }
        c.close();
        dbx.close();


        return listele;
    }

    public ArrayList<Kitap_bilgi> kitapAra(VeritabaniYardimcisi vt,String arananKelime)
    {
        ArrayList<Kitap_bilgi> listele = new ArrayList<>();
        SQLiteDatabase dbx = vt.getWritableDatabase();

        Cursor c = dbx.rawQuery("SELECT * FROM Kitap_bilgi WHERE kitap_ad like'%"+arananKelime+"%'",null);

        while (c.moveToNext())
        {
            Kitap_bilgi kb = new Kitap_bilgi(c.getInt(c.getColumnIndex("kitap_id"))
                    ,c.getString(c.getColumnIndex("kitap_ad"))
                    ,c.getInt(c.getColumnIndex("kitap_durum"))
                    ,c.getInt(c.getColumnIndex("tur_id"))
                    ,c.getString(c.getColumnIndex("yazar_ad")));

            listele.add(kb);
        }

        return listele;
    }

    public String oduncEkle(VeritabaniYardimcisi vt,String numara,String kitapadi,String tarih)
    {
        int kId = 0;
        int oId=0;
        SQLiteDatabase dbx = vt.getWritableDatabase();
        int kontrol_ogrenci=0;
        int kontrol_kitap=0;

        Cursor c2 = dbx.rawQuery("SELECT * FROM Ogrenciler",null);

        while(c2.moveToNext())
        {
            Ogrenciler ogrenciler = new Ogrenciler(c2.getInt(c2.getColumnIndex("ogrenci_id"))
                    , c2.getString(c2.getColumnIndex("ogr_ad"))
                    , c2.getString(c2.getColumnIndex("ogr_soyad"))
                    , c2.getString(c2.getColumnIndex("ogr_numara"))
                    , c2.getString(c2.getColumnIndex("ogr_bolum"))
                    , c2.getInt(c2.getColumnIndex("kullanici_id")));

            if(ogrenciler.getOgr_numara().equals(numara))
            {
                oId=ogrenciler.getOgrenci_id();
                kontrol_ogrenci++;
            }

        }
        c2.close();

        Cursor c = dbx.rawQuery("SELECT * FROM Kitap_bilgi",null);

        while(c.moveToNext())
        {
            Kitap_bilgi kb = new Kitap_bilgi(c.getInt(c.getColumnIndex("kitap_id"))
                    ,c.getString(c.getColumnIndex("kitap_ad"))
                    ,c.getInt(c.getColumnIndex("kitap_durum"))
                    ,c.getInt(c.getColumnIndex("tur_id"))
                    ,c.getString(c.getColumnIndex("yazar_ad")));

            if(kb.getKitap_ad().equals(kitapadi))
            {
                kId=kb.getKitap_id();
                kontrol_kitap++;
            }
        }
        c.close();
        if(kontrol_ogrenci==1 && kontrol_kitap==1)
        {
            SQLiteDatabase dbb = vt.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("kitap_id",kId);
            values.put("ogrenci_id",oId);
            values.put("tarih",tarih);

            long sonuc = dbb.insertOrThrow("odunc_bilgi",null,values);
            dbb.close();
            //oduncGoruntule(vt);

            SQLiteDatabase dba = vt.getWritableDatabase();
            ContentValues values1 = new ContentValues();
            values1.put("kitap_durum",1);
            dba.update("Kitap_bilgi",values1,"kitap_id=?",new String[]{String.valueOf(kId)});
            dba.close();

            if(sonuc!=-1)return "Ödünç işlemi başarılı";
            else return "Veri tabanına ekleme yapılamadı";

        }
        else if (kontrol_ogrenci==1 && kontrol_kitap==0)
        {
            return "Girilen kitap bilgisine dair kayıt bulunmuyor!";
        }
        else if(kontrol_kitap==1 && kontrol_ogrenci==0)
        {
            return "Girilen numaraya ait öğrenci bulunmamaktadır!";
        }
        else return "Kitap adı ve öğrenci numarası yanlış!!!";

    }

    public  ArrayList<String> oduncGoruntule(VeritabaniYardimcisi vt)
    {
        ArrayList<odunc_bilgi> ob = new ArrayList<>();
        ArrayList<String> odunc_ = new ArrayList<>();
        SQLiteDatabase dbx = vt.getWritableDatabase();

        Cursor c = dbx.rawQuery("SELECT * FROM odunc_bilgi",null);

        while (c.moveToNext())
        {
            odunc_bilgi odunc_bilgi = new odunc_bilgi(c.getInt(c.getColumnIndex("odunc_id"))
                    ,c.getInt(c.getColumnIndex("kitap_id"))
                    ,c.getInt(c.getColumnIndex("ogrenci_id"))
                    ,c.getString(c.getColumnIndex("tarih")));

            Cursor c2 = dbx.rawQuery("SELECT ogr_ad,ogr_soyad FROM Ogrenciler where ogrenci_id="+odunc_bilgi.getOgrenci_id(),null);
            while(c2.moveToNext())
            {
                odunc_.add(c2.getString(c2.getColumnIndex("ogr_ad"))+" "+c2.getString(c2.getColumnIndex("ogr_soyad")));
            }
            c2.close();
            Cursor c1 = dbx.rawQuery("SELECT kitap_ad FROM Kitap_bilgi where kitap_id="+odunc_bilgi.getKitap_id(),null);
            while(c1.moveToNext())
            {
                odunc_.add(c1.getString(c1.getColumnIndex("kitap_ad")));
            }
            c1.close();
            odunc_.add(odunc_bilgi.getTarih());

        }
        dbx.close();
        return odunc_;
       // return ob;

    }

    public String iadeEt(VeritabaniYardimcisi vt, String numara,String kitapAdi)
    {
        // bize oğrenci_id ve kitap_id lazım.
        SQLiteDatabase dbx = vt.getWritableDatabase();
        int oId=0,kId=0;
        int ogrenci_kontrol=0,kitap_kontrol=0;

        Cursor c2 = dbx.rawQuery("SELECT * FROM Ogrenciler WHERE ogr_numara="+numara,null);
        while (c2.moveToNext())
        {
            Ogrenciler ogrenciler = new Ogrenciler(c2.getInt(c2.getColumnIndex("ogrenci_id"))
                    , c2.getString(c2.getColumnIndex("ogr_ad"))
                    , c2.getString(c2.getColumnIndex("ogr_soyad"))
                    , c2.getString(c2.getColumnIndex("ogr_numara"))
                    , c2.getString(c2.getColumnIndex("ogr_bolum"))
                    , c2.getInt(c2.getColumnIndex("kullanici_id")));
            oId=ogrenciler.getOgrenci_id();
            ogrenci_kontrol++;
        }

        Cursor c = dbx.rawQuery("SELECT * FROM Kitap_bilgi",null);

        while (c.moveToNext())
        {
            Kitap_bilgi kb = new Kitap_bilgi(c.getInt(c.getColumnIndex("kitap_id"))
                    ,c.getString(c.getColumnIndex("kitap_ad"))
                    ,c.getInt(c.getColumnIndex("kitap_durum"))
                    ,c.getInt(c.getColumnIndex("tur_id"))
                    ,c.getString(c.getColumnIndex("yazar_ad")));
            Log.e("kitap ismi",""+kitapAdi);
            Log.e("kitap ismi get",""+kb.getKitap_ad());
            if(kb.getKitap_ad().equals(kitapAdi))
            {
                Log.e("kitap ismi iç",""+kitapAdi);
                Log.e("kitap ismi içget",""+kb.getKitap_ad());
                kId=kb.getKitap_id();
                kitap_kontrol++;
            }

        }
        if(kitap_kontrol!=0 && ogrenci_kontrol!=0)
        {
            dbx.delete("odunc_bilgi","ogrenci_id=? and kitap_id=?",new String[]{String.valueOf(oId),String.valueOf(kId)});
            dbx.close();

            SQLiteDatabase dba = vt.getWritableDatabase();
            ContentValues values1 = new ContentValues();
            values1.put("kitap_durum",0);
            dba.update("Kitap_bilgi",values1,"kitap_id=?",new String[]{String.valueOf(kId)});

            dba.close();

            return "İade işlemi başarılı...";
        }
        else if(kitap_kontrol==0)
        {
            return "Kitap ismi sistemde kayıtlı değil!";
        }
        else if(ogrenci_kontrol==0)
        {
            return "Öğrenci numarası sistemde kayıtlı değil!";
        }
        return "Öğrenci numarası ve kitap ismi yanlış!!!";

    }


}
