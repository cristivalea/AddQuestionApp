package org.example.core;

public class ContentTextUpMidleCode extends AbstractContent{
    protected String code;
    protected String textMidle;

    public ContentTextUpMidleCode(String textUP, String code, String textMidle) {
        super(textUP);
        this.code = code;
        this.textMidle = textMidle;
    }
}
