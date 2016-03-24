package business.wrapper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import data.entities.Training;
import data.entities.User;

public class TrainingWrapper {

    private Calendar startDate;

    private Calendar finishDate;

    private CourtState court;

    private List<UserWrapper> userList;

    public TrainingWrapper(Calendar startDate, Calendar finishDate, CourtState court, List<UserWrapper> userList) {
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.court = court;
        this.userList = userList;
    }

    public TrainingWrapper(Training training) {
        this.startDate = training.getStartDate();
        this.finishDate = training.getFinishDate();
        CourtState court = new CourtState(training.getCourt());
        this.court = court;
        List<UserWrapper> userList = new ArrayList<>();
        List<User> users = training.getUserList();
        for (User user : users) {
            userList.add(new UserWrapper(user.getUsername(), user.getEmail(), user.getPassword(), user.getBirthDate()));
        }
        this.userList = userList;
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

    public List<UserWrapper> getUserList() {
        return userList;
    }

    public void setUserList(List<UserWrapper> userList) {
        this.userList = userList;
    }

    @Override
    public String toString() {
        return "TrainingWrapper [startDate=" + startDate + ", finishDate=" + finishDate + ", court=" + court + ", userList=" + userList
                + "]";
    }

}
