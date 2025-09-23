package kr.co.sj.app.api;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.List;
import java.util.ResourceBundle;

@Controller
@RequestMapping(value = {"/api/"})
public class APIController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

	public final static String CIRCLES_API_URL = "http://218.48.151.16:8010/api/circlesRoomUser.do";

	@RequestMapping(
    value = {"circlesRoomUser.*"}, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String circlesRoomUser(@RequestParam(value = "data", required = false) String data,
								  @RequestParam(value = "circles_div", required = false) String circlesDiv,
								  HttpServletRequest request) throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		List<HttpMessageConverter<?>> converters = restTemplate.getMessageConverters();
		for (int i = 0; i < converters.size(); i++) {
			if (converters.get(i) instanceof StringHttpMessageConverter) {
				converters.set(i, new StringHttpMessageConverter(StandardCharsets.UTF_8));
			}
		}

		StringBuilder apiUrl = new StringBuilder(CIRCLES_API_URL);
		boolean hasParam = false;

		if (StringUtils.isNotEmpty(data)) {
			apiUrl.append(hasParam ? "&" : "?").append("data=").append(URLEncoder.encode(data, "UTF-8"));
			hasParam = true;
		}

		if (StringUtils.isNotEmpty(circlesDiv)) {
			apiUrl.append(hasParam ? "&" : "?").append("circles_div=").append(URLEncoder.encode(circlesDiv, "UTF-8"));
			hasParam = true;
		}

		ResponseEntity<String> response = restTemplate.getForEntity(apiUrl.toString(), String.class);

		log.info("@@@@@@@@@@@@@@@ circlesRoomUser log : " + request.getRequestURL() + "?" + request.getQueryString());
		log.info("CALL URL : " + apiUrl);

		return response.getBody();
	}

}
