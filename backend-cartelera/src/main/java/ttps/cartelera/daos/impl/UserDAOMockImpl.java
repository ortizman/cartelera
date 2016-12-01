package ttps.cartelera.daos.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import ttps.cartelera.daos.UserDAO;
import ttps.cartelera.entities.User;

@Repository
public class UserDAOMockImpl implements UserDAO {

	// Simulo mi base de datos
	private static List<User> users;

	public UserDAOMockImpl() {
		users = new ArrayList<>();
		users.add(new User(1000l, "Juan Roberto", "Gimmenez", "juan", "juan1", "juan@mock.com"));
		users.add(new User(1000l, "Maria Julia", "Moreno", "maria", "maria1", "maria@mock.com"));
		users.add(new User(1000l, "Pepe Luis", "Correa", "pepe", "pepe1", "pepe@mock.com"));
		users.add(new User(1000l, "Isable", "Perez", "isabel", "isabel1", "isabel@mock.com"));
	}

	@Override
	public List<User> list() {
		return users;
	}

	@Override
	public User get(Long id) {
		for (User u : users) {
			if (u.getId().equals(id)) {
				return u;
			}
		}
		return null;
	}

	@Override
	public void create(User user) {
		user.setId(System.currentTimeMillis());
		users.add(user);
	}

	@Override
	public Long delete(Long userId) {
		for (User u : users) {
			if (u.getId().equals(userId)) {
				users.remove(u);
				return u.getId();
			}
		}
		return null;
	}

	@Override
	public User update(Long userId, User user) {

		for (User u : users) {
			if (u.getId().equals(userId)) {
				user.setId(userId);
				users.remove(u);
				users.add(user);
				return u;
			}
		}
		return null;
	}

	@Override
	public User getByUserLogin(String userLogin) {
		for (User u : users) {
			if (u.getUserLogin().equals(userLogin)) {
				return u;
			}
		}
		return null;
	}

}
