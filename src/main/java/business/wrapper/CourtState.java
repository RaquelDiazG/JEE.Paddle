package business.wrapper;

import data.entities.Court;

public class CourtState {

    private int id;

    private boolean active;

    public CourtState() {
    }

    public CourtState(int id, boolean active) {
        super();
        this.id = id;
        this.active = active;
    }

    public CourtState(Court court) {
        this(court.getId(), court.isActive());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "CourtStateWrapper [id=" + id + ", active=" + active + "]";
    }

}
