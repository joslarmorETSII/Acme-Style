package services;

import domain.Action;
import domain.Actor;
import domain.Comment;
import domain.Post;
import javafx.geometry.Pos;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import utilities.AbstractTest;

import javax.transaction.Transactional;
import java.util.Collection;

@Transactional
@ContextConfiguration(locations = {
        "classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class ParticipateServiceTest extends AbstractTest {

    // The SUT
    // ====================================================

    @Autowired
    private ParticipateService participateService;

    @Autowired
    private PostService postService;

    @Autowired
    private ActorService actorService;

    // Tests
    // ====================================================

     /*  FUNCTIONAL REQUIREMENT:
            * An actor who is authenticated as user must be able to:
               -. Participate in raffles.

    */

    public void participatedRafflesTest(final String username, String actorBean, Post post, Integer lik, Collection<Comment> comment, final Class<?> expected) {
        Class<?> caught = null;
        startTransaction();
        try {

            Actor result;
            Collection<Action> actions = null;

            result = actorService.findOne(getEntityId(actorBean));

            actorService.postByFollowings(result.getId());

            if(post.isRaffle() == true){
                result.setActions(actions);
                post.setComments(comment);
            }


        } catch (final Throwable oops) {

            caught = oops.getClass();

        }

        this.checkExceptions(expected, caught);
        rollbackTransaction();

    }



    //Drivers
    // ===================================================

    @Test
    public void driverParticipatedRafflesTest() {

        final Object testingData[][] = {
                // Alguien sin registrar/logueado -> true
                {
                        null, null
                }

        };
//        for (int i = 0; i < testingData.length; i++)
//            this.participatedRafflesTest((String) testingData[i][0],  (Class<?>) testingData[i][1]);
    }


}
