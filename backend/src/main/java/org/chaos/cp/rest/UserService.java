package org.chaos.cp.rest;

import org.chaos.cp.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@Controller
@RequestMapping("/user")
public class UserService {

    @RequestMapping(path = "{userName}", method = RequestMethod.GET)
    public @ResponseBody
    User getUser(@RequestParam(value = "userName", defaultValue = "") String userName) {
        throw new NotImplementedException();
    }

}
