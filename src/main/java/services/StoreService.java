package services;

import domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import repositories.StoreRepository;
import security.Authority;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

@Service
@Transactional
public class StoreService {
    // Managed repository -----------------------------------------------------

    @Autowired
    private StoreRepository storeRepository;

    // Supporting services ----------------------------------------------------

    @Autowired
    private ActorService actorService;

    @Autowired
    private ManagerService managerService;


    // Constructors -----------------------------------------------------------

    public StoreService() {
        super();
    }

    // CRUD methods -----------------------------------------------------------

    public Store create(){
        Store result;

        result = new Store();
        result.setServises(new ArrayList<Servise>());
        result.setManager(managerService.findByPrincipal());

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

        Assert.isTrue( store.getManager().equals(managerService.findByPrincipal()),"Not the manager of the store");
        Assert.isTrue(store.getServises().isEmpty(),"The store has Servises");
        storeRepository.delete(store);
    }

    public Store save(Store store){
        Assert.notNull(store);
        Assert.isTrue(store.getManager().equals(managerService.findByPrincipal()),"Not the manager of the store");
        Store saved = storeRepository.save(store);
        for(Servise s: store.getServises()){
            Collection<Store> stores=s.getStores();
            stores.add(saved);
            s.setStores(stores);
        }

        return saved;
    }

    // Other business methods -------------------------------------------------

    public Store findOneToedit(int id){
        Store store;
        Manager manager;

        manager = managerService.findByPrincipal();
        store = findOne(id);
        Assert.isTrue(store.getManager().equals(manager),"Not the Manager of the store");
        Assert.isTrue(store.getServises().isEmpty(),"The store has Servises");


        return store;
    }

    public boolean checkMonth(Integer month, Integer year, BindingResult binding) {
        FieldError error;
        String[] codigos;
        boolean result;
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        Integer actualMonth = c.get(Calendar.MONTH)+1;
        Integer actualYear = c.get(Calendar.YEAR);

        if (month!=null && year!=null)
            result = actualYear.equals(year) && month<actualMonth;
        else
            result = false;
        if (result) {
            codigos = new String[1];
            codigos[0] = "creditCard.month.invalid";
            error = new FieldError("store", "creditCard.expirationMonth", month, false, codigos, null, "should not be in the past");
            binding.addError(error);
        }
        return result;
    }

}


