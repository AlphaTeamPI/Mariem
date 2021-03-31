/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import com.esprit.models.User;
import com.esprit.services.SUser;
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
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Meriem BEN AOUAYENE
 */
public class NEWPasswordController implements Initializable {

    @FXML
    private Label HELLOUSER;
    @FXML
    private ImageView lPhotoUser;
    @FXML
    private Button change;
    @FXML
    private TextField TFNewPassword;

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
        TFNewPassword.getScene().setRoot(root);
        SUser U = new SUser();
        U.modifierOnlineOFF();
        JOptionPane.showMessageDialog(null,"Log out successfully ");
    }

    @FXML
    private void Submit(ActionEvent event) throws IOException {
        SUser U = new SUser();
        User user=U.RechercheUserOnline();
        U.modifierPassword(user, TFNewPassword.getText());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/Login.fxml"));
        Parent root = loader.load();
        TFNewPassword.getScene().setRoot(root);
        U.modifierOnlineOFF();
    }
    
}
