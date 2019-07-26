package matt.onlineDiary.ents;

import java.util.Calendar;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import matt.onlineDiary.ents.User;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-07-26T00:52:42")
@StaticMetamodel(Appointment.class)
public class Appointment_ { 

    public static volatile SingularAttribute<Appointment, Calendar> endDate;
    public static volatile SingularAttribute<Appointment, String> description;
    public static volatile SingularAttribute<Appointment, User> admin;
    public static volatile SingularAttribute<Appointment, Long> id;
    public static volatile SingularAttribute<Appointment, String> title;
    public static volatile SingularAttribute<Appointment, Calendar> startDate;
    public static volatile ListAttribute<Appointment, User> peopleInvolved;

}