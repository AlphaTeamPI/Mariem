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
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;
import com.restfb.FacebookClient.AccessToken;
import com.restfb.Parameter;
import com.restfb.json.JsonObject;
import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.String;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.mail.MessagingException;
import javax.net.ssl.HttpsURLConnection;
import javax.swing.JOptionPane;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * FXML Controller class
 *
 * @author Meriem BEN AOUAYENE
 */
public class LoginController implements Initializable {

    @FXML
    private JFXPasswordField TFPassword;
    @FXML
    private TextField TFUserName;

    private String domain= "https://www.google.com/";
    private String appId= "435369214389488";
    private String appSecret = "ddba4e644aceb92910662d365725ef1d";
    private String State="3569";
    private String authentification ="https://www.facebook.com/v10.0/dialog/oauth?client_id=435369214389488&redirect_uri=https://www.google.com/&state=3569";
    private String graph ="https://graph.facebook.com/v10.0/oauth/access_token?client_id=435369214389488&redirect_uri=https://www.google.com/&client_secret=ddba4e644aceb92910662d365725ef1d&code=";
    @FXML
    private Button BConnecter;
    @FXML
    private Button BCreeAcc;
    @FXML
    private Button BForgotPassword;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                SUser U = new SUser();
        U.modifierOnlineOFF();
        // TODO
    }    

    public TextField getTFUserName() {
        return TFUserName;
    }


     

    @FXML
    private void AjouterParticipant(ActionEvent event) throws IOException {
        //JOptionPane.showMessageDialog(null, "User Ajouter");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/CreeParticipant.fxml"));
        Parent root = loader.load();
        TFUserName.getScene().setRoot(root);//1:09h
    }

    @FXML
    private void logIn(ActionEvent event) throws IOException {
        
        System.out.println(TFUserName);
        
        if(!TFUserName.getText().equals("")){
            if (!TFPassword.getText().equals("")) {
            SUser U = new SUser();
            boolean b=U.ExistanceB(new User(TFUserName.getText(), TFPassword.getText()));
                   
        if (b==false)
        {
                   JOptionPane.showMessageDialog(null, "check ur username and password");
                   TFUserName.clear();
                   TFPassword.clear();
        }
        else 
        {
            User Test = U.Existance(TFUserName.getText(), TFPassword.getText());
        if(Test.getRole().equals(("Admin")))
        {
        JOptionPane.showMessageDialog(null, Test.getRole());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/User.fxml"));
        Parent root = loader.load();
        U.modifierOnlineOn(Test);
        
        TFUserName.getScene().setRoot(root); 
        UserController UC = loader.getController();
        UC.setHELLOUSER(U.RechercheUserOnline().getUserName());
        UC.setPhotoUser(U.RechercheUserOnline().getImageUser());
      //  System.out.println(Test.getUserName());
        }
        else if(Test.getRole().equals(("Organisateur")))
        {
        JOptionPane.showMessageDialog(null, Test.getRole());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/User.fxml"));
        Parent root = loader.load();
        TFUserName.getScene().setRoot(root); 
        UserController UC = loader.getController();
        UC.setHELLOUSER(Test.getUserName());
        UC.setPhotoUser(Test.getImageUser());
        }
        else 
        {
            JOptionPane.showMessageDialog(null, Test.getRole());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/User.fxml"));
        Parent root = loader.load();
        TFUserName.getScene().setRoot(root);
        UserController UC = loader.getController();
        UC.setHELLOUSER(Test.getUserName());
        UC.setPhotoUser(Test.getImageUser());
            
        }
        }
            }
                                
            else 
                JOptionPane.showMessageDialog(null, "Could you please write your Password","Alert",JOptionPane.WARNING_MESSAGE);
        }
        else
            JOptionPane.showMessageDialog(null, "Could you please write your UserName","Alert",JOptionPane.WARNING_MESSAGE); 
        }


    @FXML
    private void AuthFacebook(ActionEvent event) throws IOException {
       
        
        
        if(!TFUserName.getText().equals("")){
            
        SUser U = new SUser();
        User user=U.RechercheID(TFUserName.getText());
        System.out.println(TFUserName.getText());
        U.modifierOnlineOn(user);
        Email email = new Email();
        try {
            email.sendMail(user.getEmail(), "hello", "Your password :"+user.getPasswordOublie());
                    JOptionPane.showMessageDialog(null,"Code sent successfully to  "+user.getEmail());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/Password.fxml"));
        Parent root = loader.load();
        TFUserName.getScene().setRoot(root);
        } catch (MessagingException ex) {
            JOptionPane.showMessageDialog(null,"We could not send you the code to  "+user.getEmail());
          //  Logger.getLogger(PasswordController.class.getName()).log(Level.SEVERE, null, ex);
        }
       /* PasswordController UC = loader.getController();
        UC.setPasswordCodeAverifier(U.RechercheUserOnline().getUserName());*/
        }
                else
            JOptionPane.showMessageDialog(null, "Could you please write your UserName","Alert",JOptionPane.WARNING_MESSAGE);
        
 }

}       
        
        
  /*      
        
        
                String url = "https://api.instagram.com/oauth/access_token";
		URL obj = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

		//add reuqest header
		con.setRequestMethod("POST");
	 	con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

		String urlParameters = "client_id="+commonthings.CLIENT_ID+"&client_secret="+commonthings.CLIENT_SECRET+"&grant_type=authorization_code&redirect_uri="+commonthings.AUTHORIZATION_REDIRECT_URI+"&code="+code+"";

		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		  
		System.out.println(response.toString());
	
		Insta_Profile obj_Insta_Profile=new Insta_Profile();
		
		JSONObject jsonObject = new JSONObject(response.toString());
		
		JSONObject myResponse = jsonObject.getJSONObject("user");
	 	
	 	obj_Insta_Profile.setFull_name(myResponse.getString("full_name"));
		obj_Insta_Profile.setId(myResponse.getString("id"));
		obj_Insta_Profile.setProfile_picture(myResponse.getString("profile_picture"));
		obj_Insta_Profile.setUsername(myResponse.getString("username"));
		 
		
		return obj_Insta_Profile;

        
    /*   WebView webView = new WebView();
       WebEngine eg = webView.getEngine();
       eg.load(authentification);
       Pane wView = new Pane();
       wView.getChildren().add(webView);
       Stage stage = new Stage();
       stage.initModality(Modality.APPLICATION_MODAL);
       stage.setScene(new Scene(wView, 818 , 654));
       stage.show();
       
       
       
       
       eg.locationProperty().addListener((obs,oldlocation,newlocation) ->{
           if(newlocation != null && newlocation.startsWith("https://localhost/")){
                int codeOffset= newlocation.indexOf("code=");
                String code = newlocation.substring(codeOffset + "code=".length());
                //graph += code;
                DefaultFacebookClient facebookClient = new DefaultFacebookClient(Version.LATEST);
                FacebookClient.AccessToken accessToken = facebookClient.obtainUserAccessToken(appId, appSecret, "https://localhost/", code);
                
                String access_token = accessToken.getAccessToken();
                
                FacebookClient fbClient = new DefaultFacebookClient(access_token,Version.LATEST);
                fbClient.createClientWithAccessToken(access_token);
                JsonObject profile_pic = fbClient.fetchObject("me/picture", JsonObject.class, Parameter.with("redirect", "false"));
                User user = fbClient.fetchObject("me", User.class);
                
                int si = profile_pic.toString().indexOf("url\"😕"");
                int ei = profile_pic.toString().indexOf("\",\"");
                String profile_url = profile_pic.toString().substring(si+6, ei);
                
                labelCorrect.setText(user.getName());
                System.out.println(user.getFirstName());

                
                stage.close();
                
                
           }
       }
       );
       

       
        
 
        
        /*System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        WebDriver driver =  new ChromeDriver();
        driver.get(authentification);

        System.out.println("Successfully opened the website");
        driver.manage().window().maximize();
        driver.findElement(By.id("email")).sendKeys("Enter the USERNAME");
        driver.findElement(By.id("pass")).sendKeys("Enter the PASSWORD");
        driver.findElement(By.id("u_0_r")).click();
        System.out.println("Successfully logged in");
        Thread.sleep(3000);
        driver.findElement(By.id("userNavigationLabel")).click();
        Thread.sleep(2000);
        driver.findElement(By.partialLinkText("Log out")).click();
        System.out.println("Successfully logged out");*/
    


