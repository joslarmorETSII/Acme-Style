package services;

import domain.Actor;
import domain.Gallery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.GalleryRepository;

import java.util.Collection;

@Service
@Transactional
public class GalleryService {


    // Managed repository -----------------------------------------------------

    @Autowired
    private GalleryRepository galleryRepository;

    // Supporting services ----------------------------------------------------

    @Autowired
    private ActorService actorService;

    // Constructors -----------------------------------------------------------

    public GalleryService() {
        super();
    }

    // CRUD methods -----------------------------------------------------------

    public Gallery create(){
        Gallery gallery;
        Actor principal;

        principal = actorService.findByPrincipal();
        gallery = new Gallery();
        gallery.setProfile(principal.getProfile());

        return gallery;
    }

    public Gallery save(Gallery gallery){
        return galleryRepository.save(gallery);
    }

    public void delete(Gallery gallery){
        Assert.isTrue(gallery.getProfile().getActor().equals(actorService.findByPrincipal()));
        galleryRepository.delete(gallery);
    }

    public Gallery findOne(int id){
        return galleryRepository.findOne(id);
    }

    public Collection<Gallery> findAll(){
        return galleryRepository.findAll();
    }
}
