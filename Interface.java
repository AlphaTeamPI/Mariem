/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services;

import java.util.List;

/**
 *
 * @author Meriem BEN AOUAYENE
 * @param <T>
 */
public interface Interface<T>  {
    public void ajouterUser(T t);
    public void ajouterParticipant(T t);
    public int nombre();
    public void supprimer(String t);
    public void modifier(T t);
    public List<T> afficher();
    public List<T> TrierUserName();;
    public T RechercheID(String t);
    public boolean modifierUserName(T t, String newValue);
    public boolean modifierPassword(T t, String newValue);
    public boolean modifierEmail(T t, String newValue);
    public boolean modifierImage(T t, String newValue);
}
