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
import java.util.ArrayList;
import java.util.Collection;
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

    @Autowired
    private ActorService actorService;


    // Tests
    // ====================================================

     /*  FUNCTIONAL REQUIREMENT:
            * An actor who is authenticated must be able to:
               -. Manages his or her messages including sending deleting and listing them.
    */

    public void manageMessageTest( String username, String subject, String body,String priority, String folder,
                                   String beanRecipient,String beanMessage, Class<?> expected) {
        Class<?> caught = null;
        startTransaction();
        Collection<Actor> recipients = new ArrayList<>();
        try {
            this.authenticate(username);

            Actor recipient = actorService.findOne(getEntityId(beanRecipient));
            Message messageTodelete = messageService.findOne(getEntityId(beanMessage));
            Message message1 = messageService.create();

            message1.setSubject(subject);
            message1.setBody(body);
            message1.setPriority(priority);
            recipients.add(recipient);
            message1.setActorReceivers(recipients);

            messageService.sendMessage(message1);
            messageService.delete(messageTodelete);

            Folder folder1 = folderService.findOne(getEntityId(folder));
            messageTodelete.setFolder(folder1);
            messageService.save(messageTodelete);


            messageService.flush();

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
    public void driverManageMessageTest() {


        final Object testingData[][] = {
                // Mandar un Message estando logueado como Photographer y cambiar la carpeta a la trashbox del Stylist -> true
                {
                        "photographer", "subject", "Body1", "LOW",  "trashbox5", "stylist1", "message2", null
                },
                // Mandar un Message estando logueado como photographer y borrar el mensaje de otro actor -> false
                {
                        "photographer", "subject", "Body1","HIGH", "trashbox5","stylist1","message1", IllegalArgumentException.class
                },
                // Mandar un Message estando logueado como photographer seleccionando photographer como recipient -> false
                {
                        "photographer", "subject", "Body1","HIGH", "trashbox5","photographer1","message2", IllegalArgumentException.class
                },

        };
        for (int i = 0; i < testingData.length; i++)
            this.manageMessageTest((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (String) testingData[i][4], (String) testingData[i][5],
                    (String) testingData[i][6],(Class<?>) testingData[i][7]);
    }
}
