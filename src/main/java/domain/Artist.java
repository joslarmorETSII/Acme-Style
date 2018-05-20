package domain;

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

    private Collection<Servise> services;


    @NotNull
    @Valid
    @OneToMany(mappedBy = "creator")
    public Collection<Servise> getServices() {
        return services;
    }

    public void setServices(Collection<Servise> services) {
        this.services = services;
    }


}
