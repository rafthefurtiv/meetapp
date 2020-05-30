package com.meetapp.meetapp.repositories;

import com.meetapp.meetapp.models.EventExt;
import com.meetapp.meetapp.models.Token;

public interface TokenRepository {

    Token getTokenByUserEmail(String userEmail);

    Token saveTokenByUserEmail(String userEmail, String token);
}
