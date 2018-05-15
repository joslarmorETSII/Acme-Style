package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import java.util.Collection;

@Entity
@Access(AccessType.PROPERTY)
public class Manager  extends Actor{

    // Constructors ----------------------------------------------------------------------

    public Manager() { super();}

    // Attributes ------------------------------------------------------------------------



    // Relationships ----------------------------------------------------------------------

    private Collection<Store> stores;
    private Collection<Event> events;



}
