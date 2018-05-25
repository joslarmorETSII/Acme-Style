package domain;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.URL;
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
    private Date celebrationDate;
    private GpsCoordinates location;
    private String image;

    @NotBlank
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(length = 512)
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

    @NotNull
    @Future
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    public Date getCelebrationDate() {
        return celebrationDate;
    }

    public void setCelebrationDate(Date celebrationDate) {
        this.celebrationDate = celebrationDate;
    }

    @Valid
    @NotNull
    public GpsCoordinates getLocation() {
        return location;
    }

    public void setLocation(GpsCoordinates location) {
        this.location = location;
    }

    @URL
    @NotBlank
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    // Relationships ----------------------------------------------------------------------
    private Manager manager;
    private Collection<Participate> participates;
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
    @OneToMany(mappedBy = "event")
    public Collection<Participate> getParticipates() {
        return participates;
    }

    public void setParticipates(Collection<Participate> participates) {
        this.participates = participates;
    }

    @Valid
    @ManyToOne
    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}
