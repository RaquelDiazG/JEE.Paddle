package web;

import javax.servlet.ServletContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import business.controllers.TrainingController;
import business.wrapper.TrainingWrapper;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
@SessionAttributes("name")
public class TrainingPresenter {

    @Autowired
    private ServletContext servletContext;

    @Autowired
    private TrainingController trainingController;

    public TrainingPresenter() {
    }

    @RequestMapping("/training-list")
    public ModelAndView listTrainings(Model model) {
        ModelAndView modelAndView = new ModelAndView("training-list");
        modelAndView.addObject("trainingList", trainingController.showTrainings());
        return modelAndView;
    }

    @RequestMapping(value = "/create-training", method = RequestMethod.GET)
    public String createTraining(Model model) {
        int id = trainingController.showTrainings().size() + 1;
        model.addAttribute("training", new TrainingWrapper(id));
        return "create-training";
    }

    @RequestMapping(value = "/create-training", method = RequestMethod.POST)
    public String createTrainingSubmit(@Valid TrainingWrapper training, BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
            if (trainingController.createTraining(training)) {
                model.addAttribute("elemento", training);
                return "operationOk";
            } else {
                bindingResult.rejectValue("trainingId", "error.trainingId", "Training exists");
            }
        }
        return "create-training";
    }

}
