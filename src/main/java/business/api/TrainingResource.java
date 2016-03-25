package business.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import business.api.exceptions.AlreadyExistTrainingIdException;
import business.api.exceptions.AlreadyExistUserIdInTrainingIdException;
import business.api.exceptions.NotFoundTrainingIdException;
import business.api.exceptions.NotFoundUserIdException;
import business.controllers.TrainingController;
import business.controllers.UserController;
import business.wrapper.TrainingWrapper;

@RestController
@RequestMapping(Uris.SERVLET_MAP + Uris.TRAININGS)
public class TrainingResource {

    private TrainingController trainingController;

    private UserController userController;

    @Autowired
    public void setTrainingController(TrainingController trainingController) {
        this.trainingController = trainingController;
    }

    @Autowired
    public void setUserController(UserController userController) {
        this.userController = userController;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<TrainingWrapper> showTrainings() {
        return trainingController.showTrainings();
    }

    @RequestMapping(method = RequestMethod.POST)
    public void createTraining(@RequestBody TrainingWrapper trainingWrapper) throws AlreadyExistTrainingIdException {
        if (!trainingController.createTraining(trainingWrapper)) {
            throw new AlreadyExistTrainingIdException();
        }
    }

    @RequestMapping(value = Uris.ID, method = RequestMethod.DELETE)
    public void deleteTraining(@PathVariable int id) throws NotFoundTrainingIdException {
        if (!trainingController.deleteTraining(id)) {
            throw new NotFoundTrainingIdException("id: " + id);
        }
    }

    @RequestMapping(value = Uris.ID + Uris.USERS + Uris.ID, method = RequestMethod.POST)
    public void registerTrainingPlayer(@PathVariable int userId, @PathVariable int trainingId)
            throws NotFoundTrainingIdException, NotFoundUserIdException, AlreadyExistUserIdInTrainingIdException {
        if (!trainingController.existTraining(trainingId)) {
            throw new NotFoundTrainingIdException("id: " + trainingId);
        }
        if (!userController.existUser(userId)) {
            throw new NotFoundUserIdException("id: " + userId);
        }
        if (!trainingController.registerTrainingPlayer(userId, trainingId)) {
            throw new AlreadyExistUserIdInTrainingIdException("idUser: " + userId, "idTraining: " + trainingId);
        }
    }

    @RequestMapping(value = Uris.ID + Uris.USERS + Uris.ID, method = RequestMethod.DELETE)
    public void deleteTrainingPlayer(@PathVariable int userId, @PathVariable int trainingId)
            throws NotFoundTrainingIdException, NotFoundUserIdException, AlreadyExistUserIdInTrainingIdException {
        if (!trainingController.existTraining(trainingId)) {
            throw new NotFoundTrainingIdException("id: " + trainingId);
        }
        if (!userController.existUser(userId)) {
            throw new NotFoundUserIdException("id: " + userId);
        }
        if (!trainingController.deleteTrainingPlayer(userId, trainingId)) {
            throw new AlreadyExistUserIdInTrainingIdException("idUser: " + userId, "idTraining: " + trainingId);
        }
    }
}
