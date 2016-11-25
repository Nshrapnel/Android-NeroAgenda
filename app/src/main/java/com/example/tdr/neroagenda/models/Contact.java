package com.example.tdr.neroagenda.models;

public class Contact {


    private String mName, mPhone, mEmail;
    private long id;

    public Contact(String mName, String mPhone, String mEmail) {
        this.mName = mName;
        this.mPhone = mPhone;
        this.mEmail = mEmail;
    }

    public Contact(String mName, String mPhone, String mEmail, long id) {
        this.mName = mName;
        this.mPhone = mPhone;
        this.mEmail = mEmail;
        this.id = id;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmPhone() {
        return mPhone;
    }

    public void setmPhone(String mPhone) {
        this.mPhone = mPhone;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}