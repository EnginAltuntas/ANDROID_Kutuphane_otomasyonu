package com.example.staj1.veritabanıClasslar;

import java.io.Serializable;

public class Ogrenciler implements Serializable {

    private int ogrenci_id;
    private String ogr_ad;
    private String ogr_soyad;
    private String ogr_numara;
    private String ogr_bolum;
    private int kullanici_id;

    public Ogrenciler() {
    }

    public Ogrenciler(int ogrenci_id, String ogr_ad, String ogr_soyad, String ogr_numara, String ogr_bolum, int kullanici_id) {
        this.ogrenci_id = ogrenci_id;
        this.ogr_ad = ogr_ad;
        this.ogr_soyad = ogr_soyad;
        this.ogr_numara = ogr_numara;
        this.ogr_bolum = ogr_bolum;
        this.kullanici_id = kullanici_id;
    }

    public int getOgrenci_id() {
        return ogrenci_id;
    }

    public void setOgrenci_id(int ogrenci_id) {
        this.ogrenci_id = ogrenci_id;
    }

    public String getOgr_ad() {
        return ogr_ad;
    }

    public void setOgr_ad(String ogr_ad) {
        this.ogr_ad = ogr_ad;
    }

    public String getOgr_soyad() {
        return ogr_soyad;
    }

    public void setOgr_soyad(String ogr_soyad) {
        this.ogr_soyad = ogr_soyad;
    }

    public String getOgr_numara() {
        return ogr_numara;
    }

    public void setOgr_numara(String ogr_numara) {
        this.ogr_numara = ogr_numara;
    }

    public String getOgr_bolum() {
        return ogr_bolum;
    }

    public void setOgr_bolum(String ogr_bolum) {
        this.ogr_bolum = ogr_bolum;
    }

    public int getKullanici_id() {
        return kullanici_id;
    }

    public void setKullanici_id(int kullanici_id) {
        this.kullanici_id = kullanici_id;
    }
}
