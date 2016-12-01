package ttps.cartelera.entities;

/**
 * @author Manuel Ortiz - ortizman@gmail.com
 *
 * Nov 27, 2016
 */
public class User {
	
	private Long id;
	private String firstName;
	private String lastName;
	private String userLogin;
	private String password;
	private String email;
	
	public User() {
		// Default
	}
	/**
	 * @param firstName
	 * @param lastName
	 * @param userLogin
	 * @param email
	 */
	public User(Long id, String firstName, String lastName, String userLogin, String password, String email) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userLogin = userLogin;
		this.password = password;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
