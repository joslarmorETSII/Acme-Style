package services;

import domain.Post;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
public class PostServiceTest extends AbstractTest {

    // The SUT
    // ====================================================

    @Autowired
    private PostService postService;

    // Tests
    // ====================================================

     /*  FUNCTIONAL REQUIREMENT:
            * An actor who is authenticated must be able to:
               -. Manage his or her posts including create, listing them.
    */

    public void managePostTest(final String username, String title, Date moment, String description,
                               String picture, int lik, int dislike, int heart, final Class<?> expected) {
        Class<?> caught = null;
        startTransaction();
        try {

            this.authenticate(username);

            Post result;

            result = postService.create();

            result.setTitle(title);
            result.setMoment(moment);
            result.setDescription(description);
            result.setPicture(picture);
            result.setLik(lik);
            result.setDislike(dislike);
            result.setHeart(heart);

            postService.save(result);
            postService.findAll();
            postService.delete(result);
            postService.flush();

        } catch (final Throwable oops) {

            caught = oops.getClass();

        }

        this.checkExceptions(expected, caught);
        rollbackTransaction();

    }

    //Drivers
    // ===================================================

    @Test
    public void driverManagePostTest() {

        Date moment = new Date();

        final Object testingData[][] = {
                // User con tod o correcto -> true
                {
                        "user1", "user1", moment, "description1", "http://www.images.com", 0, 0, 0, null
                },
                // Url incorrecta -> true
                {
                        "user1", "user1", moment, "description1", "images", 0, 0, 0, ConstraintViolationException.class
                },
                // Descripcion en blanco -> true
                {
                        "user1", "user1", moment, "", "http://www.images.com", 0, 0, 0, ConstraintViolationException.class
                },

        };
        for (int i = 0; i < testingData.length; i++)
            this.managePostTest((String) testingData[i][0], (String) testingData[i][1], (Date) testingData[i][2],
                    (String) testingData[i][3], (String) testingData[i][4], (int) testingData[i][5],
                    (int) testingData[i][6], (int) testingData[i][7], (Class<?>) testingData[i][8]);
    }
}
