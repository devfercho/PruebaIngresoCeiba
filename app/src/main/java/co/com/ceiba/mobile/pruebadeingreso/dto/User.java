package co.com.ceiba.mobile.pruebadeingreso.dto;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {

    @PrimaryKey
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "username")
    private String username;

    @ColumnInfo(name = "email")
    private String email;

    @ColumnInfo(name = "streetAdress")
    private String streetAdress;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStreetAdress() {
        return streetAdress;
    }

    public void setStreetAdress(String streetAdress) {
        this.streetAdress = streetAdress;
    }

    public String getSuiteAdress() {
        return suiteAdress;
    }

    public void setSuiteAdress(String suiteAdress) {
        this.suiteAdress = suiteAdress;
    }

    public String getCityAdress() {
        return cityAdress;
    }

    public void setCityAdress(String cityAdress) {
        this.cityAdress = cityAdress;
    }

    public String getZipCodeAdress() {
        return zipCodeAdress;
    }

    public void setZipCodeAdress(String zipCodeAdress) {
        this.zipCodeAdress = zipCodeAdress;
    }

    public String getLat_geo() {
        return lat_geo;
    }

    public void setLat_geo(String lat_geo) {
        this.lat_geo = lat_geo;
    }

    public String getLat_lng() {
        return lat_lng;
    }

    public void setLat_lng(String lat_lng) {
        this.lat_lng = lat_lng;
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

    @ColumnInfo(name = "suiteAdress")
    private String suiteAdress;

    @ColumnInfo(name = "cityAdress")
    private String cityAdress;

    @ColumnInfo(name = "zipCodeAdress")
    private String zipCodeAdress;

    @ColumnInfo(name = "lat_geo")
    private String lat_geo;

    @ColumnInfo(name = "lat_lng")
    private String lat_lng;

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

}