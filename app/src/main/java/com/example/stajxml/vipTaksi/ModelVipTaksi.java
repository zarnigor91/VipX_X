package com.example.stajxml.vipTaksi;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class ModelVipTaksi {
    @SerializedName("car_image")
    private int carImage;
    private String status;
    private int price;
    private String name;
    private String time;
    private int width;


    public ModelVipTaksi(int carImage, String status, String name, int price, String time, int width) {
        this.carImage = carImage;
        this.status = status;

        this.price = price;
        this.name = name;
        this.time = time;
        this.width = width;
    }


    public int getCarImage() {
        return carImage;
    }

    public String getStatus() {
        return status;
    }

    public int getPrice() {

        return price;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public int getWidth() {
        return width;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ModelVipTaksi model = (ModelVipTaksi) o;
        return Objects.equals(name, model.name);
    }
}
