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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Meriem BEN AOUAYENE
 */
public class UserController implements Initializable {

    
    private Button btnHome;
    private FileChooser fileChooser;
    private File file;
    private Window stage;
    private Pane Home;
    @FXML
    private TabPane Interface;
    @FXML
    private Tab AfficherButton;
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
    @FXML
    private Button BAffNbr;
    @FXML
    private JFXTextField TFNbr;
    @FXML
    private Tab InitialiserAjout;
    @FXML
    private JFXComboBox<String> CBRole;
    @FXML
    private JFXComboBox<String> CBSupprimer;
    @FXML
    private Button BAjouter;
    @FXML
    private JFXTextField TFEmail;
    @FXML
    private JFXTextField TFUserName;
    @FXML
    private JFXPasswordField TFPassword;
    @FXML
    private JFXDatePicker TFBirthday;
    @FXML
    private ImageView ImageUser;
    @FXML
    private JFXTextField TFImage;
    @FXML
    private JFXButton BtnUpload;
    @FXML
    private Button BSupprimer;
    @FXML
        private Label HELLOUSER;
    private String PUTUserNAme; 
    @FXML
    private ImageView lPhotoUser;


    

    public void setHELLOUSER(String text) {
        this.HELLOUSER.setText(text); 
    }

    public void setPUTUserNAme(String PUTUserNAme) {
        this.PUTUserNAme = PUTUserNAme;
    }

    public void setPhotoUser(String text) {
                javafx.scene.image.Image img = new javafx.scene.image.Image("file:" + text);
                lPhotoUser.setImage(img);
                lPhotoUser.setFitHeight(42);
                lPhotoUser.setFitWidth(52);
    }

