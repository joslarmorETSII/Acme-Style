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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Transactional
@ContextConfiguration(locations = {
        "classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class AnswerServiceTest extends AbstractTest {

    // The SUT
    // ====================================================

    @Autowired
    private AnswerService answerService;

    @Autowired
    private QuestionService questionService;

    // Tests
    // ====================================================

     /*  FUNCTIONAL REQUIREMENT:
            * An actor who is authenticated as a stylist/makeup artist or a photographer must be able to:
               -. Manage his or her answers to the corresponding questions of their services.
    */

    public void manageAnswerTest(final String username, String text, String questionBean, final Class<?> expected) {
        Class<?> caught = null;
        startTransaction();
        Collection<Answer> answers = new ArrayList<Answer>();
        try {

            this.authenticate(username);

            Answer result;
            Question question;

            question = questionService.findOne(getEntityId(questionBean));

            result = answerService.create();
            result.setText(text);
            result.setQuestion(question);
            answerService.save(result);
            answers.add(result);

            question.setAnswers(answers);
            questionService.save(question);
            questionService.flush();

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
    public void driverManageAnswerTest() {

        final Object testingData[][] = {
                // Alguien logueado como photographer responde a una pregunta de su propio servicio -> true
                {
                        "photographer", "text1", "question1",  null
                },
                // Alguien logueado como user responde a una pregunta de su propio servicio-> false
                {
                        "user", "text1", "question1",  IllegalArgumentException.class
                },
                // Alguien logueado como photographer responde a una pregunta de su propio servicio con texto en blanco-> false
                {
                        "photographer", "", "question1",  ConstraintViolationException.class
                },

        };
        for (int i = 0; i < testingData.length; i++)
            this.manageAnswerTest((String) testingData[i][0], (String) testingData[i][1],
                    (String) testingData[i][2], (Class<?>) testingData[i][3]);
    }

}
