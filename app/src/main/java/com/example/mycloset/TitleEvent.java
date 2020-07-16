package com.example.mycloset;

public class TitleEvent {

    String titleEventName;
    String titleEventLink;

    public String getTitleEventName() {
        return titleEventName;
    }

    public void setTitleEventName(String titleEventName) {
        this.titleEventName = titleEventName;
    }

    public String getTitleEventLink() {
        return titleEventLink;
    }

    public void setTitleEventLink(String titleEventLink) {
        this.titleEventLink = titleEventLink;
    }

    public TitleEvent(String titleEventName, String titleEventLink) {
        this.titleEventName = titleEventName;
        this.titleEventLink = titleEventLink;
    }
}
