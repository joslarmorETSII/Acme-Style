package repositories;

import domain.Administrator;
import domain.Servise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Integer> {

    @Query("select a from Administrator a where a.userAccount.id = ?1")
    Administrator findByUserAccountId(int userAccountId);

    /*----------------------------------------- Querys -----------------------------------------*/

    //The average, the minimum, and the maximum number of service per Photographer.
    @Query("select avg(p.servises.size),min(p.servises.size),max(p.servises.size) from Artist p join p.userAccount.authorities a where a.authority = 'PHOTOGRAPHER' ")
    Collection<Double> avgMinMaxServicesPerPhotographer();

    //The average, the minimum, and the maximum number of service per Makeup Artist.
    @Query("select avg(m.servises.size),min(m.servises.size),max(m.servises.size) from Artist m join m.userAccount.authorities a where a.authority = 'MAKEUPARTIST' ")
    Collection<Double> avgMinMaxServicesPerMakeupArtist();

    //The average, the minimum, and the maximum number of service per Stylist.
    @Query("select avg(s.servises.size),min(s.servises.size),max(s.servises.size) from Artist s join s.userAccount.authorities a where a.authority = 'STYLIST' ")
    Collection<Double> avgMinMaxServicesPerStylist();

    //The number of service with a discount over 5%.
    @Query("select s from Servise s where s.discount >= 5 ")
    Integer numberServiseWithDiscountOverFive();

    //The servise with more suscriptions
    @Query("select s1 from Servise s1 where s1.subscriptions.size >= (select max(s.subscriptions.size) from Servise s)")
    Collection<Servise> serviseBestSuscription();

    //Top 5 services with more subscriptions.
    @Query("select s from Servise s order by s.subscriptions.size desc")
    Collection<Servise> topFiveServiseWithSubscriptions();

    //Top 10 services with more answers about questions associated this services.
    @Query("select s from Servise s order by s.subscriptions.size desc")
    Collection<Servise> topTenServiseWithSubscriptions();

    //The avg of questions about services by Photographers.
    @Query("select avg(s.questions.size)*1.0 from Servise s join s.creator.userAccount.authorities p where p.authority ='PHOTOGRAPHER' ")
    Double avgQuestionPerServiseForAllPhotographer();

    //The ratio of questions about services by Stylists.

    //The average and standard deviation of number of comments of a service per user.

    //The users with 60% more answers than the average.

    //The average, the minimum, and the maximum number of stores per service.

    //The average and standard deviation of number of events published.

    //The average and standard deviation of number of users who participate in a event which celebrate in a store.

    //The ratio of events thats celebrate in a store vs the ratio of events thats not celebrate in a store.

    //The number of like of a post per Category.

    //A listing of posts in descending order by date of published.

    //The ratio and standard deviation the number of love order by user.

    //Top 10 post, posts with likes plus loves.

    //The average, max, min and standard deviation of number of photo per panel.

    //The average, max, min and standard deviation of number of panel per user.

    //The ratio of posts which are raffles.

    //The average of participate in a raffle.

    //A listing of raffle in ascendant order by date of finished.


}
