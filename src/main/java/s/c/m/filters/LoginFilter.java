package s.c.m.filters;


import s.c.m.beans.ColaboradorBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFilter implements Filter {


	@Override
	public void doFilter(ServletRequest servletRequest,
						 ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {

		HttpServletRequest httpServletRequest =
				(HttpServletRequest) servletRequest;
		HttpServletResponse httpServletResponse =
				(HttpServletResponse) servletResponse;

		// managed bean name is exactly the session attribute name
		ColaboradorBean userManager = (ColaboradorBean) httpServletRequest
				.getSession().getAttribute("colaboradorBean");

		if (userManager != null ) {
			if (userManager.isLoggedIn()) {

				filterChain.doFilter(servletRequest, servletResponse);
			} else {
				httpServletResponse.sendRedirect(
						httpServletRequest.getContextPath() + "/login.xhtml");
			}
		} else {

			httpServletResponse.sendRedirect(
					httpServletRequest.getContextPath() + "/login.xhtml");
		}
	}
		public void init(FilterConfig config) throws ServletException {
			// Nothing to do here!
		}

		public void destroy() {
			// Nothing to do here!
		}

	}
