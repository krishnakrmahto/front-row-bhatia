package com.frontrow.springapp.controller;

import com.frontrow.springapp.pojo.CreateUserRequest;
import com.frontrow.springapp.pojo.Response;
import com.frontrow.springapp.pojo.UpdateUserRequest;
import com.frontrow.springapp.pojo.UserView;
import com.frontrow.springapp.service.UserService;
import io.micrometer.core.annotation.Timed;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user", consumes = "application/json", produces = "application/json")
public class UserController extends AbstractController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @Timed
    public Response<?> getById(@PathVariable("id") Long id) {
        UserView userView = userService.getById(id);
        return getSuccessResponse(userView);
    }

    @RequestMapping(value = "/name", method = RequestMethod.GET)
    @Timed
    public Response<?> getByName(@RequestParam("name") String name) {
        UserView userView = userService.getByName(name);
        return getSuccessResponse(userView);
    }

    @RequestMapping(method = RequestMethod.POST)
    @Timed
    public Response<?> createUser(@RequestBody @Valid CreateUserRequest createUserRequest) {
        UserView userView = userService.create(createUserRequest);
        return getSuccessResponse(userView);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @Timed
    public Response<?> updateUser(@RequestBody @Valid UpdateUserRequest updateUserRequest) {
        UserView userView = userService.update(updateUserRequest);
        return getSuccessResponse(userView);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @Timed
    public Response<?> deleteUser(@PathVariable Long id) {
        UserView userView = userService.delete(id);
        return getSuccessResponse(userView);
    }
}
