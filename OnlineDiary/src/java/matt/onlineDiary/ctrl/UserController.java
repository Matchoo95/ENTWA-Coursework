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
/**
 *
 * @author Matthew Hawkins
 */
@Named(value = "userController")
@RequestScoped

public class UserController {

    /**
     * Creates a new instance of UserController class
     */
    public UserController() {
    }
    
    @EJB
    private UserService uS;  

    private User newUser = new User();


    // private List<User> allUsers = null;
    
    // public List<User> getAllUsers(){
    //     allUsers = uS.findAllUsers();
    //     return allUsers;
    // }
    
    // public void setAllUsers(List<User> allUsers){
    //     this.allUsers = allUsers;
    //}
    
    public User getNewUser() {
        return newUser;
    }
    
    public void setNewUser(User newUser) {
        this.newUser = newUser;
    }

    private User loggingInUser = new User();
    
    public User getLoggingInUser() {
        return loggingInUser;
    }

    public void setLoggingInUser(User loggingInUser) {
        this.loggingInUser = loggingInUser;
    }

    public String doUserLogin(){
        uS.checkUser(loggingInUser);
        return "";
    }

    public String doAddUser(){
        uS.createNewUser(newUser);
        return "";   
    }    
}
