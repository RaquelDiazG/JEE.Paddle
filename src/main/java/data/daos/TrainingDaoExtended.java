package data.daos;

import java.util.Calendar;
import java.util.List;

import data.entities.Court;
import data.entities.Training;
import data.entities.User;

public interface TrainingDaoExtended {

    public List<Training> createOneTrainingPerWeek(Calendar startDate, Calendar finishDate, Court court);

    public Training registerUserInTraining(User user, Training training);

    public boolean deleteUserInTraining(User user, Training training);

}
