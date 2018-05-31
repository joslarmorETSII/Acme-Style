package services;

import domain.Manager;
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
public class ManagerServiceTest extends AbstractTest {

    // The SUT
    // ====================================================

    @Autowired
    private ManagerService managerService;

    // Tests
    // ====================================================

     /*  FUNCTIONAL REQUIREMENT:
            * An actor who is not authenticated must be able to:
               -. Register to the system as a manager.
               -. Login to the system using his/her credentials.
    */

    public void managerRegisterLoginTest(final String username, final String password, final String passwordRepeat, final String name, final String surname, final String phone, final String email, final String postalAddress,  final Class<?> expected) {
        Class<?> caught = null;
        startTransaction();
        try {

            final Manager result;

            result = this.managerService.create();

            result.getUserAccount().setUsername(username);
            result.setName(name);
            result.setSurname(surname);
            result.setPhone(phone);
            result.setEmail(email);
            result.setPostalAddresses(postalAddress);


            result.getUserAccount().setPassword(new Md5PasswordEncoder().encodePassword(password, null));

            this.managerService.save(result);
            managerService.flush();

            this.authenticate(username);

        } catch (final Throwable oops) {

            caught = oops.getClass();

        }

        this.checkExceptions(expected, caught);
        rollbackTransaction();

    }

    //Drivers
    // ===================================================

    @Test
    public void driverManagerRegisterLoginTest() {

        final Object testingData[][] = {
                // Alguien sin registrar/logueado como manager -> true
                {
                        "manager33", "manager33", "manager33", "managerTestName", "managerTestSurname", "+34 123456789", "managerTest@Test.com", "addressManager",  null
                },
                // Todos los campos como null --> false
                {
                        null, null, null, null, null, null, null, null, ConstraintViolationException.class
                },
                // Todos los campos completados, introduciendo un <script> en el nombre -> false
                {
                        "manager33", "manager33", "manager33", "<script>", "manager33TestSurname43","+34123456789", "manager33Test@manager33Test.com", "adressManager33",  ConstraintViolationException.class
                },
        };
        for (int i = 0; i < testingData.length; i++)
            this.managerRegisterLoginTest((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (String) testingData[i][4], (String) testingData[i][5], (String) testingData[i][6],
                    (String) testingData[i][7], (Class<?>) testingData[i][8]);
    }
}
