package com.zosh.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import javax.crypto.SecretKey;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtTokenValidator extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
                String jwt = request.getHeader("Authorization");
                //Bearer jwt
                if(jwt != null){
                    jwt = jwt.substring(7);
                    try {
                        SecretKey key = Keys.hmacShaKeyFor(JWT_CONSTANT.SECRET_KEY.getBytes());
                        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();

                        String email = String.valueOf(claims.get("email"));
                        String authorities = String.valueOf(claims.get("authorities"));

                        List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);
                        org.springframework.security.core.Authentication authentication = new UsernamePasswordAuthenticationToken(email, null, auths);
                        SecurityContextHolder.getContext().setAuthentication(authentication);

                    } catch (Exception e) {
                        throw new BadCredentialsException("Invalid JWT token...");
                    }
                }

                filterChain.doFilter(request, response);
    }
}
