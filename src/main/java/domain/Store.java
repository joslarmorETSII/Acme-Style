package domain;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
@Access(AccessType.PROPERTY)
public class Store extends DomainEntity{


    // Constructors ----------------------------------------------------------------------

    public Store() {super(); }


    // Attributes ------------------------------------------------------------------------

    private String title;
    private String banner;
    private CreditCard creditCard;
    private GpsCoordinates gpsCoordinates;

    @NotBlank
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @URL
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    @Valid
    @NotNull
    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    @Valid
    @NotNull
    public GpsCoordinates getGpsCoordinates() {
        return gpsCoordinates;
    }

    public void setGpsCoordinates(GpsCoordinates gpsCoordinates) {
        this.gpsCoordinates = gpsCoordinates;
    }

    // Relationships ----------------------------------------------------------------------
    private Manager manager;
    private Collection<Servise> servises;
    private Collection<Event> events;

    @Valid
    @ManyToOne(optional = false)
    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    @Valid
    @NotNull
    @ManyToMany
    public Collection<Servise> getServises() {
        return servises;
    }

    public void setServises(Collection<Servise> servises) {
        this.servises = servises;
    }
    @Valid
    @NotNull
    @OneToMany
    public Collection<Event> getEvents() {
        return events;
    }

    public void setEvents(Collection<Event> events) {
        this.events = events;
    }
}
