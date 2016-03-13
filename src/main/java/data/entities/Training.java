package data.entities;

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

    @ManyToMany(fetch = FetchType.EAGER)
    private List<User> userList;

    public Training() {
    }

    public Training(Calendar startDate, Court court) {
        this.startDate = startDate;
        this.finishDate = startDate;
        finishDate.add(Calendar.HOUR_OF_DAY, 1);
        this.court = court;
        this.userList = new ArrayList<>();
    }

    public Training(Calendar startDate, Court court, List<User> userList) {
        this.startDate = startDate;
        this.finishDate = startDate;
        finishDate.add(Calendar.HOUR_OF_DAY, 1);
        this.court = court;
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

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    @Override
    public String toString() {
        return "Training [id=" + id + ", startDate=" + startDate + ", finishDate=" + finishDate + ", court=" + court + ", userList="
                + userList + "]";
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
