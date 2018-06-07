package services;

import domain.Actor;
import domain.Folder;
import domain.Post;
import forms.ActorForm;
import forms.UserAccountForm;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
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

    public boolean isManager() {
        return this.checkRole(Authority.MANAGER);
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
        userToFollow.getFollowers().add(principal);
        save(userToFollow);

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
        principal.getFollowings().remove(userToUnFollow);
        save(userToUnFollow);

        save(principal);

    }
    public void flush() {
        actorRepository.flush();
    }
    public Collection<Post> postByFollowings(int actorid){
        return this.actorRepository.postByFollowings(actorid);
    }


    public Actor reconstruct(ActorForm actorForm, BindingResult binding) {
        Actor result;

        result = findByPrincipal();
        result.setName(actorForm.getName());
        result.setSurname(actorForm.getSurname());
        result.setPhone(actorForm.getPhone());
        result.setEmail(actorForm.getEmail());
        result.setPostalAddresses(actorForm.getPostalAddresses());

        return result;
    }

    public UserAccount reconstructUserAccountForm(UserAccountForm userAccountForm, BindingResult binding) {
        UserAccount result;
        FieldError error;
        String[] codigos;
        String password;

        result = findByPrincipal().getUserAccount();
        password = new Md5PasswordEncoder().encodePassword(userAccountForm.getOldPassword(),null);
        comprobarContrasena(userAccountForm.getNewPassword(),userAccountForm.getRepeatPassword(),binding);
        if (!result.getPassword().equals(password)) {
            codigos = new String[1];
            codigos[0] = "userAccount.password.error";
            error = new FieldError("userAccountForm", "oldPassword", null, false, codigos, null, "Wrong password");
            binding.addError(error);
        }else {
            result.setUsername(userAccountForm.getUsername());
            result.setPassword(new Md5PasswordEncoder().encodePassword(userAccountForm.getNewPassword(),null));
        }

        return result;
    }

    public void saveAll(Collection<Actor> actors){
        actorRepository.save(actors);
    }

}
