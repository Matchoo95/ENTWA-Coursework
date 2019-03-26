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
import matt.onlineDiary.pers.AppointmentFacade;

/**
 *
 * @author Matthew Hawkins
 */
@Stateless
public class AppointmentService {
    @EJB
    private AppointmentFacade aF;

    public Appointment editAppointment(Appointment appointment) {
        aF.edit(appointment);
        return appointment;
    }

   public Appointment getAppointment(Appointment appointment) {
        return aF.find(appointment.getId());
    }

    public Appointment createNewAppointment(Appointment appointment) {
        aF.create(appointment);
        return appointment;
    }
    
    public Appointment removeAppointment(Appointment appointment) {
        aF.remove(appointment);
        return appointment;
    }

//    public List<Appointment> searchAppointment(User u) {
//        return aF.search(u);
//    }
//
//    public List<Appointment> searchAppointment(Date d) {
//        return aF.search(d);
//    }
//    
//    private boolean checkInvolvedUsers(Appointment a) {
//        for (User u : a.getPeopleInvolved()) {
//            if (aF.search(u, a) != null) {
//                return false;
//            }
//        }
//        return true;
//    }

    public List<Appointment> getAllAppointments() {
        return aF.findAll();
    }

}
