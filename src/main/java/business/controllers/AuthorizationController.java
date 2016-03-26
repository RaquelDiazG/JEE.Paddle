package business.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import data.daos.AuthorizationDao;
import data.entities.Authorization;

@Controller
public class AuthorizationController {

    private AuthorizationDao authorizationDao;

    @Autowired
    public void setAuthorizationDao(AuthorizationDao authorizationDao) {
        this.authorizationDao = authorizationDao;
    }

    public List<Authorization> showAuthorizations() {
        return authorizationDao.findAll();
    }
}
