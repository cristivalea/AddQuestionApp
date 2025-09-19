package org.example.core;

public abstract class AbstractContent {
    protected String textUP;

    public AbstractContent(String textUP) {
        this.textUP = textUP;
    }

    @Override
    public String toString() {
        return "AbstractContent{" +
                "textUP='" + textUP + '\'' +
                '}';
    }

    public String getTextUP() {
        return textUP;
    }

    public abstract String getCode();
}
