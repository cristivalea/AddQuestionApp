package org.example.core;

public class ContentTextUpCodeImgTextDown extends AbstractContent{
    protected String code;
    protected String imgPath;
    protected String textDown;

    public ContentTextUpCodeImgTextDown(String textUP, String code, String imgPath, String textDown) {
        super(textUP);
        this.code = code;
        this.imgPath = imgPath;
        this.textDown = textDown;
    }

    public String getCode() {
        return code;
    }

    public String getImgPath() {
        return imgPath;
    }

    public String getTextDown() {
        return textDown;
    }
}