    public UserController() {
    }


    
    
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
                ObservableList<String> Role = FXCollections.observableArrayList("Organisateur","Participant");
                CBRole.setItems(Role);
                SUser SU = new SUser();
                ObservableList<String> Id = FXCollections.observableArrayList(SU.afficherALLUserName());
                CBSupprimer.setItems(Id);
                DoublMod();
        // TODO
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
         });
        Email.setCellFactory(TextFieldTableCell.forTableColumn());
        Email.setOnEditCommit((e) -> { 
                    if(SU.modifierEmail(TableUser.getItems().get(e.getTablePosition().getRow()),e.getNewValue()))  
                        TableUser.getItems().get(e.getTablePosition().getRow()).setEmail(e.getNewValue());                          
               TableUser.refresh(); 
         });
     /*   Image.setCellFactory(TextFieldTableCell.forTableColumn());
        Image.setOnEditCommit((e) -> { //hedha 
                    if(SU.modifierImage(TableUser.getItems().get(e.getTablePosition().getRow()),e.getNewValue())) // w hedha 
                        TableUser.getItems().get(e.getTablePosition().getRow()).setImageUser(e.getNewValue());                          
               TableUser.refresh(); // hedhi apparemment refresh XD
         });*/
        
    }
    
    @FXML
    private void AfficherNbr(ActionEvent event) {
                        SUser U = new SUser();
        int nbr = U.nombre();
        TFNbr.setText(String.valueOf(nbr));
      //  System.out.println(TFImage.getText());  
    }

    @FXML
    private void AfficherUser(Event event) {
                SUser SU = new SUser();
        TFNbr.clear();
        ObservableList<User> items = FXCollections.observableArrayList(SU.afficher());
        USerName.setCellValueFactory(new PropertyValueFactory<>("UserName") );
        Password.setCellValueFactory(new PropertyValueFactory<>("Password") );
        Email.setCellValueFactory(new PropertyValueFactory<>("Email") );
        Image.setCellValueFactory(new PropertyValueFactory<>("imageview") );
        Role.setCellValueFactory(new PropertyValueFactory<>("Role") );
        Birthday.setCellValueFactory(new PropertyValueFactory<>("Birthday") );
        TableUser.setItems(items);
    }

    @FXML
    private void AjouterUser(ActionEvent event) {
                 Tooltip TypeUserNAme = new Tooltip();
            if(!TFUserName.getText().equals("")){
                          if (!TFPassword.getText().equals("")) {
                if (!TFEmail.getText().equals("")) {
                    if (!CBRole.getValue().isEmpty()) {
                    SUser SU = new SUser();
                    if (TFEmail.getText().contains("@"))
                    {
                    java.sql.Date sqlDate = java.sql.Date.valueOf(TFBirthday.getValue());
                        User u = new User (TFUserName.getText(), TFPassword.getText(), TFEmail.getText(), TFImage.getText(),CBRole.getValue(),sqlDate); 
                        JOptionPane.showMessageDialog(null,SU.ExistanceB(u));
                        if(!SU.ExistanceB(u))
                        {             
                        SU.ajouterUser(u);
                        TFUserName.clear();
                        TFPassword.clear();
                        TFEmail.clear();
                        TFImage.clear();
                        TFBirthday.getEditor().clear();
                        CBRole.getItems().clear();
                        ObservableList<String> Role = FXCollections.observableArrayList("Organisateur","Participant");
                        CBRole.setItems(Role);
                        }
                        else
                        JOptionPane.showMessageDialog(null, "User exist, you must choose an other Username");      
                    }
                    else
             JOptionPane.showMessageDialog(null, "Email must contain @ ","Alert",JOptionPane.WARNING_MESSAGE);
                           
                    }
                    else 
                    JOptionPane.showMessageDialog(null, "Could you please, insert the role","Alert",JOptionPane.WARNING_MESSAGE);
                }     
                else            
                JOptionPane.showMessageDialog(null, "Could you please, insert your email","Alert",JOptionPane.WARNING_MESSAGE);
            }
            else 
            JOptionPane.showMessageDialog(null, "Could you please, insert your password","Alert",JOptionPane.WARNING_MESSAGE);   
        }
        else
            {
           JOptionPane.showMessageDialog(null, "Could you please, insert your Username","Alert",JOptionPane.WARNING_MESSAGE);
            }
        //JOptionPane.showMessageDialog(null, "Vous devez insére le nom","Alert",JOptionPane.WARNING_MESSAGE); 
        
        


    }


     /*       try {
                request.setMethod(Method.POST);
                request.setEndpoint("mail/send");
                request.setBody(mail.build());
                Response response = sg.api(request);
                System.out.println(response.getStatusCode());
                System.out.println(response.getBody());
                System.out.println(response.getHeaders());
            } catch (IOException ex) {
                Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            }*/

  


    @FXML
    private void uploadfile(ActionEvent event) throws IOException {
        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files","*.jpg"));
        //fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        //System.out.println("test boutton upload");

        file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            TFImage.setText("D:\\TESTJAVA\\GestionUser\\src\\com\\esprit\\Pictures\\"+file.getName());
            javafx.scene.image.Image image = new javafx.scene.image.Image(file.toURI().toString(), 800, 200, true, true);
            ImageUser.setImage(image);
            BufferedImage imagee = ImageIO.read(file);
            ImageIO.write(imagee, "jpg",new File("D:\\TESTJAVA\\GestionUser\\src\\com\\esprit\\Pictures\\" + file.getName()));
        }
    }

    @FXML
    private void Supprimer(ActionEvent event) {
            SUser U = new SUser();
            int a=JOptionPane.showConfirmDialog(null,"Are you sure you want to delete this User?");  
            if(a==JOptionPane.YES_OPTION){  
                      
            U.supprimer(CBSupprimer.getValue());
            CBSupprimer.getItems().clear();
            JOptionPane.showMessageDialog(null, "User Deleted");
            } 
            CBSupprimer.getItems().clear();
            ObservableList<String> Id = FXCollections.observableArrayList(U.afficherALLUserName());
            CBSupprimer.setItems(Id);
    }

    @FXML
    private void UserPage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/User.fxml"));
        Parent root = loader.load();
        TFUserName.getScene().setRoot(root);
        UserController UC = loader.getController();
        SUser U = new SUser();
        UC.setHELLOUSER(U.RechercheUserOnline().getUserName());
        UC.setPhotoUser(U.RechercheUserOnline().getImageUser());
    }

    @FXML
    private void LogOut(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/Login.fxml"));
        Parent root = loader.load();
        TFUserName.getScene().setRoot(root);
        SUser U = new SUser();
        U.modifierOnlineOFF();
        JOptionPane.showMessageDialog(null,"Log out successfully ");
    }

    @FXML
    private void SettingUser(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/SettingUser.fxml"));
        Parent root = loader.load();
        TFUserName.getScene().setRoot(root);
        UserController UC = loader.getController();
        SUser U = new SUser();
        UC.setHELLOUSER(U.RechercheUserOnline().getUserName());
        UC.setPhotoUser(U.RechercheUserOnline().getImageUser());
    }
    
}
