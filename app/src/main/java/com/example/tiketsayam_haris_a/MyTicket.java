package com.example.tiketsayam_haris_a;

public class MyTicket {
    public String nama_wisata, lokasi;
    public Integer jumlahTiket;

    public MyTicket() {

    }

    public MyTicket(String nama_wisata, String lokasi, Integer jumlahTiket) {
        this.nama_wisata = nama_wisata;
        this.lokasi = lokasi;
        this.jumlahTiket = jumlahTiket;
    }

    public String getNama_wisata() {
        return nama_wisata;
    }

    public void setNama_wisata(String nama_wisata) {
        this.nama_wisata = nama_wisata;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public Integer getJumlahTiket() {
        return jumlahTiket;
    }

    public void setJumlahTiket(Integer jumlahTiket) {
        this.jumlahTiket = jumlahTiket;
    }
}
