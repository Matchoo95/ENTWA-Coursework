/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matt.onlineDiary.ctrl;

import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import matt.onlineDiary.ents.Appointment;
import matt.onlineDiary.bus.AppointmentService;

/**
 *
 * @author Matthew Hawkins
 */
@Named(value = "appointmentController")
@RequestScoped

public class AppointmentController {

    /**
     * Creates a new instance of AppointmentController class
     */
    public AppointmentController() {
    }
    @EJB
    private AppointmentService aC;
    private Appointment newAppointment = new Appointment();
    private List<Appointment> allAppointments = null;
    
    public List<Appointment> getAllAppointments(){
        allAppointments = aC.getAllAppointments();
        return allAppointments;
    }
    
    public void setAllAppointments(List<Appointment> allAppointments){
        this.allAppointments = allAppointments;
    }
    
    public Appointment getNewAppointment() {
        return newAppointment;
    }
    
    public void setNewAppointment(Appointment newAppointment) {
        this.newAppointment = newAppointment;
    }
    
    public String doInsertAppointment(){
        aC.createNewAppointment(newAppointment);
        return "";   
    }    
}
