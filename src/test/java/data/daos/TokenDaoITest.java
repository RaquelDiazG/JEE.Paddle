package data.daos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.PersistenceConfig;
import config.TestsPersistenceConfig;
import data.entities.Token;
import data.entities.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class})
public class TokenDaoITest {

    @Autowired
    private TokenDao tokenDao;

    @Autowired
    private DaosService daosService;

    @Test
    public void testFindByUser() {
        // existe token
        Token token = (Token) daosService.getMap().get("tu1");
        assertEquals(token, tokenDao.findByUser(token.getUser()));
        // no existe token
        User user = (User) daosService.getMap().get("u4");
        assertNull(tokenDao.findByUser(user));
    }

    @Test
    public void testRemoveExpiredTokens() {
        // obtenemos todos los tokens
        int sizeOriginal = tokenDao.findAll().size();
        assertEquals(sizeOriginal, 8);
        // eliminamos los tokens caducados
        tokenDao.removeExpiredTokens();
        int size = tokenDao.findAll().size();
        assertEquals(size, 4);
    }
}
