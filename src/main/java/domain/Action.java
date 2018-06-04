package domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Access(AccessType.PROPERTY)
@Table(indexes = {
        @Index(columnList = "actor_id"),@Index(columnList = "post_id")
})
public class Action extends DomainEntity{

    // Constructors ----------------------------------------------------------------------

    public Action() {super(); }

    // Attributes ------------------------------------------------------------------------

    private boolean lik;
    private boolean dislike;
    private boolean heart;

    public boolean isLik() {
        return lik;
    }

    public void setLik(boolean lik) {
        this.lik = lik;
    }

    public boolean isDislike() {
        return dislike;
    }

    public void setDislike(boolean dislike) {
        this.dislike = dislike;
    }

    public boolean isHeart() {
        return heart;
    }

    public void setHeart(boolean heart) {
        this.heart = heart;
    }

    // Relationships ----------------------------------------------------------------------

    private Actor actor;
    private Post post;

    @Valid
    @ManyToOne(optional = false)
    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    @Valid
    @ManyToOne(optional = false)
    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
