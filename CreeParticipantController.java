/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import com.esprit.models.User;
import com.esprit.services.SUser;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import javax.swing.JOptionPane;
import nl.captcha.Captcha;

/**
 * FXML Controller class
 *
 * @author Meriem BEN AOUAYENE
 */
public class CreeParticipantController implements Initializable {

    @FXML
    private TextField TFUserName;
    private FileChooser fileChooser;
    private File file;
    private Window stage;
    @FXML
    private TextField TFPassword;
    @FXML
    private TextField TFEmail;
    @FXML
    private JFXDatePicker TFBirthday;
    @FXML
    private JFXTextField TFImage;
    @FXML
    private ImageView ImageUser;
    @FXML
    private Button BAjouter;
    @FXML
    private JFXButton BtnUpload;
    @FXML
    private ImageView cap;
    @FXML
    private TextField code;
        Captcha captcha;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        captcha =  setCaptcha();
        // TODO
    }    

    @FXML
    private void AjouterUser(ActionEvent event) throws IOException {

            if(!TFUserName.getText().equals("")){
            if (!TFPassword.getText().equals("")) {
                if (!TFEmail.getText().equals("")) {
                    
                  if (TFEmail.getText().contains("@"))
                    {  
                        if(CaptchaTest()) {
                            JOptionPane.showMessageDialog(null, "Welcome "+TFUserName.getText());
                        java.sql.Date sqlDate = java.sql.Date.valueOf(TFBirthday.getValue());
             
                SUser U = new SUser();
        U.ajouterParticipant(new User (TFUserName.getText(), TFPassword.getText(), TFEmail.getText(), TFImage.getText(),sqlDate));
        
        JOptionPane.showMessageDialog(null, "User Ajouter");
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        Parent root = loader.load();
        TFUserName.getScene().setRoot(root);//1:09h
                        }
                        else
                        {
                            captcha =  setCaptcha();
                            code.clear();
                            JOptionPane.showMessageDialog(null, "code captcha Wrong");
                        }
                }     
                    else
             JOptionPane.showMessageDialog(null, "L'Email doit contenir @","Alert",JOptionPane.WARNING_MESSAGE);
            }
                else            
                JOptionPane.showMessageDialog(null, "Vous devez insére votre Email","Alert",JOptionPane.WARNING_MESSAGE);
            }
            else 
            JOptionPane.showMessageDialog(null, "Vous devez insére le Mdp","Alert",JOptionPane.WARNING_MESSAGE);
        }
        else
        JOptionPane.showMessageDialog(null, "Vous devez insére le nom","Alert",JOptionPane.WARNING_MESSAGE);   
    }


    @FXML
    private void uploadfile(ActionEvent event) {
        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        //System.out.println("test boutton upload");

        file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            TFImage.setText(file.getAbsolutePath());
            javafx.scene.image.Image image = new javafx.scene.image.Image(file.toURI().toString(), 800, 200, true, true);
            ImageUser.setImage(image);
        }
    }

    @FXML
    private void ReturnBack(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/Login.fxml"));
        Parent root = loader.load();
        TFUserName.getScene().setRoot(root);
    }

    
        public Captcha setCaptcha() {
        Captcha captchaV = new Captcha.Builder(250, 200)
                .addText()
                .addBackground()
                .addNoise()
                .gimp()
                .addBorder()
                .build();

        System.out.println(captchaV.getImage());
        Image image = SwingFXUtils.toFXImage(captchaV.getImage(), null);

        cap.setImage(image);
        

        return captchaV;
    }

                public boolean CaptchaTest() {

    System.out.println(captcha.getAnswer());
    System.out.println(code.getText());
        
        if (captcha.isCorrect(code.getText())) 
            return true;
        else 
            return false;
        
                }
    
}
