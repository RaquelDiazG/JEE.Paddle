package business.wrapper;

import java.util.Calendar;

import org.springframework.format.annotation.DateTimeFormat;

import data.entities.Training;

public class TrainingWrapper {

    private int id;

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private Calendar startDate;

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private Calendar finishDate;

    private int courtId;

    private int trainerId;

    public TrainingWrapper() {
    }

    public TrainingWrapper(int id) {
        this.id = id;
        this.startDate = null;
        this.finishDate = null;
        this.courtId = -1;
        this.trainerId = -1;
    }

    public TrainingWrapper(int id, Calendar startDate, Calendar finishDate, int courtId, int trainerId) {
        this.id = id;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.courtId = courtId;
        this.trainerId = trainerId;
    }

    public TrainingWrapper(Training training) {
        this.id = training.getId();
        this.startDate = training.getStartDate();
        this.finishDate = training.getFinishDate();
        this.courtId = training.getCourt().getId();
        this.trainerId = training.getTrainer().getId();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getCourtId() {
        return courtId;
    }

    public void setCourtId(int courtId) {
        this.courtId = courtId;
    }

    public int getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(int trainerId) {
        this.trainerId = trainerId;
    }

    @Override
    public String toString() {
        return "TrainingWrapper [startDate=" + startDate + ", finishDate=" + finishDate + ", court=" + courtId + ", trainer=" + trainerId
                + "]";
    }

}
