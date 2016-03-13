package data.daos;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;

import data.entities.Court;
import data.entities.Training;
import data.entities.User;

public class TrainingDaoImpl implements TrainingDaoExtended {

    @Autowired
    private TrainingDao trainingDao;

    @Autowired
    private UserDao userDao;

    @Override
    public void createOneTrainingPerWeek(Calendar startDate, Calendar finishDate, Court court) {
        Calendar date = startDate;
        do {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
            System.out.println("------->Probando a insertar el:" + sdf.format(date.getTime()));
            if (!existTraining(date) && !existTrainingInWeek(date, court)) {
                Training training = new Training(startDate, court);
                trainingDao.save(training);
            }
            // sumamos 1 dia
            date.add(Calendar.DAY_OF_YEAR, 1);
        } while (date.getTimeInMillis() < finishDate.getTimeInMillis());
    }

    private boolean existTraining(Calendar date) {
        return trainingDao.findTrainingsByStartDate(date).size() > 0;
    }

    private boolean existTrainingInWeek(Calendar date, Court court) {
        // Clear date
        Calendar cleanDate = Calendar.getInstance();
        cleanDate.clear();
        cleanDate.set(Calendar.WEEK_OF_YEAR, date.get(Calendar.WEEK_OF_YEAR));
        cleanDate.set(Calendar.YEAR, date.get(Calendar.YEAR));
        return trainingDao.findTrainingInWeek(cleanDate, court.getId()).size() > 0;
    }

    @Override
    public Training registerUserInTraining(User user, Training training) {
        training.getUserList().add(user);
        return trainingDao.save(training);
    }

    @Override
    public boolean deleteUserInTraining(User user, Training training) {
        return training.getUserList().remove(user);
    }

}
