package org.example.core;

public class ContentTextUPCod extends AbstractContent{
    private String cod;

    public ContentTextUPCod(String textUP, String cod) {
        super(textUP);
        this.cod = cod;
    }

    @Override
    public String toString() {
        return text + "\n" + cod + "\n";
    }

    public String getCode() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }


}
