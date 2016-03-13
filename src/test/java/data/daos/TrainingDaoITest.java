package data.daos;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Calendar;

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

    @Test
    public void testFindByStartDate() {
        Calendar date = Calendar.getInstance();
        date.add(Calendar.DAY_OF_YEAR, 1);
        date.set(Calendar.HOUR_OF_DAY, 9);
        date.set(Calendar.MINUTE, 0);
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);
        // Invalid
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        System.out.println(sdf.format(date.getTime()));
        assertEquals(0, trainingDao.findTrainingsByStartDate(date).size());
        // Valid
        date.add(Calendar.HOUR_OF_DAY, 1);
        date.add(Calendar.HOUR_OF_DAY, 1);
        System.out.println(sdf.format(date.getTime()));
        assertEquals(1, trainingDao.findTrainingsByStartDate(date).size());
    }

    @Test
    public void testFindByCourt() {
        assertEquals(1, trainingDao.findTrainingsByCourt(courtDao.findAll().get(2)).size());
    }

    @Test
    public void testRegisterUserInTraining() {
        User u1 = new User("user1", "user1@gmail.com", "p", Calendar.getInstance());
        userDao.save(u1);
        authorizationDao.save(new Authorization(u1, Role.PLAYER));
        assertEquals(0, trainingDao.findAll().get(0).getUserList().size());
        Training training = trainingDao.findAll().get(0);
        trainingDao.registerUserInTraining(u1, training);
        assertEquals(1, trainingDao.findAll().get(0).getUserList().size());
    }

    @Test
    public void testDeleteUserInTraining() {
        // Register user in training
        User u0 = userDao.findByUsernameOrEmail("u0");
        Training training = trainingDao.findAll().get(0);
        trainingDao.registerUserInTraining(u0, training);
        // Remove user from training
        assertEquals(1, trainingDao.findAll().get(0).getUserList().size());
        trainingDao.deleteUserInTraining(u0, training);
        assertEquals(0, trainingDao.findAll().get(0).getUserList().size());
    }

    @Test
    public void testDeleteTraining() {
        assertEquals(4, trainingDao.findAll().size());
        Training training = trainingDao.findAll().get(3);
        trainingDao.delete(training);
        assertEquals(3, trainingDao.findAll().size());
    }

    @Test
    public void testCreateOneTrainingPerWeek() {
        Court court = courtDao.findAll().get(0);
        Calendar startDate = Calendar.getInstance();
        startDate.set(2016, 2, 14, 9, 0, 0);
        Calendar finishDate = Calendar.getInstance();
        finishDate.set(2016, 2, 25, 9, 0, 0);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        System.out.println(sdf.format(startDate.getTime()));
        System.out.println(sdf.format(finishDate.getTime()));
        assertEquals(4, trainingDao.findAll().size());
        trainingDao.createOneTrainingPerWeek(startDate, finishDate, court);
        assertEquals(5, trainingDao.findAll().size());
    }

}
