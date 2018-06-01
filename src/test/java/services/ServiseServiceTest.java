package services;

import domain.Actor;
import domain.Servise;
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

@Transactional
@ContextConfiguration(locations = {
        "classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class ServiseServiceTest extends AbstractTest {

    // The SUT
    // ====================================================

    @Autowired
    private ServiseService serviseService;

    @Autowired
    private SubscribeService subscribeService;

    @Autowired
    private UserService userService;

    // Tests
    // ====================================================

     /*  FUNCTIONAL REQUIREMENT:
            * An actor who is not authenticated must be able to:
               -. Browse the list of published services and navigate to the corresponding
               creators profile which must include personal data.
               -. List the services in the system and navigate to the corresponding feedbacks
               also questions and answers if any.

    */

    public void listServiseTest(final String username,  final Class<?> expected) {
        Class<?> caught = null;
        startTransaction();
        try {

            this.serviseService.findAll();

        } catch (final Throwable oops) {

            caught = oops.getClass();

        }

        this.checkExceptions(expected, caught);
        rollbackTransaction();

    }

     /*  FUNCTIONAL REQUIREMENT:
            * An actor who is authenticated as user must be able to:
               -. Subscribe to a service, providing a valid credit card regardless of the price.
    */

    public void subscribeServiseTest(final String username, String userBean, String serviseBean, final Class<?> expected) {
        Class<?> caught = null;
        startTransaction();
        try {

            this.authenticate(username);

            User result;
            Servise servise;

            servise = serviseService.findOne(getEntityId(serviseBean));
            result = userService.findOne(getEntityId(userBean));

            subscribeService.subscriptionByUserAndService(result.getId(),servise.getId() );

            this.unauthenticate();

        } catch (final Throwable oops) {

            caught = oops.getClass();

        }

        this.checkExceptions(expected, caught);
        rollbackTransaction();

    }

    /*  FUNCTIONAL REQUIREMENT:
            * An actor who is authenticated as user must be able to:
               -. List the services where he or she is subscribed.

    */

    public void listServiseSubscribeTest(final String username, String userBean,  final Class<?> expected) {
        Class<?> caught = null;
        startTransaction();
        try {

            this.authenticate(username);

            User result;

            result = userService.findOne(getEntityId(userBean));

            serviseService.servisesPerCreator(result.getId());

        } catch (final Throwable oops) {

            caught = oops.getClass();

        }

        this.checkExceptions(expected, caught);
        rollbackTransaction();

    }


    //Drivers
    // ===================================================

    @Test
    public void driverListServiseTest() {

        final Object testingData[][] = {
                // Alguien sin registrar/logueado -> true
                {
                        null, null
                }

        };
        for (int i = 0; i < testingData.length; i++)
            this.listServiseTest((String) testingData[i][0],  (Class<?>) testingData[i][1]);
    }

    @Test
    public void driverSubscribeServiseTest() {

        final Object testingData[][] = {
                // Alguien sin registrar/logueado -> true
                {
                        "user1", "user1", "servise1", null
                },

        };
        for (int i = 0; i < testingData.length; i++)
            this.subscribeServiseTest((String) testingData[i][0], (String) testingData[i][1],
                     (String) testingData[i][2],(Class<?>) testingData[i][3]);
    }

    @Test
    public void driverListServiseSubscribeTest() {

        final Object testingData[][] = {
                // Un usuario se subscribe a un servise -> true
                {
                        "user1", "user1", null
                },
                // Un stylist se subscribe a un servise -> false
                {
                        "stylist1", "user1", IllegalArgumentException.class
                },
                // Un makeup se subscribe a un servise   -> false
                {
                        "makeup1", "user1", IllegalArgumentException.class
                }

        };
        for (int i = 0; i < testingData.length; i++)
            this.listServiseSubscribeTest((String) testingData[i][0],  (String) testingData[i][1],
                    (Class<?>) testingData[i][2]);
    }
}
