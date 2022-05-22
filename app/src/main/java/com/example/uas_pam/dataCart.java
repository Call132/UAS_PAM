package com.example.uas_pam;

public class dataCart {

    String nama, harga, mUrl;

    public dataCart(){

    }

    public dataCart(String nama, String harga, String mUrl) {
        this.nama = nama;
        this.harga = harga;
        this.mUrl = mUrl;

    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getmUrl() {
        return mUrl;
    }

    public void setmUrl(String mUrl) {
        this.mUrl = mUrl;
    }


}
