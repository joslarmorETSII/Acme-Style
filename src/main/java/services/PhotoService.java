package services;

import domain.Photo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import repositories.PhotoRepository;

import org.springframework.transaction.annotation.Transactional;
import java.util.Collection;

@Service
@Transactional
public class PhotoService {

    // Managed repository -----------------------------------------------------

    @Autowired
    private PhotoRepository      photoRepository;

    // Supporting services ----------------------------------------------------

    @Autowired
    private UserService          userService;

    // Constructors -----------------------------------------------------------

    public PhotoService() {
        super();
    }

    // Simple CRUD methods ----------------------------------------------------

    public Photo create() {
        Photo res;

        res= new Photo();

        return res;
    }


    public Collection<Photo> findAll(){
        Collection<Photo> res;
        res= photoRepository.findAll();
        return  res;
    }

    public Photo findOne(int photoId){
        return photoRepository.findOne(photoId);
    }

    public Photo findOneToEdit(int pictureId){
        Photo res;

        res= this.photoRepository.findOne(pictureId);
        Assert.isTrue(res.getPanel().getUser().equals(userService.findByPrincipal()));
        return  res;
    }

    public void delete(Photo photo){
        Assert.notNull(photo);
        Assert.isTrue(photo.getPanel().getUser().equals(userService.findByPrincipal()));
        this.photoRepository.delete(photo);
    }

    public void deleteAll(Collection<Photo> photos){
        this.photoRepository.delete(photos);
    }

    public Photo save(Photo photo){
        Assert.isTrue(photo.getPanel().getUser().equals(userService.findByPrincipal()));
        return photoRepository.save(photo);
    }

}
