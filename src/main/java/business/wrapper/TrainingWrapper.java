package business.wrapper;

import java.util.Calendar;

import data.entities.Training;

public class TrainingWrapper {

    private Calendar startDate;

    private Calendar finishDate;

    public TrainingWrapper(Calendar startDate) {

        this.startDate = startDate;
        this.finishDate = startDate;
        finishDate.add(Calendar.HOUR_OF_DAY, 1);
    }

    public TrainingWrapper(Calendar startDate, Calendar finishDate) {

        this.startDate = startDate;
        this.finishDate = finishDate;
    }

    public TrainingWrapper(Training training) {
        this(training.getStartDate(), training.getFinishDate());
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

    @Override
    public String toString() {
        return "TrainingWrapper [startDate=" + startDate + ", finishDate=" + finishDate + "]";
    }

}
