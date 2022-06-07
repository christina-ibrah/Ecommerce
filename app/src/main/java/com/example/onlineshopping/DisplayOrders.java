package com.example.onlineshopping;

public class DisplayOrders {
    String name;
    String quantity;
    String price;

    public DisplayOrders(String name, String quantity, String price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }
public void changeText(String text)
{
    name=text;
}
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
