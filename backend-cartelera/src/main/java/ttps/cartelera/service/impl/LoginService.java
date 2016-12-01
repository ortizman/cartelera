/**
 * 
 */
package ttps.cartelera.service.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import ttps.cartelera.daos.UserDAO;
import ttps.cartelera.entities.User;

/**
 * @author Manuel Ortiz - ortizman@gmail.com
 *
 *         Nov 27, 2016
 */
@Service
public class LoginService {
	
	@Inject
	private UserDAO userDAO;

	public void logout(User user) {
		
	}
	
	public User login(String userLogin, String password) {
		User u = userDAO.getByUserLogin(userLogin);
		if(u != null){
			if(u.getPassword().equals(password)){
				return u;
			}
		}
		
		throw new IllegalArgumentException("Usuario o password invalido");
	}

	
}
