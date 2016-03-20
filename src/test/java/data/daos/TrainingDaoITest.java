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
import data.entities.Court;
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
    private ReserveDao reserveDao;

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
    public void testRegisterTrainingPlayer() {
        // entrenamiento con menos de 4 alumnos
        User u1 = (User) daosService.getMap().get("u1");
        Training training = trainingDao.findOne(2);
        assertEquals(0, training.getUserList().size());
        trainingDao.registerTrainingPlayer(u1, training);
        assertEquals(1, trainingDao.findOne(2).getUserList().size());
        // entrenamiento con más de 4 alumnos
        // User u2 = (User) daosService.getMap().get("u2");
        // trainingDao.registerTrainingPlayer(u2, training);
        // assertEquals(2, trainingDao.findOne(2).getUserList().size());
        // User u3 = (User) daosService.getMap().get("u3");
        // trainingDao.registerTrainingPlayer(u3, training);
        // assertEquals(3, trainingDao.findOne(2).getUserList().size());
        // User u4 = (User) daosService.getMap().get("u4");
        // trainingDao.registerTrainingPlayer(u4, training);
        // assertEquals(4, trainingDao.findOne(2).getUserList().size());
        // User u5 = (User) daosService.getMap().get("u5");
        // trainingDao.registerTrainingPlayer(u5, training);
        // assertEquals(4, trainingDao.findOne(2).getUserList().size());
    }

    @Test
    public void testDeleteTrainingPlayer() {
        // borrar el usuario u3 del cuarto entrenamiento
        assertEquals(2, trainingDao.findOne(4).getUserList().size());
        User user = userDao.findByUsernameOrEmail("u3");
        Training training = trainingDao.findOne(4);
        System.out.println(user);
        System.out.println(training);
        trainingDao.deleteTrainingPlayer(user, training);
        assertEquals(1, trainingDao.findOne(4).getUserList().size());
    }

    @Test
    public void testDeleteTraining() {
        // borramos el tercer entrenamiento (no contiene usuarios)
        assertEquals(4, trainingDao.findAll().size());
        Training training = trainingDao.findOne(3);
        trainingDao.delete(training);
        assertEquals(3, trainingDao.findAll().size());
    }

    @Test
    public void testCreateTraining() {
        assertEquals(3, trainingDao.findAll().size());
        assertEquals(4, reserveDao.findAll().size());
        Calendar startDate = new GregorianCalendar(2016, Calendar.JULY, 17, 12, 00, 00);
        Calendar finishDate = new GregorianCalendar(2016, Calendar.JULY, 31, 12, 00, 00);
        Court court = (Court) daosService.getMap().get("c2");
        trainingDao.createTraining(new Training(startDate, finishDate, court));
        assertEquals(4, trainingDao.findAll().size());
        assertEquals(7, reserveDao.findAll().size());
    }

}
