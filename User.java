/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.models;


/**
 *
 * @author Meriem BEN AOUAYENE
 */
public class User {
    
    private int IDUser;
    private String Nom;
    private String Prenom;
    private String Email;

    public User(int IDUser, String Nom, String Prenom, String Email) {
        this.IDUser = IDUser;
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.Email = Email;
    }

    public void setIDUser(int IDUser) {
        this.IDUser = IDUser;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public void setPrenom(String Prenom) {
        this.Prenom = Prenom;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public int getIDUser() {
        return IDUser;
    }

    public String getNom() {
        return Nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public String getEmail() {
        return Email;
    }

    @Override
    public String toString() {
        return "IDUser=" + IDUser + ", Nom=" + Nom + ", Prenom=" + Prenom + ", Email=" + Email + ' ';
        
    }


}
