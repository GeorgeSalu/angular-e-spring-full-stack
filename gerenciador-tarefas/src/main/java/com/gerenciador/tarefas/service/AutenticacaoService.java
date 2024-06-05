package com.gerenciador.tarefas.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AutenticacaoService {
	
	private static final int EXPIRATION_TOKEN_ONE_HOUR = 3600000;
	private static final String BEARER = "Bearer ";
	private static final String SIGNIN_KEY = "signinKey";
	private static final String AUTHORITIES = "authorities";
	private static final String HEADER_AUTHORIZATION = "Authorization";

	static public void addJwtToken(HttpServletResponse response,Authentication authentication) {
		
		
		Map<String,Object> claims = new HashMap<>();
		claims.put(AUTHORITIES, authentication.getAuthorities().stream().map(role -> role.getAuthority()).collect(Collectors.toList()));
		
		String jwtToken = Jwts.builder()
				.setSubject(authentication.getName())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TOKEN_ONE_HOUR))
				.signWith(SignatureAlgorithm.HS512, SIGNIN_KEY)
				.addClaims(claims)
				.compact();
		
		response.addHeader(HEADER_AUTHORIZATION, BEARER+jwtToken);
		response.addHeader("Access-Control-Expose-Headers", HEADER_AUTHORIZATION);
		
	}
	
	static public Authentication obterAutenticacao(HttpServletRequest request) {
		
		String token = request.getHeader(HEADER_AUTHORIZATION);
		
		if(token != null) {
			
			Claims user = Jwts.parser()
					.setSigningKey(SIGNIN_KEY)
					.parseClaimsJws(token.replace(BEARER, ""))
					.getBody();
			
			
			if(user != null) {
				
				List<SimpleGrantedAuthority> permissoes =  ((ArrayList<String>)user.get(AUTHORITIES))
						.stream()
						.map(SimpleGrantedAuthority::new)
						.collect(Collectors.toList());
				
				return new UsernamePasswordAuthenticationToken(user, null, permissoes);
			} else {
				throw new RuntimeException("autenticacao falhou");
			}
			
		}
		return null;
		
	}
	
}
