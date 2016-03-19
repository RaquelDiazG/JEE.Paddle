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
    public Training registerTrainingPlayer(User user, Training training) {
        training.addUser(user);
        return trainingDao.save(training);
    }

    @Override
    public Training deleteTrainingPlayer(User user, Training training) {
        training.removeUser(user);
        return trainingDao.save(training);
    }

}
