/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matt.onlineDiary.bus;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import matt.onlineDiary.ents.Appointment;
import matt.onlineDiary.ents.User;
import matt.onlineDiary.pers.AppointmentFacade;
import matt.onlineDiary.pers.UserFacade;
import matt.onlineDiary.except.IncorrectPasswordException;
import matt.onlineDiary.except.NotAUserException;


/**
 *
 * @author Matthew Hawkins
 */
@Stateless
public class UserService { 
    @EJB
    private UserFacade uF;
    @EJB
    private AppointmentFacade aF;

    public boolean checkUser(User loggingInUser){
        return true;
    }
    
    // done but could be better
    public User createNewUser(User u){        
        if (!this.userExists(u.getUsername())){
            uF.create(u);
        }else{
            return null;
        }
        return u;
    }
    
    public List<User> findAllUsers(){
        return uF.findAll();
    }
        
    // done but could be better
    public boolean userExists(String username) {
        return uF.find(username) != null;
    }
    
    // add method to confirm a user's password?
    
    public User validateSignIn(String username, String password) {
        User user = uF.findUserAccount(username, password);
        return user;
    }
    
    public User getUser(User user) {
        return uF.find(user.getId());
    }
    
    public User getUser(String username) {
        return uF.find(username);
    }

    public List<User> searchForUser(String searchText) {
        return uF.search(searchText);
    }
    
    public User removeUser(User user) {
        List<Appointment> appointments = aF.search(user);
        if (appointments != null) { 
            appointments.stream().map((appointment) -> { 
                List<User> newPeopleInvolved = appointment.getPeopleInvolved();
                newPeopleInvolved.remove(user);
                appointment.setPeopleInvolved(newPeopleInvolved);
                return appointment;
            }).forEachOrdered((appointment) -> {
                aF.edit(appointment);
            });
        }
        uF.remove(user);
        return user;
    }

    public User editUser(User user) {
        uF.edit(user);
        return user;
    }
    
    public User editUser(User user, String inputPassword) throws IncorrectPasswordException, NotAUserException {
        if (null != this.checkLogin(user, inputPassword)) {
            uF.edit(user);
        } else {
            throw new IncorrectPasswordException(user);
        }
        return user;
    }

    public User checkLogin(String username, String password) throws NotAUserException {
        User user = uF.findUser(username); 

        if (user != null) { 
            return uF.findUserAccount(username, password);
        }        
    throw new NotAUserException(username);
    }
    
    public User checkLogin(User user, String password) throws NotAUserException {
        User u = uF.find(user.getId());         
        if (u != null) {
            return uF.findUserAccount(user.getUsername(), password);
        }
        throw new NotAUserException(user.getUsername());
    }

    public List<User> getAll() {
        return uF.findAll();
    }  
}