package com.example.atv2Dac.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

    public class TokenProvider {

        private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);
        private static final long EXPIRATION_TIME = 7200000; // 2H em milissegundos

        public static String generateToken(String username) {
            Date expiration = new Date(System.currentTimeMillis() + EXPIRATION_TIME);
            System.out.println("token "+ username);
            return Jwts.builder()
                    .setSubject(username)
                    .setExpiration(expiration)
                    .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                    .compact();
        }
    }
