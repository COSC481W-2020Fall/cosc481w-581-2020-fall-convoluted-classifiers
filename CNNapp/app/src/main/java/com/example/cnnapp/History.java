package com.example.cnnapp;

public class History {

    private String breed;
    private String confidence;
    private byte[] image;

    public History(byte[] image, String breed, String confidence){
        this.image = image;
        this.breed = breed;
        this.confidence = confidence;
    }

    public byte[] getImage() {
        return image;
    }

    public String getBreed() {
        return breed;
    }

    public String getConfidence() {
        return confidence;
    }
}
