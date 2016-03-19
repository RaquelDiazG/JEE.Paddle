package data.daos;

import org.springframework.beans.factory.annotation.Autowired;

import data.entities.Training;
import data.entities.User;

public class TrainingDaoImpl implements TrainingDaoExtended {

    @Autowired
    private TrainingDao trainingDao;

    @Autowired
    private UserDao userDao;

    @Override
    public Training registerUserInTraining(User user, Training training) {
        training.addUser(user);
        return trainingDao.save(training);
    }

    @Override
    public boolean deleteUserInTraining(User user, Training training) {
        return training.removeUser(user);
    }

}
