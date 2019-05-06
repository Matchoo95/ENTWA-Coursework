/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matt.onlineDiary.pers;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import matt.onlineDiary.ents.User;
import javax.ejb.Stateless;

/**
 *
 * @author Matthew Hawkins
 */
@Stateless
public class UserFacade extends AbstractFacade<User> {
    @PersistenceContext(unitName = "OnlineDiaryPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public UserFacade() {
        super(User.class);
    }
    
    public List<User> search(String searchText) {
        List<User> users;
        users = this.getEntityManager().createQuery(
        "SELECT user FROM User user WHERE LOWER(user.username) LIKE :username OR"
                + " LOWER(user.firstName) LIKE :firstName OR LOWER(user.lastName)"
                + " LIKE :lastName OR LOWER(user.address) LIKE :address OR"
                + " LOWER(user.postcode) LIKE :postcode OR LOWER(user.phone)"
                + " LIKE :phone OR LOWER(user.email) LIKE :email")                
                .setParameter("username", searchText.toLowerCase())
                .setParameter("firstName", searchText.toLowerCase())
                .setParameter("lastName", searchText.toLowerCase())
                .setParameter("address", searchText.toLowerCase())
                .setParameter("postcode", searchText.toLowerCase())
                .setParameter("phone", searchText.toLowerCase())
                .setParameter("email", searchText.toLowerCase())
                .getResultList();  
        if (users.isEmpty())
            return null;
        return users;
    }
    
    public User findUser(String username) {
        List<User> users = this.getEntityManager().createQuery(
                "SELECT u FROM User u WHERE (u.username = :username)")
                .setParameter("userName", username).setMaxResults(1).getResultList();
        if (users.isEmpty())
            return null; 
        return users.get(0);
    }
            
    public User findUserAccount(String username, String password) {
        List<User> users = this.getEntityManager().createQuery(
                "SELECT u FROM User u WHERE (u.username = :username AND u.password = :password)"
                + "OR(u.email = :username AND u.password = :password)")
                .setParameter("userName", username).setParameter("userName", username)
                .setMaxResults(1).getResultList();
        if (users.isEmpty())
            return null; 
        return users.get(0);        
    }
    
}
