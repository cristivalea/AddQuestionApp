package org.example.core;

public enum Dificulty {

    SOFTQUESTION("softquestion"),
    MIDLELEVELQUESTION("middlelevelquestion"),
    HARDQUESTION("hardquestion");

    protected String denumire;

    Dificulty(String denumire) {
        this.denumire = denumire;
    }

    public String getDenumire() {
        return this.denumire;
    }
}
