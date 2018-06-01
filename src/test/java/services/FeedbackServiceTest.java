package services;


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

@Transactional
@ContextConfiguration(locations = {
        "classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class FeedbackServiceTest extends AbstractTest {

    // The SUT
    // ====================================================

    @Autowired
    private FeedbackService feedbackService;

    @Autowired
    private UserService userService;

    @Autowired
    private ServiseService serviseService;

    // Tests
    // ====================================================

    /*  FUNCTIONAL REQUIREMENT:
            * An actor who is authenticated as user must be able to:
               -. Leave a feedback on the services where he or she has participated.
    */

    public void feedbackServiceTest(final String username, String userBean, final Class<?> expected) {

        Class<?> caught = null;
        startTransaction();
        try {

            this.authenticate(username);

            User result;

            result = userService.findOne(getEntityId(userBean));




        } catch (final Throwable oops) {

            caught = oops.getClass();

        }

        this.checkExceptions(expected, caught);
        rollbackTransaction();
    }

    //Drivers
    // ===================================================

    @Test
    public void driverFeedbackServiceTest() {

        final Object testingData[][] = {

                // Algun actor logueado con to do correcto-> true
                {
                        "user3", "user3", "user3", "userTestName", "userTestSurname", "+34 123456789", "userTest@userTest.com", "addressTest", "fullNameTest", "http://www.images.com", "educationTest", null
                },
                // Patrón del telefono erroneo -> false
                {
                        "userTest3", "userTest3", "userTest3", "userTestName3", "userTestSurname3", "635", "managerTest@managerTest.com", "12345", "fullNameTest", "http://www.images.com", "educationTest", ConstraintViolationException.class
                },
                // Todos los campos como null --> false
                {
                        null, null, null, null, null, null, null, null, null, null, null, ConstraintViolationException.class
                },

        };
        for (int i = 0; i < testingData.length; i++)
            this.feedbackServiceTest((String) testingData[i][0], (String) testingData[i][1],(Class<?>) testingData[i][2]);
    }

}
