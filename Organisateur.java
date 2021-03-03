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
public class Organisateur extends User{
    
    private String Adresse;
    private int Tel;

    public Organisateur(String Adresse, int Tel, int IDUser, String Nom, String Prenom, String Email) {
        super(IDUser, Nom, Prenom, Email);
        this.Adresse = Adresse;
        this.Tel = Tel;
    }

    @Override
    public String toString() {
        return "Organisateur{" + super.toString() +", Adresse=" + Adresse + ", Tel=" + Tel + '}';
    }


    public String getAdresse() {
        return Adresse;
    }

    public int getTel() {
        return Tel;
    }


    public void setAdresse(String Adresse) {
        this.Adresse = Adresse;
    }

    public void setTel(int Tel) {
        this.Tel = Tel;
    }


    
    
}
