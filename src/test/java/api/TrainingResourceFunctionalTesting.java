package api;

import org.apache.logging.log4j.LogManager;
import org.junit.After;
import org.junit.Test;

import business.api.Uris;

public class TrainingResourceFunctionalTesting {

    RestService restService = new RestService();

    @Test
    public void testShowTrainings() {
        String token = restService.loginTrainer();
        String response = new RestBuilder<String>(RestService.URL).path(Uris.TRAININGS).basicAuth(token, "").clazz(String.class).get()
                .build();
        LogManager.getLogger(this.getClass()).info("testShowTrainings (" + response + ")");
    }

    @Test
    public void testCreateTraining() {
        String token = restService.loginTrainer();
        // // restService.createTraining("1");
        // Calendar startDate = new GregorianCalendar(2016, 10, 14, 12, 00, 00);
        // Calendar finishDate = new GregorianCalendar(2016, 10, 22, 12, 00, 00);
        // restService.createCourt("1");
        // CourtState court = new CourtState(1, true);
        // UserWrapper trainer = new UserWrapper("trainer", "trainer@gmail.com", "trainer", new GregorianCalendar(2000, 8, 22, 00, 00, 00));
        // TrainingWrapper trainingWrapper = new TrainingWrapper(startDate, finishDate, court, trainer);
        // new RestBuilder<Object>(RestService.URL).path(Uris.TRAININGS).basicAuth(token, "").body(trainingWrapper).post().build();
        // //400-PETICION INCORRECTA
    }

    @Test
    public void testCreateTrainingUnauthorized() {
        String token = restService.loginTrainer();
        // TODO
    }

    @Test
    public void testDeleteTraining() {
        String token = restService.loginTrainer();
        // restService.createTraining("1");
        // new RestBuilder<Object>(RestService.URL).path(Uris.TRAININGS).param("id", "1").delete().build();
    }

    @Test
    public void testDeleteTrainingUnauthorized() {
        String token = restService.loginTrainer();
        // TODO
    }

    @Test
    public void testRegisterTrainingPlayer() {
        String token = restService.loginTrainer();
        // TODO
    }

    @Test
    public void testRegisterTrainingPlayerUnauthorized() {
        String token = restService.loginTrainer();
        // TODO
    }

    @Test
    public void testDeleteTrainingPlayer() {
        String token = restService.loginTrainer();
        // TODO
    }

    @Test
    public void testDeleteTrainingPlayerUnauthorized() {
        String token = restService.loginTrainer();
        // TODO
    }

    @After
    public void deleteAll() {
        new RestService().deleteAll();
    }

}
