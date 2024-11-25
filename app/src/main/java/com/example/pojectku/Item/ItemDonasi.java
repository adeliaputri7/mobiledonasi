package com.example.pojectku.Item;



public class ItemDonasi {
    private String id;
    private String gambar;
    private String judul;
    private String kategori;
    private int target;
    private int terkumpul;
    private String keterangan;
    private String status;

    public ItemDonasi(String id, String judul, String kategori, int target, int terkumpul, String keterangan, String status) {
        this.id = id;
        this.gambar = gambar;
        this.judul = judul;
        this.kategori = kategori;
        this.target = target;
        this.terkumpul = terkumpul;
        this.keterangan = keterangan;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ItemDonasi(String gambar) {
        this.gambar = gambar;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public int getTerkumpul() {
        return terkumpul;
    }

    public void setTerkumpul(int terkumpul) {
        this.terkumpul = terkumpul;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
