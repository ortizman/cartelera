package ttps.cartelera.controllers;

import java.util.Collections;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ttps.cartelera.entities.Token;
import ttps.cartelera.entities.User;
import ttps.cartelera.jwt.TokenManagerSecurity;
import ttps.cartelera.service.impl.LoginService;

/**
 * @author Manuel Ortiz - ortizman@gmail.com
 *
 * Nov 28, 2016
 */
@CrossOrigin
@RestController
public class LoginController {
	
	@Inject
	private LoginService loginService;
	
	@Inject
	private TokenManagerSecurity tokenManagerSecurity;
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User userPost) {
		try {
			User user = loginService.login(userPost.getUserLogin(), userPost.getPassword());
			Token token = new Token(tokenManagerSecurity.createJWT(user));
			return ResponseEntity.ok(token);
		} catch (Exception e) {
			return new ResponseEntity<>(Collections.singletonMap("AuthenticationException",e.getMessage()), HttpStatus.UNAUTHORIZED);
		}
	}

}
