package business.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import business.wrapper.TrainingWrapper;
import data.daos.CourtDao;
import data.daos.TrainingDao;
import data.daos.UserDao;
import data.entities.Court;
import data.entities.Training;
import data.entities.User;

@Controller
@Transactional
public class TrainingController {

    private TrainingDao trainingDao;

    private CourtDao courtDao;

    private UserDao userDao;

    @Autowired
    public void setTrainingDao(TrainingDao trainingDao) {
        this.trainingDao = trainingDao;
    }

    @Autowired
    public void setCourtDao(CourtDao courtDao) {
        this.courtDao = courtDao;
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<TrainingWrapper> showTrainings() {
        List<TrainingWrapper> trainings = new ArrayList<>();
        for (Training training : trainingDao.findAll()) {
            trainings.add(new TrainingWrapper(training));
        }
        return trainings;
    }

    public boolean createTraining(TrainingWrapper trainingWrapper) {
        Training training = trainingDao.findOne(trainingWrapper.getId());
        if (training == null) {// no existe
            Court court = courtDao.findOne(trainingWrapper.getCourtId());
            User trainer = userDao.getOne(trainingWrapper.getTrainerId());
            trainingDao.createTraining(new Training(trainingWrapper.getStartDate(), trainingWrapper.getFinishDate(), court, trainer));
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteTraining(int id) {
        Training training = trainingDao.findOne(id);
        if (training != null) {
            trainingDao.deleteTraining(training);
            return true;
        } else {
            return false;
        }
    }

    public boolean registerTrainingPlayer(int userId, int trainingId) {
        User user = userDao.findOne(userId);
        Training training = trainingDao.findOne(trainingId);
        if (trainingDao.registerTrainingPlayer(user, training) != null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteTrainingPlayer(int userId, int trainingId) {
        User user = userDao.findOne(userId);
        Training training = trainingDao.findOne(trainingId);
        if (trainingDao.deleteTrainingPlayer(user, training) != null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean existTraining(int trainingId) {
        return (trainingDao.findOne(trainingId) != null);
    }

}
