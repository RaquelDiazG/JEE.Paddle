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

import business.controllers.UserController;
import business.wrapper.UserWrapper;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
@SessionAttributes("name")
public class UserPresenter {

    @Autowired
    private ServletContext servletContext;

    @Autowired
    private UserController userController;

    public UserPresenter() {
    }

    @RequestMapping("/user-list")
    public ModelAndView listUsers(Model model) {
        ModelAndView modelAndView = new ModelAndView("user-list");
        modelAndView.addObject("userList", userController.showUsers());
        return modelAndView;
    }

    @RequestMapping(value = "/create-user", method = RequestMethod.GET)
    public String createUser(Model model) {
        model.addAttribute("user", new UserWrapper());
        return "create-user";
    }

    @RequestMapping(value = "/create-user", method = RequestMethod.POST)
    public String createUserSubmit(@Valid UserWrapper user, BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
            if (userController.registration(user)) {
                model.addAttribute("elemento", user);
                return "operationOk";
            } else {
                bindingResult.rejectValue("username", "error.username", "User exists");
            }
        }
        return "create-user";
    }

}
