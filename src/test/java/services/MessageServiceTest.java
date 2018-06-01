package services;

import domain.Actor;
import domain.Folder;
import domain.Message;
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
public class MessageServiceTest extends AbstractTest {

    // The SUT
    // ====================================================

    @Autowired
    private MessageService messageService;

    @Autowired
    private FolderService folderService;


    // Tests
    // ====================================================

     /*  FUNCTIONAL REQUIREMENT:
            * An actor who is authenticated must be able to:
               -. Manages his or her messages including sending deleting and listing them.
    */

    public void manageMessageTest(final String username, String messageBean, Folder folder, final Class<?> expected) {
        Class<?> caught = null;
        startTransaction();
        try {

            this.authenticate(username);

            Message message1 = messageService.findOne(getEntityId(messageBean));
            Message message2 = messageService.findOne(getEntityId(messageBean));

            messageService.moveMessage(folder, message1);
            messageService.delete(message1);

            messageService.sendMessage(message2);

            messageService.findAll();

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

        Folder folder = new Folder();
        final Object testingData[][] = {
                // User con tod o correcto -> true
                {
                        "user1","message1",folder,null
                },
//                // Url incorrecta -> true
//                {
//                        "user1", moment, "description1", "images", 0, 0, 0, ConstraintViolationException.class
//                },
//                // Descripcion en blanco -> true
//                {
//                        "user1", moment, "", "http://www.images.com", 0, 0, 0, ConstraintViolationException.class
//                },

        };
        for (int i = 0; i < testingData.length; i++)
            this.manageMessageTest((String) testingData[i][0],(String) testingData[i][1], (Folder) testingData[i][2]
                    , (Class<?>) testingData[i][3]);
    }
}
