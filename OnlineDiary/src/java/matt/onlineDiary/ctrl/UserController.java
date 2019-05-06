/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matt.onlineDiary.ctrl;

import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import matt.onlineDiary.ents.User;
import matt.onlineDiary.bus.UserService;
import matt.onlineDiary.except.IncorrectPasswordException;
import matt.onlineDiary.except.NotAUserException;
import matt.onlineDiary.pers.UserFacade;
/**
 *
 * @author Matthew Hawkins
 */
@Named(value = "userController")
@RequestScoped
public class UserController  {
    
    private UserService uS; 
    @EJB
    private UserFacade uF;
    private User editUser;
    private List<User> searchResults;
    private String searchText;
    private User loggingInUser = new User();

    public boolean userExists(String username, boolean message, String element) {
        if ((this.editUser.getId() == null && uS.userExists(username)) || (uS.userExists(username)
                && (!uS.getUser(username).equals(this.editUser)))) { 
            if (message) {                 
                        // tell user this account already exists
            }
            return true;
        }
        return false; 
    }
    
    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }
    
    public List<User> getSearchResults() {
        return searchResults;
    }

    public void setSearchResults(List<User> searchResults) {
        this.searchResults = searchResults;
    }

    
    public String doSearchClient(String searchText) {
        this.setSearchText(searchText);
        this.setSearchResults(uS.searchForUser(searchText));
        return ""; 
    }   
    
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
    
    public String goToEditUser(User user) {
        this.setEditUser(user);
        return "editUser";
    }
    
        public String goToViewUser(User user) {
        this.setEditUser(user);
        return "viewUser"; 
    }

    public String doDeleteUser(User user) {
        uS.removeUser(user);
        this.clearEditUser();
        this.doSearchUser(this.searchText);
        return "";
    }

    public String doSearchUser( String searchText) {
        this.setSearchText(searchText);        
        this.setSearchResults(uS.searchForUser(searchText));
        return ""; 
}

    public User clearEditUser() {
        this.editUser = new User();
        return this.editUser;
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
    
    public String doAddClient(String password, String passwordAgain) {
        if (!password.equals(passwordAgain)) { 
            // tell user passwords are incorrect
            return "editUser";
        }
        this.editUser.setPassword(password); 
        uS.createNewUser(this.editUser); 
        this.clearEditUser(); 
        return "users?faces-redirect=true";
    }
    
    public String doEditUser(String currentPassword, String newPassword, String passwordAgain) throws IncorrectPasswordException, NotAUserException {
        if (!newPassword.equals("")) { 
            if (!newPassword.equals(passwordAgain)) { 
                return "editUser"; 
            }
            this.editUser.setPassword(newPassword);
        }
        uS.editUser(this.editUser, currentPassword);
        this.clearEditUser();
        return "users?faces-redirect=true"; 
    }
    
    public List<User> getAllUsers(){
        return uS.getAll();
    } 
    
    public UserFacade getFacade(){
        return uF;
    }
        
}
