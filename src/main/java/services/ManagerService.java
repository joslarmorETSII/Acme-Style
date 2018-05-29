package services;

import domain.*;
import forms.UserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import repositories.ManagerRepository;
import security.LoginService;
import security.UserAccount;

import java.util.ArrayList;
import java.util.Collection;

@Service
@Transactional
public class ManagerService {

    // Managed repository -----------------------------------------------------

    @Autowired
    private ManagerRepository managerRepository;

    // Supporting services ----------------------------------------------------

    @Autowired
    private ActorService actorService;

    @Autowired
    private ProfileService profileService;

    // Constructors -----------------------------------------------------------

    public ManagerService() {
        super();
    }

    // Simple CRUD methods ----------------------------------------------------

    public Manager create(){
        Manager result;

        result = new Manager();
        result.setComments(new ArrayList<Comment>());
        result.setFolders(new ArrayList<Folder>());
        result.setPosts(new ArrayList<Post>());
        result.setFollowers(new ArrayList<Actor>());
        result.setFollowings(new ArrayList<Actor>());
        result.setStores(new ArrayList<Store>());
        result.setEvents(new ArrayList<Event>());
        result.setUserAccount(new UserAccountService().create("MANAGER"));

        return result;
    }

    public Manager findOne(int userId) {
        return  managerRepository.findOne(userId);
    }

    public Collection<Manager> findAll() {
        return managerRepository.findAll();
    }

    public Manager save(Manager artist){
        Assert.notNull(artist);
        Manager result;
        Collection<Folder> folders;

        if(artist.getId()==0){
            result = managerRepository.save(artist);
            folders = actorService.generateFolders(result);
            Profile saved = profileService.generateProfileForActors(result);
            result.setProfile(saved);
            result.setFolders(folders);
            result = managerRepository.save(result);
        }else {
            result = managerRepository.save(artist);
        }
        return result;
    }


    // Other business methods -------------------------------------------------


    public Manager findByPrincipal() {
        Manager result;
        UserAccount userAccount = LoginService.getPrincipal();
        result = this.findByUserAccountId(userAccount.getId());
        return result;
    }

    public Manager findByUserAccountId(int userAccountId) {
        return managerRepository.findByUserAccountId(userAccountId);
    }


    public Manager reconstruct(UserForm userForm, BindingResult binding) {

        Manager result;

        result = this.create();
        result.getUserAccount().setUsername(userForm.getUsername());
        result.setName(userForm.getName());
        result.setSurname(userForm.getSurname());
        result.setPhone(userForm.getPhone());
        result.setEmail(userForm.getEmail());
        result.setPostalAddresses(userForm.getPostalAddresses());
        result.getUserAccount().setPassword(new Md5PasswordEncoder().encodePassword(userForm.getPassword(), null));

        actorService.comprobarContrasena(userForm.getPassword(), userForm.getRepeatPassword(), binding);

        return result;
    }

    public void flush() {
        managerRepository.flush();
    }
}
