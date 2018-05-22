package services;

import domain.*;
import forms.UserForm;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import repositories.ArtistRepository;
import security.LoginService;
import security.UserAccount;

import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.Collection;

@Service
@Transactional
public class ArtistService {


    // Managed repository -----------------------------------------------------

    @Autowired
    private ArtistRepository artistRepository;

    // Supporting services ----------------------------------------------------

    @Autowired
    private ActorService actorService;

    @Autowired
    private ProfileService profileService;

    // Constructors -----------------------------------------------------------

    public ArtistService() {
        super();
    }

    // Simple CRUD methods ----------------------------------------------------

    public Artist create(){
        Artist result;

        result = new Artist();
        result.setServises(new ArrayList<Servise>());
        result.setComments(new ArrayList<Comment>());
        result.setFolders(new ArrayList<Folder>());
        result.setPosts(new ArrayList<Post>());
        result.setFollowers(new ArrayList<Actor>());
        result.setFollowings(new ArrayList<Actor>());

        return result;
    }

    public Artist findOne(int userId) {
        return  artistRepository.findOne(userId);
    }

    public Collection<Artist> findAll() {
        return artistRepository.findAll();
    }

    public Artist save(Artist artist){
        Assert.notNull(artist);
        Artist result;
        Collection<Folder> folders;

        if(artist.getId()==0){
            result = artistRepository.save(artist);
            folders = actorService.generateFolders(result);
            Profile saved = profileService.generateProfileForActors(result);
            result.setProfile(saved);
            result.setFolders(folders);
            result = artistRepository.save(result);
        }else {
            result = artistRepository.save(artist);
        }
        return result;
    }


    // Other business methods -------------------------------------------------


    public Artist findByPrincipal() {
        Artist result;
        UserAccount userAccount = LoginService.getPrincipal();
        result = this.findByUserAccountId(userAccount.getId());
        return result;

    }

    public Artist findByUserAccountId(int userAccountId) {

        Artist result;
        result = artistRepository.findByUserAccountId(userAccountId);
        return result;
    }


    public Artist reconstruct(UserForm userForm, BindingResult binding) {

        Artist result;

        result = this.create();
        result.setName(userForm.getName());
        result.setSurname(userForm.getSurname());
        result.setPhone(userForm.getPhone());
        result.setEmail(userForm.getEmail());
        result.setPostalAddresses(userForm.getPostalAddresses());
        result.setUserAccount(new UserAccountService().create(userForm.getRole()));
        result.getUserAccount().setUsername(userForm.getUsername());
        result.getUserAccount().setPassword(new Md5PasswordEncoder().encodePassword(userForm.getPassword(), null));

        actorService.comprobarContrasena(userForm.getPassword(), userForm.getRepeatPassword(), binding);

        return result;
    }


}
