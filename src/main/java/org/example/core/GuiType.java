package org.example.core;

public enum GuiType {
    TEXT_UP_COD("text_up_cod_raspunsuri"),
    TEXT_UP("text_up_raspunsuri"),
    TEXT_UP_COD_TEXT_DOWN("text_up_cod_text_down"),
    TEXT_UP_RASPUNSURI("text_up_raspunsuri"),
    TEXT_UP_IMG_RASPUNSURI("text_up_img_raspunsuri"),
    TEXT_UP_IMG_TEXT_DOWN_RASPUNSURI("text_up_img_text_down_raspunsuri");

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
