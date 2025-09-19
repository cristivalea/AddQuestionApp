package org.example.core;

public class ContentTextUP extends AbstractContent{
    public ContentTextUP(String textUP) {
        super(textUP);
    }

    @Override
    public String toString() {
        return text + "\n";
    }

}
