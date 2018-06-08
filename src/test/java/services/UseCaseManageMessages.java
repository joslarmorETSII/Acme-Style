package services;

import domain.Actor;
import domain.Folder;
import domain.Message;
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


@Transactional
@ContextConfiguration(locations = {
        "classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)


public class UseCaseManageMessages extends AbstractTest {

    // The SUT
    // ====================================================

    @Autowired
    private MessageService messageService;

    @Autowired
    private FolderService folderService;

    @Autowired
    private ActorService actorService;


    /*  CASO DE USO:
     *
     *   . An actor who is authenticated must be able to:
     *      - Exchange messages with other actors and manage them, which includes deleting and
     *        moving them from one folder to another folder.
     *
     *   . Un actor se loguea y manda un mensaje a otro usuario, borra un mensaje y le cambia la carpeta
     *   a otro mensaje.
     *
     *
     *
     *
     *   . COMO SE VA HA HACER?
     *
     *      En este caso de uso vamos a hacer tests positivos y negativos:
     *
     *   . Como caso positivo:
     *
     *   � Mandar Mensaje con datos validos estando logueado, borrar uno de sus mensajes que no esta alojado en la trashbox,
     *     finalmente cambiarlo de carpeta.
     *
     *
     *   . Para forzar el error pueden darse varios casos negativos, como son:
     *
     *
     *   � Dejar el subject vac�os.
     *   � Introducir un script en el subject del Message.
     *   . Introducir un script en el body del Message.
     *   � Borrar el Message de otro actor.
     *   . Mandar Message sin loguearse.
     *   � Mandar un mensaje a si mismo.
     */

    public void manageMessage( String username, String subject, String body,String priority,String folder,String beanRecipient,String beanMessage,
                               Class<?> expected) {
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


    @Test
    public void driverServiseEdit() {

        final Object testingData[][] = {

                // 1. Mandar un Message estando logueado como photographer y cambiar la carpeta a la trashbox del stylist1 -> true
                {
                        "photographer", "subject", "Body1","LOW", "trashbox5","stylist1","message2", null
                },
               // 2. Mandar un Message estando logueado como admin y cambiar la carpeta a la inbox del admin -> true
                {
                        "admin", "subject", "Body1","NEUTRAL", "inbox1","stylist1","message3", null
                },
                 // 3. Mandar un Message estando logueado como stylist sin rellenar el Subject -> false
                {
                        "stylist", "", "Body1","LOW", "trashbox2","user1","message1", ConstraintViolationException.class
                },
                // 4. Mandar un Message sin estar logueado -> false
                {
                        null, "subject", "Body1","NEUTRAL", "trashbox2","user1","message1", IllegalArgumentException.class
                },

                // 5. Mandar un Message estando logueado como photographer y borrar el mensaje de otro actor -> false
                {
                        "photographer", "subject", "Body1","HIGH", "trashbox5","user1","message3", IllegalArgumentException.class
                },

                // 6. Mandar un Message estando logueado como stylist seleccionando stylist como recipient -> false
                {
                        "stylist", "subject", "Body1","HIGH", "trashbox4","stylist1","message1", IllegalArgumentException.class
                },

                // 7. Mandar un Message estando logueado como stylist introduciendo un script en el body -> false
                {
                        "stylist", "subject", "<sql>","LOW", "trashbox4","photographer1","message1", ConstraintViolationException.class
                },

                // 8. Mandar un mensaje estando logueado con todos los campos vacios -> false
                {
                        "admin", "", "", "","trashbox1","stylist1","message3", ConstraintViolationException.class
                },

                // 9. Mandar un mensaje estando logueado como admin sin seleccion prioridad-> false
                {
                        "admin", "subject", "Body", "","trashbox1","user1","message3", ConstraintViolationException.class
                },
                // 10. Mandar un Message estando logueado como admin introduciendo un script en el subject -> false
                {
                        "admin", "<script>", "Body1","LOW", "trashbox1","user1","message3", ConstraintViolationException.class
                },


        };
        for (int i = 0; i < testingData.length; i++)
            this.manageMessage((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (String) testingData[i][4], (String) testingData[i][5],
                    (String) testingData[i][6],(Class<?>) testingData[i][7]);
    }


}
