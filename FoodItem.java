package com.example;

import android.os.Parcel;
import android.os.Parcelable;

public class FoodItem implements Parcelable {
    private int itemId;
    private String name;
    private int price;
    private String description;
    private int calories;
    private int saturatedFat;
    private int fat;
    private int quantity;

    public FoodItem() {
        quantity = 1;
    }

    public FoodItem(int itemId, String name, int price, String description, int calories, int saturatedFat, int fat) {
        this.itemId = itemId;
        this.name = name;
        this.price = price;
        this.description = description;
        this.calories = calories;
        this.saturatedFat = saturatedFat;
        this.fat = fat;
        this.quantity = 1;
    }

    protected FoodItem(Parcel in) {
        itemId = in.readInt();
        name = in.readString();
        price = in.readInt();
        description = in.readString();
        calories = in.readInt();
        saturatedFat = in.readInt();
        fat = in.readInt();
        quantity = in.readInt();
    }

    public static final Creator<FoodItem> CREATOR = new Creator<FoodItem>() {
        @Override
        public FoodItem createFromParcel(Parcel in) {
            return new FoodItem(in);
        }

        @Override
        public FoodItem[] newArray(int size) {
            return new FoodItem[size];
        }
    };

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getSaturatedFat() {
        return saturatedFat;
    }

    public void setSaturatedFat(int saturatedFat) {
        this.saturatedFat = saturatedFat;
    }

    public int getFat() {
        return fat;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(itemId);
        dest.writeString(name);
        dest.writeInt(price);
        dest.writeString(description);
        dest.writeInt(calories);
        dest.writeInt(saturatedFat);
        dest.writeInt(fat);
        dest.writeInt(quantity);
    }
}
