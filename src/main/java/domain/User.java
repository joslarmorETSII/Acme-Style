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
    private Collection<Event> events;
    private Collection<Panel> panels;

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
    @ManyToMany
    public Collection<Event> getEvents() {
        return events;
    }

    public void setEvents(Collection<Event> events) {
        this.events = events;
    }
}
