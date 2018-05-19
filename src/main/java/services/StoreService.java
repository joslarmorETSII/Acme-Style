package services;

import domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.StoreRepository;
import security.Authority;

import java.util.ArrayList;
import java.util.Collection;

@Service
@Transactional
public class StoreService {
    // Managed repository -----------------------------------------------------

    @Autowired
    private StoreRepository storeRepository;

    // Supporting services ----------------------------------------------------

    @Autowired
    private ActorService actorService;


    // Constructors -----------------------------------------------------------

    public StoreService() {
        super();
    }

    // CRUD methods -----------------------------------------------------------

    public Store create(){
        Store result;

        result = new Store();
        result.setServices(new ArrayList<Servise>());
        result.setEvents(new ArrayList<Event>());

        return result;
    }

    public Store findOne(int id){
        return storeRepository.findOne(id);
    }

    public Collection<Store> findAll(){
        return storeRepository.findAll();
    }

    public void delete(Store store){
        Assert.notNull(store);

        Assert.isTrue( actorService.checkRole(Authority.MANAGER));
        storeRepository.delete(store);
    }

    public Store save(Store store){
        Assert.notNull(store);
        Assert.isTrue(actorService.checkRole(Authority.MANAGER));
        return storeRepository.save(store);
    }

    // Other business methods -------------------------------------------------

}


