/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matt.onlineDiary.bus;

import java.util.List;
import javax.ejb.EJB;
import matt.onlineDiary.ents.User;
import matt.onlineDiary.pers.AppointmentFacade;
import matt.onlineDiary.pers.UserFacade;

/**
 *
 * @author Matthew Hawkins
 */

public class UserService {
 
    @EJB
    private UserFacade uF;
    @EJB
    private AppointmentFacade aF;

    public boolean checkUser(User loggingInUser){
        return true;
    }
    
    public User createNewUser(User u){
        boolean ok = true;
        
        if (ok){
            uF.create(u);
            return u;
        }else{
            return null;
        }
    }
    
    public List<User> findAllUsers(){
        return uF.findAll();
    }
    
}