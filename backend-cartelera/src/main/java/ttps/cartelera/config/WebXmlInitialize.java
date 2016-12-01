package ttps.cartelera.config;

import javax.servlet.Filter;

import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Esta clase de configuracion sutituye al web.xml.
 * 
 * En esta clase se puede registrar servlets, filtros, etc
 * 
 * @author Manuel Ortiz - ortizman@gmail.com
 *
 *         Nov 27, 2016
 */
public class WebXmlInitialize extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] {WebMvcConfig.class, CarteleraConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/api/*" };
	}

	@Override
	protected Filter[] getServletFilters() {
		return new Filter[] { new DelegatingFilterProxy("securityFilter") };
	}

}
