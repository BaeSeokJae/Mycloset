package com.example.mycloset;

public class Event {

    String eventName; // 이름
    String eventDate; // 날짜
    String eventDate1;
//    String productSalePrice; // 세일가격
//    String productPrice; // 원래 가격
    String eventImage; // 이미지
    String eventLink; // 세부페이지 링

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventDate1() {
        return eventDate1;
    }

    public void setEventDate1(String eventDate1) {
        this.eventDate1 = eventDate1;
    }

    public String getEventImage() {
        return eventImage;
    }

    public void setEventImage(String eventImage) {
        this.eventImage = eventImage;
    }

    public String getEventLink() {
        return eventLink;
    }

    public void setEventLink(String eventLink) {
        this.eventLink = eventLink;
    }

    public Event() {
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventDate1 = eventDate1;
        this.eventImage = eventImage;
        this.eventLink = eventLink;
    }
}