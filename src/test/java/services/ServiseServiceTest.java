package services;

import domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import utilities.AbstractTest;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import java.util.Date;

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

    @Autowired
    private  ArtistService artistService;

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

    public void subscribeServiseTest(final String username, String serviseBean, final Class<?> expected) {
        Class<?> caught = null;
        startTransaction();
        try {

            this.authenticate(username);

            User result;
            Servise servise;

            servise = serviseService.findOne(getEntityId(serviseBean));
            result = userService.findByPrincipal();

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

    public void listServiseSubscribeTest(final String username,  final Class<?> expected) {
        Class<?> caught = null;
        startTransaction();
        try {

            this.authenticate(username);

            User result;

            result = userService.findByPrincipal();

            serviseService.servisesPerCreator(result.getId());

        } catch (final Throwable oops) {

            caught = oops.getClass();

        }

        this.checkExceptions(expected, caught);
        rollbackTransaction();

    }

    /*  FUNCTIONAL REQUIREMENT:
            * An actor who is authenticated as a stylist/makeup artist or a photographer must be able to:
               -. Manage his or her services which include create edit and delete.
    */


    public void manageServiseTest(final String username, String title, String description, String picture,
                                  boolean taboo, Double price, Double discount,  final Class<?> expected) {
        Class<?> caught = null;
        startTransaction();
        try {

            this.authenticate(username);

            Servise result;

            result = serviseService.create();

            result.setTitle(title);
            result.setDescription(description);
            result.setPicture(picture);
            result.setPrice(price);
            result.setDiscount(discount);
            result.setTaboo(taboo);

            Servise servise = serviseService.save(result);
            serviseService.findAll();
            serviseService.flush();
            serviseService.delete(servise);
            this.unauthenticate();

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
                        "user1", "servise1", null
                },

        };
        for (int i = 0; i < testingData.length; i++)
            this.subscribeServiseTest((String) testingData[i][0],
                     (String) testingData[i][1],(Class<?>) testingData[i][2]);
    }

    @Test
    public void driverListServiseSubscribeTest() {

        final Object testingData[][] = {
                // Un usuario se subscribe a un servise -> true
                {
                        "user1",  null
                },
                // Un stylist se subscribe a un servise -> false
                {
                        "stylist1",  IllegalArgumentException.class
                },
                // Un makeup se subscribe a un servise   -> false
                {
                        "makeup1",  IllegalArgumentException.class
                }

        };
        for (int i = 0; i < testingData.length; i++)
            this.listServiseSubscribeTest((String) testingData[i][0],
                    (Class<?>) testingData[i][1]);
    }

    @Test
    public void driverManageServiseTest() {

        Date moment = new Date(System.currentTimeMillis() - 1000);

        final Object testingData[][] = {
                // Alguien logueado como stylist con tod o correcto -> true
                {
                        "stylist", "title", "description1", "http://www.images.com", false, 10., 0.0, null
                },
                // Manage servise logueado como user -> false
                {
                        "user", "title", "description1", "http://www.images.com", false, 10., 0.0, IllegalArgumentException.class
                },
                // Descripcion en blanco -> false
                {
                        "stylist", "title", "", "http://www.images.com", false, 10., 0.0, ConstraintViolationException.class
                },

        };
        for (int i = 0; i < testingData.length; i++)
            this.manageServiseTest((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2],
                    (String) testingData[i][3], (boolean) testingData[i][4], (Double) testingData[i][5],
                    (Double) testingData[i][6], (Class<?>) testingData[i][7]);
    }
}
