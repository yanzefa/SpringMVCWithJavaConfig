package com.github.izhangzhihao.SpringMVCSeedProject.Controller;


import com.github.izhangzhihao.SpringMVCSeedProject.Model.User;
import com.github.izhangzhihao.SpringMVCSeedProject.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/User")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/UserList", method = RequestMethod.GET)
    @ResponseBody
    public List<User> getAllUsers() throws Exception {
        return userService.getAll();
    }

    /*@RequestMapping(value = "/UserList", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            List<User> userList = userService.getAll();
            return new ResponseEntity<>(userList, HttpStatus.OK);
        } catch (Exception e) {
            LogToDB(e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }*/

    @RequestMapping(value = "/getUser/{userName}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<User> getUser(@PathVariable String userName)
            throws Exception {
        User user = userService.getById(userName);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/createUser/userName/{userName}/passWord/{passWord}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Void> createUser(@PathVariable String userName,
                                           @PathVariable String passWord)
            throws Exception {
        User byId = userService.getById(userName);
        if (byId != null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } else {
            userService.save(new User(userName, passWord));
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    @RequestMapping(value = "/updateUser/userName/{userName}/passWord/{passWord}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Void> updateUser(@PathVariable String userName,
                                           @PathVariable String passWord)
            throws Exception {
        User byId = userService.getById(userName);
        if (byId == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            userService.saveOrUpdate(new User(userName,passWord));
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/deleteUser/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Void> deleteUser(@PathVariable String id)
            throws Exception {
        User byId = userService.getById(id);
        if (byId == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            userService.delete(byId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
