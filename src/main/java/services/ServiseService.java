package services;

import domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import repositories.ServiseRepository;
import org.springframework.validation.Validator;

import org.springframework.transaction.annotation.Transactional;
import security.Authority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Transactional
public class ServiseService {

    // Managed repository -----------------------------------------------------

    @Autowired
    private ServiseRepository serviseRepository;



    // Supporting services ----------------------------------------------------

    @Autowired
    private ArtistService artistService;

    @Autowired
    private ActorService actorService;

    @Autowired
    private Validator validator;

    @Autowired
    private ConfigurationService configurationService;

    @Autowired
    private AdministratorService administratorService;

    @Autowired
    private SubscribeService subscribeService;

    @Autowired
    private FeedbackService feedbackService;


    // Constructors -----------------------------------------------------------

    public ServiseService() {
        super();
    }

    // Simple CRUD methods ----------------------------------------------------

    public Servise create(){
        Servise result;
        Artist artist;

        artist=artistService.findByPrincipal();
        result = new Servise();
        result.setCreator(artist);
        result.setPublicationDate(new Date());
        result.setQuestions(new ArrayList<Question>());
        result.setStores(new ArrayList<Store>());
        result.setSubscriptions(new ArrayList<Subscription>());
        result.setFeedbacks(new ArrayList<Feedback>());

        return result;
    }

    public Servise findOne(int serviseId){
        return serviseRepository.findOne(serviseId);
    }

    public Collection<Servise> findAll(){
        return serviseRepository.findAll();
    }


    public Servise save(Servise servise){
        Assert.notNull(servise);
        servise.setPublicationDate(new Date(System.currentTimeMillis()-1000));
        if(isTabooServise(servise)){
            servise.setTaboo(true);
        }


        return serviseRepository.save(servise);
    }

    public void delete(Servise servise){
        Assert.isTrue(checkByPrincipalAdmin(servise)||actorService.findByPrincipal().equals(servise.getCreator()),"Not the creator of this Service" );
        subscribeService.deleteAll(servise.getSubscriptions());
        feedbackService.deleteAll(servise.getFeedbacks());
        serviseRepository.delete(servise);
    }

    // Other business methods -----------------------------------------------------------------

    public Servise findOneToEdit(int serviseId){
        Servise result;
        Artist principal;

        result = serviseRepository.findOne(serviseId);
        principal = artistService.findByPrincipal();
        Assert.isTrue(result.getSubscriptions().isEmpty(),"Users are subscribed to this Service");
        Assert.isTrue(principal.equals(result.getCreator()),"Not the creator of this Service");

        return result;
    }

    public Servise reconstructS(final Servise servicePruned, final BindingResult binding) {
        Servise res;
        if (servicePruned.getId() == 0) {
            res = this.create();
        } else {
            res = this.findOne(servicePruned.getId());

        }
        res.setTitle(servicePruned.getTitle());
        res.setPublicationDate(servicePruned.getPublicationDate());
        res.setPublicationDate(servicePruned.getPublicationDate());
        res.setDescription(servicePruned.getDescription());
        res.setPicture(servicePruned.getPicture());
        res.setDiscount(servicePruned.getDiscount());
        res.setPrice(servicePruned.getPrice());

        this.validator.validate(res,binding);

    return res;


    }

    private boolean isTabooServise(final Servise servise) {
        boolean result = false;
        Pattern p;
        Matcher isAnyMatcherTitle;
        Matcher isAnyMatcherDescription;

        p = this.tabooWords();
        isAnyMatcherTitle = p.matcher(servise.getTitle());
        isAnyMatcherDescription = p.matcher(servise.getDescription());

        if (isAnyMatcherTitle.find() || isAnyMatcherDescription.find())
            result = true;

        return result;
    }

    public Pattern tabooWords() {
        Pattern result;
        List<String> tabooWords;

        final Collection<String> taboolist = this.configurationService.findAll().iterator().next().getTabooWords();
        tabooWords = new ArrayList<>(taboolist);

        String str = ".*\\b(";
        for (int i = 0; i <= tabooWords.size(); i++)
            if (i < tabooWords.size())
                str += tabooWords.get(i) + "|";
            else
                str += tabooWords.iterator().next() + ")\\b.*";

        result = Pattern.compile(str, Pattern.CASE_INSENSITIVE);

        return result;
    }



    public Double finalPrice(Servise servise){
        return servise.getPrice()-(servise.getPrice()*(servise.getDiscount()/100));

    }

    public Collection<Servise> servisesPerCreator(int creatorId){
        return serviseRepository.servisesPerCreator(creatorId);
    }

    public Collection<Servise> servisesTaboo(){
        return serviseRepository.servisesTaboo();
    }

    public boolean checkByPrincipalAdmin(Servise servise){
        Boolean res= false;
        Administrator administrator = administratorService.findByPrincipal();
        if(administrator!=null) {
            Collection<Authority> authorities = administrator.getUserAccount().getAuthorities();
            String authority = authorities.toArray()[0].toString();
            res = authority.equals("ADMINISTRATOR");
        }
        return res;

    }

    public Collection<Servise> searchServisesPerKeyword(String keyword){
        return this.serviseRepository.searchServisesPerKeyword(keyword,keyword);
    }
}
