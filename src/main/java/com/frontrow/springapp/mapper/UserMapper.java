package com.frontrow.springapp.mapper;

import com.frontrow.springapp.entity.User;
import com.frontrow.springapp.pojo.UserView;
import com.frontrow.springapp.utils.NullAwareBeanUtils;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements AbstractMapper<User, UserView> {

    @Override
    public void entityToView(User user, UserView userView) {
        if (user != null)
            NullAwareBeanUtils.copyPropertiesWithoutNull(user, userView);
    }

    @Override
    public void viewToEntity(User user, UserView userView) {
        if (userView != null)
            NullAwareBeanUtils.copyPropertiesWithoutNull(userView, user);

    }
}
