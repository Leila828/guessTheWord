package com.example.quizz;

public class Score {
    long idS;

    String score;
    long position;

    public Score(long idS, String score, long position) {
        this.idS = idS;
        this.score = score;
        this.position = position;
    }

    public long getPosition() {
        return position;
    }

    public void setPosition(long position) {
        this.position = position;
    }

    public Score(long position) {
        this.position = position;
    }

    public Score(){}

    public Score(String score) {
        this.score = score;
    }

    public long getIdS() {
        return idS;
    }

    public void setIdS(long idS) {
        this.idS = idS;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
