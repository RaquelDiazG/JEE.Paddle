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

import business.controllers.CourtController;
import business.wrapper.CourtState;
import data.entities.Court;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
@SessionAttributes("name")
public class CourtPresenter {

    @Autowired
    private ServletContext servletContext;

    @Autowired
    private CourtController courtController;

    public CourtPresenter() {
    }

    @RequestMapping("/court-list")
    public ModelAndView listCourts(Model model) {
        ModelAndView modelAndView = new ModelAndView("court-list");
        modelAndView.addObject("courtList", courtController.showCourts());
        return modelAndView;
    }

    @RequestMapping(value = "/create-court", method = RequestMethod.GET)
    public String createUser(Model model) {
        int id = courtController.showCourts().size() + 1;
        model.addAttribute("court", new CourtState(new Court(id)));
        return "create-court";
    }

    @RequestMapping(value = "/create-court", method = RequestMethod.POST)
    public String createUserSubmit(@Valid CourtState court, BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
            if (courtController.createCourt(court.getCourtId())) {
                model.addAttribute("elemento", court);
                return "operationOk";
            } else {
                bindingResult.rejectValue("courtId", "error.courtId", "Court exists");
            }
        }
        return "create-court";
    }

}
