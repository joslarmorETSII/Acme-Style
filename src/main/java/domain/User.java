package domain;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Collection;


@Entity
@Access(AccessType.PROPERTY)
public class User extends Actor{

    // Constructors ----------------------------------------------------------------------

    public User() { super();}

    // Attributes ------------------------------------------------------------------------



    // Relationships ----------------------------------------------------------------------

    private Collection<Feedback> feedbacks;
    private Collection<Subscription> subscriptions;
    private Collection<Participate> participates;
    private Collection<Panel> panels;
    private Collection<Raffle> raffles;

    @Valid
    @NotNull
    @OneToMany(mappedBy = "user")
    public Collection<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(Collection<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }

    @Valid
    @NotNull
    @OneToMany(mappedBy = "user")
    public Collection<Subscription> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(Collection<Subscription> subscriptions) {
        this.subscriptions = subscriptions;
    }

    @Valid
    @NotNull
    @OneToMany(mappedBy = "user")
    public Collection<Participate> getParticipates() {
        return participates;
    }

    public void setParticipates(Collection<Participate> participates) {
        this.participates = participates;
    }

    @Valid
    @NotNull
    @OneToMany(mappedBy = "user")
    public Collection<Panel> getPanels() {
        return panels;
    }

    public void setPanels(Collection<Panel> panels) {
        this.panels = panels;
    }

    @Valid
    @NotNull
    @ManyToMany
    public Collection<Raffle> getRaffles() {
        return raffles;
    }

    public void setRaffles(Collection<Raffle> raffles) {
        this.raffles = raffles;
    }
}
