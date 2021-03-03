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
public class Client extends User{
    
    private int IDEvent;

    public Client(int IDEvent, int IDUser, String Nom, String Prenom, String Email) {
        super(IDUser, Nom, Prenom, Email);
        this.IDEvent = IDEvent;
    }

    public int getIDEvent() {
        return IDEvent;
    }

    public void setIDEvent(int IDEvent) {
        this.IDEvent = IDEvent;
    }

    @Override
    public String toString() {
        return "Client{" + super.toString() +"IDEvent=" + IDEvent + '}';
    }


    
}
