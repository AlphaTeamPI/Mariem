/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services;

import com.esprit.models.Client;
import com.esprit.utils.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Meriem BEN AOUAYENE
 */
public class GClient implements Interface<Client> {

            Connection cnx = DataSource.getInstance().getCnx();

        @Override
    public void ajouter(Client c) {
        try {
            String requete = "INSERT INTO user (IDUser,nom,prenom,email) VALUES ('" + c.getIDUser() + "','"  + c.getNom() + "','"  + c.getPrenom() + "','" + c.getEmail() + "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("Utilisateur ajouté !");


        try {
            String req = "INSERT INTO client (IDEvent,IDUser) VALUES ('" + c.getIDEvent()+   "','"  +  c.getIDUser() + "')";
            Statement st1 = cnx.createStatement();
            st1.executeUpdate(req);
            System.out.println("Client ajouté !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        
                } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(Client c) {
                 try {
            String requete = "DELETE FROM user WHERE IDUser=" + c.getIDUser();
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("Client supprimé !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Client c) {
        try {
            String requete = "UPDATE user SET nom='" + c.getNom() + "',prenom='" + c.getPrenom() + "',email='" + c.getEmail() +"' WHERE IDUser =" + c.getIDUser();
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            
            String req = "UPDATE client SET IDEvent='" + c.getIDEvent() + "' WHERE IDUser =" + c.getIDUser();
            Statement st1 = cnx.createStatement();
            st1.executeUpdate(req);
            System.out.println("Client modifié !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }


    @Override
    public List<Client> afficher() {
        List<Client> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM client c Inner JOIN user u where c.IDUser=u.IDUser";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                
                list.add(new Client(rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5),rs.getString(6)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }

    @Override
    public int nombre() {
        int i = 0;
        String requete = "SELECT COUNT(*) as nbr FROM client";

        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);

            while (rs.next()) {

                i = rs.getInt("nbr");

            }
        } catch (SQLException ex) {
             System.err.println(ex.getMessage());
        }
        return i;
    }

    @Override
    public List<Client> TrierNom() {
        List<Client> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM client c Inner JOIN user u where c.IDUser=u.IDUser ORDER BY nom ASC";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                
                list.add(new Client(rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5),rs.getString(6)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }

    @Override
    public List<Client> TrierPrenom() {
        List<Client> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM client c Inner JOIN user u where c.IDUser=u.IDUser ORDER BY prenom ASC";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                
                list.add(new Client(rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5),rs.getString(6)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }


    @Override
    public List<Client> RechercheNom(String t) {
        ResultSet rs;
          List<Client> list = new ArrayList<>();
        try {
            String requete = "SELECT * FROM client a Inner JOIN user u where (a.IDUser=u.IDUser) and (nom='"+t+"')";
            Statement st = cnx.createStatement();
            rs= st.executeQuery(requete); 
            
            while(rs.next()){
                list.add(new Client(rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5),rs.getString(6)));
            }
            int size = list.size();
             if(size!=0){
                 System.out.println("client Retrouvé"); 
            }else 
                       System.out.println("client Non Retrouvé");
           
           
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } 
         return list;
    }
    
}
