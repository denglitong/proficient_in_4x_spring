package com.denglitong.spel;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/11
 */
public class PlaceOfBirth {

    private String city;

    private String country;

    public PlaceOfBirth(String city) {
        this.city = city;
    }

    public PlaceOfBirth(String city, String country) {
        this.city = city;
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
