package services;


import domain.Feedback;
import domain.Profile;
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

    public void feedbackServiceTest( String username, String servisebean, String text,Integer points,  Class<?> expected) {

        Class<?> caught = null;
        startTransaction();
        try {

            this.authenticate(username);

            User result;
            Servise servise = serviseService.findOne(getEntityId(servisebean));

            Feedback feedback = feedbackService.create();
            feedback.setServise(servise);
            feedback.setText(text);
            feedback.setPoints(points);

            Feedback saved = feedbackService.save(feedback);
            servise.getFeedbacks().add(feedback);
            serviseService.save(servise);

            feedbackService.flush();
            serviseService.flush();


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

                // User deja un feedback en un servicio donde esta suscrito-> true
                {
                        "user1", "servise4",  "text",4, null
                },
                // User deja un feedback en un servicio donde No esta suscrito-> false
                {
                        "user1", "servise3",  "text",4, IllegalArgumentException.class
                },
                // User deja un feedback con script-> false
                {
                        "user1", "servise4",  "<sql>",4, ConstraintViolationException.class
                },

        };
        for (int i = 0; i < testingData.length; i++)
            this.feedbackServiceTest((String) testingData[i][0], (String) testingData[i][1],(String) testingData[i][2], (Integer) testingData[i][3],(Class<?>) testingData[i][4]);
    }

}
