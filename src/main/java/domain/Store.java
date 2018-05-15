package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

@Entity
@Access(AccessType.PROPERTY)
public class Store extends DomainEntity{


    // Constructors ----------------------------------------------------------------------

    public Store() {super(); }


    // Attributes ------------------------------------------------------------------------



    // Relationships ----------------------------------------------------------------------
    private Manager manager;
}
