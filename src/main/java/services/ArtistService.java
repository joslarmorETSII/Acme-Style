package services;

import domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
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

    // Constructors -----------------------------------------------------------

    public ArtistService() {
        super();
    }

    // Simple CRUD methods ----------------------------------------------------

    public Artist create(){
        Artist result;

        result = new Artist();
        result.setServices(new ArrayList<Servise>());
        result.setComments(new ArrayList<Comment>());
        result.setFolders(new ArrayList<Folder>());
        result.setPosts(new ArrayList<Post>());
        result.setFollowers(new ArrayList<Actor>());
        result.setFollowings(new ArrayList<Actor>());
        result.setUserAccount(new UserAccountService().create("STYLIST")); // TODO: al registrar un artist tiene que eligir el rol

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
}
