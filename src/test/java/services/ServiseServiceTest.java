package services;

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

    // Tests
    // ====================================================

     /*  FUNCTIONAL REQUIREMENT:
            * An actor who is not authenticated must be able to:
               -. Browse the list of published services and navigate to the corresponding
               creator?s profile which must include personal data.

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

    //Drivers
    // ===================================================

    @Test
    public void driverListServiseTest() {

        final Object testingData[][] = {
                // Alguien sin registrar/logueado -> true
                {
                        null, null
                },
                // Todos los campos como null --> false
                {
                        "user1", ConstraintViolationException.class
                },
                // Todos los campos completados, introduciendo un <script> en el nombre -> false
                {
                        "",  ConstraintViolationException.class
                },

        };
        for (int i = 0; i < testingData.length; i++)
            this.listServiseTest((String) testingData[i][0],  (Class<?>) testingData[i][1]);
    }
}
