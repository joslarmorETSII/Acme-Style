package services;

import com.sun.xml.internal.bind.v2.TODO;
import domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import utilities.AbstractTest;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Collection;

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

    @Autowired
    private ParticipateService participateService;

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

    public void listEventUserParticipateTest(final String username, Integer CVV, Integer expirationMonth,
                                             Integer expirationYear, String brand, String holder, String number,
                                             String eventBean, final Class<?> expected) {
        Class<?> caught = null;
        startTransaction();
        Collection<Participate> participates = new ArrayList<Participate>();
        try {

           this.authenticate(username);

           User user;
           Participate participate;
           Event event;

           event = eventService.findOne(getEntityId(eventBean));
           CreditCard creditCard = new CreditCard();
           participate = participateService.create();
           user = userService.findByPrincipal();

           creditCard.setCvv(CVV);
           creditCard.setExpirationMonth(expirationMonth);
           creditCard.setExpirationYear(expirationYear);
           creditCard.setBrand(brand);
           creditCard.setHolder(holder);
           creditCard.setNumber(number);

           participate.setCreditCard(creditCard);
           participate.setEvent(event);
           participateService.save(participate);
           participates.add(participate);

           user.setParticipates(participates);

           eventService.participatedEvents(user.getId());

           eventService.flush();;
           participateService.flush();
           userService.flush();;

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
                        "user1", 123, 12, 2018, "Holder 2", "visa", "4785530860520625","event1", null

                },
                // Actor a null -> false
                {
                        null, 123, 12, 2018, "Holder 2", "visa", "4785530860520625","event1", IllegalArgumentException.class
                },

                //  Alguien logueado como user con cvv incorrecto-> false
                {
                        "user1", 99, 12, 2018, "Holder 2", "visa", "4785530860520625", "event1", ConstraintViolationException.class
                },


        };
        for (int i = 0; i < testingData.length; i++)
            this.listEventUserParticipateTest((String) testingData[i][0],
                    (Integer) testingData[i][1], (Integer) testingData[i][2],
                    (Integer) testingData[i][3], (String) testingData[i][4], (String) testingData[i][5],
                    (String) testingData[i][6],(String) testingData[i][7], (Class<?>) testingData[i][8]);
    }
}
