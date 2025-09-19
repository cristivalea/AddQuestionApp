package org.example.core;

public abstract class AbstractResponse {
    protected int idRaspuns;
    protected String txt;

    public AbstractResponse(int idRaspuns, String txt) {
        this.idRaspuns = idRaspuns;
        this.txt = txt;
    }

    private String transformToString(){
        return String.valueOf((char)(this.idRaspuns + 'a' - 1));
    }

    @Override
    public String toString() {
        return transformToString()  + ".) " + txt + "\n";
    }

    public int getIdRaspuns() {
        return idRaspuns;
    }

    public String getTxt() {
        return txt;
    }
}
