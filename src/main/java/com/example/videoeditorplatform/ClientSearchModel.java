package com.example.videoeditorplatform;

public class ClientSearchModel {
    Integer id;
    String NomPrenom;
    String address;
    Integer tel;

    public ClientSearchModel(Integer id, String nomPrenom, String address, Integer tel) {
        this.id = id;
        NomPrenom = nomPrenom;
        this.address = address;
        this.tel = tel;
    }

    public Integer getId() {
        return id;
    }

    public String getNomPrenom() {
        return NomPrenom;
    }

    public String getAddress() {
        return address;
    }

    public Integer getTel() {
        return tel;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNomPrenom(String nomPrenom) {
        NomPrenom = nomPrenom;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setTel(Integer tel) {
        this.tel = tel;
    }
}
