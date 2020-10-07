package com.ikubinfo.konferenca.filter;

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
import com.ikubinfo.konferenca.dto.UserDto;
import com.ikubinfo.konferenca.managedBeans.LoggedUserBean;

/**
 * Servlet Filter implementation class LoggedInUserFilter
 */
@WebFilter(filterName = "/LoggedInUserFilter", urlPatterns = { "/*" })
public class LoggedInUserFilter implements Filter {

    public LoggedInUserFilter() {
    }

	public void destroy() {
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

			HttpServletResponse httpResponse = (HttpServletResponse) response;
			HttpSession httpSession = ((HttpServletRequest) request).getSession(false);
			
			LoggedUserBean loggedUser = (httpSession!=null)? (LoggedUserBean)httpSession.getAttribute("loggedUserBean"):null;
			UserDto userDto = (loggedUser!=null)? loggedUser.getLoggedUser():null;
			
			String currentPath = ((HttpServletRequest)request).getRequestURL().toString();
		
			if(userDto!=null) {

				switch(userDto.getKategoria()) {
					case "admin" :
						if((currentPath.contains("autor_res") || currentPath.contains("shqyrtues_res")) 
								&& !allowed(currentPath)) {
							httpResponse.sendError(403);
						}else if((currentPath.contains("login.xhtml") || currentPath
								.contains("accessDenied.xhtml")) && !allowed(currentPath)) {
							httpResponse.sendRedirect("admin/faqja1.xhtml");
						}
						break;
					case "autor" :
						if((currentPath.contains("admin") || currentPath.contains("shqyrtues_res")) 
								&& !allowed(currentPath)) {
							httpResponse.sendError(403);
						}else if((currentPath.contains("login.xhtml") || currentPath
								.contains("accessDenied.xhtml")) && !allowed(currentPath)) {
							httpResponse.sendRedirect("autor_res/faqja1.xhtml");
						}
						break;
					case "shqyrtues":
						if((currentPath.contains("autor_res") || currentPath.contains("admin")) 
								&& !allowed(currentPath)) {
							httpResponse.sendError(403);
						}else if((currentPath.contains("login.xhtml") || currentPath
								.contains("accessDenied.xhtml")) && !allowed(currentPath)) {
							httpResponse.sendRedirect("shqyrtues_res/faqja1.xhtml");
						}
						break;
					default : 
						chain.doFilter(request, response);
				}
				
				chain.doFilter(request, httpResponse);
				
			}else {
				if (!(currentPath.contains("login.xhtml") || currentPath.contains("index.xhtml") 
						|| currentPath.contains("regjistrim.xhtml") 
						|| currentPath.contains("shikues.xhtml")
						|| currentPath.contains("perfundimRegjistrimi.xhtml")
						|| currentPath.contains("harrimFjalekalimi.xhtml")
						|| currentPath.contains("harrimFjalekalimiCodeSent.xhtml")
						|| currentPath.contains("harrimFjalekalimiConfirm.xhtml")) && !allowed(currentPath)){
					
					httpResponse.sendRedirect(request.getServletContext().getContextPath().toString() + "/index.xhtml");
				
				} else {
				
					chain.doFilter(request, response);
				}
			}
	}


	public void init(FilterConfig fConfig) throws ServletException {
	}
	
	private boolean allowed(String path) {
		return path.contains("javax.faces.resource") || path.contains(".png")
				|| path.contains("resources"); 
	}
}
