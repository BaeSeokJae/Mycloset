package com.example.mycloset;

import android.graphics.Bitmap;

public class Memo {

    String mainText; // 메모
    String mainDate; // 날짜
    String subText; // 내용
    Bitmap bitmap; // 이미지
    int isdone; // 완료여부

    public void setSubText(String subText) {
        this.subText = subText;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getMainText() {
        return mainText;
    }

    public void setMainText(String mainText) {
        this.mainText = mainText;
    }

    public String getMainDate() {
        return mainDate;
    }

    public void setMainDate(String mainDate) {
        this.mainDate = mainDate;
    }

    public String getSubText() {
        return subText;
    }

    public void setsubText(String subText) {
        this.subText = subText;
    }

    public int getIsdone() {
        return isdone;
    }

    public void setIsdone(int isdone) {
        this.isdone = isdone;
    }

    public Memo(String mainText, String mainDate, String subText, Bitmap bitmap, int isdone) {
        this.mainText = mainText;
        this.mainDate = mainDate;
        this.subText = subText;
        this.bitmap = bitmap;
        this.isdone = isdone;
    }
}
