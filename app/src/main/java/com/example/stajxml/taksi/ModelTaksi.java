package com.example.stajxml.taksi;
import com.google.gson.annotations.SerializedName;

public class ModelTaksi {
    private String text;
    private int image;
    @SerializedName("image_checked")
    private int imageChecked;
    @SerializedName("is_checked")
    private boolean isChecked = false;

    public ModelTaksi(String text, int image, int image_hecked) {
        this.text = text;
        this.image = image;
        this.imageChecked= image_hecked;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getImageChecked() {
        return imageChecked;
    }

    public void setImageChecked(int imageChecked) {
        this.imageChecked = imageChecked;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setIscChecked(boolean iscChecked) {
        this.isChecked = iscChecked;
    }


}
