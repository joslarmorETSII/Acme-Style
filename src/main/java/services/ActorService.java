package services;

import domain.Actor;
import domain.Folder;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import repositories.ActorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;

@Service
@Transactional
public class ActorService {

    // Managed repository -----------------------------------------------------

    @Autowired
    private ActorRepository actorRepository;

    // Supporting services ----------------------------------------------------

    @Autowired
    private FolderService folderService;

    // Constructors -----------------------------------------------------------

    public ActorService() {
        super();
    }

    // Simple CRUD methods ----------------------------------------------------


    public Actor findOne(final int actorId) {

        Actor result;
        result = this.actorRepository.findOne(actorId);
        return result;
    }

    public Collection<Actor> findAll() {

        Collection<Actor> result;
        result = this.actorRepository.findAll();
        return result;
    }

    public Actor save(final Actor actor) {

        Assert.notNull(actor);
        Assert.notNull(actor.getUserAccount());
        Assert.isTrue(this.findByPrincipal().getId() == actor.getId());

        Actor result;

        result = this.actorRepository.save(actor);

        return result;
    }

    // Other business methods -------------------------------------------------

    public Actor findByPrincipal() {

        Actor result;
        final UserAccount userAccount = LoginService.getPrincipal();
        result = this.findByUserAccountId(userAccount.getId());
        return result;
    }

    public Actor findByUserAccountId(final int userAccountId) {

        Actor result;
        result = this.actorRepository.findByUserAccountId(userAccountId);
        return result;
    }


    public boolean checkRole(String role) {
        boolean result;
        Collection<Authority> authorities;

        result = false;
        authorities = LoginService.getPrincipal().getAuthorities();
        for (final Authority a : authorities)
            result = result || a.getAuthority().equals(role);

        return result;
    }

    public boolean isAdministrator() {
        return this.checkRole(Authority.ADMINISTRATOR);
    }

    public boolean isStylist() {
        return this.checkRole(Authority.STYLIST);
    }

    public boolean isPhotographer() {
        return this.checkRole(Authority.PHOTOGRAPHER);
    }

    public boolean isMakeUpArtist() {
        return this.checkRole(Authority.MAKEUPARTIST);
    }

    public boolean isUser() {
        return this.checkRole(Authority.USER);
    }


    public Collection<Folder> generateFolders(Actor actor){
        Collection<Folder> folders = new ArrayList<>();

        Folder inbox = folderService.createInFolder(actor);
        folderService.saveCreate(inbox);
        Folder outbox = folderService.createOutFolder(actor);
        folderService.saveCreate(outbox);
        Folder spambox = folderService.createSpamFolder(actor);
        folderService.saveCreate(spambox);
        Folder trashbox = folderService.createTrashFolder(actor);
        folderService.saveCreate(trashbox);
        Folder notification = folderService.createNotificationFolder(actor);
        folderService.saveCreate(notification);

        folders.add(inbox);
        folders.add(outbox);
        folders.add(spambox);
        folders.add(trashbox);
        folders.add(notification);

        return folders;
    }

    public boolean comprobarContrasena(String password,  String passwordRepeat,  BindingResult binding) {
        FieldError error;
        String[] codigos;
        boolean result;

        if (StringUtils.isNotBlank(password) && StringUtils.isNotBlank(passwordRepeat))
            result = password.equals(passwordRepeat);
        else
            result = false;

        if (!result && password.length()>=5 && passwordRepeat.length()>=5) {
            codigos = new String[1];
            codigos[0] = "user.password.mismatch";
            error = new FieldError("userForm", "repeatPassword", password, false, codigos, null, "password mismatch");
            binding.addError(error);
        }

        return result;
    }

    public void follow(int actorId){
        Actor principal;
        Actor userToFollow;

        principal = findByPrincipal();
        userToFollow = findOne(actorId);
        Assert.notNull(userToFollow);
        Assert.isTrue(!principal.getFollowings().contains(userToFollow),"You already follow this user");
        Assert.isTrue(principal.getId() != actorId,"Can't follow yourself");
        principal.getFollowings().add(userToFollow);

        save(principal);

    }

    public void unfollow(int actorId){
        Actor principal;
        Actor userToUnFollow;

        principal = findByPrincipal();
        userToUnFollow = findOne(actorId);
        Assert.notNull(userToUnFollow);
        Assert.isTrue(principal.getFollowings().contains(userToUnFollow),"You don't follow this user");
        principal.getFollowings().remove(userToUnFollow);
        Assert.isTrue(principal.getId() != actorId);

        userToUnFollow.getFollowers().remove(principal);


        save(principal);

    }
}
