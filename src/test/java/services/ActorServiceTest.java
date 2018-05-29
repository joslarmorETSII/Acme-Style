package services;


import domain.Actor;
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
public class ActorServiceTest extends AbstractTest {

    // The SUT
    // ====================================================

    @Autowired
    private ActorService actorService;


    // Tests
    // ====================================================



}
