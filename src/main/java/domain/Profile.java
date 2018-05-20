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
public class Profile extends DomainEntity{

    // Constructors ----------------------------------------------------------------------

    public Profile() { super();}

    // Attributes ------------------------------------------------------------------------
    private String fullName;
    private String profilePhoto;
    private String education;

    @NotBlank
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @URL
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    @NotBlank
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    // Relationships ----------------------------------------------------------------------
    private Actor actor;
    private Collection<Gallery> galleries;

    @Valid
    @OneToOne(optional = false)
    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    @NotNull
    @Valid
    @OneToMany
    public Collection<Gallery> getGalleries() {
        return galleries;
    }

    public void setGalleries(Collection<Gallery> galleries) {
        this.galleries = galleries;
    }
}
