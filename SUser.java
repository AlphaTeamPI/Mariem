/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services;

import com.esprit.models.User;
import com.esprit.utils.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.ImageView;

/**
 *
 * @author Meriem BEN AOUAYENE
 */
public class SUser implements Interface<User> {
               Connection cnx = DataSource.getInstance().getCnx();

        @Override
    public void ajouterUser(User u) {
         int num = (int)(Math.random()*((9999-1000)+1))+1000;
        try {
            String requete = "INSERT INTO user (UserName,Password,email,ImageUser,Role,DateDeNaissance,PasswordOublie,online) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement st = cnx.prepareStatement(requete);

               st.setString(1,u.getUserName());
               st.setString(2,u.getPassword());
               st.setString(3,u.getEmail());
               st.setString(4,u.getImageUser());
               System.out.println(u.getImageUser());
               st.setString(5,u.getRole());
               st.setDate(6, (Date) u.getBirthday());
               st.setInt(7,num);
               st.setInt(8,0);
               st.executeUpdate();
            
           // if(String.valueOf(u.getTel()).length()==8   
            System.out.println("User ajouté !");
        
                } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }   
        
        
        

    }
    
     public void ajouterParticipant(User u) {
         int num = (int)(Math.random()*((9999-1000)+1))+1000;
        try {
            String requete = "INSERT INTO user (UserName,Password,email,ImageUser,Role,DateDeNaissance,PasswordOublie,online) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement st = cnx.prepareStatement(requete);

               st.setString(1,u.getUserName());
               st.setString(2,u.getPassword());
               st.setString(3,u.getEmail());
               st.setString(4,u.getImageUser());
               System.out.println(u.getImageUser());
               st.setString(5,"Participant");
               st.setDate(6, (Date) u.getBirthday());
               st.setInt(7,num);
                st.setInt(8,0);
               st.executeUpdate();
            System.out.println("User ajouté !");
        
                } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

            
    
    @Override
    public void supprimer(String u) {
                 try {
            String requete = "DELETE FROM user WHERE UserName='"+u+"'";
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("User supprimé !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(User u) {
        try {
            String requete = "UPDATE user SET UserName='" + u.getUserName() + 
                    "',Password='" + u.getPassword() + "',email='" + u.getEmail() + 
                    "',ImageUser='" + u.getImageUser() + "',Role='" + u.getRole() +
                    "',WHERE IDUser =" + u.getIDUser();
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("User modifié !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }


    @Override
    public List<User> afficher() {       
        List<User> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM user";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                User u = new User();
                u.setUserName(rs.getString(2));
                u.setPassword(rs.getString(3));
                u.setEmail(rs.getString(4));
                u.setImageUser(rs.getString(5));
                u.setRole(rs.getString(6));
                u.setBirthday(rs.getDate(7));
                ImageView a = new ImageView();
                javafx.scene.image.Image img = new javafx.scene.image.Image("file:" + rs.getString(5));
                a.setImage(img);
                a.setFitHeight(200);
                a.setFitWidth(200);
                u.setImageview(a);               
                
                System.err.println(rs.getDate(7));
                
                list.add(u);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }
    
    public List<String> afficherALLUserName() {       
        List<String> list = new ArrayList<>();

        try {
            String requete = "SELECT UserName FROM user";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                
                list.add(rs.getString(1));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }
    
            @Override
        public int nombre() {

        int i = 0;
        String requete = "SELECT COUNT(*) as nbr FROM user";

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
    public List<User> TrierUserName() {
        List<User> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM user ORDER BY UserName ASC";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                
                list.add(new User(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),rs.getString(6)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }


    @Override
    public User RechercheID(String t) {
        ResultSet rs;
        User u = new User();
        try {
            String requete = "SELECT * FROM user where UserName ='"+t+"'";
            Statement st = cnx.createStatement();
            rs= st.executeQuery(requete); 

            while(rs.next()){
                                
                u.setUserName(rs.getString(2));
                u.setPassword(rs.getString(3));
                u.setEmail(rs.getString(4));
                u.setImageUser(rs.getString(5));
                u.setRole(rs.getString(6));
                u.setBirthday(rs.getDate(7));
                u.setPasswordOublie(rs.getInt(8));          
                System.err.println(rs.getDate(7));
                
               // us=new User(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),rs.getString(6),rs.getInt(0));
            }
           /** int size = list.size();
             if(size!=0){
                 System.out.println("User Retrouvé"); 
            }else 
                       System.out.println("User Non Retrouvé");*/
           
           
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } 
        return u;
    }
    
    public void clear_M(){
        try {
            String requete = "TRUNCATE user ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.executeUpdate();
            System.out.println("La table a été supprimer");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public User Existance(String nom,String mdp ) {
        System.out.println(nom);
        System.out.println(mdp);
        ResultSet rs;
          User us = null;
          List<User> list = new ArrayList<>();
        try {
            String requete = "SELECT * FROM user where (UserName ='"+nom+"') and (Password ='"+mdp+"')";
            Statement st = cnx.createStatement();
            rs= st.executeQuery(requete); 
            
            while(rs.next()){
                us = new User(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),rs.getString(6));
                list.add(new User(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),rs.getString(6)));
                System.out.println(rs.getString(6));
            }
            int size = list.size();
             if(size!=0){
                 System.out.println("User Retrouvé"); 
            }else 
                       System.out.println("User Non Retrouvé");
           
           
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } 
         return us;
    } 
    
    
        public User RechercheUserOnline() {
        ResultSet rs;
          User us = null;
          List<User> list = new ArrayList<>();
        try {
            String requete = "SELECT * FROM user where (online ='"+1+"')";
            Statement st = cnx.createStatement();
            rs= st.executeQuery(requete); 
            
            while(rs.next()){
              System.err.println(rs.getInt(8));
                
              us = new User(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),rs.getString(6),rs.getDate(7),rs.getInt(8));
            }
         
           
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } 
         return us;
    } 
    
        public boolean ExistanceB(User u ) {
        ResultSet rs;
          User us = null;
          List<User> list = new ArrayList<>();
        try {
            String requete = "SELECT * FROM user where (UserName ='"+u.getUserName()+"') and (Password ='"+u.getPassword()+"')";
            Statement st = cnx.createStatement();
            rs= st.executeQuery(requete); 
            
            while(rs.next()){
              //  us = new User(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),rs.getString(6));
                list.add(new User(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),rs.getString(6)));
            }         
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } 
            int size = list.size();
            System.out.println(size);
             if(size!=0){
         
                 return true; 
            }else 
                 return false;
             
            
    }


    @Override
        public boolean modifierUserName(User U, String newValue) {
      Boolean ok = false;
        try {
            PreparedStatement req = cnx.prepareStatement("update user set UserName=? where UserName = ? ");
            
       req.setString(1, newValue);
       req.setString(2, U.getUserName());
       req.executeUpdate();
        ok = true;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
return ok;
    }
        
           
        public void modifierOnlineOn(User U) {

        try {
            PreparedStatement req = cnx.prepareStatement("update user set online=? where UserName = ? ");
            
       req.setInt(1,1);
       req.setString(2, U.getUserName());
       req.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }
        
                public void modifierOnlineOFF() {
        try {
            PreparedStatement req = cnx.prepareStatement("update user set online=?");
            
       req.setInt(1,0);
       req.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
                
        public void modifierPasswordCode(User U) {
int num = (int)(Math.random()*((9999-1000)+1))+1000;
        try {
            PreparedStatement req = cnx.prepareStatement("update user set PasswordOublie=? where UserName = ? ");
            
       req.setInt(1,num);
       req.setString(2, U.getUserName());
       req.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }
                
                
        
            @Override
        public boolean modifierPassword(User U, String newValue) {
      Boolean ok = false;
        try {
            PreparedStatement req = cnx.prepareStatement("update user set Password=? where UserName = ? ");
            
       req.setString(1, newValue);
       req.setString(2, U.getUserName());
       req.executeUpdate();
        ok = true;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
return ok;
    }
    
                    @Override
        public boolean modifierEmail(User U, String newValue) {
      Boolean ok = false;
        try {
            PreparedStatement req = cnx.prepareStatement("update user set email=? where UserName = ? ");
            
       req.setString(1, newValue);
       req.setString(2, U.getUserName());
       req.executeUpdate();
        ok = true;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
return ok;
    }
        
                    @Override
        public boolean modifierImage(User U, String newValue) {
      Boolean ok = false;
        try {
            PreparedStatement req = cnx.prepareStatement("update user set ImageUser=? where UserName = ? ");
            
       req.setString(1, newValue);
       req.setString(2, U.getUserName());
       req.executeUpdate();
        ok = true;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
return ok;
    }
        
}
