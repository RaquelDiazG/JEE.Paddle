package data.daos;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;

import data.entities.Court;
import data.entities.Reserve;
import data.entities.Training;
import data.entities.User;

public class TrainingDaoImpl implements TrainingDaoExtended {

    @Autowired
    private TrainingDao trainingDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ReserveDao reserveDao;

    @Override
    public Training registerTrainingPlayer(User user, Training training) {
        if (training.addUser(user)) {
            return trainingDao.save(training);
        } else {
            return null;
        }
    }

    @Override
    public Training deleteTrainingPlayer(User user, Training training) {
        training.removeUser(user);
        return trainingDao.save(training);
    }

    @Override
    public Training createTraining(Training training) {
        // guardar el entrenamiento
        Training insert = trainingDao.save(training);
        // generar reservas
        Reserve reserve;
        Court court = training.getCourt();
        User trainer = training.getTrainer();
        Calendar startDate = training.getStartDate();
        Calendar finishDate = training.getFinishDate();
        Calendar date = startDate;
        do {
            reserve = new Reserve(court, trainer, date);
            Reserve reserveDB = reserveDao.findByCourtAndDate(court, date);
            if (reserveDB != null) { // si existe la reserva, la borramos
                reserveDao.delete(reserveDB);
            }
            reserveDao.save(reserve);
            date.add(Calendar.DAY_OF_YEAR, 7); // sumamos 7 días
        } while (date.getTimeInMillis() <= finishDate.getTimeInMillis());
        return insert;
    }

    @Override
    public void deleteTraining(Training training) {
        training.removeAllUsers();
        trainingDao.delete(training);
    }

}
