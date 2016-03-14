package data.daos;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
    public  List<Training> createOneTrainingPerWeek(Calendar startDate, Calendar finishDate, Court court) {
        // el entrenador podrá crear clases de padel, de una hora a la semana, con una fecha de inicio y una de final, asociados a una
        // pista.
        List<Training> trainingsCreated = new ArrayList<>();
        Calendar date = startDate;
        do {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
            System.out.println("------->Probando a insertar el:" + sdf.format(date.getTime()));
            // comprobamos que la pista no esta reservada en esa hora y que no hay mas reservas para la misma pista en una semana
            if (!existTraining(date) && !existTrainingInWeek(date, court)) {
                Training training = new Training(startDate, court);
                trainingDao.save(training);
                trainingsCreated.add(training);
            }
            // sumamos 1 dia
            date.add(Calendar.DAY_OF_YEAR, 1);
        } while (date.getTimeInMillis() < finishDate.getTimeInMillis());
        return trainingsCreated;
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
