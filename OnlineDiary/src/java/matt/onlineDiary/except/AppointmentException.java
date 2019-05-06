package matt.onlineDiary.except;

import matt.onlineDiary.ents.Appointment;

public abstract class AppointmentException extends Exception {
    protected Appointment appointment;

    public AppointmentException(Appointment appointment) {
        super();
        this.appointment = appointment;
    }
}
