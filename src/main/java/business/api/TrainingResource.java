package business.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import business.controllers.TrainingController;

@RestController
@RequestMapping(Uris.SERVLET_MAP + Uris.TRAININGS)
public class TrainingResource {

    private TrainingController trainingController;

    @Autowired
    public void setReserveController(TrainingController trainingController) {
        this.trainingController = trainingController;
    }

    @RequestMapping(method = RequestMethod.GET)
    public void showTrainings() {
        // TODO
    }

    @RequestMapping(method = RequestMethod.POST)
    public void createTraining(@RequestParam(required = true) int id) {
        // TODO
    }

    @RequestMapping(value = Uris.ID, method = RequestMethod.DELETE)
    public void deleteTraining(@PathVariable int trainintId) {
        // TODO
    }

    @RequestMapping(value = Uris.ID + Uris.USERS + Uris.ID, method = RequestMethod.POST)
    public void registerUserInTraining(@PathVariable int trainintId, @PathVariable int userId) {
        // TODO
    }

    @RequestMapping(value = Uris.ID + Uris.USERS + Uris.ID, method = RequestMethod.DELETE)
    public void deleteUserInTraining(@PathVariable int trainintId, @PathVariable int userId) {
        // TODO
    }
}
