/**
 * 
 */
package ttps.cartelera.daos;

import java.util.List;

import ttps.cartelera.entities.User;

/**
 * @author Manuel Ortiz - ortizman@gmail.com
 *
 *         Nov 27, 2016
 */
public interface UserDAO {

	List<User> list();

	User get(Long id);

	void create(User user);

	Long delete(Long userId);

	User update(Long id, User user);

	User getByUserLogin(String userLogin);

}
