package repositories;

import domain.Administrator;
import domain.Post;
import domain.Servise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Integer> {

    @Query("select a from Administrator a where a.userAccount.id = ?1")
    Administrator findByUserAccountId(int userAccountId);

    /*----------------------------------------- Queries -----------------------------------------*/

    //The average, the minimum, and the maximum number of service per Photographer.
    @Query("select avg(p.servises.size),min(p.servises.size),max(p.servises.size) from Artist p join p.userAccount.authorities a where a.authority = 'PHOTOGRAPHER' ")
    Object[] avgMinMaxServicesPerPhotographer();

    //The average, the minimum, and the maximum number of service per Makeup Artist.
    @Query("select avg(m.servises.size),min(m.servises.size),max(m.servises.size) from Artist m join m.userAccount.authorities a where a.authority = 'MAKEUPARTIST' ")
    Object[] avgMinMaxServicesPerMakeupArtist();

    //The average, the minimum, and the maximum number of service per Stylist.
    @Query("select avg(s.servises.size),min(s.servises.size),max(s.servises.size) from Artist s join s.userAccount.authorities a where a.authority = 'STYLIST' ")
    Object[] avgMinMaxServicesPerStylist();

    //The number of service with a discount over 5%.
    @Query("select count(s) from Servise s where s.discount >= 5 ")
    Integer numberServiseWithDiscountOverFive();

    //The name of servise with more suscriptions and the sum of suscriptions of this servise
    @Query("select s1.title, count(s1) from Servise s1 where s1.subscriptions.size >= (select max(s.subscriptions.size) from Servise s)")
    Object[] serviseBestSuscription();

    //Top 5 services with more subscriptions.
    @Query("select s from Servise s order by s.subscriptions.size desc")
    Collection<Servise> topFiveServiseWithSubscriptions();

    //Top 10 services with more subscriptions.
    @Query("select s from Servise s order by s.subscriptions.size desc")
    Collection<Servise> topTenServiseWithSubscriptions();

    //The avg of questions about services by Photographers.
    @Query("select avg(s.questions.size)*1.0 from Servise s join s.creator.userAccount.authorities p where p.authority ='PHOTOGRAPHER' ")
    Double avgQuestionPerServiseForAllPhotographer();

    //The ratio of questions about services by Stylists.
    @Query("select count(q)*1.0 / (select count(q1)*1.0 from Question q1) from Question q join q.servise.creator.userAccount.authorities a where a.authority ='STYLISTS'")
    Double ratioQuestionsServicesByStylists();

    //The average and standard deviation of number of feedbacks of a service per user.
    @Query("select avg(s.feedbacks.size),sqrt(sum(s.feedbacks.size *s.feedbacks.size)/ count(s) - (avg(s.feedbacks.size) *avg(s.feedbacks.size))) from  Servise s join s.feedbacks f order by f.user")
    Object[] avgAndSqrtCommentsPerUser();


    //The services that have at least 60% more answers than the average about their questions.
    @Query("select s from Servise s join s.questions q where q.answers.size > (select avg(q1.answers.size)*1.6 from Question q1)")
    Double servicesMoreAnsThan60PercentAboutQuestiosn();

    //The average, the minimum, and the maximum number of stores per service.
    @Query("select avg(s.stores.size), min(s.stores.size), max(s.stores.size) from Servise s")
    Object[] avgMinMaxStoresPerService();

    //The average and standard deviation of number of events per manager.
    @Query("select avg(m.events.size), sqrt(sum(m.events.size *m.events.size)/ count(m) - (avg(m.events.size) *avg(m.events.size))) from Manager m")
    Object[] avgSqrtEventsPerManager();


    //The average and standard deviation of number of users who participate in a event which celebrate in a store.
    @Query("select avg(u.participates.size), sqrt(sum(u.participates.size *u.participates.size)/ count(u) - (avg(u.participates.size) *avg(u.participates.size))) from User u join u.participates p where p.event.store > 0")
    Object[] avgSqrtParticipatersParticipateAEventStore();

    //The ratio of events thats celebrate in a store vs the ratio of events thats not celebrate in a store.
    @Query("select count(e1)*1.0 /(select count(e2)*1.0 from Event e2 where e2.store > 0) from Event e1 where e1.store = 0")
    Double ratioEventsStoreVSEvents();

    //The number of like of a post per Category.
    @Query("select count(p.lik), c.name from Post p join p.categories c where p.lik > 0 group by c")
    Object[] numberOfLikePostPerCategory();

    //A listing of posts in descending order by moment of creation.
    @Query("select p.title, p.moment from Post p order by p.moment desc")
    Collection <Post> listingPostByMomentOfCreation();

    //The number of hearts of a post per Category.
    @Query("select count(p.heart), c.name from Post p join p.categories c where p.heart > 0 group by c")
    Object[] numberOfHeartPostPerCategory();

    //Top 10 post, posts with more likes, loves.
    @Query("select p.title, p.lik, p.heart from Post p order by p.lik, p.heart")
    Collection<Post> topTenPostsWithLikesLoves();

    //The average, max, min and standard deviation of number of photo per panel.
    @Query("select avg(p.photos.size), min(p.photos.size), max(p.photos.size), sqrt(sum(p.photos.size *p.photos.size)/ count(p) - (avg(p.photos.size) *avg(p.photos.size))) from Panel p")
    Object[] avgMaxMinSqrtPhotosPerPanel();

    //The average, max, min and standard deviation of number of panel per user.
    @Query("select avg(u.panels.size), min(u.panels.size), max(u.panels.size), sqrt(sum(u.panels.size *u.panels.size)/ count(u) - (avg(u.panels.size) *avg(u.panels.size))) from User u")
    Object[] avgMaxMinSqrtPanelsPerUser();

    //The ratio of posts which has raffles.
    @Query("select count(p)*1.0 /(select count(p1)*1.0 from Post p1) from Post p where p.raffle = true")
    Double ratioPostsWithRaffles();

    //The average of post with reward.
    @Query("select avg(p) from Post p where p.reward <> null")
    Double avgPostWithReward();

    //A listing of posts in ascendant order by end of raffle.
    @Query("select p from Post p order by p.endDate asc")
    Collection<Post> listingPostByEndDate();
}
