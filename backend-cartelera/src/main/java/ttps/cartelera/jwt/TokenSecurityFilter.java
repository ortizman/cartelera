/*
 * Flux IT Argentina
 * La Plata - Buenos Aires - Argentina
 * http://www.fluxit.com.ar
 * Author:  Manuel Ortiz
 * Date:  22/09/2015 - 10:54:55
 */
package ttps.cartelera.jwt;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import ttps.cartelera.entities.User;
import ttps.cartelera.service.impl.LoginService;

/**
 * Servlet Filter implementation class TokenSecurityFilter
 * 
 * Valida el token de usuario. Si el token es invalido o expiro se corta la
 * cadena de ejeccion y se devuelve un codigo http 403
 * 
 * @author Manuel Ortiz - manuel.ortiz@fluxit.com.ar
 *
 *         Nov 27, 2016
 */
// Defino el filtro como un Bean de Spring para poder injectarle dependencias.
// Luego se registra el filtro desde WebXMLInitialize
@Component(value = "securityFilter")
@Order(Ordered.LOWEST_PRECEDENCE) // Le doy un valor con menor prioridad que el filtro de CORS
public class TokenSecurityFilter implements Filter {

	@Inject
	private TokenManagerSecurity tokenManagerSecurity;

	@Inject
	private LoginService loginService;

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {

	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		String path = req.getRequestURI().substring(req.getRequestURI().length() - 6);

		if ("/login".equals(path)) {
			// sigue la cadena de ejecucion hacia el login
			chain.doFilter(req, response);
		} else {
			// valido token
			String jwt = req.getHeader("Authorization");
			try {
				// Si la validacion es correcta y el token no expiro, parsea el
				// contenido del token y devuelve el user
				User user = tokenManagerSecurity.parseJWT(jwt);

				// Seteo el user en un atributo nuevo, de esta forma
				// ya estaria disponible para el resto de los controllers
				request.setAttribute("user", user);

				chain.doFilter(req, response);

			} catch (ExpiredJwtException e) {
				System.out.println(
						"El token ya no es valido, expiro su tiempo de validez: " + jwt + " " + e.getMessage());

				// ejecuta el logout para invalidar la session en banca
				if (e.getClaims().containsKey("content")) {
					User user = this.getManagerSecurity().getContentJWT(e.getClaims().get("content").toString());
					System.out.println(
							"Expiro el tiempo de ejecucion del token, por lo que se invalida la session del usuario (logout) "
									+ e.getMessage());
					loginService.logout(user);
				}
				((HttpServletResponse) response).setStatus(HttpServletResponse.SC_UNAUTHORIZED);

			} catch (SignatureException e) {
				System.out.println("No es un token valido, token: " + jwt + " " + e.getMessage());
				((HttpServletResponse) response).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			} catch (Exception e) {
				System.out.println("No es un token valido, token: " + jwt + " " + e.getMessage());
				((HttpServletResponse) response).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			}

		}

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
		// fConfig.getServletContext());
	}

	/**
	 * 
	 * @return the managerSecurity
	 */
	public TokenManagerSecurity getManagerSecurity() {
		return tokenManagerSecurity;
	}

	/**
	 * 
	 * @param managerSecurity
	 *            the managerSecurity to set
	 */
	public void setManagerSecurity(TokenManagerSecurity managerSecurity) {
		this.tokenManagerSecurity = managerSecurity;
	}

	/**
	 * @return the loginService
	 */
	public LoginService getLoginService() {
		return loginService;
	}

	/**
	 * @param loginService
	 *            the loginService to set
	 */
	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

}
