/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matt.onlineDiary.pers;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;
import matt.onlineDiary.ents.Appointment;
import matt.onlineDiary.ents.User;

/**
 *
 * @author Matthew Hawkins
 */
@Stateless
public class AppointmentFacade extends AbstractFacade<Appointment> {

    @PersistenceContext(unitName = "OnlineDiaryPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AppointmentFacade() {
        super(Appointment.class);
    }

    public List<Appointment> search(User user) {
        List<Appointment> appointments;

        appointments = this.getEntityManager().createQuery(
                "SELECT appointment FROM Appointment appointment WHERE :user "
                    + "MEMBER OF appointment.peopleInvolved").setParameter("user", user)
                    .getResultList();

        if (appointments.isEmpty()) 
            return null; 
        return appointments; 
    }

    public List<Appointment> search(Date date) {
        List<Appointment> appointments;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 1);

        appointments = this.getEntityManager().createQuery(
                "SELECT appointment FROM Appointment appointment WHERE "
                    + "appointment.startTime >= :date AND appointment.startTime "
                    + "< :dateAfter")
                .setParameter("date", date, TemporalType.DATE)
                .setParameter("dateAfter", calendar.getTime(), TemporalType.DATE)
                .getResultList();

        if (appointments.isEmpty()) 
            return null; 
        return appointments; 
    }
    
public List<Appointment> search(User user, Appointment appointment) {
    List<Appointment> appointments;
    if (appointment.getId() != null) { 
        appointments = this.getEntityManager().createQuery(
                "SELECT appointment FROM Appointment appointment WHERE (appointment.startTime < :end) "
                        + "AND (appointment.endTime > :start) AND :user MEMBER OF "
                        + "appointment.peopleInvolved AND appointment != :appointment")
                .setParameter("start", appointment.getStartDate(), TemporalType.TIMESTAMP)
                .setParameter("end", appointment.getEndDate(), TemporalType.TIMESTAMP)
                .setParameter("user", user)
                .setParameter("appointment", appointment)
                .getResultList();
    } else { 
        appointments = this.getEntityManager().createQuery(
                "SELECT appointment FROM Appointment appointment WHERE (appointment.startTime < :end)"
                        + " AND (appointment.endTime > :start) AND :user MEMBER OF "
                        + "appointment.peopleInvolved")
                .setParameter("start", appointment.getStartDate(), TemporalType.TIMESTAMP)
                .setParameter("end", appointment.getEndDate(), TemporalType.TIMESTAMP)
                .setParameter("user", user)
                .getResultList();
    }

    if (appointments.isEmpty()) 
        return null; 
    return appointments; 
    }    
}
