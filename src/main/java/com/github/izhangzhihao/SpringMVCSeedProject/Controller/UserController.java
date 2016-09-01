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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/User")
@Api(value = "/User", tags = "UserAPI", description = "用户信息接口")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/UserList")
    @ApiOperation(value = "获取所有用户", response = User.class, responseContainer = "List")
    public List<User> getAllUsers() {
        return userService.getAll();
    }

    @GetMapping("/User/{userName}")
    @ApiOperation(
            value = "根据id获取用户信息,不包含密码",
            response = User.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "指定id的用户不存在")
    })
    public ResponseEntity<User> getUser(@PathVariable String userName) {
        User user = userService.getById(userName);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/User/userName/{userName}/passWord/{passWord}")
    @ApiOperation(
            value = "创建用户",
            response = User.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 409, message = "指定id的用户已存在，冲突"),
            @ApiResponse(code = 201, message = "创建成功")
    })
    public ResponseEntity<Void> createUser(@PathVariable String userName,
                                           @PathVariable String passWord) {
        User byId = userService.getById(userName);
        if (byId != null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } else {
            userService.save(new User(userName, passWord));
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    @PutMapping("/User/userName/{userName}/passWord/{passWord}")
    @ApiOperation(
            value = "更新用户",
            response = User.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "指定id的用户不存在"),
            @ApiResponse(code = 200, message = "更新成功")
    })
    public ResponseEntity<Void> updateUser(@PathVariable String userName,
                                           @PathVariable String passWord) {
        User byId = userService.getById(userName);
        if (byId == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            userService.saveOrUpdate(new User(userName, passWord));
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @DeleteMapping("/User/{id}")
    @ApiOperation(
            value = "删除用户",
            response = User.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "指定id的用户不存在"),
            @ApiResponse(code = 200, message = "删除成功")
    })
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        User byId = userService.getById(id);
        if (byId == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            userService.delete(byId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
