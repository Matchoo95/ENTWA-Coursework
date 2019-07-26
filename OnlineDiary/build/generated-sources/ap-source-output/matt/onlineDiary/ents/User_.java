package matt.onlineDiary.ents;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import matt.onlineDiary.ents.Appointment;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-07-26T00:52:42")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, String> firstName;
    public static volatile SingularAttribute<User, String> lastName;
    public static volatile SingularAttribute<User, String> password;
    public static volatile SingularAttribute<User, String> address;
    public static volatile SingularAttribute<User, String> phoneNumber;
    public static volatile SingularAttribute<User, String> postcode;
    public static volatile SingularAttribute<User, String> confirmPassword;
    public static volatile SingularAttribute<User, Appointment> appointment;
    public static volatile SingularAttribute<User, Long> id;
    public static volatile SingularAttribute<User, String> email;
    public static volatile SingularAttribute<User, String> username;

}