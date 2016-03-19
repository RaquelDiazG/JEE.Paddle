package data.daos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.PersistenceConfig;
import config.TestsPersistenceConfig;
import data.entities.Authorization;
import data.entities.Court;
import data.entities.Role;
import data.entities.Training;
import data.entities.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class})
public class TrainingDaoITest {

    @Autowired
    private TrainingDao trainingDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private AuthorizationDao authorizationDao;

    @Autowired
    private CourtDao courtDao;

    @Autowired
    private DaosService daosService;

    private SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");

    // System.out.println(sdf.format(startDate.getTime()));

    @Test
    public void findTrainingByStartDateAndFinishDateAndCourt() {
        Court court = (Court) daosService.getMap().get("c1");
        // Invalid
        Calendar startDate = new GregorianCalendar(2000, Calendar.AUGUST, 14, 10, 00, 00);
        Calendar finishDate = new GregorianCalendar(2000, Calendar.AUGUST, 22, 10, 00, 00);
        assertNull(trainingDao.findByStartDateAndFinishDateAndCourt(startDate, finishDate, court));
        // Valid
        startDate = new GregorianCalendar(2015, Calendar.JANUARY, 15, 10, 00, 00);
        finishDate = new GregorianCalendar(2015, Calendar.JANUARY, 30, 10, 00, 00);
        assertNotNull(trainingDao.findByStartDateAndFinishDateAndCourt(startDate, finishDate, court));
    }

    @Test
    public void testFindByCourt() {
        Court court = (Court) daosService.getMap().get("c1");
        assertEquals(1, trainingDao.findTrainingsByCourt(court).size());
    }

    @Test
    public void testRegisterUserInTraining() {
        // crear usuario
        User newUser = new User("newUser", "newUser@gmail.com", "p", Calendar.getInstance());
        userDao.save(newUser);
        authorizationDao.save(new Authorization(newUser, Role.PLAYER));
        // registrar usuario en entrenamiento
        Training training = trainingDao.findOne(2);
        assertEquals(0, training.getUserList().size());
        trainingDao.registerTrainingPlayer(newUser, training);
        assertEquals(1, trainingDao.findOne(2).getUserList().size());
    }

    //
    // @Test
    // public void testDeleteUserInTraining() {
    // // Register user in training
    // User u0 = userDao.findByUsernameOrEmail("u0");
    // Training training = trainingDao.findAll().get(0);
    // trainingDao.registerUserInTraining(u0, training);
    // // Remove user from training
    // assertEquals(1, trainingDao.findAll().get(0).getUserList().size());
    // trainingDao.deleteUserInTraining(u0, training);
    // assertEquals(0, trainingDao.findAll().get(0).getUserList().size());
    // }
    //
    @Test
    public void testDeleteTraining() {
        // borramos el tercer entrenamiento (no contiene usuarios)
        assertEquals(3, trainingDao.findAll().size());
        Training training = trainingDao.findAll().get(2);
        trainingDao.delete(training);
        assertEquals(2, trainingDao.findAll().size());
    }

}
