package com.example.staj1.veritabanıClasslar;

import java.io.Serializable;

public class turler implements Serializable {

    private int tur_id;
    private String tur_ad;

    public turler() {
    }

    public turler(int tur_id, String tur_ad) {
        this.tur_id = tur_id;
        this.tur_ad = tur_ad;
    }

    public int getTur_id() {
        return tur_id;
    }

    public void setTur_id(int tur_id) {
        this.tur_id = tur_id;
    }

    public String getTur_ad() {
        return tur_ad;
    }

    public void setTur_ad(String tur_ad) {
        this.tur_ad = tur_ad;
    }

}
