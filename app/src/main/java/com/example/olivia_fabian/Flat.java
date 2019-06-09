package com.example.olivia_fabian;

public class Flat {
    private float price;
    private String short_description;
    private int size;
    private int img;
    private boolean like;


    public Flat(float price, String short_description, int size, int img, boolean like) {
        this.price = price;
        this.short_description = short_description;
        this.size = size;
        this.img = img;
        this.like = like;
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
