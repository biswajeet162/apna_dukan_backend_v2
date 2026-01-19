package com.apna_dukan_backend.catalog.product.model.dto;

public class PricingDetailsDto {
    private double sellingPrice;
    private double mrp;
    private int discountPercent;
    private String currency;

    public PricingDetailsDto() {
    }

    public PricingDetailsDto(double sellingPrice, double mrp, int discountPercent, String currency) {
        this.sellingPrice = sellingPrice;
        this.mrp = mrp;
        this.discountPercent = discountPercent;
        this.currency = currency;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public double getMrp() {
        return mrp;
    }

    public void setMrp(double mrp) {
        this.mrp = mrp;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}





