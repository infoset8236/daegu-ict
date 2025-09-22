package kr.co.sj.framework.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.UUID;

public class HomepageBaseInterceptor extends HandlerInterceptorAdapter {

	protected final Logger log = LoggerFactory.getLogger(getClass());

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		String queryString = request.getQueryString();

		if (queryString != null) {
			if (queryString.contains("javascript")) {
				return alertMessage("보안상 잘못된 요청이 발생했습니다.", request, response);
			} else if (queryString.contains("script")) {
				return alertMessage("보안상 잘못된 요청이 발생했습니다.", request, response);
			}
		}

		HttpSession session = request.getSession();
		session.setAttribute("CSRF_TOKEN", UUID.randomUUID().toString());

		addStatisticsCount(request);

		return super.preHandle(request, response, handler);
	}

	@Async
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		String uri = request.getRequestURI().substring(request.getContextPath().length());

	}

	private void addStatisticsCount(HttpServletRequest request) {

	}
	
	private boolean alertMessage(String message, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=" + request.getCharacterEncoding());
		PrintWriter writer = response.getWriter();
		writer.println("<script>");
		writer.println("alert('" + message + "');"); 
		writer.println("history.back();");
		writer.println("</script>");
		writer.flush();
		
		return false;
	}
}
