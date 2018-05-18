package domain;

import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.SafeHtml;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Feedback extends DomainEntity {

    // Constructors ----------------------------------------------------------------------
    public Feedback() {super();}

    // Attributes ------------------------------------------------------------------------
    private String text;
    private int points;

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