/*
   /*     String auth ="https://www.facebook.com/v10.0/dialog/oauth?client_id=435369214389488&redirect_uri=https://www.google.com/&state=3569"
                ;
        String authUrl = "https://graph.facebook.com/oauth/authorize?type=user_agent&client_id="+appId;
           /**     + "graph.facebook.com/oauth/authorize?type=user_agent"
                + "&client_id="+appId
                + "&redirect_uri=https://www.google.com/";
            //    + "&scope=first_name";
        
        
      //  System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        //     JOptionPane.showMessageDialog(null, "Vous devez insére le Mdp","Alert",JOptionPane.WARNING_MESSAGE);
 
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        WebDriver driver =  new ChromeDriver();
        driver.get(authentification);
       // String accessToken;
        
      WebView webView = new WebView();
      WebEngine eg= webView.getEngine();
      //  eg.load(authentification);
        
      /*  Pane wView = new Pane();
        wView.getChildren().add(webView);
        
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(wView));
        stage.show();
      
                DefaultFacebookClient facebookClient = new DefaultFacebookClient(Version.VERSION_10_0);
                FacebookClient.AccessToken accessToken = facebookClient.obtainUserAccessToken(appId, appSecret, code, newLocation);
                        //facebookClient.obtainUserAccessToken(appId, appSecret, domain, code);
                String access_Token = accessToken.getAccessToken();
               // System.out.println(access_Token);
               // JOptionPane.showMessageDialog(null, access_Token,"Alert",JOptionPane.WARNING_MESSAGE);
                FacebookClient fbclient = new DefaultFacebookClient(access_Token,Version.VERSION_10_0);
                fbclient.createClientWithAccessToken(access_Token);
                System.out.println(access_Token);
                JsonObject profile_pic = fbclient.fetchObject("me", JsonObject.class, Parameter.with("redirect", "false"));
                User user = fbclient.fetchObject("me",User.class);
                this.TFUserName.setText(user.getUserName());
                
                
       /* eg.locationProperty().addListener((obs,oldLocation,newLocation)->{
            if (newLocation != null && newLocation.startsWith(domain)){
              //  JOptionPane.showMessageDialog(null, "if","Alert",JOptionPane.WARNING_MESSAGE);
                int codeOffset = newLocation.indexOf("code=");
                String code = newLocation.substring(codeOffset+"code=".length());
                graph +=code;
                System.out.println(graph);
                DefaultFacebookClient facebookClient = new DefaultFacebookClient(Version.VERSION_10_0);
                FacebookClient.AccessToken accessToken = facebookClient.obtainUserAccessToken(appId, appSecret, code, newLocation);
                        //facebookClient.obtainUserAccessToken(appId, appSecret, domain, code);
                String access_Token = accessToken.getAccessToken();
               // System.out.println(access_Token);
               // JOptionPane.showMessageDialog(null, access_Token,"Alert",JOptionPane.WARNING_MESSAGE);
                FacebookClient fbclient = new DefaultFacebookClient(access_Token,Version.VERSION_10_0);
                fbclient.createClientWithAccessToken(access_Token);
                System.out.println(access_Token);
                JsonObject profile_pic = fbclient.fetchObject("me", JsonObject.class, Parameter.with("redirect", "false"));
                User user = fbclient.fetchObject("me",User.class);
                this.TFUserName.setText(user.getUserName());
                
              //  int s1 = 
            
            
            
            }
            
        });

    }   */