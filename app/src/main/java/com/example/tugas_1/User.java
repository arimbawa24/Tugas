package com.example.tugas_1;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable{
    private String userId;
    String email;
    String password;


    public User(String userId,String email, String password){
        this.userId=userId;
        this.email=email;
        this.password=password;
    }

    public User(String userId, String email) {
        this.userId=userId;
        this.email=email;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email){
        this.email=email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    protected User(Parcel in) {
        userId = in.readString();
        email = in.readString();
        password = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userId);
        dest.writeString(email);
        dest.writeString(password);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
