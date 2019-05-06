package matt.onlineDiary.ctrl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import matt.onlineDiary.bus.UserService;
import matt.onlineDiary.ents.User;
import matt.onlineDiary.bus.AppointmentService;
import matt.onlineDiary.ents.Appointment;
import matt.onlineDiary.except.ClashException;
import matt.onlineDiary.except.EndStartTimeException;

/**
 * @author Matthew Hawkins
 */
@Named(value = "startController")
@RequestScoped
public class StartController {

    private UserService uS;
    @EJB
    private AppointmentService aS;

    public String doSetup() {
        if (!uS.userExists("admin")) { 
            
            // generate users
            User user = new User();
            user.setUsername("admin");
            user.setPassword("admin");
            user.setFirstName("admin");
            user.setLastName("admin");
            user.setAddress("admin");
            user.setPostcode("PO1 0AA");
            user.setPhoneNumber("01111111111");
            user.setEmail("admin@admin.co.uk");            
            User user2 = new User();
            user2.setUsername("MattHawk");
            user2.setPassword("MattHawkins");
            user2.setFirstName("Matthew");
            user2.setLastName("Hawkins");
            user2.setAddress("Portsmouth");
            user2.setPostcode("PO1 0AA");
            user2.setPhoneNumber("011 1111 1111");
            user2.setEmail("johnsmith@johnsmith.co.uk");          

            uS.createNewUser(user);
            uS.createNewUser(user2);
            
            // generate appointments
            Appointment appointment = new Appointment();
            appointment.setAdmin(user2);
            appointment.setDescription("Christmas");
            ArrayList<User> peopleInvolved = new ArrayList<>();
            peopleInvolved.add(user2);
            appointment.setPeopleInvolved(peopleInvolved);
            Calendar start = Calendar.getInstance();
            start.set(Calendar.YEAR, 2019);
            start.set(Calendar.MONTH, Calendar.MAY);
            start.set(Calendar.DAY_OF_MONTH, 12);
            start.set(Calendar.HOUR_OF_DAY, 15);
            start.set(Calendar.MINUTE, 0);
            appointment.setStartDate(start);
            Calendar end = (Calendar) start.clone();
            end.set(Calendar.MINUTE, 1);
            appointment.setEndDate(end);
            
            Appointment appointment2 = new Appointment();
            appointment2.setAdmin(user2);
            appointment2.setDescription("Christmas");
            ArrayList<User> peopleInvolved2 = new ArrayList<>();
            peopleInvolved2.add(user2);
            appointment.setPeopleInvolved(peopleInvolved2);
            Calendar start2 = Calendar.getInstance();
            start2.set(Calendar.YEAR, 2019);
            start2.set(Calendar.MONTH, Calendar.MAY);
            start2.set(Calendar.DAY_OF_MONTH, 18);
            start2.set(Calendar.HOUR_OF_DAY, 10);
            start2.set(Calendar.MINUTE, 0);
            appointment.setStartDate(start);
            Calendar endDate = (Calendar) start.clone();
            end.set(Calendar.MINUTE, 1);
            appointment.setEndDate(endDate);
        
            try {
                aS.createNewAppointment(appointment);
                aS.createNewAppointment(appointment2);
            } catch (ClashException | EndStartTimeException ex) {
                Logger.getLogger(StartController.class.getName()).log(Level.SEVERE, null, ex);
            }
            return "start"; 
        }
        return "start"; 
    }

    public String goToHome() {
        return "index";
    }

    public String goToUsers() {
        return "user/users";
    }
    
    public String goToAddUser() {
        return "editUser";
    }

    public String goToBrowseUsers() {
        return "browseUsers";
    }

    public String goToAppointments() {
        return "appointment/appointments";
    }

    public String goToCreateAppointment() {
        return "createEditAppointment";
    }
    
    public String goToBrowseAppointments() {
        return "browseAppointments";
    }

    public String goToAppointmentsByUser() {
        return "appointmentsByUser";
    }

    public String goToAppointmentsByDate() {
        return "appointmentsByDate";
    }

    public String goToAppointmentsCalendar() {
        return "appointmentsCalendar";
    }
}
