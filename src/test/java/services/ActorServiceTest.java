package services;


import domain.Actor;
import domain.Profile;
import domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import utilities.AbstractTest;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import java.util.Collection;

@Transactional
@ContextConfiguration(locations = {
        "classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class ActorServiceTest extends AbstractTest {

    // The SUT
    // ====================================================

    @Autowired
    private ActorService actorService;


    // Tests
    // ====================================================

    /*  FUNCTIONAL REQUIREMENT:
            * An actor who is authenticated must be able to:
               -. List the actor of the system and follow  them.
    */

    public void followActorTest(final String username, String actorBean, final Class<?> expected) {

        Class<?> caught = null;
        startTransaction();
        try {

           this.authenticate(username);

           Actor result;

           actorService.findAll();
           result = actorService.findOne(getEntityId(actorBean));
           actorService.follow(result.getId());

           actorService.flush();


        } catch (final Throwable oops) {

            caught = oops.getClass();

        }

        this.checkExceptions(expected, caught);
        rollbackTransaction();
    }

    /*  FUNCTIONAL REQUIREMENT:
            * An actor who is authenticated must be able to:
               -. List the actor of the system and unfollow them.
    */

    public void unFollowActorTest(final String username, String actorBean, final Class<?> expected) {

        Class<?> caught = null;
        startTransaction();
        try {

            this.authenticate(username);

            Actor result;

            actorService.findAll();
            result = actorService.findOne(getEntityId(actorBean));
            actorService.unfollow(result.getId());

            actorService.flush();

        } catch (final Throwable oops) {

            caught = oops.getClass();

        }

        this.checkExceptions(expected, caught);
        rollbackTransaction();
    }

    //Drivers
    // ===================================================

    @Test
    public void driverFollowActorTest() {

        final Object testingData[][] = {
                // User1 follow user2 -> true
                {
                        "user1","stylist1",  null
                },
                // El usuario a null --> false
                {
                        null, "stylist1", IllegalArgumentException.class
                },
                // User1 follow  user1-> false
                {
                        "user1", "user1", IllegalArgumentException.class
                }

        };
        for (int i = 0; i < testingData.length; i++)
            this.followActorTest((String) testingData[i][0],(String) testingData[i][1],
                    (Class<?>) testingData[i][2]);
    }

    @Test
    public void driverUnFollowActorTest() {

        final Object testingData[][] = {
                // User1 follow user2 -> true
                {
                        "user1","manager1",  null
                },
                // El usuario a null --> false
                {
                        null, "stylist1", IllegalArgumentException.class
                },
                // User1 unfollow  user1-> false
                {
                        "user1", "user1", IllegalArgumentException.class
                }

        };
        for (int i = 0; i < testingData.length; i++)
            this.unFollowActorTest((String) testingData[i][0],(String) testingData[i][1],
                    (Class<?>) testingData[i][2]);
    }

}
