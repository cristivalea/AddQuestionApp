package org.example.core;

public class ContentTextUpImage extends AbstractContent{
    protected String imagePath;

    public ContentTextUpImage(String textUP, String imagePath) {
        super(textUP);
        this.imagePath = imagePath;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return "ContentTextUpImage{" +
                "text " + text +
                " imagePath='" + imagePath + '\'' +
                '}';
    }
}
