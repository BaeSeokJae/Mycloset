package com.example.mycloset;

public class Product {

    String productName; // 이름
    String productSalePrice; // 세일가격
    String productPrice; // 원래 가
    String productImage; // 이미지
    String productLink; // 세부페이지 링
    String memoName;
    String memoData;
    String memoContent;
    private boolean isSelected;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductSalePrice() {
        return productSalePrice;
    }

    public void setProductSalePrice(String productSalePrice) {
        this.productSalePrice = productSalePrice;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductLink() {
        return productLink;
    }

    public void setProductLink(String productLink) {
        this.productLink = productLink;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getMemoName() {
        return memoName;
    }

    public void setMemoName(String memoName) {
        this.memoName = memoName;
    }

    public String getMemoData() {
        return memoData;
    }

    public void setMemoData(String memoData) {
        this.memoData = memoData;
    }

    public String getMemoContent() {
        return memoContent;
    }

    public void setMemoContent(String memoContent) {
        this.memoContent = memoContent;
    }

    public Product() {
        this.productName = productName;
        this.productSalePrice = productSalePrice;
        this.productPrice = productPrice;
        this.productImage = productImage;
        this.productLink = productLink;
        this.memoName = memoName;
        this.memoData = memoData;
        this.memoContent = memoContent;
        this.isSelected = isSelected;
    }
}