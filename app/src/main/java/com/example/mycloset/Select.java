package com.example.mycloset;

public class Select {

    String selectName; // 이름
    String selectSalePrice; // 세일가격
    String selectPrice; // 원래 가격
    String selectImage; // 이미지
    String selectLink; // 세부페이지 링크
    String memoName; // 메모제목
    String memoData; // 날
    String memoContent;
    private boolean isSelected;

    public String getSelectName() {
        return selectName;
    }

    public void setSelectName(String selectName) {
        this.selectName = selectName;
    }

    public String getSelectSalePrice() {
        return selectSalePrice;
    }

    public void setSelectSalePrice(String selectSalePrice) {
        this.selectSalePrice = selectSalePrice;
    }

    public String getSelectPrice() {
        return selectPrice;
    }

    public void setSelectPrice(String selectPrice) {
        this.selectPrice = selectPrice;
    }

    public String getSelectImage() {
        return selectImage;
    }

    public void setSelectImage(String selectImage) {
        this.selectImage = selectImage;
    }

    public String getSelectLink() {
        return selectLink;
    }

    public void setSelectLink(String selectLink) {
        this.selectLink = selectLink;
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

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public Select(String selectName, String selectSalePrice, String selectPrice, String selectImage, String selectLink, String memoName, String memoData, String memoContent) {
        this.selectName = selectName;
        this.selectSalePrice = selectSalePrice;
        this.selectPrice = selectPrice;
        this.selectImage = selectImage;
        this.selectLink = selectLink;
        this.memoName = memoName;
        this.memoData = memoData;
        this.memoContent = memoContent;
        this.isSelected = isSelected;
    }
}