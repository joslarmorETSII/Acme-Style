package services;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ActorServiceTest extends AbstractTest {

    // The SUT
    // ====================================================

    @Autowired
    private ActorService actorService;


    // Tests
    // ====================================================

    /*  FUNCTIONAL REQUIREMENT:
            * An actor who is not authenticated must be able to:
               -. Login to the system using his/her credentials.
    */

    public void actorLoginTest(final String username, final String password, final Class<?> expected) {
        Class<?> caught = null;
        startTransaction();
        try {

            this.authenticate(username);

        } catch (final Throwable oops) {

            caught = oops.getClass();

        }

        this.checkExceptions(expected, caught);
        rollbackTransaction();

    }

    //Drivers
    // ===================================================

    @Test
    public void driverActorLoginTest() {

        final Object testingData[][] = {
                // Alguien sin registrar/logueado -> true
                {
                        "user1", "user1", "user1", null
                },
//                // Todos los campos como null --> false
//                {
//                        null, null, null, null, null, null, null, null, null, ConstraintViolationException.class
//                },
//                // Todos los campos completados, excepto la direccion postal -> true
//                {
//                        "agentTest3", "agentTest3", "agentTest3", "agentTestName3", "agentTestSurname3","+34123456789", "agentTest@agentTest.com", "",  null
//                },
//                // Username size menor que 5-> false
//                {
//                        "age", "agentTest3", "agentTest3", "agentTestName3", "agentTestSurname3","+34123456789", "agentTest@agentTest.com", "",  ConstraintViolationException.class
//                },
//                // Todos los campos completados, introduciendo un <script> en el nombre -> false
//                {
//                        "agent343", "agent343", "agent343", "<script>", "agentTestSurname43","+34123456789", "agentTest@agentTest.com", "",  ConstraintViolationException.class
//                },

        };
        for (int i = 0; i < testingData.length; i++)
            this.actorLoginTest((String) testingData[i][0],  (String) testingData[i][1], (Class<?>) testingData[i][2]);
    }
}
