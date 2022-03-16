package com.frontrow.springapp.service;

import com.frontrow.springapp.entity.User;
import com.frontrow.springapp.mapper.UserMapper;
import com.frontrow.springapp.pojo.CreateUserRequest;
import com.frontrow.springapp.pojo.UpdateUserRequest;
import com.frontrow.springapp.pojo.UserView;
import com.frontrow.springapp.repository.UserRepository;
import com.frontrow.springapp.utils.CacheName;
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
    @Cacheable(CacheName.USER_CACHE)
    public Optional<UserView> getById(Long id) {
        return userRepository.getById(id)
            .map(userMapper::entityToView);
    }

    //TODO: Write a function to get user by name
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Optional<UserView> getByName(String name) {
        return userRepository.findByName(name)
            .map(userMapper::entityToView);
    }

    //TODO: Write a function to create a user in the system
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public UserView create(CreateUserRequest createUserRequest) {

        User user = User.builder()
            .name(createUserRequest.getName())
            .email(createUserRequest.getEmail())
            .createdAt(createUserRequest.getCreatedAt())
            .build();
        userRepository.save(user);
        return userMapper.entityToView(user);
    }

    //TODO: Write a function to update an existing user.
    // The existing user will be fetched by the id in the request, and the fields will be updated with ones in the request
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Optional<UserView> update(UpdateUserRequest updateUserRequest) {

        Optional<User> user = userRepository.findById(updateUserRequest.getId());
        if (user.isPresent()) {
            User userEntity = user.get();
            userEntity.setName(updateUserRequest.getName());
            userEntity.setEmail(updateUserRequest.getEmail());
            return Optional.of(userMapper.entityToView(userEntity));
        }
        return Optional.empty();
    }

    //TODO: Delete a user by id
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Optional<UserView> delete(Long id) {

        Optional<User> user = userRepository.findById(id);

        if (user.isPresent()) {
            UserView userView = userMapper.entityToView(user.get());
            userRepository.deleteById(id);
            return Optional.of(userView);
        }
        return Optional.empty();
    }
}
