package com.example.olivia_fabian;

public class Flat {
    private float price;
    private String description;
    private int size;
    private int img;
    private boolean like;

    public Flat(float price, String description, int size, int img, boolean like) {
        this.price = price;
        this.description = description;
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
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSize() {
        return Integer.toString(size) + "m2";
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img_path) {
        this.img = img_path;
    }

    public boolean getLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
    }
}
