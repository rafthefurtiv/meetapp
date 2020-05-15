package com.meetapp.meetapp.repositories;

import com.meetapp.meetapp.models.User;

public interface UserRepository {

    User getUserByUserId(Integer userId);
}
