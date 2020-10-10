package co.com.ceiba.mobile.pruebadeingreso.dto;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "User")
public class User implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int idUser;

    @ColumnInfo(name = "name")
    private String name;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @ColumnInfo(name = "username")
    private String userName;

    @ColumnInfo(name = "email")
    private String email;

    @ColumnInfo(name = "streetAdress")
    private String street;

    @ColumnInfo(name = "suiteAdress")
    private String suite;

    @ColumnInfo(name = "cityAdress")
    private String city;

    @ColumnInfo(name = "zipCodeAdress")
    private String zipCode;

    @ColumnInfo(name = "lat_geo")
    private String lat;

    @ColumnInfo(name = "lat_lng")
    private String lng;

    @ColumnInfo(name = "phone")
    private String phone;

    @ColumnInfo(name = "website")
    private String website;

    @ColumnInfo(name = "nameCompany")
    private String nameCompany;

    @ColumnInfo(name = "cathPhraseCompany")
    private String cathPhraseCompany;

    @ColumnInfo(name = "bsCompany")
    private String bsCompany;

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getSuite() {
        return suite;
    }

    public void setSuite(String suite) {
        this.suite = suite;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getNameCompany() {
        return nameCompany;
    }

    public void setNameCompany(String nameCompany) {
        this.nameCompany = nameCompany;
    }

    public String getCathPhraseCompany() {
        return cathPhraseCompany;
    }

    public void setCathPhraseCompany(String cathPhraseCompany) {
        this.cathPhraseCompany = cathPhraseCompany;
    }

    public String getBsCompany() {
        return bsCompany;
    }

    public void setBsCompany(String bsCompany) {
        this.bsCompany = bsCompany;
    }


}