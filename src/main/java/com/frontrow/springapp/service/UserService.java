package com.frontrow.springapp.service;

import com.frontrow.springapp.entity.User;
import com.frontrow.springapp.mapper.UserMapper;
import com.frontrow.springapp.pojo.CreateUserRequest;
import com.frontrow.springapp.pojo.UpdateUserRequest;
import com.frontrow.springapp.pojo.UserView;
import com.frontrow.springapp.repository.UserRepository;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository,
                       UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    //TODO: Implement a simple in-memory cache for this function
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    @Cacheable()
    public UserView getById(Long id) {
        Optional<User> userOptional = userRepository.getById(id);
        if (userOptional.isPresent()) {
            UserView userView = new UserView();
            userMapper.entityToView(userOptional.get(), userView);

            return userView;
        }
        else
            return null;
    }

    //TODO: Write a function to get user by name
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public UserView getByName(String name) {

        userRepository.findByName(name)
            .map(userMapper::entityToView)
            .orElse(null);
        return null;
    }

    //TODO: Write a function to create a user in the system
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public UserView create(CreateUserRequest createUserRequest) {

        return null;
    }

    //TODO: Write a function to update an existing user.
    // The existing user will be fetched by the id in the request, and the fields will be updated with ones in the request
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public UserView update(UpdateUserRequest createUserRequest) {

        return null;
    }

    //TODO: Delete a user by id
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public UserView delete(Long id) {

        return null;
    }
}
