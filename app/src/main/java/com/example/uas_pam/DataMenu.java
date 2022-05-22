package com.example.uas_pam;

public class DataMenu {


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    String Nama, Harga, mUrl, id;

    public DataMenu(){

    }

    public DataMenu(String nama, String harga, String mUrl) {
        this.Nama = nama;
        this.Harga = harga;
        this.mUrl = mUrl;

    }



    public String getNama() {
        return Nama;
    }

    public void setNama(String nama) {
        Nama = nama;
    }

    public String getHarga() {
        return Harga;
    }

    public void setHarga(String harga) {
        Harga = harga;
    }

    public String getmUrl() {
        return mUrl;
    }

    public void setmUrl(String mUrl) {
        this.mUrl = mUrl;
    }
}