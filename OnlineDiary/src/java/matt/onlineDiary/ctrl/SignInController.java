/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matt.onlineDiary.ctrl;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import matt.onlineDiary.bus.UserService;
import matt.onlineDiary.ents.User;
import matt.onlineDiary.pers.AbstractFacade;
import matt.onlineDiary.except.NotAUserException;
/**
 *
 * @author Matthew Hawkins
 */
@Named(value = "signInController")
@RequestScoped
public class SignInController {

    private UserService uS;
    private User signedInUser;
    private String username;
    private String password;

    public User getSignedInUser() {
        return this.signedInUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String doSignIn() {
        User user;
        try {
            user = uS.checkLogin(this.username, this.password);

            if (user != null) { 
                this.signedInUser = user;
                return "welcome?faces-redirect=true";
            } else {
                return "";
            }
        } catch (NotAUserException ex) {
            Logger.getLogger(SignInController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "index";
    }
    
    public String doSignOut() {
        this.signedInUser = null; 
        return "index"; 
    }

    public void viewSignInCheck() throws IOException {
        if (this.signedInUser == null) { 
            ExternalContext eC = FacesContext.getCurrentInstance().getExternalContext();
            eC.redirect(eC.getRequestContextPath() + "/faces/index.xhtml");
        }
    }

    public SignInController() {
        this.username = "";
        this.password = "";
    }

    public AbstractFacade getFacade() {
        return null; 
    }

}
