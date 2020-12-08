package com.example.cnnapp;

public class History {

    private String imageURI;
    private String breed;
    private String confidence;

    public History(String imageURI, String breed, String confidence){
        this.imageURI = imageURI;
        this.breed = breed;
        this.confidence = confidence;
    }

    public String getImageURI() {
        return imageURI;
    }

    public String getBreed() {
        return breed;
    }

    public String getConfidence() {
        return confidence;
    }

    public String toString(){
        return breed + confidence;
    }
}
