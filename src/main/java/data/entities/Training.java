package data.entities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Training {

    @Id
    @GeneratedValue
    private int id;

    private Calendar startDate;

    private Calendar finishDate;

    @ManyToOne
    @JoinColumn
    private Court court;

    @ManyToOne
    @JoinColumn
    private User trainer;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<User> userList;

    private static final int MAX_USERS_IN_TRAINING = 4;

    public Training() {
    }

    public Training(Calendar startDate, Calendar finishDate, Court court, User trainer) {
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.court = court;
        this.trainer = trainer;
        this.userList = new ArrayList<>();
    }

    public Training(Calendar startDate, Calendar finishDate, Court court, User trainer, List<User> userList) {
        this.startDate = startDate;
        this.finishDate = startDate;
        this.finishDate = finishDate;
        this.court = court;
        this.trainer = trainer;
        this.userList = userList;
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

    public Court getCourt() {
        return court;
    }

    public void setCourt(Court court) {
        this.court = court;
    }

    public User getTrainer() {
        return trainer;
    }

    public void setTrainer(User trainer) {
        this.trainer = trainer;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public boolean addUser(User user) {
        // solo puede haber como maximo 4 alumnos por clase
        if (this.userList.size() < MAX_USERS_IN_TRAINING && !this.userList.contains(user)) {
            return this.userList.add(user);
        } else {
            return false;
        }
    }

    public boolean removeUser(User user) {
        return this.userList.remove(user);
    }

    public void removeAllUsers() {
        this.userList.clear();
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        return "Training [id=" + id + ", startDate=" + sdf.format(startDate.getTime()) + ", finishDate=" + sdf.format(finishDate.getTime())
                + ", court=" + court + ", trainer=" + trainer + ", userList=" + userList + "]";
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (getClass() != obj.getClass()) {
            return false;
        } else {
            return id == ((Training) obj).id;
        }
    }

}
