/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.models;

import java.util.Date;
import java.util.Objects;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;


/**
 *
 * @author Meriem BEN AOUAYENE
 */
public class User {
    
    private int IDUser;
    private String UserName;
    private String Password; 
    private String Email;
    private String ImageUser;
    private String Role;
    private Date Birthday;
    private int PasswordOublie;
    private int online;
    private ImageView imageview;

    public User(String UserName, String Password, String Email) {
        this.UserName = UserName;
        this.Password = Password;
        this.Email = Email;
    }

    public User() {
    }

    public User(int IDUser, String UserName, String Password, String Email, String ImageUser, String Role, Date Birthday, int PasswordOublie) {
        this.IDUser = IDUser;
        this.UserName = UserName;
        this.Password = Password;
        this.Email = Email;
        this.ImageUser = ImageUser;
        this.Role = Role;
        this.Birthday = Birthday;
        this.PasswordOublie = PasswordOublie;
    }



    public User(int IDUser, String UserName, String Password, String Email, String ImageUser, String Role, Date Birthday, ImageView imageview) {
        this.IDUser = IDUser;
        this.UserName = UserName;
        this.Password = Password;
        this.Email = Email;
        this.ImageUser = ImageUser;
        this.Role = Role;
        this.Birthday = Birthday;
        this.imageview = imageview;
    }

    public User(String UserName, String Password, String Email, String ImageUser, String Role, Date Birthday, int PasswordOublie, ImageView imageview) {
        this.UserName = UserName;
        this.Password = Password;
        this.Email = Email;
        this.ImageUser = ImageUser;
        this.Role = Role;
        this.Birthday = Birthday;
        this.PasswordOublie = PasswordOublie;
        this.imageview = imageview;
    }

    
    
    public User(int IDUser, String UserName, String Password, String Email, String Role) {
        this.IDUser = IDUser;
        this.UserName = UserName;
        this.Password = Password;
        this.Email = Email;
        this.Role = Role;
    }

    public User(String UserName, String Password) {
        this.UserName = UserName;
        this.Password = Password;
    }



    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (!Objects.equals(this.Role, other.Role)) {
            return false;
        }
        return true;
    }

    public int getPasswordOublie() {
        return PasswordOublie;
    }

    public void setPasswordOublie(int PasswordOublie) {
        this.PasswordOublie = PasswordOublie;
    }


   

    public User(int IDUser, String UserName, String Password, String Email, String ImageUser, String Role) {
        this.IDUser = IDUser;
        this.UserName = UserName;
        this.Password = Password;
        this.Email = Email;
        this.ImageUser = ImageUser;
        this.Role = Role;
    }


    public User(String UserName, String Password, String Email, String Role) {
        this.UserName = UserName;
        this.Password = Password;
        this.Email = Email;
        this.Role = Role;
    }

    public int getIDUser() {
        return IDUser;
    }

    public String getUserName() {
        return UserName;
    }

    public String getPassword() {
        return Password;
    }

    public String getEmail() {
        return Email;
    }

    public void setImageview(ImageView imageview) {
        this.imageview = imageview;
    }

    public ImageView getImageview() {
        return imageview;
    }

    public String getImageUser() {
        return ImageUser;
    }

    public String getRole() {
        return Role;
    }

    public void setIDUser(int IDUser) {
        this.IDUser = IDUser;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public void setImageUser(String ImageUser) {
        this.ImageUser = ImageUser;
    }

    public void setRole(String Role) {
        this.Role = Role;
    }

    public Date getBirthday() {
        return Birthday;
    }

    public void setBirthday(Date Birthday) {
        this.Birthday = Birthday;
    }

    @Override
    public String toString() {
        return "User{" + "IDUser=" + IDUser + ", UserName=" + UserName + ", Password=" + Password + ", Email=" + Email + ", ImageUser=" + ImageUser + ", Role=" + Role + ", Birthday=" + Birthday + '}';
    }

    public User(String UserName, String Password, String Email, String ImageUser, Date Birthday) {
        this.UserName = UserName;
        this.Password = Password;
        this.Email = Email;
        this.ImageUser = ImageUser;
        this.Birthday = Birthday;
    }



    public User(int IDUser, String UserName, String Password, String Email, String ImageUser, String Role, Date Birthday) {
        this.IDUser = IDUser;
        this.UserName = UserName;
        this.Password = Password;
        this.Email = Email;
        this.ImageUser = ImageUser;
        this.Role = Role;
        this.Birthday = Birthday;
    }

    public User(String UserName, String Password, String Email, String ImageUser, String Role, Date Birthday) {
        this.UserName = UserName;
        this.Password = Password;
        this.Email = Email;
        this.ImageUser = ImageUser;
        this.Role = Role;
        this.Birthday = Birthday;
    }

 





}
