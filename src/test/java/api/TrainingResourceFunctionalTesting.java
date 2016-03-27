package api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.logging.log4j.LogManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.HttpClientErrorException;

import business.api.Uris;
import business.wrapper.TrainingWrapper;
import config.PersistenceConfig;
import config.TestsPersistenceConfig;
import data.daos.UserDao;
import data.entities.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class})
public class TrainingResourceFunctionalTesting {

    RestService restService = new RestService();

    @Autowired
    private UserDao userDao;

    @Before
    public void init() {
        restService.createCourt("10");
        restService.registerTrainer(11);
        restService.registerPlayer(12);
        User user = userDao.findByUsernameOrEmail("u" + 11);
        restService.createTraining(new GregorianCalendar(2016, 10, 14, 12, 00, 00), new GregorianCalendar(2016, 10, 22, 12, 00, 00), 10,
                user.getId());
    }

    @Test
    public void testShowTrainings() {
        String token = restService.loginTrainer();
        String response = new RestBuilder<String>(RestService.URL).path(Uris.TRAININGS).basicAuth(token, "").clazz(String.class).get()
                .build();
        LogManager.getLogger(this.getClass()).info("testShowTrainings (" + response + ")");
    }

    @Test
    public void testCreateTraining() {
        User user = userDao.findByUsernameOrEmail("u" + 11);
        restService.createTraining(new GregorianCalendar(2018, 10, 14, 12, 00, 00), new GregorianCalendar(2018, 10, 22, 12, 00, 00), 10,
                user.getId());
    }

    @Test
    public void testCreateTrainingUnauthorized() {
        try {
            Calendar startDate = new GregorianCalendar(2017, 10, 14, 12, 00, 00);
            Calendar finishDate = new GregorianCalendar(2017, 10, 22, 12, 00, 00);
            User user = userDao.findByUsernameOrEmail("u" + 11);
            TrainingWrapper trainingWrapper = new TrainingWrapper(startDate, finishDate, 10, user.getId());
            new RestBuilder<Object>(RestService.URL).path(Uris.TRAININGS).body(trainingWrapper).post().build();
            fail();
        } catch (HttpClientErrorException httpError) {
            assertEquals(HttpStatus.UNAUTHORIZED, httpError.getStatusCode());
            LogManager.getLogger(this.getClass())
                    .info("testCreateTraining (" + httpError.getMessage() + "):\n    " + httpError.getResponseBodyAsString());
        }
    }

    @Test
    public void testDeleteTraining() {
        String token = restService.loginTrainer();
        // restService.createTraining("1");
        // new RestBuilder<Object>(RestService.URL).path(Uris.TRAININGS).param("id", "1").delete().build();
    }

    @Test
    public void testDeleteTrainingUnauthorized() {
        String token = restService.loginTrainer();
        // TODO
    }

    @Test
    public void testRegisterTrainingPlayer() {
        String token = restService.loginTrainer();
        // TODO
    }

    @Test
    public void testRegisterTrainingPlayerUnauthorized() {
        String token = restService.loginTrainer();
        // TODO
    }

    @Test
    public void testDeleteTrainingPlayer() {
        String token = restService.loginTrainer();
        // TODO
    }

    @Test
    public void testDeleteTrainingPlayerUnauthorized() {
        String token = restService.loginTrainer();
        // TODO
    }

    @After
    public void deleteAll() {
        new RestService().deleteAll();
    }

}
