/**
 * 
 */
package ttps.cartelera.controllers;

import java.util.List;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ttps.cartelera.daos.UserDAO;
import ttps.cartelera.entities.User;

/**
 * @author Manuel Ortiz - ortizman@gmail.com
 *
 * Nov 27, 2016
 */
@CrossOrigin
@RestController
public class UserController {
	
	@Inject
	private UserDAO userDAO;
	
	@GetMapping("/usuarios")
	public List<User> getUsers() {
		return userDAO.list();
	}

}
