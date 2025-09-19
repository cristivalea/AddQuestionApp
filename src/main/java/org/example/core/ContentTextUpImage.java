package org.example.core;

public class ContentTextUpImage extends AbstractContent{
    protected String imagePath;

    public ContentTextUpImage(String textUP, String imagePath) {
        super(textUP);
        this.imagePath = imagePath;
    }
}
