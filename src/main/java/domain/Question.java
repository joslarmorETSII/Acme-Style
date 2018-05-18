package domain;

import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Date;

@Entity
@Access(AccessType.PROPERTY)
public class Question extends DomainEntity{

    // Constructors ----------------------------------------------------------------------

    public Question() { super();}

    // Attributes ------------------------------------------------------------------------

    private String text;
    private Date moment;

    @NotNull
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    public Date getMoment() {
        return moment;
    }

    public void setMoment(Date moment) {
        this.moment = moment;
    }

    // Relationships ---------------------------------------------------------------------
    private Servise service;
    private Collection<Answer> answers;

    @ManyToOne(optional = false)
    public Servise getService() {
        return service;
    }

    public void setService(Servise service) {
        this.service = service;
    }
    @OneToMany(mappedBy = "question")
    public Collection<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(Collection<Answer> answers) {
        this.answers = answers;
    }
}
