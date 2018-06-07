package services;

import domain.*;
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
public class AdminServiceTest extends AbstractTest{

    // The SUT
    // ====================================================

    @Autowired
    private AdministratorService administratorService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private ServiseService serviseService;

    @Autowired
    private EventService eventService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private AnswerService answerService;


    // Tests
    // ====================================================

    /*  FUNCTIONAL REQUIREMENT:
            * An actor who is authenticated an administrator must be able to:
               -. Navigate to the questions and their corresponding answers and delete the
                    inappropriate ones.
    */

    public void deleteInapropriateQuestionsTest( String username, String questionBean, final Class<?> expected) {
        Class<?> caught = null;
        startTransaction();
        try {
            this.authenticate(username);
            Question questionTodelete;

            questionTodelete = questionService.findOne(getEntityId(questionBean));
            questionService.delete(questionTodelete);


        } catch (final Throwable oops) {
            caught = oops.getClass();
        }

        this.checkExceptions(expected, caught);
        rollbackTransaction();

    }

    // Tests
    // ====================================================

    /*  FUNCTIONAL REQUIREMENT:
            * An actor who is authenticated an administrator must be able to:
               -. Navigate to the questions and their corresponding answers and delete the
                    inappropriate ones.
                    -> delete Answer
    */

    public void deleteInapropriateAnswerTest( String username, String answerBean, final Class<?> expected) {
        Class<?> caught = null;
        startTransaction();
        try {
            this.authenticate(username);
            Answer answer;

            answer = answerService.findOne(getEntityId(answerBean));
            answerService.delete(answer);
            answerService.flush();


        } catch (final Throwable oops) {
            caught = oops.getClass();
        }

        this.checkExceptions(expected, caught);
        rollbackTransaction();

    }


     /*  FUNCTIONAL REQUIREMENT:
            * An actor who is authenticated an administrator must be able to:
               -. List to the taboo services and delete them.
    */

    public void deleteTabooServicesTest( String username, String serviseBean, final Class<?> expected) {
        Class<?> caught = null;
        startTransaction();
        try {
            this.authenticate(username);
            Servise servise;

            serviseService.servisesTaboo();
            servise = serviseService.findOne(getEntityId(serviseBean));
            serviseService.delete(servise);
            eventService.flush();

        } catch (final Throwable oops) {
            caught = oops.getClass();
        }

        this.checkExceptions(expected, caught);
        rollbackTransaction();

    }

      /*  FUNCTIONAL REQUIREMENT:
            * An actor who is authenticated an administrator must be able to:
               -. List the events of the system and delete the inappropriate ones..
    */

    public void deleteInapropriateEventsTest( String username, String eventBean, final Class<?> expected) {
        Class<?> caught = null;
        startTransaction();
        try {
            this.authenticate(username);
            Event event;

            eventService.findAll();
            event = eventService.findOne(getEntityId(eventBean));
            eventService.delete(event);
            eventService.flush();

        } catch (final Throwable oops) {
            caught = oops.getClass();
        }

        this.checkExceptions(expected, caught);
        rollbackTransaction();

    }

     /*  FUNCTIONAL REQUIREMENT:
            * An actor who is authenticated an administrator must be able to:
               -.   Manage the taxonomy of categories, which involves creating, listing,
                        modifying, and deleting them.
    */

    public void createEditdeleteCategoryTest( String username, String categoryToDeleteBean, String categoryToEditBean,
                                              String name,final Class<?> expected) {
        Class<?> caught = null;
        startTransaction();
        try {
            this.authenticate(username);
            Category categoryToDelete;
            Category categoryToEdit;
            Category created;

            categoryService.findAll();
            categoryToDelete = categoryService.findOne(getEntityId(categoryToDeleteBean));
            categoryService.delete(categoryToDelete);
            eventService.flush();

            categoryToEdit = categoryService.findOne(getEntityId(categoryToEditBean));
            categoryToEdit.setName(name);
            categoryService.save(categoryToEdit);

            created = categoryService.create();
            created.setName(name);

            categoryService.flush();


        } catch (final Throwable oops) {
            caught = oops.getClass();
        }

        this.checkExceptions(expected, caught);
        rollbackTransaction();

    }

    //Drivers
    // ===================================================


    @Test
    public void driverDeleteInapropriateQuestionsTest() {

        final Object testingData[][] = {
                // Alguien logueado como admin borra una pregunta que considera inapropiada -> true
                {
                        "admin", "question1", null
                },
                // Alguien din loguearse intenta borrar una pregunta -> false
                {
                        null, "question1", IllegalArgumentException.class
                },


        };
        for (int i = 0; i < testingData.length; i++)
            this.deleteInapropriateQuestionsTest((String) testingData[i][0], (String) testingData[i][1],
                    (Class<?>) testingData[i][2]);
    }


    @Test
    public void driverDeleteInapropriateAnswerTest() {

        final Object testingData[][] = {
                // Alguien logueado como admin borra una respuesta que considera inapropiada -> true
                {
                        "admin", "answer1", null
                },
                // Alguien din loguearse intenta borrar una respuesta -> false
                {
                        null, "answer1", IllegalArgumentException.class
                },

        };
        for (int i = 0; i < testingData.length; i++)
            this.deleteInapropriateAnswerTest((String) testingData[i][0], (String) testingData[i][1],
                    (Class<?>) testingData[i][2]);
    }

    @Test
    public void driverdeleteInapropriateEventsTest() {

        final Object testingData[][] = {
                // Alguien logueado como adminborra un evento que considera inapropiado -> true
                {
                        "admin", "event1",null
                },
                // Alguien sin loguearse intenta borrar un evento  -> false
                {
                        null, "event1",IllegalArgumentException.class
                },
                // Alguien logueado como manager un evento  -> false
                {
                        "manager", "event1",IllegalArgumentException.class
                },

        };
        for (int i = 0; i < testingData.length; i++)
            this.deleteInapropriateEventsTest((String) testingData[i][0], (String) testingData[i][1],
                     (Class<?>) testingData[i][2]);
    }

    @Test
    public void driverDeleteInapropriateEventsTest() {

        final Object testingData[][] = {
                // Alguien logueado como admin crea, borra y edita catgorias -> true
                {
                        "admin", "category5","category4","NewOne",null
                },

                // Alguien logueado como admin crea, borra y edita catgorias sin proporcionar titulo a la categoria-> false
                {
                        "admin", "category5","category4","", ConstraintViolationException.class
                },
                // Alguien logueado como admin crea, borra una categoria que no se debe borrar y edita catgorias -> false
                {
                        "admin", "category1","category4","NewOne",IllegalArgumentException.class
                },

        };
        for (int i = 0; i < testingData.length; i++)
            this.createEditdeleteCategoryTest((String) testingData[i][0], (String) testingData[i][1],(String) testingData[i][2],
                    (String) testingData[i][3],(Class<?>) testingData[i][4]);
    }

}
