package domain;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import security.UserAccount;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Collection;

@Entity
@Access(AccessType.PROPERTY)
public abstract class Actor extends DomainEntity {


    // Constructors -----------------------------------------------------------

    public Actor() {
        super();
    }


    // Attributes -------------------------------------------------------------

    private String	name;
    private String	surname;
    private String	phone;
    private String	email;
    private String	postalAddresses ;

    @NotBlank
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotBlank
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Pattern(regexp = "^\\+([3][4])( )(\\d{9})|()$")
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Email
    @NotNull
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    public String getPostalAddresses() {
        return postalAddresses;
    }

    public void setPostalAddresses(String postalAddresses) {
        this.postalAddresses = postalAddresses;
    }

    // Relationships ----------------------------------------------------------

    private UserAccount userAccount;
    private Profile profile;
    private Collection<Folder> folders;
    private Collection<Actor> followings;
    private Collection<Actor> followers;
    private Collection<Post> posts;
    private Collection<Comment> comments;
    private Collection<Action> actions;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL, optional = false)
    public UserAccount getUserAccount() {
        return userAccount;
    }


    @Valid
    @OneToMany(mappedBy = "actor")
    @NotNull
    public Collection<Folder> getFolders() {
        return this.folders;
    }

    @NotNull
    @Valid
    @ManyToMany(mappedBy = "followings",fetch= FetchType.EAGER)
    public Collection<Actor> getFollowers() {
        return this.followers;
    }

    @NotNull
    @Valid
    @ManyToMany(cascade = CascadeType.PERSIST, fetch= FetchType.EAGER)
    public Collection<Actor> getFollowings() {
        return this.followings;
    }

    @Valid
    @NotNull
    @OneToMany(mappedBy = "actor")
    public Collection<Post> getPosts() {
        return posts;
    }

    @OneToMany(mappedBy = "actor")
    public Collection<Comment> getComments() {
        return comments;
    }

    @Valid
    @OneToOne(optional = true)
    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public void setPosts(Collection<Post> posts) {
        this.posts = posts;
    }

    public void setFollowings(Collection<Actor> following) {
        this.followings = following;
    }

    public void setFollowers(Collection<Actor> followers) {
        this.followers = followers;
    }

    public void setFolders(final Collection<Folder> folders) {
        this.folders = folders;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public void setComments(Collection<Comment> comments) {
        this.comments = comments;
    }

    @Valid
    @NotNull
    @OneToMany(mappedBy = "actor")
    public Collection<Action> getActions() {
        return actions;
    }

    public void setActions(Collection<Action> actions) {
        this.actions = actions;
    }
}
