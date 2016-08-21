package com.github.izhangzhihao.SpringMVCSeedProject.Controller;


import com.github.izhangzhihao.SpringMVCSeedProject.Model.User;
import com.github.izhangzhihao.SpringMVCSeedProject.Service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@Api(value = "/User", tags = "UserAPI", description = "用户信息接口")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/UserList", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取所有用户", response = User.class, responseContainer = "List")
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
    @ApiOperation(
            value = "根据id获取用户信息,不包含密码",
            response = User.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "指定id的用户不存在")
    })
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
    @ApiOperation(
            value = "创建用户",
            response = User.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 409, message = "指定id的用户已存在，冲突"),
            @ApiResponse(code = 201, message = "创建成功")
    })
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
    @ApiOperation(
            value = "更新用户",
            response = User.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "指定id的用户不存在"),
            @ApiResponse(code = 200, message = "更新成功")
    })
    public ResponseEntity<Void> updateUser(@PathVariable String userName,
                                           @PathVariable String passWord)
            throws Exception {
        User byId = userService.getById(userName);
        if (byId == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            userService.saveOrUpdate(new User(userName, passWord));
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/deleteUser/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(
            value = "删除用户",
            response = User.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "指定id的用户不存在"),
            @ApiResponse(code = 200, message = "删除成功")
    })
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
