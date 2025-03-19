package com.example;

public class recyclerview_list {
    private Integer image;

    public String name;
    public String price;
    public String fat, saturated_fat;
    public String calories;

    public recyclerview_list(Integer image,String itemName, String itemPrice, String itemFat, String saturated_Fat, String calories) {
        this.image = image;
        this.name= itemName;
        this.price= itemPrice;
        this.fat=itemFat;
        this.saturated_fat= saturated_Fat;
        this.calories= calories;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }
    public String getitemName() {
        return name;
    }

    public void setitemName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getFat() {
        return fat;
    }

    public void setFat(String fat) {
        this.fat = fat;
    }

    public String getSaturated_fat() {
        return saturated_fat;
    }

    public void setSaturated_fat(String saturated_fat) {
        this.saturated_fat = saturated_fat;
    }




    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }
}
