package data.daos;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.PersistenceConfig;
import config.TestsPersistenceConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class})
public class TrainingDaoITest {

    @Autowired
    private TrainingDao trainingDao;

    @Autowired
    private DaosService daosService;

    @Test
    public void findByStartDate() {
        Calendar date = Calendar.getInstance();
        date.add(Calendar.DAY_OF_YEAR, 1);
        date.set(Calendar.HOUR_OF_DAY, 9);
        date.set(Calendar.MINUTE, 0);
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);
        // Invalid
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        System.out.println(sdf.format(date.getTime()));
        assertEquals(0, trainingDao.findTrainingsByStartDate(date).size());
        // Valid
        date.add(Calendar.HOUR_OF_DAY, 1);
        date.add(Calendar.HOUR_OF_DAY, 1);
        System.out.println(sdf.format(date.getTime()));
        assertEquals(1, trainingDao.findTrainingsByStartDate(date).size());
    }

}
