package web;

import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import data.daos.CourtDao;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
@SessionAttributes("name")
public class Presenter {

    @Autowired
    private ServletContext servletContext;

    @Autowired
    private CourtDao courtDao;

    public Presenter() {
    }
    
    @RequestMapping("/home")
    public String home(Model model) {
        return "/home";
    }

    @RequestMapping("/court-list")
    public ModelAndView listCourts(Model model) {
        ModelAndView modelAndView = new ModelAndView("/court-list");
        modelAndView.addObject("courtList", courtDao.findAll());
        return modelAndView;
        
    }
   

}
