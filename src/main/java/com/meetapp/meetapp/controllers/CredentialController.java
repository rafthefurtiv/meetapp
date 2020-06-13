package com.meetapp.meetapp.controllers;

import com.meetapp.meetapp.models.EventExt;
import com.meetapp.meetapp.models.Token;
import com.meetapp.meetapp.repositories.TokenRepository;
import com.meetapp.meetapp.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/credential")
public class CredentialController {

    @Autowired
    TokenRepository tokenRepository;

    @CrossOrigin
    @GetMapping("/get")
    public ResponseEntity model(@RequestParam("userEmail") String userEmail) {
        Token token = tokenRepository.getTokenByUserEmail(userEmail);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping("/saveToken")
    public ResponseEntity model(@RequestBody Token token) {
        Token tempToken = tokenRepository.saveTokenByUserEmail(token.getUserEmail(), token.getToken());
        return new ResponseEntity<>(tempToken, HttpStatus.OK);
    }

}
