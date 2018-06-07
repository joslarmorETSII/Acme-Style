package services;

import domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import utilities.AbstractTest;

import javax.transaction.SystemException;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.Collection;
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

    @Autowired
    private ActorService actorService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private ActionService actionService;


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

    /*  FUNCTIONAL REQUIREMENT:
            * An actor who is authenticated must be able to:
               -. Display the posts of all the actors that he or she follows.
    */

    public void displayPostTest(final String username,  final Class<?> expected) {
        Class<?> caught = null;
        startTransaction();
        try {

            this.authenticate(username);

            Actor result;

            result = actorService.findByPrincipal();
            actorService.postByFollowings(result.getId());

        } catch (final Throwable oops) {

            caught = oops.getClass();

        }

        this.checkExceptions(expected, caught);
        rollbackTransaction();

    }

    /*  FUNCTIONAL REQUIREMENT:
            * An actor who is authenticated as a stylist/makeup artist or a photographer must be able to:
               -. Create a raffle and a post it. A raffle can be deleted while it has no
                participates or the winner is selected.
                -. Edit raffle as long as it is not published.
    */

    public void createRaffleTest(final String username,String title, Date moment, String description,
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
            result.setRaffle(true);
            result.setEndDate(new Date());

            postService.save(result);

            result.setTitle(title);
            result.setFinalMode(true);
            postService.save(result);

            postService.delete(result);
            postService.flush();

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

    @Test
    public void driverDisplayPostTest() {

        final Object testingData[][] = {
                // User con tod o correcto -> true
                {
                        "user1", null
                },
                // Stylist con tod o correcto -> true
                {
                        "stylist", null
                },
                // Photograph con tod o correcto -> true
                {
                        "photographer", null
                },

        };
        for (int i = 0; i < testingData.length; i++)
            this.displayPostTest((String) testingData[i][0], (Class<?>) testingData[i][1]);
    }

    @Test
    public void driverCreateRaffleTest() {

        Date moment = new Date(System.currentTimeMillis()-1000);

        final Object testingData[][] = {
                // Alguien logueado como Stylist crea un raffle y lo borra -> true
                {
                        "stylist", "title", moment, "description1", "http://www.images.com", 0, 0, 0, null
                },
                // Alguien logueado como user crea un raffle y lo borra-> false
                {
                        "user1", "title", moment, "description1", "images", 0, 0, 0, ConstraintViolationException.class
                },
                // Alguien logueado como stylist con descripcion vacia-> false
                {
                        "stylist", "title", moment, "", "http://www.images.com", 0, 0, 0, ConstraintViolationException.class
                },

        };
        for (int i = 0; i < testingData.length; i++)
            this.createRaffleTest((String) testingData[i][0], (String) testingData[i][1], (Date) testingData[i][2],
                    (String) testingData[i][3], (String) testingData[i][4], (int) testingData[i][5],
                    (int) testingData[i][6], (int) testingData[i][7], (Class<?>) testingData[i][8]);
    }



     /*  FUNCTIONAL REQUIREMENT:
            * An actor who is authenticated as user must be able to:
               -. Participate in raffles.

    */

    public void participateRaffleTest( String username,String raffleBean, String commentText,  Class<?> expected) {
        Class<?> caught = null;
        startTransaction();
        try {

            this.authenticate(username);

            User principal;
            Comment comment;
            Action action;
            Post raffle = postService.findOne(getEntityId(raffleBean));
            principal = userService.findByPrincipal();
            comment = commentService.create();
            comment.setText(commentText);
            comment.setPost(raffle);

            Comment saved = commentService.save(comment);
            principal.getComments().add(comment);
            raffle.getComments().add(saved);

            action = actionService.create();
            action.setLik(true);
            action.setPost(raffle);
            Action actionSaved = actionService.save(action);

            raffle.getActions().add(actionSaved);
            postService.likePost(raffle);
            userService.save(principal);

            actionService.flush();
            postService.flush();
            userService.flush();

        } catch (final Throwable oops) {

            caught = oops.getClass();

        }

        this.checkExceptions(expected, caught);
        rollbackTransaction();

    }


    @Test
    public void driverParticipateRaffleTest() {

        Date moment = new Date(System.currentTimeMillis()-1000);

        final Object testingData[][] = {
                //  Algiuen logueado como Usuario participa en un raffle -> true
                {
                        "user1", "post2", "comment1", null
                },
                //  Algiuen logueado como Usuario participa en un raffle dejando un comentario con script en el texto -> false
                {
                        "user1", "post2", "<script>", ConstraintViolationException.class
                },
                //  Algiuen sin loguearse participa en un raffle -> false
                {
                        null, "post2", "Commentario1", IllegalArgumentException.class
                },


        };
        for (int i = 0; i < testingData.length; i++)
            this.participateRaffleTest((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2],
                      (Class<?>) testingData[i][3]);
    }
}
