package business.api;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
        // TODO
        return null;
    }

    @RequestMapping(method = RequestMethod.POST)
    public void createTraining(@RequestParam(required = true) int id, @RequestParam(required = true) Calendar startDate,
            Calendar finishDate) {
        // TODO
    }

    @RequestMapping(value = Uris.ID, method = RequestMethod.DELETE)
    public void deleteTraining(@PathVariable int trainingId) {
        // TODO
    }

    @RequestMapping(value = Uris.ID + Uris.USERS + Uris.ID, method = RequestMethod.POST)
    public void registerTrainingPlayer(@PathVariable int trainingId, @PathVariable int userId) {
        // TODO
    }

    @RequestMapping(value = Uris.ID + Uris.USERS + Uris.ID, method = RequestMethod.DELETE)
    public void deleteTrainingPlayer(@PathVariable int trainingId, @PathVariable int userId) {
        // TODO
    }
}
