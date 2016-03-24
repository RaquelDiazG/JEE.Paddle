package business.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import business.controllers.TrainingController;
import business.wrapper.TrainingWrapper;

@RestController
@RequestMapping(Uris.SERVLET_MAP + Uris.TRAININGS)
public class TrainingResource {

    private TrainingController trainingController;

    @Autowired
    public void setReserveController(TrainingController trainingController) {
        this.trainingController = trainingController;
    }

    // OJO! HAY QUE GENERAR EXCEPCIONES

    @RequestMapping(method = RequestMethod.GET)
    public List<TrainingWrapper> showTrainings() {
        return trainingController.showTrainings();
    }

    @RequestMapping(method = RequestMethod.POST)
    public boolean createTraining(@RequestBody TrainingWrapper trainingWrapper) {
        return trainingController.createTraining(trainingWrapper);
    }

    @RequestMapping(value = Uris.ID, method = RequestMethod.DELETE)
    public boolean deleteTraining(@PathVariable int id) {
        return trainingController.deleteTraining(id);
    }

    @RequestMapping(value = Uris.ID + Uris.USERS + Uris.ID, method = RequestMethod.POST)
    public boolean registerTrainingPlayer(@PathVariable int trainingId, @PathVariable int userId) {
        return trainingController.registerTrainingPlayer(userId, trainingId);
    }

    @RequestMapping(value = Uris.ID + Uris.USERS + Uris.ID, method = RequestMethod.DELETE)
    public boolean deleteTrainingPlayer(@PathVariable int trainingId, @PathVariable int userId) {
        return trainingController.deleteTrainingPlayer(userId, trainingId);
    }
}
