package org.example.core;

public class ContentTextUpMidleCode extends AbstractContent{
    protected String code;
    protected String textMidle;

    public ContentTextUpMidleCode(String textUP, String code, String textMidle) {
        super(textUP);
        this.code = code;
        this.textMidle = textMidle;
    }

    @Override
    public String toString() {
        return "ContentTextUpMidleCode{" +
                "text= " + text +
                "code='" + code + '\'' +
                ", textMidle='" + textMidle + '\'' +
                '}';
    }

    public String getCode() {
        return code;
    }

    public String getTextMidle() {
        return textMidle;
    }
}
