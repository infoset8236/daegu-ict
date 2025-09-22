package kr.co.sj.framework.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class CrossScriptingFilter implements Filter {

	public FilterConfig filterConfig;
	
	@Override
	public void destroy() {
		this.filterConfig = null;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if(excludeUrl((HttpServletRequest)request)) {
			chain.doFilter(new RequestWrapper((HttpServletRequest) request), response);
		} else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}
	
	private boolean excludeUrl(HttpServletRequest request) {
		String uri = request.getRequestURI().toString().trim();
		
		if(uri.startsWith("/cms")) {
			return false;
		} else {
			return true;
		}
	}

}
