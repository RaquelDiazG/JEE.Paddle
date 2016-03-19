package data.daos;

import java.util.Calendar;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import data.entities.Court;
import data.entities.Training;

public interface TrainingDao extends JpaRepository<Training, Integer>, TrainingDaoExtended {

    Training findByStartDateAndFinishDateAndCourt(Calendar startDate, Calendar finishDate, Court court);

    List<Training> findTrainingsByCourt(Court court);

}
