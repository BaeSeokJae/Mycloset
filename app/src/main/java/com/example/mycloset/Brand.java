package com.example.mycloset;

public class Brand {

    String brandText; // 브랜드 이름
    String brandSite;
    String brandSite1;
    int brandImage; // 이미지

    public String getBrandText() {
        return brandText;
    }

    public void setBrandText(String brandText) {
        this.brandText = brandText;
    }

    public int getBrandImage() {
        return brandImage;
    }

    public void setBrandImage(int brandImage) {
        this.brandImage = brandImage;
    }

    public String getBrandSite() {
        return brandSite;
    }

    public void setBrandSite(String brandSite) {
        this.brandSite = brandSite;
    }

    public String getBrandSite1() {
        return brandSite1;
    }

    public void setBrandSite1(String brandSite1) {
        this.brandSite1 = brandSite1;
    }

    public Brand(String brandText, String brandSite, String brandSite1, int brandImage) {
        this.brandText = brandText;
        this.brandSite = brandSite;
        this.brandSite1 = brandSite1;
        this.brandImage = brandImage;

    }
}