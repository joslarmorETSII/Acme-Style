package domain;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Date;

@Entity
@Access(AccessType.PROPERTY)
public class Servise extends DomainEntity{
    // Constructors ----------------------------------------------------------------------

    public Servise() { super();}

    // Attributes ------------------------------------------------------------------------

    private String title;
    private String description;
    private Date publicationDate;
    private String picture;
    private boolean taboo;
    private Double price;
    private Double discount;

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
    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    @URL
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public boolean getTaboo() {
        return taboo;
    }

    public void setTaboo(boolean taboo) {
        this.taboo = taboo;
    }

    @NotNull
    @Min(value = 0)
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Range(min = 0,max = 100)
    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }


    // Relationships ---------------------------------------------------------------------
    private Collection<Question> questions;
    private Collection<Subscription> subscriptions;
    private Artist creator;
    private Collection<Store> stores;
    private Collection<Feedback> feedbacks;

    @Valid
    @NotNull
    @OneToMany(mappedBy = "servise")
    public Collection<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Collection<Question> questions) {
        this.questions = questions;
    }

    @Valid
    @NotNull
    @OneToMany(mappedBy = "servise")
    public Collection<Subscription> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(Collection<Subscription> subscriptions) {
        this.subscriptions = subscriptions;
    }

    @Valid
    @ManyToOne(optional = false)
    public Artist getCreator() {
        return creator;
    }

    public void setCreator(Artist creator) {
        this.creator = creator;
    }

    @Valid
    @NotNull
    @ManyToMany
    public Collection<Store> getStores() {
        return stores;
    }

    public void setStores(Collection<Store> stores) {
        this.stores = stores;
    }

    @Valid
    @NotNull
    @OneToMany(mappedBy = "servise")
    public Collection<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(Collection<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }
}
