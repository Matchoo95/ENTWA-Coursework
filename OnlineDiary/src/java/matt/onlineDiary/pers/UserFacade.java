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
        "SELECT u FROM User u WHERE LOWER(u.username) LIKE :username OR"
                + " LOWER(u.firstName) LIKE :firstName OR LOWER(u.lastName)"
                + " LIKE :lastName OR LOWER(user.address) LIKE :address OR"
                + " LOWER(u.postcode) LIKE :postcode OR LOWER(u.phone)"
                + " LIKE :phone OR LOWER(u.email) LIKE :email")                
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
                .setParameter("username", username).setMaxResults(1).getResultList();
        if (users.isEmpty())
            return null; 
        return users.get(0);
    }
            
    public User findUserAccount(String username, String password) {
        List<User> users = this.getEntityManager().createQuery(
                "SELECT u FROM User u WHERE (u.username = :username AND u.password = :password)"
                + "OR(u.email = :username AND u.password = :password)")
                .setParameter("username", username).setParameter("username", username)
                .setMaxResults(1).getResultList();
        if (users.isEmpty())
            return null; 
        return users.get(0);        
    }
    
}
