package data.daos;

import data.entities.User;

public interface UserDaoExtended {

    public User findByTokenValueValid(String tokenValue);

}
