package kr.co.sj.framework.interceptor;

import org.slf4j.MDC;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

public class MDCInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		UUID requestId = UUID.randomUUID();
		MDC.clear();
		MDC.put("requestId", String.valueOf(requestId));
		MDC.put("sessionId", request.getSession().getId());

		return true;
	}
}
