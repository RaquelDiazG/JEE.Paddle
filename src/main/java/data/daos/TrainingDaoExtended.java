package data.daos;

import data.entities.Training;
import data.entities.User;

public interface TrainingDaoExtended {

    public Training registerTrainingPlayer(User user, Training training);

    public boolean deleteTrainingPlayer(User user, Training training);

}
