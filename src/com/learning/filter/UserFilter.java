package com.learning.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class UserFilter
 */
@WebFilter("/UserFilter")
public class UserFilter implements Filter {

    /**
     * Default constructor. 
     */
    public UserFilter() {
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest newRequest =  (HttpServletRequest) request;
		
		HttpSession session = ((HttpServletRequest) request).getSession();
		
		HttpServletResponse newResponse = (HttpServletResponse) response;
		
		String address = newRequest.getServletPath();
		
		if(session.getAttribute("user") != null || address.equals("/logon")){
			
			chain.doFilter(request, response);
			
		}else{
			
			newRequest.getRequestDispatcher("logon.jsp").forward(request,response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
