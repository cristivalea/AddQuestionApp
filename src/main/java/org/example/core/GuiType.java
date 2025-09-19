package org.example.core;

public enum GuiType {
    TEXT_UP_COD("text_up_cod_raspunsuri"),
    TEXT_UP("text_up_raspunsuri");

    protected String denumire;

    GuiType(String denumire) {
        this.denumire = denumire;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }
}
