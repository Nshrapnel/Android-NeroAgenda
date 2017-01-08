package com.example.tdr.neroagenda.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Contact implements Parcelable {

    public static final Parcelable.Creator<Contact> CREATOR = new Parcelable.Creator<Contact>() {
        @Override
        public Contact createFromParcel(Parcel source) {
            return new Contact(source);
        }

        @Override
        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };
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

    protected Contact(Parcel in) {
        this.mName = in.readString();
        this.mPhone = in.readString();
        this.mEmail = in.readString();
        this.id = in.readLong();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mName);
        dest.writeString(this.mPhone);
        dest.writeString(this.mEmail);
        dest.writeLong(this.id);
    }
}