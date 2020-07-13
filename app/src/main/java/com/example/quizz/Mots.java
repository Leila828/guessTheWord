package com.example.quizz;

public class Mots {
    long id;

    String mots;

    public Mots(){}
    public Mots(long id, String mots) {
        this.id = id;
        this.mots = mots;
    }

    public Mots(String mots) {
        this.mots = mots;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMots() {
        return mots;
    }

    public void setMots(String mots) {
        this.mots = mots;
    }
}
