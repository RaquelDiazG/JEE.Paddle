package data.daos;

import data.entities.Training;
import data.entities.User;

public interface TrainingDaoExtended {

    public Training registerUserInTraining(User user, Training training);

}
