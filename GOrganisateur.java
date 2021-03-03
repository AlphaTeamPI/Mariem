/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services;

import com.esprit.models.Organisateur;
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
public class GOrganisateur implements Interface<Organisateur>{
                Connection cnx = DataSource.getInstance().getCnx();

        @Override
    public void ajouter(Organisateur o) {
        try {
            String requete = "INSERT INTO user (IDUser,nom,prenom,email) VALUES ('" + o.getIDUser() + "','"  + o.getNom() + "','"  + o.getPrenom() + "','" + o.getEmail() + "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            String req = "INSERT INTO organisateur (Adresse,Tel,IDUser) VALUES ('" + o.getAdresse() + "','"  + o.getTel() + "','"  + o.getIDUser() + "')";
            Statement st1 = cnx.createStatement();
            st1.executeUpdate(req);
            System.out.println("Organisateur ajouté !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(Organisateur o) {
                 try {
            String requete = "DELETE FROM user WHERE IDUser=" + o.getIDUser();
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("Organisateur supprimé !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Organisateur o) {
        try {
            String requete = "UPDATE user SET nom='" + o.getNom() + "',prenom='" + o.getPrenom() + "',email='" + o.getEmail() +"' WHERE IDUser =" + o.getIDUser();
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            
            String req = "UPDATE Organisateur SET Adresse='" + o.getAdresse() + "',Tel='" + o.getTel() + "' WHERE IDUser =" + o.getIDUser();
            Statement st1 = cnx.createStatement();
            st1.executeUpdate(req);
            System.out.println("Organisateur modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }


    @Override
    public List<Organisateur> afficher() {
        List<Organisateur> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM organisateur a Inner JOIN user u where a.IDUser=u.IDUser";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                
                list.add(new Organisateur(rs.getString(2),rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getString(6),rs.getString(7)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }

                @Override
    public int nombre() {
        int i = 0;
        String requete = "SELECT COUNT(*) as nbr FROM organisateur";

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
    public List<Organisateur> TrierNom() {
        List<Organisateur> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM organisateur a Inner JOIN user u where a.IDUser=u.IDUser ORDER BY nom ASC";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                
                list.add(new Organisateur(rs.getString(2),rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getString(6),rs.getString(7)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }

    @Override
    public List<Organisateur> TrierPrenom() {
        List<Organisateur> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM organisateur a Inner JOIN user u where a.IDUser=u.IDUser ORDER BY prenom ASC";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                
                list.add(new Organisateur(rs.getString(2),rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getString(6),rs.getString(7)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }

    @Override
    public List<Organisateur> RechercheNom(String t) {
        ResultSet rs;
          List<Organisateur> list = new ArrayList<>();
        try {
            String requete = "SELECT * FROM organisateur a Inner JOIN user u where (a.IDUser=u.IDUser) and (nom='"+t+"')";
            Statement st = cnx.createStatement();
            rs= st.executeQuery(requete); 
            
            while(rs.next()){
                list.add(new Organisateur(rs.getString(2),rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getString(6),rs.getString(7)));
            }
            int size = list.size();
             if(size!=0){
                 System.out.println("organisateur Retrouvé"); 
            }else 
                       System.out.println("organisateur Non Retrouvé");
           
           
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } 
         return list;
    }

    
}
