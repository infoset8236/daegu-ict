package kr.co.sj.framework.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExceptionResolver implements HandlerExceptionResolver {
	protected final Logger log = LoggerFactory.getLogger(getClass());

	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) {

		request.setAttribute("errorMessage", exception.getMessage());
		String exceptionAllMessage = exception.toString() + "\n";
		exceptionAllMessage += exception.getMessage() + "\n";

		//권한제외 모든 경우
		log.error(exceptionAllMessage);

		return new ModelAndView("/exception/error");
	}
}
