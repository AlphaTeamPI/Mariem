/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services;

import com.esprit.models.Admin;
import com.esprit.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Meriem BEN AOUAYENE
 */
public class GAdmin implements Interface<Admin>{
            Connection cnx = DataSource.getInstance().getCnx();

        @Override
    public void ajouter(Admin u) {
        try {
            String requete = "INSERT INTO user (IDUser,nom,prenom,email) VALUES ('" + u.getIDUser() + "','"  + u.getNom() + "','"  + u.getPrenom() + "','" + u.getEmail() + "')";
            Statement st = cnx.createStatement();
            String req = "INSERT INTO admin (Adresse,Tel,IDUser) VALUES ('" + u.getAdresse() + "','"  + u.getTel() + "','"  + u.getIDUser() + "')";
            Statement st1 = cnx.createStatement();
            if(String.valueOf(u.getTel()).length()==8)
            {        
            st.executeUpdate(requete);
            st1.executeUpdate(req);
            System.out.println("Admin ajouté !");
            }
            else 
            System.out.println("num Tel = 8 !");
        
                } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(Admin u) {
                 try {
            String requete = "DELETE FROM user WHERE IDUser=" + u.getIDUser();
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("Admin supprimé !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Admin u) {
        try {
            String requete = "UPDATE user SET nom='" + u.getNom() + "',prenom='" + u.getPrenom() + "',email='" + u.getEmail() +"' WHERE IDUser =" + u.getIDUser();
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            
            String req = "UPDATE admin SET Adresse='" + u.getAdresse() + "',Tel='" + u.getTel() + "' WHERE IDUser =" + u.getIDUser();
            Statement st1 = cnx.createStatement();
            st1.executeUpdate(req);
            System.out.println("admin modifié !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }


    @Override
    public List<Admin> afficher() {       
        List<Admin> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM admin a Inner JOIN user u where a.IDUser=u.IDUser ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                
                list.add(new Admin(rs.getString(2),rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getString(6),rs.getString(7)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }
    
            @Override
        public int nombre() {

        int i = 0;
        String requete = "SELECT COUNT(*) as nbr FROM admin";

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
    public List<Admin> TrierNom() {
        List<Admin> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM admin a Inner JOIN user u where a.IDUser=u.IDUser ORDER BY nom ASC";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                
                list.add(new Admin(rs.getString(2),rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getString(6),rs.getString(7)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }

    @Override
    public List<Admin> TrierPrenom() {
        List<Admin> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM admin a Inner JOIN user u where a.IDUser=u.IDUser ORDER BY prenom ASC";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                
                list.add(new Admin(rs.getString(2),rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getString(6),rs.getString(7)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }

    @Override
    public List<Admin> RechercheNom(String t) {
        ResultSet rs;
          List<Admin> list = new ArrayList<>();
        try {
            String requete = "SELECT * FROM admin a Inner JOIN user u where (a.IDUser=u.IDUser) AND (nom ='"+t+"')";
            Statement st = cnx.createStatement();
            rs= st.executeQuery(requete); 
            
            while(rs.next()){
               list.add(new Admin(rs.getString(2),rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getString(6),rs.getString(7)));
            }
            int size = list.size();
             if(size!=0){
                 System.out.println("Admin Retrouvé"); 
            }else 
                       System.out.println("Admin Non Retrouvé");
           
           
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } 
         return list;
    }
    
    
    public int ExistanceAdmin(int t) {
        ResultSet rs;
          List<Admin> list = new ArrayList<>();
        try {
            String requete = "SELECT * FROM admin a Inner JOIN user u where (a.IDUser=u.IDUser) AND (a.IDUser ='"+t+"')";
            Statement st = cnx.createStatement();
            rs= st.executeQuery(requete); 
            
            while(rs.next()){
               list.add(new Admin(rs.getString(2),rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getString(6),rs.getString(7)));
            }
            int size = list.size();
             if(size!=0){
                 System.out.println("Admin Retrouvé"); 
                 return 1;
            }else 
                       System.out.println("Admin Non Retrouvé");
           
           
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } 
         return 0;
    }
      
}
