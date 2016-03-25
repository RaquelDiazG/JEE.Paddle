package business.wrapper;

import java.util.Calendar;

import data.entities.Training;
import data.entities.User;

public class TrainingWrapper {

    private Calendar startDate;

    private Calendar finishDate;

    private CourtState court;

    private UserWrapper trainer;

    public TrainingWrapper(Calendar startDate, Calendar finishDate, CourtState court, UserWrapper trainer) {
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.court = court;
        this.trainer = trainer;
    }

    public TrainingWrapper(Training training) {
        this.startDate = training.getStartDate();
        this.finishDate = training.getFinishDate();
        CourtState court = new CourtState(training.getCourt());
        this.court = court;
        User trainer = training.getTrainer();
        this.trainer = new UserWrapper(trainer.getUsername(), trainer.getEmail(), trainer.getPassword(), trainer.getBirthDate());
    }

    public Calendar getStartDate() {
        return startDate;
    }

    public void setStartDate(Calendar startDate) {
        this.startDate = startDate;
    }

    public Calendar getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Calendar finishDate) {
        this.finishDate = finishDate;
    }

    public CourtState getCourt() {
        return court;
    }

    public void setCourt(CourtState court) {
        this.court = court;
    }

    public UserWrapper getTrainer() {
        return trainer;
    }

    public void setTrainer(UserWrapper trainer) {
        this.trainer = trainer;
    }

    @Override
    public String toString() {
        return "TrainingWrapper [startDate=" + startDate + ", finishDate=" + finishDate + ", court=" + court + ", trainer=" + trainer + "]";
    }

}
