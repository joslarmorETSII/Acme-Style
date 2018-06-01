package services;

import domain.Administrator;
import domain.Servise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import repositories.AdministratorRepository;
import security.LoginService;
import security.UserAccount;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class AdministratorService {

    // Managed repository -----------------------------------------------------

    @Autowired
    private AdministratorRepository administratorRepository;

    // Supporting services ----------------------------------------------------

    @Autowired
    private UserAccountService userAccountService;

    // Constructors -----------------------------------------------------------

    public AdministratorService() {
        super();
    }

    // Simple CRUD methods ----------------------------------------------------


    public Administrator save(final Administrator administrator) {

        Assert.notNull(administrator);

        Administrator result;

        result = this.administratorRepository.save(administrator);

        return result;
    }

    // Other business methods -------------------------------------------------

    public Administrator findByPrincipal() {
        Administrator result;
        UserAccount userAccount;

        userAccount = LoginService.getPrincipal();
        //Assert.notNull(userAccount);

        result = this.findByUserAccount(userAccount);
        //Assert.notNull(result);

        return result;
    }

    public Administrator findByUserAccount(final UserAccount userAccount) {

        Assert.notNull(userAccount);
        Administrator result;
        result = this.administratorRepository.findByUserAccountId(userAccount.getId());
        return result;
    }

    public Administrator findOne(){
        return administratorRepository.findAll().get(0);
    }

    public Collection<Double> avgMinMaxServicesPerPhotographer(){
        return this.administratorRepository.avgMinMaxServicesPerPhotographer();
    }

    public Collection<Double> avgMinMaxServicesPerMakeupArtist(){
        return this.administratorRepository.avgMinMaxServicesPerMakeupArtist();
    }

    public Collection<Double> avgMinMaxServicesPerStylist(){
        return this.administratorRepository.avgMinMaxServicesPerStylist();
    }

    public Integer numberServiseWithDiscountOverFive(){
        return this.administratorRepository.numberServiseWithDiscountOverFive();
    }

    public Collection<Servise> serviseBestSuscription(){
        return this.administratorRepository.serviseBestSuscription();
    }

    public Collection<Servise> topFiveServiseWithSubscriptions() {
        List<Servise> result;
        result = new ArrayList<>(this.administratorRepository.topFiveServiseWithSubscriptions());

        if (result.size() > 5)
            result.subList(0, 4);

        return result;
    }

    public Collection<Servise> topTenServiseWithSubscriptions(){
        List<Servise> result;
        result = new ArrayList<>(this.administratorRepository.topTenServiseWithSubscriptions());

        if (result.size() > 10)
            result.subList(0, 9);

        return result;
    }
}
