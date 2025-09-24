package org.example.core;

public enum GuiType {
    TEXT_UP_COD("TEXT_UP_COD_RESPONSES", 1),
    TEXT_UP("TEXT_UP_RESPONSES", 2),
    TEXT_UP_COD_TEXT_DOWN("TEXT UP COD TEXT DOWN RESPONSES", 3),
    TEXT_UP_IMG_RASPUNSURI("TEXT_UP_IMAGE_RESPONSES", 4),
    TEXT_UP_IMG_TEXT_DOWN_RASPUNSURI("TEXT_UP_IMAGE_TEXT_DOWN_RESPONSES", 5),
    TEXT_UP_CODE_IMG_TEXT_DOWN_RESPONSES("TEXT UP CODE IMAGE TEXT DOWN RESPONSES", 6);

    protected String denumire;
    protected int id;

    GuiType(String denumire, int id) {
        this.denumire = denumire;
        this.id = id;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
