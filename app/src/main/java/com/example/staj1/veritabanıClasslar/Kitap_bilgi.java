package com.example.staj1.veritabanıClasslar;

import java.io.Serializable;

public class Kitap_bilgi implements Serializable {

    private int kitap_id;
    private String kitap_ad;
    private int kitap_durum;
    private int tur_id;
    private String yazar_ad;

    public Kitap_bilgi() {
    }

    public Kitap_bilgi(int kitap_id, String kitap_ad, int kitap_durum, int tur_id, String yazar_ad) {
        this.kitap_id = kitap_id;
        this.kitap_ad = kitap_ad;
        this.kitap_durum = kitap_durum;
        this.tur_id = tur_id;
        this.yazar_ad = yazar_ad;
    }

    public int getKitap_id() {
        return kitap_id;
    }

    public void setKitap_id(int kitap_id) {
        this.kitap_id = kitap_id;
    }

    public String getKitap_ad() {
        return kitap_ad;
    }

    public void setKitap_ad(String kitap_ad) {
        this.kitap_ad = kitap_ad;
    }

    public int getKitap_durum() {
        return kitap_durum;
    }

    public void setKitap_durum(int kitap_durum) {
        this.kitap_durum = kitap_durum;
    }

    public int getTur_id() {
        return tur_id;
    }

    public void setTur_id(int tur_id) {
        this.tur_id = tur_id;
    }

    public String getYazar_ad() {
        return yazar_ad;
    }

    public void setYazar_ad(String yazar_ad) {
        this.yazar_ad = yazar_ad;
    }
}
