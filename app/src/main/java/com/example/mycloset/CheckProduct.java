package com.example.mycloset;

public class CheckProduct {

    String checkProductName; // 이름
    String checkProductSalePrice; // 세일가격
    String checkProductPrice; // 원래 가
    String checkProductImage; // 이미지
    String checkProductLink; // 세부페이지 링
    private boolean isSelected;

    public String getcheckProductName() {
        return checkProductName;
    }

    public void setcheckProductName(String checkProductName) {
        this.checkProductName = checkProductName;
    }

    public String getcheckProductSalePrice() {
        return checkProductSalePrice;
    }

    public void setcheckProductSalePrice(String checkProductSalePrice) {
        this.checkProductSalePrice = checkProductSalePrice;
    }

    public String getcheckProductPrice() {
        return checkProductPrice;
    }

    public void setcheckProductPrice(String checkProductPrice) {
        this.checkProductPrice = checkProductPrice;
    }

    public String getcheckProductImage() {
        return checkProductImage;
    }

    public void setcheckProductImage(String checkProductImage) {
        this.checkProductImage = checkProductImage;
    }

    public String getcheckProductLink() {
        return checkProductLink;
    }

    public void setcheckProductLink(String checkProductLink) {
        this.checkProductLink = checkProductLink;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public CheckProduct(String productName, boolean isSelected) {
        this.checkProductName = checkProductName;
        this.checkProductSalePrice = checkProductSalePrice;
        this.checkProductPrice = checkProductPrice;
        this.checkProductImage = checkProductImage;
        this.checkProductLink = checkProductLink;
        this.isSelected = isSelected();
    }
}