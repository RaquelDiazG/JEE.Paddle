package data.daos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import data.entities.Token;

public class TokenDaoImpl implements TokenDaoExtended {

    @Autowired
    private TokenDao tokenDao;

    @Override
    public void removeExpiredTokens() {
        List<Token> allTokens = tokenDao.findAll();
        for (Token token : allTokens) {
            if (!token.isValid()) {
                tokenDao.delete(token);
            }
        }
    }

}
