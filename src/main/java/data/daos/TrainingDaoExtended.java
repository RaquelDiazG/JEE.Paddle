package data.daos;

import java.util.Calendar;

import data.entities.Court;
import data.entities.Training;
import data.entities.User;

public interface TrainingDaoExtended {

    public void createOneTrainingPerWeek(Calendar startDate, Calendar finishDate, Court court);

    public Training registerUserInTraining(User user, Training training);

    public boolean deleteUserInTraining(User user, Training training);

}
