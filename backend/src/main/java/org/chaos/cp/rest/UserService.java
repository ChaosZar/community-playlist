package org.chaos.cp.rest;

import org.chaos.cp.entity.User;
import org.chaos.cp.manager.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@RequestMapping("/user")
public class UserService {


    @Autowired
    private UserManager userManager;

    @RequestMapping(path = "/{userName}", method = RequestMethod.GET)
    public @ResponseBody
    User getUser(@PathVariable String userName) {
        return userManager.getUser(userName);
    }
}
