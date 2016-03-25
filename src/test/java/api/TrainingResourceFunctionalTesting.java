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
        // TODO
    }

    @Test
    public void testCreateTrainingUnauthorized() {
        String token = restService.loginTrainer();
        // TODO
    }

    @Test
    public void testDeleteTraining() {
        String token = restService.loginTrainer();
        // TODO
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
