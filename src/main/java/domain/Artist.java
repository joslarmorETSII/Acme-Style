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

    private Collection<Servise> servises;


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
