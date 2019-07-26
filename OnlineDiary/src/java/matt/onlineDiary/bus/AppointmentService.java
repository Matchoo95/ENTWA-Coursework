/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matt.onlineDiary.bus;

import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import matt.onlineDiary.ents.Appointment;
import matt.onlineDiary.ents.User;
import matt.onlineDiary.pers.AppointmentFacade;
import matt.onlineDiary.except.EndStartTimeException;
import matt.onlineDiary.except.ClashException;

/**
 *
 * @author Matthew Hawkins
 */
@Stateless
public class AppointmentService {
    @EJB
    private AppointmentFacade aF;

    /**
     *
     * @param appointment
     * @return
     */
    public Appointment appointmentToEdit(Appointment appointment) {
        aF.edit(appointment);
        return appointment;
    }

    /**
     *
     * @param appointment
     * @return
     */
    public Appointment getAppointment(Appointment appointment) {
        return aF.find(appointment.getId());
    }

    /**
     *
     * @param appointment
     * @return
     * @throws EndStartTimeException
     * @throws ClashException
     */
    public Appointment createNewAppointment(Appointment appointment) throws EndStartTimeException, ClashException {
        if (appointment.getEndDate().before(appointment.getStartDate())) {
            throw new EndStartTimeException(appointment);
        }        
        if (!this.checkPeopleInvolved(appointment)) { 
            throw new ClashException(appointment);
        }
        aF.create(appointment);
        return appointment;       
    }
    
    /**
     *
     * @param appointment
     * @return
     */
    public Appointment removeAppointment(Appointment appointment) {
        aF.remove(appointment);
        return appointment;
    }

    /**
     *
     * @param user
     * @return
     */
    public List<Appointment> searchForAppointment(User user) {
        return aF.search(user);
    }

    /**
     *
     * @param date
     * @return
     */
    public List<Appointment> searchForAppointment(Date date) {
        return aF.search(date);
    }

    /**
     *
     * @return
     */
    public List<Appointment> getAllAppointments() {
        return aF.findAll();
    }

    private boolean checkPeopleInvolved(Appointment appointment) {
        for (User user : appointment.getPeopleInvolved()) { 
            if (aF.search(user, appointment) != null) { 
                return false;
            }
        }
        return true;
    }
}
