package com.example.mobilbeadando;

public class Note {
    private String title;
    private String description;
    private int price;

    public Note() {
        //empty constructor needed
    }

    public Note(String title, String description, int price) {
        this.title = title;
        this.description = description;
        this.price = price;
    }


    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }
}