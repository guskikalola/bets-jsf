package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.FacadeBean;
import bean.LoginRegisterBean;
import businessLogic.BLFacade;

@WebFilter(filterName = "AuthFilter", urlPatterns = { "*.xhtml" })
public class AuthFilter implements Filter {

	public static final String[] PUBLIC = { "BaimenikEz.xhtml", "Login.xhtml", "Register.xhtml", "QueryQuestion.xhtml",
			"Main.xhtml" };
	public static final String[] SAIOA_HASITA = { "MyAccount.xhtml" };
	public static final String[] ADMIN = { "CreateQuestion.xhtml" };
	public static final String[] ERABILTZAILEA = { "AddMoney.xhtml", "Mugimenduak.xhtml"};

	public static final String BAIMENIK_EZ = "/faces/BaimenikEz.xhtml";
	public static final String LOGIN_PAGE = "/faces/Login.xhtml";

	private boolean barruanDago(String reqURI, String[] domain) {
		boolean dago = false;
		for (String elem : domain) {
			if (reqURI.indexOf(elem) != -1) {
				dago = true;
				break;
			}
		}
		System.err.println(reqURI + " " + dago);
		return dago;
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		BLFacade facade = FacadeBean.getBusinessLogic();
		HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
		HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

		LoginRegisterBean login = (LoginRegisterBean) httpServletRequest.getSession().getAttribute("login");
		String reqURI = httpServletRequest.getRequestURI();
//			filterChain.doFilter(servletRequest, servletResponse);
//			httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + BAIMENIK_EZ);
		if (barruanDago(reqURI, PUBLIC)) {
			filterChain.doFilter(servletRequest, servletResponse);
		} else {
			if (login == null || !login.saioaHasita()) {
				httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + LOGIN_PAGE);
			} else {
				if (barruanDago(reqURI, SAIOA_HASITA))
					filterChain.doFilter(servletRequest, servletResponse);
				else if (barruanDago(reqURI, ADMIN) && login.getRola().equals("admin"))
					filterChain.doFilter(servletRequest, servletResponse);
				else if (barruanDago(reqURI, ERABILTZAILEA) && login.getRola().equals("erabiltzailea"))
					filterChain.doFilter(servletRequest, servletResponse);
				else
					httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + BAIMENIK_EZ);
			}
		}
	}

	@Override
	public void destroy() {

	}

}
