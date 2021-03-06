package data.daos;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import data.entities.Authorization;
import data.entities.Court;
import data.entities.Reserve;
import data.entities.Role;
import data.entities.Token;
import data.entities.Training;
import data.entities.User;
import data.services.DataService;

@Service
public class DaosService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private TokenDao tokenDao;

    @Autowired
    private AuthorizationDao authorizationDao;

    @Autowired
    private CourtDao courtDao;

    @Autowired
    private ReserveDao reserveDao;

    @Autowired
    private TrainingDao trainingDao;

    @Autowired
    private DataService genericService;

    private Map<String, Object> map;

    private SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");

    @PostConstruct
    public void populate() {

        /*
         * DATOS GENERADOS EN BBDD
         * 
         * 12 usuarios -> u0,u1,u2,u3,u4,u5,u6,u7,u8,u9,u10,u11,u12,u13,u14,u15
         * 
         * 4 tokens -> tu0,tu1,tu2,tu3
         * 
         * 4 tokens expirados -> texpu0,texpu1,texpu2,texpu3
         * 
         * 4 pistas -> c1,c2,c3,c4
         * 
         * 4 reservas-> rc1u2,rc2u3,rc3u4,rc4u5
         * 
         * 4 entrenamientos -> trc1,trc2,trc3,trc4
         */

        map = new HashMap<>();
        User[] users = this.createPlayers(0, 4);
        for (User user : users) {
            map.put(user.getUsername(), user);
        }
        System.out.println("ADD USERS - " + map.keySet());
        for (Token token : this.createTokens(users)) {
            map.put("t" + token.getUser().getUsername(), token);
        }
        System.out.println("ADD TOKENS - " + map.keySet());
        User[] users2 = this.createPlayers(4, 4);
        for (User user : users2) {
            map.put(user.getUsername(), user);
        }
        System.out.println("ADD USERS - " + map.keySet());
        for (Court court : this.createCourts(1, 4)) {
            map.put("c" + court.getId(), court);
        }
        System.out.println("ADD COURTS - " + map.keySet());
        User[] users3 = this.createPlayers(8, 4);
        for (User user : users3) {
            map.put(user.getUsername(), user);
        }
        System.out.println("ADD USERS - " + map.keySet());
        User[] users4 = this.createPlayers(12, 4);
        System.out.println("ADD USERS - " + map.keySet());
        List<Token> tokens = this.createExpiredTokens(users4);
        for (Token token : tokens) {
            map.put("texp" + token.getUser().getUsername(), token);
        }
        System.out.println("ADD TOKENS EXPIRED - " + map.keySet());
        Calendar date = Calendar.getInstance();
        date.add(Calendar.DAY_OF_YEAR, 1);
        date.set(Calendar.HOUR_OF_DAY, 9);
        date.set(Calendar.MINUTE, 0);
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);
        Reserve reserve;
        for (int i = 0; i < 4; i++) {
            date.add(Calendar.HOUR_OF_DAY, 1);
            reserve = new Reserve(courtDao.findOne(i + 1), users[i], date);
            reserveDao.save(reserve);
            map.put("r" + "c" + courtDao.findOne(i + 1).getId() + users[i].getUsername(), reserve);
        }
        System.out.println("ADD RESERVES - " + map.keySet());
        Calendar startDate;
        Calendar finishDate;
        // entrenamiento para consultar
        startDate = new GregorianCalendar(2015, Calendar.JANUARY, 15, 10, 00, 00);
        finishDate = new GregorianCalendar(2015, Calendar.JANUARY, 30, 10, 00, 00);
        Training training1 = new Training(startDate, finishDate, courtDao.findOne(1), users[0]);
        trainingDao.save(training1);
        // entrenamiento para registrar un usuario
        map.put("tr" + "c" + training1.getCourt(), training1);
        startDate = new GregorianCalendar(2015, Calendar.FEBRUARY, 15, 10, 00, 00);
        finishDate = new GregorianCalendar(2015, Calendar.FEBRUARY, 30, 10, 00, 00);
        Training training2 = new Training(startDate, finishDate, courtDao.findOne(2), users[0]);
        trainingDao.save(training2);
        map.put("tr" + "c" + training2.getCourt(), training2);
        // entrenamiento para borrar
        startDate = new GregorianCalendar(2015, Calendar.MARCH, 15, 10, 00, 00);
        finishDate = new GregorianCalendar(2015, Calendar.MARCH, 30, 10, 00, 00);
        Training training3 = new Training(startDate, finishDate, courtDao.findOne(3), users[0]);
        trainingDao.save(training3);
        map.put("tr" + "c" + training3.getCourt(), training3);
        // entrenamiento con usuario u3 para borrar usuario
        startDate = new GregorianCalendar(2015, Calendar.APRIL, 15, 10, 00, 00);
        finishDate = new GregorianCalendar(2015, Calendar.APRIL, 30, 10, 00, 00);
        Training training4 = new Training(startDate, finishDate, courtDao.findOne(4), users[0]);
        training4.addUser((User) map.get("u2"));
        training4.addUser((User) map.get("u3"));
        trainingDao.save(training4);
        map.put("tr" + "c" + training4.getCourt(), training4);

        System.out.println(map.keySet());
    }

    public User[] createPlayers(int initial, int size) {
        User[] users = new User[size];
        for (int i = 0; i < size; i++) {
            users[i] = new User("u" + (i + initial), "u" + (i + initial) + "@gmail.com", "p", Calendar.getInstance());
            userDao.save(users[i]);
            authorizationDao.save(new Authorization(users[i], Role.PLAYER));
        }
        return users;
    }

    public List<Token> createTokens(User[] users) {
        List<Token> tokenList = new ArrayList<>();
        Token token;
        for (User user : users) {
            token = new Token(user);
            tokenDao.save(token);
            tokenList.add(token);
        }
        return tokenList;
    }

    private List<Token> createExpiredTokens(User[] users) {
        List<Token> tokenList = new ArrayList<>();
        Token token;
        for (User user : users) {
            token = new Token(user);
            Calendar date = Calendar.getInstance();
            date.set(2010, 8, 7);
            token.setCreationDate(date);
            tokenDao.save(token);
            tokenList.add(token);
        }
        return tokenList;
    }

    public List<Court> createCourts(int initial, int size) {
        List<Court> courtList = new ArrayList<>();
        Court court;
        for (int id = 0; id < size; id++) {
            court = new Court(id + initial);
            courtDao.save(court);
            courtList.add(court);
        }
        return courtList;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void deleteAll() {
        genericService.deleteAllExceptAdmin();
    }
}
