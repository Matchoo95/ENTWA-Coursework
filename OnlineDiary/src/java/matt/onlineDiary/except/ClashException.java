/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matt.onlineDiary.except;

import matt.onlineDiary.ents.Appointment;


public class ClashException extends AppointmentException {

    public ClashException(Appointment appointment) {
        super(appointment);
    }

    @Override
    public String getMessage() {
        return "One or more of the people involved with this appointment already"
                + "have an appointment at this time.";
    }
}
