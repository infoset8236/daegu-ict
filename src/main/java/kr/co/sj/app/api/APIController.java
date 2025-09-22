package kr.co.sj.app.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

@Controller
@RequestMapping(value = {"/api/"})
public class APIController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = {"circlesRoomUser.*"})
	public @ResponseBody ResponseEntity<String> circlesRoomUser(String data, String facility_id, HttpServletRequest request) throws Exception {
		RestTemplate restTemplate = new RestTemplate();

        String apiUrl = "https://외부API주소?facility_id=" + facility_id;

        ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);

		log.info("@@@@@@@@@@@@@@@ circlesRoomUserList log");
		log.info("URL : " + request.getRequestURL() +"?"+ request.getQueryString());
		log.info("Result : " + response.getStatusCode());

		return response;
	}

	@RequestMapping(value = "circlesRoomUserList.*")
	public @ResponseBody ResponseEntity<String> circlesRoomUserList(String facility_id, String reserve_date, HttpServletRequest request) throws ParseException {
		RestTemplate restTemplate = new RestTemplate();

        String apiUrl = "https://외부API주소?facility_id=" + facility_id + "&reserve_date=" + reserve_date;

        ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);

		log.info("@@@@@@@@@@@@@@@ circlesRoomUserList log");
		log.info("URL : " + request.getRequestURL() +"?"+ request.getQueryString());
		log.info("Result : " + response.getStatusCode());

		return response;
	}
}
