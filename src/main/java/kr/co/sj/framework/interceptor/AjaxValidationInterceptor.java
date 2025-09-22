package kr.co.sj.framework.interceptor;

import kr.co.sj.framework.base.BaseController;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AjaxValidationInterceptor extends HandlerInterceptorAdapter {
	
	private static final long serialVersionUID = 1L;
	
	private static final String ENABLE_AJAX_VALIDATION_PARAM = "spring.enableAjaxValidation";
	private static final String FORM_ID_PARAM = "spring.formId";
	
	
	@Override
	public void postHandle(
			HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
			throws Exception {

		String ajaxEnabled = request.getParameter( ENABLE_AJAX_VALIDATION_PARAM );
		String formId = request.getParameter( FORM_ID_PARAM );
		
		if ( ajaxEnabled != null && ajaxEnabled.equals( "true" ) ){
			System.out.println(BindingResult.MODEL_KEY_PREFIX);
			if(handler instanceof BaseController){
				System.out.println("Controller확인");
			}
		}else {
		}
	}
	
}
