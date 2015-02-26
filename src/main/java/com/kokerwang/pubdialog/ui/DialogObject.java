package com.kokerwang.pubdialog.ui;


/**
 * Dialog item对象
 * <p/>
 * Created by KokerWang on 15/2/26.
 */
public class DialogObject {

    /**
     * 显示名称
     */
    private String name;
    /**
     * 图片资源id
     */
    private int imgSrc;
    /**
     * 字体颜色资源id
     */
    private int textColor;
    /**
     * 背景资源id
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
