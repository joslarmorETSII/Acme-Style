package domain;

import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;

@Entity
@Access(AccessType.PROPERTY)
@Table(indexes = {
        @Index(columnList = "servise_id")
})
public class Feedback extends DomainEntity {

    // Constructors ----------------------------------------------------------------------
    public Feedback() {super();}

    // Attributes ------------------------------------------------------------------------
    private String text;
    private int points;
    private Date moment;

    @NotNull
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Range(min = 0,max = 5)
    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Past
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    public Date getMoment() {
        return moment;
    }

    public void setMoment(Date moment) {
        this.moment = moment;
    }

    // Relationships ---------------------------------------------------------------------
    private User user;
    private Servise servise;

    @ManyToOne(optional = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Valid
    @ManyToOne(optional = false)
    public Servise getServise() {
        return servise;
    }

    public void setServise(Servise servise) {
        this.servise = servise;
    }
}
