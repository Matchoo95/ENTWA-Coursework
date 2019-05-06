/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matt.onlineDiary.except;

import matt.onlineDiary.ents.Appointment;

/**
 *
 * @author Matthew Hawkins
 */
public class EndStartTimeException extends AppointmentException {

    public EndStartTimeException(Appointment appointment) {
        super(appointment);
    }

    @Override
    public String getMessage() {
        return "An appointment's End time cannot be before it's Start time.";
    }
}
