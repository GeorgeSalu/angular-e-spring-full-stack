package com.gerenciador.tarefas.service;

import java.util.Date;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AutenticacaoService {

	static public void addJwtToken(HttpServletResponse response,String username) {
		
		String jwtToken = Jwts.builder()
				.setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + 3600000))
				.signWith(SignatureAlgorithm.HS512, "signinKey")
				.compact();
		
		response.addHeader("Authorization", "Bearer "+jwtToken);
		response.addHeader("Access-Control-Expose-Headers", "Authorization");
		
	}
	
	static public Authentication obterAutenticacao(HttpServletRequest request) {
		
		String token = request.getHeader("Authorization");
		
		if(token != null) {
			Claims user = Jwts.parser()
					.setSigningKey("signinKey")
					.parseClaimsJws(token.replace("Bearer ", ""))
					.getBody();
			
			if(user != null) {
				return new UsernamePasswordAuthenticationToken(user, null, null);
			} else {
				throw new RuntimeException("autenticacao falhou");
			}
			
		}
		return null;
		
	}
	
}
