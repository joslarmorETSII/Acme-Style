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
    private Profile profile;

    @NotNull
    @Valid
    @OneToMany(mappedBy = "creator")
    public Collection<Servise> getServices() {
        return services;
    }

    public void setServices(Collection<Servise> services) {
        this.services = services;
    }

    @Valid
    @OneToOne
    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}
