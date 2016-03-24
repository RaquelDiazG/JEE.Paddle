package data.daos;

import data.entities.Training;
import data.entities.User;

public interface TrainingDaoExtended {

    public Training registerTrainingPlayer(User user, Training training);

    public Training deleteTrainingPlayer(User user, Training training);

    public Training createTraining(Training training);

    public void deleteTraining(Training training);
}
