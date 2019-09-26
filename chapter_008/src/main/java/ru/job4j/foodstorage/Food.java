package ru.job4j.foodstorage;

/**
 * Class Food | Implement FoodStorage [#852]
 * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 24.09.2019
 */
public class Food {
    private String name;
    private String createDate;
    private String expireDate;
    private double price;
    private double discount;

    public Food(String name, String createDate, String expireDate, double price) {
        this.name = name;
        this.expireDate = expireDate;
        this.createDate = createDate;
        this.price = price;
        this.discount = 0;
    }

    public String getCreateDate() {
        return createDate;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public String getName() {
        return name;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}