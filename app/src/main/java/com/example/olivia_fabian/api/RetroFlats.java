package com.example.olivia_fabian.api;

public class RetroFlats {
    private int id;
    private String shortdescription;
    private String longdescription;
    private long price;
    private String province;
    private String city;
    private String image;
    private int meters;


    public RetroFlats(int id, String shortdescription, String longdescription, long price, String province, String city, String image, int meters) {
        this.id = id;
        this.shortdescription = shortdescription;
        this.longdescription = longdescription;
        this.price = price;
        this.province = province;
        this.city = city;
        this.image = image;
        this.meters = meters;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShortdescription() {
        return shortdescription;
    }

    public void setShortdescription(String shortdescription) {
        this.shortdescription = shortdescription;
    }

    public String getLongdescription() {
        return longdescription;
    }

    public void setLongdescription(String longdescription) {
        this.longdescription = longdescription;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getMeters() {
        return meters;
    }

    public void setMeters(int meters) {
        this.meters = meters;
    }
}

