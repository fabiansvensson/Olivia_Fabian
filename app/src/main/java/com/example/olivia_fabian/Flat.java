package com.example.olivia_fabian;

public class Flat {
    private float price;
    private String short_description;
    private int size;
    private int img;
    private boolean like;
    private String img_api;
    private int id;
    private String date;
    private String time;
    private String long_description;
    private boolean appointment;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Flat(float price, String short_description, int size, int img, boolean like, String img_api, int id, String long_description, boolean appointment) {
        this.price = price;
        this.short_description = short_description;
        this.size = size;
        this.img = img;
        this.like = like;
        this.img_api = img_api;
        this.id = id;
        this.date = null;
        this.time = null;
        this.long_description = long_description;
        this.appointment = appointment;

    }


    public String getLong_description() {
        return long_description;
    }

    public void setLong_description(String long_description) {
        this.long_description = long_description;
    }

    public boolean isAppointment() {
        return appointment;
    }

    public void setAppointment(boolean appointment) {
        this.appointment = appointment;
    }

    public int getId() {
        return id;
    }

    public String getImg_api() {
        return img_api;
    }

    public void setImg_api(String img_api) {
        this.img_api = img_api;
    }

    public String getPrice() {
        return (Float.toString(price)) + "€";
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return short_description;
    }

    public void setDescription(String description) {
        this.short_description = description;
    }

    public String getSize() {
        return Integer.toString(size) + "m2";
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public int getImg() {
        return img;
    }

    public boolean getLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
    }
}
