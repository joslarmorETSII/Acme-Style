package services;

import domain.Actor;
import domain.Event;
import domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import utilities.AbstractTest;

import javax.transaction.Transactional;

@Transactional
@ContextConfiguration(locations = {
        "classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class EventServiceTest extends AbstractTest {

    // The SUT
    // ====================================================

    @Autowired
    private EventService eventService;

    @Autowired
    private UserService userService;

    // Tests
    // ====================================================

     /*  FUNCTIONAL REQUIREMENT:
            * An actor who is not authenticated must be able to:
               -. Browse the list of events and navigate to the corresponding store if any.
    */

    public void listEventTest(final String username,  final Class<?> expected) {
        Class<?> caught = null;
        startTransaction();
        try {

            this.eventService.findAll();

        } catch (final Throwable oops) {

            caught = oops.getClass();

        }

        this.checkExceptions(expected, caught);
        rollbackTransaction();

    }

     /*  FUNCTIONAL REQUIREMENT:
            * An actor who is authenticated as user must be able to:
               -. List the events of the system and the events in which he or she is
                  participating.
    */

    public void listEventUserParticipateTest(final String username, String actorBean,  final Class<?> expected) {
        Class<?> caught = null;
        startTransaction();
        try {

            this.eventService.findAll();

            User result;

            result = userService.findOne(getEntityId(actorBean));

            eventService.participatedEvents(result.getId());

        } catch (final Throwable oops) {

            caught = oops.getClass();

        }

        this.checkExceptions(expected, caught);
        rollbackTransaction();

    }

    //Drivers
    // ===================================================

    @Test
    public void driverListEventTest() {

        final Object testingData[][] = {
                // Alguien sin registrar/logueado -> true
                {
                        null, null
                }

        };
        for (int i = 0; i < testingData.length; i++)
            this.listEventTest((String) testingData[i][0],  (Class<?>) testingData[i][1]);
    }

    @Test
    public void driverListEventUserParticipateTest() {

        final Object testingData[][] = {
                // Alguien logueado como user con tod o correcto -> true
                {
                        "user1", "event1", null
                },
                // Alguien logueado como stylist con tod o correcto -> false
                {
                        "manager1", "", NullPointerException.class
                },


        };
        for (int i = 0; i < testingData.length; i++)
            this.listEventUserParticipateTest((String) testingData[i][0], (String) testingData[i][1],
                    (Class<?>) testingData[i][2]);
    }
}
