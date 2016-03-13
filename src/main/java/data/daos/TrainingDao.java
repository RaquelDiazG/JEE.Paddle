package data.daos;

import java.util.Calendar;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import data.entities.Training;

public interface TrainingDao extends JpaRepository<Training, Integer>, TrainingDaoExtended {

    List<Training> findTrainingsByStartDate(Calendar startDate);

    @Query(value = "SELECT * FROM training WHERE court_id=?2 AND EXTRACT(WEEK FROM startDate) = EXTRACT(WEEK FROM ?1)", nativeQuery = true)
    List<Training> findTrainingInWeek(Calendar date, int court_id);
}
