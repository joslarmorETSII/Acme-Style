package services;


import domain.Actor;
import domain.Profile;
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
import java.util.Date;

@Transactional
@ContextConfiguration(locations = {
        "classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class ProfileServiceTest extends AbstractTest {

    // The SUT
    // ====================================================

    @Autowired
    private ActorService actorService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProfileService profileService;


    // Tests
    // ====================================================

    /*  FUNCTIONAL REQUIREMENT:
            * An actor who is authenticated must be able to:
               -. Edit his or her profile.
               -. Edit his or her personal data information.
    */

    public void actorEditTest(final String username, String actorBean, final String password, final String passwordRepeat,
                              final String name, final String surname, final String phone, final String email,
                              final String postalAddress, final String fullName, final String profilePhoto,
                              final String education,final Class<?> expected) {

        Class<?> caught = null;
        startTransaction();
        try {

            Actor result;
            Profile profile;
            result = actorService.findOne(getEntityId(actorBean));
            profile = result.getProfile();

            result.getUserAccount().setUsername(username);
            result.setName(name);
            result.setSurname(surname);
            result.setPhone(phone);
            result.setEmail(email);
            result.setPostalAddresses(postalAddress);
            result.getUserAccount().setPassword(new Md5PasswordEncoder().encodePassword(password, null));

            profile.setFullName(fullName);
            profile.setEducation(education);
            profile.setProfilePhoto(profilePhoto);

            result.setProfile(profile);

            this.actorService.save(result);
            userService.flush();


        } catch (final Throwable oops) {

            caught = oops.getClass();

        }

        this.checkExceptions(expected, caught);
        rollbackTransaction();
    }

    //Drivers
    // ===================================================

    @Test
    public void driverActorEditTest() {

        final Object testingData[][] = {

                // Algun actor logueado con to do correcto-> true
                {
                        "user1","user1", "user1", "user1", "userTestName", "userTestSurname", "+34 123456789", "userTest@userTest.com", "addressTest", "fullNameTest", "http://www.images.com", "educationTest", null
                },
                // Patrón del telefono erroneo -> false
                {
                        "user1", "user1",  "user1", "user1", "userTestName3", "userTestSurname3", "635", "managerTest@managerTest.com", "12345", "fullNameTest", "http://www.images.com", "educationTest", ConstraintViolationException.class
                },
                // User con tod o a null--> false
                {
                        "user1", "user1", "user1", "user1", null, null, null, null, null, null, null, null, ConstraintViolationException.class
                },

        };
        for (int i = 0; i < testingData.length; i++)
            this.actorEditTest((String) testingData[i][0], (String) testingData[i][1] , (String) testingData[i][2],
                    (String) testingData[i][3], (String) testingData[i][4], (String) testingData[i][5],
                    (String) testingData[i][6], (String) testingData[i][7], (String) testingData[i][8],
                    (String) testingData[i][9], (String) testingData[i][10], (String) testingData[i][11],
                    (Class<?>) testingData[i][12]);
    }

}
