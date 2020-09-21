package com.example.staj1.veritabanıClasslar;

import java.io.Serializable;

public class odunc_bilgi implements Serializable {

    private int odunc_id;
    private int kitap_id;
    private int ogrenci_id;
    private String tarih;

    public odunc_bilgi() {
    }

    public odunc_bilgi(int odunc_id, int kitap_id, int ogrenci_id, String tarih) {
        this.odunc_id = odunc_id;
        this.kitap_id = kitap_id;
        this.ogrenci_id = ogrenci_id;
        this.tarih = tarih;
    }

    public int getOdunc_id() {
        return odunc_id;
    }

    public void setOdunc_id(int odunc_id) {
        this.odunc_id = odunc_id;
    }

    public int getKitap_id() {
        return kitap_id;
    }

    public void setKitap_id(int kitap_id) {
        this.kitap_id = kitap_id;
    }

    public int getOgrenci_id() {
        return ogrenci_id;
    }

    public void setOgrenci_id(int ogrenci_id) {
        this.ogrenci_id = ogrenci_id;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }
}
