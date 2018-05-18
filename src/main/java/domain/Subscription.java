package domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Access(AccessType.PROPERTY)
public class Subscription extends DomainEntity{

    // Constructors ----------------------------------------------------------------------

    public Subscription() {super(); }

    // Attributes ------------------------------------------------------------------------

    private CreditCard creditCard;
    private Date moment;

    @NotNull
    @Valid
    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    public Date getMoment() {
        return moment;
    }

    public void setMoment(Date moment) {
        this.moment = moment;
    }

    // Relationships ----------------------------------------------------------------------

    private User user;
    private Servise service;

    @Valid
    @ManyToOne(optional = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Valid
    @ManyToOne(optional = false)
    public Servise getService() {
        return service;
    }

    public void setService(Servise service) {
        this.service = service;
    }
}
