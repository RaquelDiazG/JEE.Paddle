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

}
