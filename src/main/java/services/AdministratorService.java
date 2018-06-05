package services;

import domain.Administrator;
import domain.Post;
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

    public Collection<Administrator> findAll(){
        return this.administratorRepository.findAll();
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

    public Object[] avgMinMaxServicesPerPhotographer(){
        return this.administratorRepository.avgMinMaxServicesPerPhotographer();
    }

    public Object[] avgMinMaxServicesPerMakeupArtist(){
        return this.administratorRepository.avgMinMaxServicesPerMakeupArtist();
    }

    public Object[] avgMinMaxServicesPerStylist(){
        return this.administratorRepository.avgMinMaxServicesPerStylist();
    }

    public Integer numberServiseWithDiscountOverFive(){
        return this.administratorRepository.numberServiseWithDiscountOverFive();
    }

    public Object[] serviseBestSuscription(){
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

    public Double avgQuestionPerServiseForAllPhotographer(){
        return this.administratorRepository.avgQuestionPerServiseForAllPhotographer();
    }

    public Double ratioQuestionsServicesByStylists(){
        return this.administratorRepository.ratioQuestionsServicesByStylists();
    }

    public Object[] avgAndSqrtCommentsPerUser(){
        return this.administratorRepository.avgAndSqrtCommentsPerUser();
    }

    public Double servicesMoreAnsThan60PercentAboutQuestiosn(){
        return this.administratorRepository.servicesMoreAnsThan60PercentAboutQuestiosn();
    }

    public Object[] avgSqrtEventsPerManager(){
        return this.administratorRepository.avgSqrtEventsPerManager();
    }

    public Object[] avgSqrtParticipatersParticipateAEventStore(){
        return this.administratorRepository.avgSqrtParticipatersParticipateAEventStore();
    }

    public Double ratioEventsStoreVSEvents(){
        return this.administratorRepository.ratioEventsStoreVSEvents();
    }

    public Object[] NumberOfLikePostPerCategory(){
        return this.administratorRepository.numberOfLikePostPerCategory();
    }

    public Collection <Post> listingPostByMomentOfCreation(){
        return this.administratorRepository.listingPostByMomentOfCreation();
    }

    public Object[] NumberOfHeartPostPerCategory(){
        return this.administratorRepository.numberOfHeartPostPerCategory();
    }

    public Collection<Post> topTenPostsWithLikesLoves(){
        List<Post> result;
        result = new ArrayList<>(this.administratorRepository.topTenPostsWithLikesLoves());

        if (result.size() > 10)
            result.subList(0, 9);

        return result;
    }

    public Object[] avgMinMaxStoresPerService(){
        return this.administratorRepository.avgMinMaxStoresPerService();
    }

    public Object[] numberOfLikePostPerCategory(){
        return this.administratorRepository.numberOfLikePostPerCategory();
    }

    public Object[] numberOfHeartPostPerCategory(){
        return this.administratorRepository.numberOfHeartPostPerCategory();
    }

    public Object[] avgMaxMinSqrtPhotosPerPanel(){
        return this.administratorRepository.avgMaxMinSqrtPhotosPerPanel();
    }

    public Object[] avgMaxMinSqrtPanelsPerUser(){
        return this.administratorRepository.avgMaxMinSqrtPanelsPerUser();
    }

    public Double ratioPostsWithRaffles(){
        return this.administratorRepository.ratioPostsWithRaffles();
    }

    public Double avgPostWithReward(){
        return this.administratorRepository.avgPostWithReward();
    }

    public Collection<Post> listingPostByEndDate(){
        return this.administratorRepository.listingPostByEndDate();
    }
}
