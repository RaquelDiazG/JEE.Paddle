package data.daos;

import org.springframework.beans.factory.annotation.Autowired;

import data.entities.Token;
import data.entities.User;

public class UserDaoImpl implements UserDaoExtended {

    @Autowired
    private UserDao userDao;

    @Autowired
    private TokenDao tokenDao;

    @Override
    public User findByTokenValueValid(String tokenValue) {
        Token token = tokenDao.findByValue(tokenValue);
        if (token != null && token.isValid()) { // existe y no está caducado
            return token.getUser();
        } else {
            return null;
        }
    }

}
