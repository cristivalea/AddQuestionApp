package org.example.core;

public enum GuiType {
    TEXT_UP_COD("TEXT UP COD RESPONSES", 1),
    TEXT_UP("TEXT UP RESPONSES", 2),
    TEXT_UP_COD_TEXT_DOWN("TEXT UP COD TEXT DOWN RESPONSES", 3),
    TEXT_UP_IMG_RASPUNSURI("TEXT UP IMAGE RESPONSES", 4),
    TEXT_UP_IMG_TEXT_DOWN_RASPUNSURI("TEXT UP IMAGE TEXT DOWN RESPONSES", 5);

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
