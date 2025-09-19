package org.example.core;

public abstract class AbstractContent {
    protected String text;

    public AbstractContent(String textUP) {
        this.text = textUP;
    }

    @Override
    public String toString() {
        return "AbstractContent{" +
                "textUP='" + this.text + '\'' +
                '}';
    }

    public String getText() {
        return this.text;
    }
}
