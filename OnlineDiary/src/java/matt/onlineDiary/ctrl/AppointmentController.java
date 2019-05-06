/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matt.onlineDiary.ctrl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import matt.onlineDiary.ents.Appointment;
import matt.onlineDiary.bus.AppointmentService;
import matt.onlineDiary.bus.UserService;
import matt.onlineDiary.ents.User;
import matt.onlineDiary.except.ClashException;
import matt.onlineDiary.except.EndStartTimeException;
import matt.onlineDiary.pers.AppointmentFacade;

/**
 *
 * @author Matthew Hawkins
 */

@Named(value = "appointmentController")
@RequestScoped
public class AppointmentController {
   
    @EJB
    private AppointmentService aS;
    private UserService uS;
    @EJB
    private AppointmentFacade aF;
    private Appointment appointmentToEdit;
    private List<String> peopleInvolved;    
    private List<Appointment> searchResults;
    private String searchUser;
    private Date searchDate;    
        
    public List<Appointment> getAppointmentsForDate(int day){
        final Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, day);
        final Date date = calendar.getTime();
        return aS.searchForAppointment(date);
    }
    
    
    public List<Appointment> getSearchResults() {
        return searchResults;
    }
    
    public void setSearchResults(List<Appointment> searchResults) {
        this.searchResults = searchResults;
    }
    
    public Date getSearchDate() {
        return searchDate;
    }
    
    public void setSearchDate(Date searchDate) {
        this.searchDate = searchDate;
    }
    
    public String getSearchUser() {
        return searchUser;
    }
    
    public void setSearchUser(String searchUser) {
        this.searchUser = searchUser;
        this.setSearchResults(aS.searchForAppointment(uS.getUser(this.searchUser)));
    }
    
    public List<String> getPeopleInvolved() {
        return peopleInvolved;
    }

    public void setPeopleInvolved(List<String> peopleInvolved) {
        this.peopleInvolved = peopleInvolved;
    }    
    
    public AppointmentController() {
        this.appointmentToEdit = new Appointment(); 
        this.peopleInvolved = new ArrayList<>();
    }
    
        public Appointment clearAppointmentToEdit() {
        this.appointmentToEdit = new Appointment(); 
        this.peopleInvolved.clear();
        return this.appointmentToEdit;
    }

    public Appointment getAppointmentToEdit() {
        return appointmentToEdit;
    }
    
    public void setAppointmentToEdit(Appointment appointmentToEdit) {
        this.appointmentToEdit = appointmentToEdit;
        if (this.appointmentToEdit.getId() != null) {
            this.peopleInvolved = this.usersToUsernames(this.appointmentToEdit.getPeopleInvolved());
        }
    }
    
    public String doCreateAppointment(User admin) throws EndStartTimeException {
        this.appointmentToEdit.setAdmin(admin); 
        this.appointmentToEdit.setPeopleInvolved(this.usernamesToUsers(this.peopleInvolved));
        
        try {
            aS.createNewAppointment(this.appointmentToEdit);
        } catch (ClashException ex) {
            Logger.getLogger(AppointmentController.class.getName()).log(Level.SEVERE, null, ex);
            return "editAppointment"; 
        } catch (EndStartTimeException ex) {
            Logger.getLogger(AppointmentController.class.getName()).log(Level.SEVERE, null, ex);
            return "editAppointment"; 
        }
        this.clearAppointmentToEdit(); 
        return "appointments?faces-redirect=true"; 
    }


    public String doAppointmentToEdit(User admin) {
        this.appointmentToEdit.setAdmin(admin); 
        this.appointmentToEdit.setPeopleInvolved(this.usernamesToUsers(this.peopleInvolved));
        aS.appointmentToEdit(this.appointmentToEdit);
        this.clearAppointmentToEdit(); 
        return "appointments?faces-redirect=true"; 
    }

    public List<User> usernamesToUsers(List<String> usernames) {
        ArrayList<User> users = new ArrayList<>();

        for (String username : usernames) { 
            users.add(uS.getUser(username)); 
        }

        return users;
    }

    public List<String> usersToUsernames(List<User> users) {
        ArrayList<String> usernames = new ArrayList<>();

        users.forEach((user) -> { 
            usernames.add(user.toString());
        });
        return usernames;
    }

    public List<Appointment> getAllAppointments() {
        return aS.getAllAppointments();
    }

    public String goToEditAppointment(Appointment appointment) {
        this.setAppointmentToEdit(appointment);
        this.setPeopleInvolved(this.usersToUsernames(this.appointmentToEdit.getPeopleInvolved()));
        return "editAppointment";
    }

    public String doSearchAppointment(String searchText) {
        this.setSearchUser(searchText);
        return ""; 
    }

    /**
     * Search for appointments by date
     */
    public String doSearchAppointment() {
        this.setSearchResults(aS.searchForAppointment(this.searchDate));
        return ""; 
    }

    /**
     * Delete appointment
     *
     * @param appointment appointment to delete
     * @param page view that this method call originated from
     * @return new view
     */
    public String doDeleteAppointment(Appointment appointment, String page) {
        aS.removeAppointment(appointment);
        this.clearAppointmentToEdit();
        if (page.equals("day")) { 
            this.doSearchAppointment(); 
        }
        this.doSearchAppointment(this.searchUser); 
        return ""; 
    }

    /**
     * Go to appointment view
     *
     * @param appointment appointment to view
     * @return new view
     */
    public String goToAppointmentView(Appointment appointment) {
        this.setAppointmentToEdit(appointment);
        return "viewAppointment"; 
    }
    
    public AppointmentFacade getFacade() {
        return aF;
    }

}