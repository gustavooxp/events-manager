package com.senai.eventsmanager.config;

import java.security.Key;
import java.security.Signature;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    // CHAVE SECRETA QUE IRÁ ASSINAR E VERIFICAR OS JWTs
    private final String SEGREDO = "umaChaveMuitoSecretaDeNoMinimo32Caracteres!";

    /* CHAVE CRIPTOGRAFICA UTILIZADA PARA ASSINAR E VERIFICAR TOKEN
    USANDO O ALGORITMO CHAMADO DE HMAC-SHA */
    private final Key key = Keys.hmacShaKeyFor(SEGREDO.getBytes());


    public String gerarToken(String email) {
        return Jwts.builder()
                .setSubject(email) // defino qual o email que sera utiliado na autentificação
                .setIssuedAt(new Date(System.currentTimeMillis())) // quando com data, hora, minutos e segundos a sessão foi iniciada
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 4)) // diz quando aquela sessão irá expirar (4 horas)
                .signWith(key, SignatureAlgorithm.HS256) // assina e criptografa todo o conteudo com a super senha 
                .compact();
    }

    public boolean verificarSeTokenEValido(String token) {
        try {
            getClaims(token);
            return true;
        } catch  (JwtException | IllegalArgumentException e) {
            return false; 
        }
    }

    public String extrairEmail(String token) {
        return getClaims(token).getBody().getSubject();
    }

    private Jws<Claims> getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key) //pega a chave secreta para descriptografar o token
                .build()
                .parseClaimsJws(token); // transforma o token em algo legivel
    }
}
