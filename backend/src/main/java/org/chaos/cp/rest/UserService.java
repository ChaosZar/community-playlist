package org.chaos.cp.rest;

import org.chaos.cp.entity.User;
import org.chaos.cp.manager.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@CrossOrigin
@RequestMapping("/user")
public class UserService {


    @Autowired
    private UserManager userManager;

    @RequestMapping(path = "{userName}", method = RequestMethod.GET)
    public @ResponseBody
    User getUser(@RequestParam(value = "userName", defaultValue = "") String userName) {
        return userManager.getUser(userName);
    }
}
