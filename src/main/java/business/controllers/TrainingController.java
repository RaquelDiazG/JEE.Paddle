package business.controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import business.wrapper.TrainingWrapper;
import data.daos.CourtDao;
import data.daos.TrainingDao;
import data.entities.Court;
import data.entities.Training;
import data.entities.User;

@Controller
@Transactional
public class TrainingController {

    private TrainingDao trainingDao;

    private CourtDao courtDao;

    private static final int MAX_USERS_IN_TRAINING = 4;

    @Autowired
    public void setTrainingDao(TrainingDao trainingDao) {
        this.trainingDao = trainingDao;
    }

    @Autowired
    public void setCourtDao(CourtDao courtDao) {
        this.courtDao = courtDao;
    }

    public List<TrainingWrapper> showTrainings() {
        List<TrainingWrapper> trainings = new ArrayList<>();
        for (Training training : trainingDao.findAll()) {
            trainings.add(new TrainingWrapper(training));
        }
        return trainings;
    }

    public boolean createTraining(Calendar startDate, Calendar finishDate, Court court) {
        Training training = trainingDao.findByStartDateAndFinishDateAndCourt(startDate, finishDate, court);
        if (trainingDao.createTraining(training) != null) {
            return true;
        } else {
            return false;
        }
    }

    public void deleteTraining(Training training) {
        trainingDao.delete(training);
    }

    public boolean registerTrainingPlayer(User user, Training training) {
        // solo puede haber como maximo 4 alumnos por clase
        if (training.getUserList().size() < MAX_USERS_IN_TRAINING) {
            Training register = trainingDao.registerTrainingPlayer(user, training);
            return register != null;
        } else {
            return false;
        }
    }

    public boolean deleteTrainingPlayer(User user, Training training) {
        if (trainingDao.deleteTrainingPlayer(user, training) != null) {
            return true;
        } else {
            return false;
        }
    }

}
