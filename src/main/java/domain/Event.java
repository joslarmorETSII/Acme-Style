package domain;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Date;

@Entity
@Access(AccessType.PROPERTY)
public class Event extends DomainEntity{

    // Constructors ----------------------------------------------------------------------

    public Event() {super(); }


    // Attributes ------------------------------------------------------------------------
    private String title;
    private String description;
    private Date moment;
    private String tipo;
    private Double price;
    private Date publicationDate;
    private GpsCoordinates location;

    @NotBlank
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @NotBlank
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    public Date getMoment() {
        return moment;
    }

    public void setMoment(Date moment) {
        this.moment = moment;
    }

    @NotBlank
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String type) {
        this.tipo = type;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    @Valid
    @NotNull
    public GpsCoordinates getLocation() {
        return location;
    }

    public void setLocation(GpsCoordinates location) {
        this.location = location;
    }

    // Relationships ----------------------------------------------------------------------
    private Manager manager;
    private Collection<User> participants;
    private Store store;

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
    public Collection<User> getParticipants() {
        return participants;
    }

    public void setParticipants(Collection<User> participants) {
        this.participants = participants;
    }

    @Valid
    @NotNull
    @ManyToOne
    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}
