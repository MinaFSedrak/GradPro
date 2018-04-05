package com.example.mahmoudamin.gradpro;

/**
 * Created by MahmoudAmin on 2/26/2018.
 */

public class Users {
    String userid;
    String fullname;
    String email;
    String password;
    String phone;

    public Users(){

    }

    public Users(String userid, String fullname,String email,String password, String phone) {
        this.fullname = fullname;
        this.userid = userid;
        this.password = password;
        this.phone = phone;
        this.email = email;
    }

    public String getUserid() {
        return userid;
    }

    public String getFullname() {
        return fullname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }


    public String getPhone() {
        return phone;
    }
}


