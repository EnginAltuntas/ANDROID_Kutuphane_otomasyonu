package com.example.staj1.veritabanıClasslar;

import java.io.Serializable;

public class Kullanicilar implements Serializable {

    private int kullanici_id;
    private String k_adi;
    private String k_sifre;

    public Kullanicilar() {
    }

    public Kullanicilar(int kullanici_id, String k_adi, String k_sifre) {
        this.kullanici_id = kullanici_id;
        this.k_adi = k_adi;
        this.k_sifre = k_sifre;
    }

    public int getKullanici_id() {
        return kullanici_id;
    }

    public void setKullanici_id(int kullanici_id) {
        this.kullanici_id = kullanici_id;
    }

    public String getK_adi() {
        return k_adi;
    }

    public void setK_adi(String k_adi) {
        this.k_adi = k_adi;
    }

    public String getK_sifre() {
        return k_sifre;
    }

    public void setK_sifre(String k_sifre) {
        this.k_sifre = k_sifre;
    }
}
