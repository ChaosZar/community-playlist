package org.chaos.cp.rest;

import org.chaos.cp.entity.User;
import org.chaos.cp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(path = "{userName}", method = RequestMethod.GET)
    public @ResponseBody
    User getUser(@RequestParam(value = "userName", defaultValue = "") String userName) {
        return userRepository.findByLogin(userName);
    }
}
