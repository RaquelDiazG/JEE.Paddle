package data.entities;

import static org.junit.Assert.assertTrue;

import java.util.Calendar;

import org.junit.Test;

public class TokenTest {

    @Test
    public void testTokenUser() {
        User user = new User("u", "u@gmail.com", "p", Calendar.getInstance());
        Token token = new Token(user);
        assertTrue(token.getValue().length() > 20);
    }

    @Test
    public void testTokenIsValid() {
        User user = new User("u", "u@gmail.com", "p", Calendar.getInstance());
        // Valid token
        Token token1 = new Token(user);
        assertTrue(token1.isValid());
        // Invalid token
        Token token2 = new Token(user);
        Calendar newDate = Calendar.getInstance();
        newDate.set(2000, Calendar.JANUARY, 30);
        token2.setCreationDate(newDate);
        assertTrue(!token2.isValid());
    }
}
