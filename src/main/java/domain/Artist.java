package domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
@Access(AccessType.PROPERTY)
public class Artist extends Actor{

    // Constructors ----------------------------------------------------------------------

    public Artist() { super();}

    // Attributes ------------------------------------------------------------------------



    // Relationships ----------------------------------------------------------------------

    private Collection<Servise> servises;
    private Collection<Event> events;

    @Valid
    @NotNull
    @ManyToMany(mappedBy = "artists")
    public Collection<Event> getEvents() {
        return events;
    }

    public void setEvents(Collection<Event> events) {
        this.events = events;
    }

    @NotNull
    @Valid
    @OneToMany(mappedBy = "creator")
    public Collection<Servise> getServises() {
        return servises;
    }

    public void setServises(Collection<Servise> servises) {
        this.servises = servises;
    }


}
