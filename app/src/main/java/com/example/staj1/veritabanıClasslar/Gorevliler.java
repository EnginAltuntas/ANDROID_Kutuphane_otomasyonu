package com.example.staj1.veritabanıClasslar;

import java.io.Serializable;

public class Gorevliler implements Serializable {

    private int gorevli_id;
    private String g_ad;
    private String g_soyad;
    private int kullanici_id;

    public Gorevliler() {
    }

    public Gorevliler(int gorevli_id, String g_ad, String g_soyad, int kullanici_id) {
        this.gorevli_id = gorevli_id;
        this.g_ad = g_ad;
        this.g_soyad = g_soyad;
        this.kullanici_id = kullanici_id;
    }

    public int getGorevli_id() {
        return gorevli_id;
    }

    public void setGorevli_id(int gorevli_id) {
        this.gorevli_id = gorevli_id;
    }

    public String getG_ad() {
        return g_ad;
    }

    public void setG_ad(String g_ad) {
        this.g_ad = g_ad;
    }

    public String getG_soyad() {
        return g_soyad;
    }

    public void setG_soyad(String g_soyad) {
        this.g_soyad = g_soyad;
    }

    public int getKullanici_id() {
        return kullanici_id;
    }

    public void setKullanici_id(int kullanici_id) {
        this.kullanici_id = kullanici_id;
    }
}
