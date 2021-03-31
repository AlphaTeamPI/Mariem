/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import com.esprit.models.User;
import com.esprit.services.SUser;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Meriem BEN AOUAYENE
 */
public class SettingUserController implements Initializable {

    @FXML
    private Label HELLOUSER;
    @FXML
    private ImageView lPhotoUser;
    @FXML
    private TableView<User> TableUser;
    @FXML
    private TableColumn<User, String> USerName;
    @FXML
    private TableColumn<User, String> Password;
    @FXML
    private TableColumn<User, String> Email;
    @FXML
    private TableColumn<User, ImageView> Image;
    @FXML
    private TableColumn<User, String> Role;   
    @FXML
    private TableColumn<User, Date> Birthday;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AfficherUser();
        DoublMod();
        // TODO
    }    

    private void AfficherUser() {
        SUser SU = new SUser();
                SUser U = new SUser();
        ObservableList<User> items = FXCollections.observableArrayList(U.RechercheUserOnline());
        USerName.setCellValueFactory(new PropertyValueFactory<>("UserName") );
        Password.setCellValueFactory(new PropertyValueFactory<>("Password") );
        Email.setCellValueFactory(new PropertyValueFactory<>("Email") );
        Image.setCellValueFactory(new PropertyValueFactory<>("imageview") );
        Role.setCellValueFactory(new PropertyValueFactory<>("Role") );
        Birthday.setCellValueFactory(new PropertyValueFactory<>("Birthday") );
        TableUser.setItems(items);
    }
    
    @FXML
    private void UserPage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/User.fxml"));
        Parent root = loader.load();
        
        UserController UC = loader.getController();
        SUser U = new SUser();
        UC.setHELLOUSER(U.RechercheUserOnline().getUserName());
        UC.setPhotoUser(U.RechercheUserOnline().getImageUser());
    }

    @FXML
    private void LogOut(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/Login.fxml"));
        Parent root = loader.load();
        
        SUser U = new SUser();
        U.modifierOnlineOFF();
    }

    @FXML
    private void SettingUser(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/User.fxml"));
        Parent root = loader.load();
        
        UserController UC = loader.getController();
        SUser U = new SUser();
        UC.setHELLOUSER(U.RechercheUserOnline().getUserName());
        UC.setPhotoUser(U.RechercheUserOnline().getImageUser());
    }
    
    
                    public void DoublMod(){ 
        SUser SU = new SUser();
        
        USerName.setCellFactory(TextFieldTableCell.forTableColumn()); 
        USerName.setOnEditCommit((e) -> {  
                 if(SU.modifierUserName(TableUser.getItems().get(e.getTablePosition().getRow()),e.getNewValue()))  
                    TableUser.getItems().get(e.getTablePosition().getRow()).setUserName(e.getNewValue());                               
                    TableUser.refresh();
                    JOptionPane.showMessageDialog(null, "User updated successfully"); 
         });
        Password.setCellFactory(TextFieldTableCell.forTableColumn());
        Password.setOnEditCommit((e) -> {  
                    if(SU.modifierPassword(TableUser.getItems().get(e.getTablePosition().getRow()),e.getNewValue())) 
                        TableUser.getItems().get(e.getTablePosition().getRow()).setPassword(e.getNewValue());                          
               TableUser.refresh(); 
               JOptionPane.showMessageDialog(null, "User updated successfully"); 
         });
        Email.setCellFactory(TextFieldTableCell.forTableColumn());
        Email.setOnEditCommit((e) -> { 
                    if(SU.modifierEmail(TableUser.getItems().get(e.getTablePosition().getRow()),e.getNewValue()))  
                        TableUser.getItems().get(e.getTablePosition().getRow()).setEmail(e.getNewValue());                          
               TableUser.refresh(); 
               JOptionPane.showMessageDialog(null, "User updated successfully"); 
         });
     /*   Image.setCellFactory(TextFieldTableCell.forTableColumn());
        Image.setOnEditCommit((e) -> { //hedha 
                    if(SU.modifierImage(TableUser.getItems().get(e.getTablePosition().getRow()),e.getNewValue())) // w hedha 
                        TableUser.getItems().get(e.getTablePosition().getRow()).setImageUser(e.getNewValue());                          
               TableUser.refresh(); // hedhi apparemment refresh XD
         });*/
        
    }

    
}
