package com.kokerwang.pubdialog;


/**
 * Dialog item
 * <p/>
 * Created by KokerWang on 15/2/26.
 */
public class DialogObject {

    /**
     * Name
     */
    private String name;
    /**
     * Img resources id
     */
    private int imgSrc;
    /**
     * Font color resources id
     */
    private int textColor;
    /**
     * Background resources id
     */
    private int bgId;

    public DialogObject() {
    }

    public DialogObject(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getImgSrc() {
        return imgSrc;
    }

    public int getTextColor() {
        return textColor;
    }

    public int getBgId() {
        return bgId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImgSrc(int imgSrc) {
        this.imgSrc = imgSrc;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public void setBgId(int bgId) {
        this.bgId = bgId;
    }

}
