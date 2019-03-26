/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matt.onlineDiary.pers;

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
}
