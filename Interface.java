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
    public void ajouter(T t);
    public int nombre();
    public void supprimer(T t);
    public void modifier(T t);
    public List<T> afficher();
    public List<T> TrierNom();
    public List<T> TrierPrenom();
    public List<T> RechercheNom(String t);
}
