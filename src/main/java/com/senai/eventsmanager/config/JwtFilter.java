package com.senai.eventsmanager.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

    // Injetar dependencias, que no caso é o JWTUTIL
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

                //Pega o HEADER e procura o campo Autorization dentro dele
                String authHeader = request.getHeader("Authorization");

                //Verificar se a autentificação existe e se começa com Bearer
                if(authHeader != null && authHeader.startsWith("Bearer ")) {

                    //Extrar token de acesso
                    String token = authHeader.substring(7);

                    //Extrar o email do usuario
                    String email = jwtUtil.extrairEmail(token);

                    //Verifica se realemnte existe um email e se ainda não está autenticado
                    if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                        //Cria uma sessão de autentificação
                        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(token, null, null);

                        //Salvar a sessão recem criada
                        SecurityContextHolder.getContext().setAuthentication(authToken);

                        
                    }
                }
                //CONTINUA A REQUISIÇÃO NORMALMENTE 
                filterChain.doFilter(request, response);
    }

}
