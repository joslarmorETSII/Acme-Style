package services;

import domain.Actor;
import domain.Panel;
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
public class PanelServiceTest extends AbstractTest {

    // The SUT
    // ====================================================

    @Autowired
    private PanelService panelService;

    @Autowired
    private ActorService actorService;

    // Tests
    // ====================================================

     /*  FUNCTIONAL REQUIREMENT:
            * An actor who is authenticated as user must be able to:
               -. Manage his or her panels which include creating, editing and deleting them.
    */

    public void managePanelTest(final String username, String name, final Class<?> expected) {
        Class<?> caught = null;
        startTransaction();
        try {

            this.authenticate(username);

            Panel result;

            result = panelService.create();

            result.setName(name);

            panelService.save(result);
            panelService.findAll();
            panelService.delete(result);
            panelService.flush();

        } catch (final Throwable oops) {

            caught = oops.getClass();

        }

        this.checkExceptions(expected, caught);
        rollbackTransaction();

    }



    //Drivers
    // ===================================================

    @Test
    public void driverManagePanelTest() {

        Date moment = new Date();

        final Object testingData[][] = {
                // User con tod o correcto -> true
                {
                        "user1", "panel1",  null
                },
                // User con el nombre del panel null -> true
                {
                        "user1", null,  ConstraintViolationException.class
                },
                // Manage panel como stylist-> true
                {
                        "stylist1", "panel1", IllegalArgumentException.class
                },

        };
        for (int i = 0; i < testingData.length; i++)
            this.managePanelTest((String) testingData[i][0], (String) testingData[i][1],
                    (Class<?>) testingData[i][2]);
    }


}
