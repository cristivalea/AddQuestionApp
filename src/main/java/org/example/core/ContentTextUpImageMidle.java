package org.example.core;

public class ContentTextUpImageMidle extends AbstractContent{
    protected String imagePath;
    protected String textMidle;

    public ContentTextUpImageMidle(String textUP, String imagePath, String textMidle) {
        super(textUP);
        this.imagePath = imagePath;
        this.textMidle = textMidle;
    }

    @Override
    public String toString() {
        return "ContentTextUpImageMidle{" +
                "imagePath='" + imagePath + '\'' +
                ", textMidle='" + textMidle + '\'' +
                '}';
    }
}
