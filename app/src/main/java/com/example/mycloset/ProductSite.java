package com.example.mycloset;

public class ProductSite {

    String productSiteName; // 이름
    String productSiteSalePrice; // 세일가격
    String productSitePrice; // 원래 가격
    String productSiteImage; // 이미지
    String productSiteLink; // 세부페이지 링
    String memoName;
    String memoDate;
    String memoContent;

    public String getProductSiteName() {
        return productSiteName;
    }

    public void setProductSiteName(String productSiteName) {
        this.productSiteName = productSiteName;
    }

    public String getProductSiteSalePrice() {
        return productSiteSalePrice;
    }

    public void setProductSiteSalePrice(String productSiteSalePrice) {
        this.productSiteSalePrice = productSiteSalePrice;
    }

    public String getProductSitePrice() {
        return productSitePrice;
    }

    public void setProductSitePrice(String productSitePrice) {
        this.productSitePrice = productSitePrice;
    }

    public String getProductSiteImage() {
        return productSiteImage;
    }

    public void setProductSiteImage(String productSiteImage) {
        this.productSiteImage = productSiteImage;
    }

    public String getProductSiteLink() {
        return productSiteLink;
    }

    public void setProductSiteLink(String productSiteLink) {
        this.productSiteLink = productSiteLink;
    }

    public String getMemoName() {
        return memoName;
    }

    public void setMemoName(String memoName) {
        this.memoName = memoName;
    }

    public String getMemoDate() {
        return memoDate;
    }

    public void setMemoDate(String memoDate) {
        this.memoDate = memoDate;
    }

    public String getMemoContent() {
        return memoContent;
    }

    public void setMemoContent(String memoContent) {
        this.memoContent = memoContent;
    }

    public ProductSite(String productSiteName, String productSiteSalePrice, String productSitePrice, String productSiteImage, String productSiteLink, String memoName, String memoDate, String memoContent) {
        this.productSiteName = productSiteName;
        this.productSiteSalePrice = productSiteSalePrice;
        this.productSitePrice = productSitePrice;
        this.productSiteImage = productSiteImage;
        this.productSiteLink = productSiteLink;
        this.memoName = memoName;
        this.memoDate = memoDate;
        this.memoContent = memoContent;
    }
}