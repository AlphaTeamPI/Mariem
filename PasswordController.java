/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import com.esprit.models.Email;
import com.esprit.models.User;
import com.esprit.services.SUser;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javax.mail.MessagingException;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Meriem BEN AOUAYENE
 */
public class PasswordController implements Initializable {

    @FXML
    private Label HELLOUSER;
    @FXML
    private ImageView lPhotoUser;
    @FXML
    private Button BSubmit;
    @FXML
    private TextField TFCode;
    private String PasswordCodeAverifier;
    @FXML
    private Button BResendCode;

    public void setPasswordCodeAverifier(String PasswordCodeAverifier) {
        this.PasswordCodeAverifier = PasswordCodeAverifier;
    }

    public String getPasswordCodeAverifier() {
        return PasswordCodeAverifier;
    }
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void LogOut(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/Login.fxml"));
        Parent root = loader.load();
        TFCode.getScene().setRoot(root);
        SUser U = new SUser();
        U.modifierOnlineOFF();
        JOptionPane.showMessageDialog(null,"Log out successfully ");
    }



    @FXML
    private void Submit(ActionEvent event) throws IOException {
        if(!TFCode.getText().equals("")){
        SUser U = new SUser();
        User user=U.RechercheUserOnline();
        System.out.println(user.getPasswordOublie());
        System.out.println(user.getUserName());
        if(String.valueOf(user.getPasswordOublie()).equals(TFCode.getText())){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/NEWPassword.fxml"));
                Parent root = loader.load();
                TFCode.getScene().setRoot(root);
                JOptionPane.showMessageDialog(null,"The code is verified, you can change your Password ");
        }
        }
                 else
            JOptionPane.showMessageDialog(null, "Could you please enter the code","Alert",JOptionPane.WARNING_MESSAGE);
    }

    @FXML
    private void ResendCode(ActionEvent event) {
        
        SUser U = new SUser();
        U.modifierPasswordCode(U.RechercheUserOnline());
        User user=U.RechercheUserOnline();
     
        System.out.println(user.getPasswordOublie());
        Email email = new Email();
        try {
            email.sendMail(user.getEmail(), "hello", "Your password :"+user.getPasswordOublie());
            JOptionPane.showMessageDialog(null,"Code sent successfully");
                    //+user.getPassword());
        } catch (MessagingException ex) {
            System.out.println("famma mochkill");
          //  Logger.getLogger(PasswordController.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }
    
}
