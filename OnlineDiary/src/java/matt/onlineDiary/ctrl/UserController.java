/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matt.onlineDiary.ctrl;

import javax.ejb.EJB;
import javax.inject.Named;
import matt.onlineDiary.ents.User;
import matt.onlineDiary.bus.UserService;
import matt.onlineDiary.pers.UserFacade;
/**
 *
 * @author Matthew Hawkins
 */
@Named(value = "userController")
@RequestScoped
public class UserController {
    private static final long serialVersionUID = 1L;
    @EJB
    private UserService uS; 
    @EJB
    private UserFacade cF;
    private User editUser;

    private User loggingInUser = new User();
    
    public User getLoggingInUser() {
        return loggingInUser;
    }

    public void setLoggingInUser(User loggingInUser) {
        this.loggingInUser = loggingInUser;
    }

    public String doUserLogin(){
        if(uS.checkUser(loggingInUser)) {
            return "index?faces-redirect=true";
        }
        return "";
    } 

    public String addUser(String password, String confirmPassword){
        if(!password.equals(confirmPassword)) {
            return "incorrect?faces-redirect=true";
        }
        this.editUser.setPassword(password);
        return "loggedin?faces-redirect=true";
    }
    

        
    public User getEditUser() {
        return this.editUser;
    }
        
    public void setEditUser(User editUser) {
        this.editUser = editUser;
    }
        
    public UserController() {
        this.editUser = new User();
    }
        
        
    public User clearEditUser(){
        this.editUser = new User();
        return this.editUser;
    }
        
}
