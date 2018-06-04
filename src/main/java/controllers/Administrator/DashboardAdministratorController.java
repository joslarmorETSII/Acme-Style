package controllers.Administrator;

import controllers.AbstractController;
import domain.Post;
import domain.Servise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import services.AdministratorService;

import java.util.Collection;

@Controller
@RequestMapping("/dashboard/administrator")
public class DashboardAdministratorController extends AbstractController {

    // Services --------------------------------------------

    @Autowired
    private AdministratorService administratorService;

    // Constructor -----------------------------------------
    public DashboardAdministratorController() {
        super();
    }

    // Dashboard -------------------------------------------------------------
    @RequestMapping("/dashboard")
    public ModelAndView dashboard() {
        ModelAndView result;

        result = new ModelAndView("administrator/dashboard");

        Object[]  avgMinMaxServicesPerPhotographer;
        Object[]  avgMinMaxServicesPerMakeupArtist;
        Object[]  avgMinMaxServicesPerStylist;
        Integer numberServiseWithDiscountOverFive;
        Object[] serviseBestSuscription;
        Collection<Servise> topFiveServiseWithSubscriptions;
        Collection<Servise> topTenServiseWithSubscriptions;
        Double avgQuestionPerServiseForAllPhotographer;
        Double ratioQuestionsServicesByStylists;
        Object[] avgAndSqrtCommentsPerUser;
        Double servicesMoreAnsThan60PercentAboutQuestiosn;
        Object[] avgMinMaxStoresPerService;
        Object[] avgSqrtEventsPerManager;
        Object[] avgSqrtParticipatersParticipateAEventStore;
        Double ratioEventsStoreVSEvents;
        Object[] numberOfLikePostPerCategory;
        Collection <Post> listingPostByMomentOfCreation;
        Object[] numberOfHeartPostPerCategory;
        Collection<Post> topTenPostsWithLikesLoves;
        Object[] avgMaxMinSqrtPhotosPerPanel;
        Object[] avgMaxMinSqrtPanelsPerUser;
        Double ratioPostsWithRaffles;
        Double avgPostWithReward;
        Collection<Post> listingPostByEndDate;


        avgMinMaxServicesPerPhotographer = this.administratorService.avgMinMaxServicesPerPhotographer();
        avgMinMaxServicesPerMakeupArtist = this.administratorService.avgMinMaxServicesPerMakeupArtist();
        avgMinMaxServicesPerStylist = this.administratorService.avgMinMaxServicesPerStylist();
        numberServiseWithDiscountOverFive = this.administratorService.numberServiseWithDiscountOverFive();
        serviseBestSuscription = this.administratorService.serviseBestSuscription();
        topFiveServiseWithSubscriptions = this.administratorService.topFiveServiseWithSubscriptions();
        topTenServiseWithSubscriptions = this.administratorService.topTenServiseWithSubscriptions();
        avgQuestionPerServiseForAllPhotographer = this.administratorService.avgQuestionPerServiseForAllPhotographer();
        ratioQuestionsServicesByStylists = this.administratorService.ratioQuestionsServicesByStylists();
        avgAndSqrtCommentsPerUser = this.administratorService.avgAndSqrtCommentsPerUser();
        servicesMoreAnsThan60PercentAboutQuestiosn = this.administratorService.servicesMoreAnsThan60PercentAboutQuestiosn();
        avgMinMaxStoresPerService = this.administratorService.avgMinMaxStoresPerService();
        avgSqrtEventsPerManager = this.administratorService.avgSqrtEventsPerManager();
        avgSqrtParticipatersParticipateAEventStore = this.administratorService.avgSqrtParticipatersParticipateAEventStore();
        ratioEventsStoreVSEvents = this.administratorService.ratioEventsStoreVSEvents();
        numberOfLikePostPerCategory = this.administratorService.numberOfLikePostPerCategory();
        listingPostByMomentOfCreation = this.administratorService.listingPostByMomentOfCreation();
        numberOfHeartPostPerCategory = this.administratorService.numberOfHeartPostPerCategory();
        topTenPostsWithLikesLoves = this.administratorService.topTenPostsWithLikesLoves();
        avgMaxMinSqrtPhotosPerPanel = this.administratorService.avgMaxMinSqrtPhotosPerPanel();
        avgMaxMinSqrtPanelsPerUser = this.administratorService.avgMaxMinSqrtPanelsPerUser();
        ratioPostsWithRaffles = this.administratorService.ratioPostsWithRaffles();
        avgPostWithReward = this.administratorService.avgPostWithReward();
        listingPostByEndDate = this.administratorService.listingPostByEndDate();


        result.addObject("avgMinMaxServicesPerPhotographer", avgMinMaxServicesPerPhotographer);
        result.addObject("avgMinMaxServicesPerMakeupArtist", avgMinMaxServicesPerMakeupArtist);
        result.addObject("avgMinMaxServicesPerStylist", avgMinMaxServicesPerStylist);
        result.addObject("numberServiseWithDiscountOverFive", numberServiseWithDiscountOverFive);
        result.addObject("serviseBestSuscription", serviseBestSuscription);
        result.addObject("topFiveServiseWithSubscriptions", topFiveServiseWithSubscriptions);
        result.addObject("topTenServiseWithSubscriptions", topTenServiseWithSubscriptions);
        result.addObject("avgQuestionPerServiseForAllPhotographer", avgQuestionPerServiseForAllPhotographer);
        result.addObject("ratioQuestionsServicesByStylists", ratioQuestionsServicesByStylists);
        result.addObject("avgAndSqrtCommentsPerUser", avgAndSqrtCommentsPerUser);
        result.addObject("servicesMoreAnsThan60PercentAboutQuestiosn", servicesMoreAnsThan60PercentAboutQuestiosn);
        result.addObject("avgMinMaxStoresPerService",avgMinMaxStoresPerService);
        result.addObject("avgSqrtEventsPerManager",avgSqrtEventsPerManager);
        result.addObject("avgSqrtParticipatersParticipateAEventStore",avgSqrtParticipatersParticipateAEventStore);
        result.addObject("ratioEventsStoreVSEvents",ratioEventsStoreVSEvents);
        result.addObject("numberOfLikePostPerCategory",numberOfLikePostPerCategory);
        result.addObject("listingPostByMomentOfCreation",listingPostByMomentOfCreation);
        result.addObject("numberOfHeartPostPerCategory",numberOfHeartPostPerCategory);
        result.addObject("topTenPostsWithLikesLoves",topTenPostsWithLikesLoves);
        result.addObject("avgMaxMinSqrtPhotosPerPanel",avgMaxMinSqrtPhotosPerPanel);
        result.addObject("avgMaxMinSqrtPanelsPerUser",avgMaxMinSqrtPanelsPerUser);
        result.addObject("ratioPostsWithRaffles",ratioPostsWithRaffles);
        result.addObject("avgPostWithReward",avgPostWithReward);
        result.addObject("listingPostByEndDate",listingPostByEndDate);

        return result;
    }
}
