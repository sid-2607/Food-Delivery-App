package com.example;

public class itemPageInfo {
    public String name;
    public String price;
    public String fat, saturated_fat;
    public String calories;
    public itemPageInfo(String itemName, String itemPrice, String itemFat, String saturated_Fat, String calories){
        this.name= itemName;
        this.price= itemPrice;
        this.fat=itemFat;
        this.saturated_fat= saturated_Fat;
        this.calories= calories;
    }
}