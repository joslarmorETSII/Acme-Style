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

@Transactional
@ContextConfiguration(locations = {
        "classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class StoreServiceTest extends AbstractTest{

    // The SUT
    // ====================================================

    @Autowired
    private StoreService storeService;

    @Autowired
    private ServiseService serviseService;


    // Tests
    // ====================================================

    /*  FUNCTIONAL REQUIREMENT:
            * An actor who is authenticated as manager must be able to:
               -. Manage his or her stores which includes creating, editing listing
                    and deleting theme as long as it has no associated service.

                    -> List and create a store.
    */

    public void listEditStoresTest( String username, String title,String banner,String serviseBean,String locationName,
                                    Double latitud,Double longitud, String credtiCardBean, final Class<?> expected) {
        Class<?> caught = null;
        startTransaction();
        try {
            Manager manager;
            Servise servise;
            CreditCard creditCard;
            Store store;
            GpsCoordinates location;


            this.authenticate(username);

            serviseService.findAll();
            servise = serviseService.findOne(getEntityId(serviseBean));
            Collection<Servise> servises = new ArrayList<Servise>();
            servises.add(servise);
            creditCard = new CreditCard();
            location = new GpsCoordinates();

            location.setName(locationName);
            location.setLatitude(latitud);
            location.setLongitude(longitud);

            creditCard.setCvv(333);
            creditCard.setExpirationYear(2020);
            creditCard.setExpirationMonth(12);
            creditCard.setBrand("Visa");
            creditCard.setHolder("Manager");
            creditCard.setNumber("6011146209895718");

            store = storeService.create();

            store.setTitle(title);
            store.setBanner(banner);
            store.setGpsCoordinates(location);
            store.setCreditCard(creditCard);
            store.setServises(servises);
            storeService.save(store);
            storeService.flush();

        } catch (final Throwable oops) {

            caught = oops.getClass();

        }

        this.checkExceptions(expected, caught);
        rollbackTransaction();

    }

    // Tests
    // ====================================================

    /*  FUNCTIONAL REQUIREMENT:
            * An actor who is authenticated as manager must be able to:
               -. Manage his or her stores which includes creating, editing listing
                    and deleting theme as long as it has no associated service.

                    ->Edit a store
    */

    public void createStoresTest( String username, String storeBean,String title,String banner,String locationName,
                                    Double latitud,Double longitud, final Class<?> expected) {
        Class<?> caught = null;
        startTransaction();
        try {
            CreditCard creditCard;
            GpsCoordinates location;
            Store storeToEdit;

            this.authenticate(username);


            storeToEdit = storeService.findOneToedit(getEntityId(storeBean));
            creditCard = new CreditCard();
            location = new GpsCoordinates();

            location.setName(locationName);
            location.setLatitude(latitud);
            location.setLongitude(longitud);

            creditCard.setCvv(333);
            creditCard.setExpirationYear(2020);
            creditCard.setExpirationMonth(12);
            creditCard.setBrand("Visa");
            creditCard.setHolder("Manager");
            creditCard.setNumber("6011146209895718");

            storeToEdit.setTitle(title);
            storeToEdit.setBanner(banner);
            storeToEdit.setGpsCoordinates(location);
            storeToEdit.setCreditCard(creditCard);

            storeService.save(storeToEdit);
            storeService.flush();

        } catch (final Throwable oops) {

            caught = oops.getClass();

        }

        this.checkExceptions(expected, caught);
        rollbackTransaction();

    }

    // Tests
    // ====================================================

    /*  FUNCTIONAL REQUIREMENT:
            * An actor who is authenticated as manager must be able to:
               -. Sponsor a service by associating a store to the service.
    */

    public void sponsorAServiceTest( String username, String storeBean,String serviseBean, final Class<?> expected) {
        Class<?> caught = null;
        startTransaction();
        try {
            Store store;
            Servise servise;

            this.authenticate(username);


            store = storeService.findOneToedit(getEntityId(storeBean));
            servise = serviseService.findOne(getEntityId(serviseBean));
            Collection<Servise> servises = new ArrayList<Servise>();
            servises.add(servise);
            servise.getStores().add(store);
            store.setServises(servises);

            serviseService.save(servise);
            serviseService.flush();
            storeService.save(store);
            storeService.flush();


        } catch (final Throwable oops) {

            caught = oops.getClass();

        }

        this.checkExceptions(expected, caught);
        rollbackTransaction();

    }

    //Drivers
    // ===================================================

    @Test
    public void driverCreateStoresTest() {

        final Object testingData[][] = {
                // Alguien logueado como manager edita una de sus tiendas -> true
                {
                        "manager", "store1","title","http://www.banner.com","Sevilla",90.0,90.0,null
                },
                // Alguien logueado como user edita una tienda -> false
                {
                        "user1", "store1","title","http://www.banner.com","Sevilla",90.0,90.0,IllegalArgumentException.class
                },
                // Alguien logueado como manager edita una de sus tiendas sin rellenar un campo obligatorio-> false
                {
                        "manager", "store1","","http://www.banner.com","Sevilla",90.0,90.0,ConstraintViolationException.class
                },


        };
        for (int i = 0; i < testingData.length; i++)
            this.createStoresTest((String) testingData[i][0], (String) testingData[i][1],(String) testingData[i][2],(String) testingData[i][3],
                    (String) testingData[i][4],(Double) testingData[i][5],(Double) testingData[i][6],
                    (Class<?>) testingData[i][7]);
    }



    @Test
    public void driverListEditStoresTest() {

        final Object testingData[][] = {
                // Alguien logueado como manager lista y crea una tienda -> true
                {
                        "manager", "title","http://www.banner.com","servise1","Sevilla",90.0,90.0,"creditCard1",null
                },
                // Alguien sin loguearse lista y crea una tienda -> false
                {
                        null, "title","http://www.banner.com","servise1","Sevilla",90.0,90.0,"creditCard1",IllegalArgumentException.class
                },
                // Alguien logueado como manager lista y crea una tienda  sin rellenar un campo obligatorio-> false
                {
                        "manager", "title","http://www.banner.com","servise1","",90.0,90.0,"creditCard1", ConstraintViolationException.class
                },

        };
        for (int i = 0; i < testingData.length; i++)
            this.listEditStoresTest((String) testingData[i][0], (String) testingData[i][1],(String) testingData[i][2],
                    (String) testingData[i][3],(String) testingData[i][4],(Double) testingData[i][5],(Double) testingData[i][6],
                    (String) testingData[i][7],(Class<?>) testingData[i][8]);
    }

    @Test
    public void driverSponsorServiseTest() {

        final Object testingData[][] = {
                // Alguien logueado como manager patrocina un servicio -> true
                {
                        "manager", "store1","servise1",null
                },

                // Alguien logueado como Stylist patrocina un servicio -> false
                {
                        "stylist", "store1","servise1",IllegalArgumentException.class
                },

                // Alguien sin loguearse patrocina un servicio -> false
                {
                       null , "store1","servise1",IllegalArgumentException.class
                },



        };
        for (int i = 0; i < testingData.length; i++)
            this.sponsorAServiceTest((String) testingData[i][0], (String) testingData[i][1],(String) testingData[i][2],
                    (Class<?>) testingData[i][3]);
    }
}
