package com.example.mycloset;

public class MemoEdit {

    String editText; // 메모
    String editDate; // 날짜
    String editcontent; // 내용

    public String getEditText() {
        return editText;
    }

    public void setEditText(String editText) {
        this.editText = editText;
    }

    public String getEditDate() {
        return editDate;
    }

    public void setEditDate(String editDate) {
        this.editDate = editDate;
    }

    public String getEditcontent() {
        return editcontent;
    }

    public void setEditcontent(String editcontent) {
        this.editcontent = editcontent;
    }

    public MemoEdit(String editText, String editDate, String editcontent) {
        this.editText = editText;
        this.editDate = editDate;
        this.editcontent = editcontent;
    }
}
