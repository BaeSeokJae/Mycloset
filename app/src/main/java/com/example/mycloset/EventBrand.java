package com.example.mycloset;

public class EventBrand {

    String eventBrandName; // 이름
    String eventBrandDate; // 날짜
    String eventBrandSubContent;
    int eventBrandImage; // 이미지
    String eventBrandLink; // 세부페이지 링

    public String getEventBrandName() {
        return eventBrandName;
    }

    public void setEventBrandName(String eventBrandName) {
        this.eventBrandName = eventBrandName;
    }

    public String getEventBrandDate() {
        return eventBrandDate;
    }

    public void setEventBrandDate(String eventBrandDate) {
        this.eventBrandDate = eventBrandDate;
    }

    public String getEventBrandSubContent() {
        return eventBrandSubContent;
    }

    public void setEventBrandSubContent(String eventBrandSubContent) {
        this.eventBrandSubContent = eventBrandSubContent;
    }

    public int getEventBrandImage() {
        return eventBrandImage;
    }

    public void setEventBrandImage(int eventBrandImage) {
        this.eventBrandImage = eventBrandImage;
    }

    public String getEventBrandLink() {
        return eventBrandLink;
    }

    public void setEventBrandLink(String eventBrandLink) {
        this.eventBrandLink = eventBrandLink;
    }

    public EventBrand(String eventBrandName, String eventBrandDate, String eventBrandSubContent, String eventBrandLink, int eventBrandImage) {
        this.eventBrandName = eventBrandName;
        this.eventBrandDate = eventBrandDate;
        this.eventBrandSubContent = eventBrandSubContent;
        this.eventBrandImage = eventBrandImage;
        this.eventBrandLink = eventBrandLink;
    }
}