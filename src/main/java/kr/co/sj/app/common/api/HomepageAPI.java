package kr.co.sj.app.common.api;

import kr.co.sj.app.common.bean.HomepageConfig;
import kr.co.sj.app.common.bean.LibraryConfig;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

@Controller("homepageAPI")
@RequestMapping(value = {"/api/homepage"})
public class HomepageAPI {
	protected final static Logger log = LoggerFactory.getLogger(HomepageAPI.class);

    public final static String HOMEPAGE_API_URL = ResourceBundle.getBundle("api").getString("homepage.api.url");

    public static HttpURLConnection initConn(String urlStr) throws Exception {
		URL url = new URL(urlStr);

		HttpURLConnection connection = null;
		connection = (HttpURLConnection) url.openConnection();
		connection.setRequestProperty("Accept-Charset", "UTF-8");
		connection.setRequestProperty("Accept-Language", "utf-8,ko;q=0.8,en-us;q=0.5,en;q=0.3");
		connection.setDoOutput(true);
		connection.setConnectTimeout(10000);
		connection.setReadTimeout(10000);
		return connection;
	}

	public static Map<String, Object> sendAPI(String requestName, Map<String, Object> param) {
		HttpURLConnection connection = null;
		Map<String, Object> resultMap = null;
		try {
			String apiUrl = HOMEPAGE_API_URL + requestName;
			connection = initConn(apiUrl);

			DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(wr, "UTF-8"));

			if ( param != null ) {
				Set<String> keys = param.keySet();
				List<String> paramList = new ArrayList<String>();
				for ( String oneKey : keys ) {
					paramList.add(String.format("%s=%s", oneKey, param.get(oneKey)));
				}

				log.info("@@@@@@@@@@@@@@@@@@ HOMEPAGE_API_URL : " + apiUrl + "?" + StringUtils.join(paramList, "&"));

				writer.write(StringUtils.join(paramList, "&"));
			}

			writer.close();
			wr.close();
			wr.flush();

			String result = IOUtils.toString(connection.getInputStream(), "UTF-8").trim();
			ObjectMapper om = new ObjectMapper();
			resultMap = om.readValue(result, new TypeReference<Map<String, Object>>(){});
		}
		catch ( Exception e ) {
			e.printStackTrace();
		}
		return resultMap;
	}

	@RequestMapping(value = {"/eventList.*"})
	public static Map<String, Object> getEventList(HomepageConfig config) {
		Map<String, Object> params = new HashMap<>();

		if (StringUtils.isNotEmpty(config.getHomepage_id())) {
			params.put("homepage_id", config.getHomepage_id());
		}

		putIfNotEmpty(params, "start_date", config.getStart_date());
		putIfNotEmpty(params, "end_date", config.getEnd_date());
		putIfNotEmpty(params, "date_type", config.getDate_type());

		return sendAPI("eventList.do", params);
	}

	@RequestMapping(value = {"/boardList.*"})
	public static Map<String, Object> getBoardList(HomepageConfig config) {
		Map<String, Object> params = new HashMap<>();

		putIfNotEmpty(params, "manage_idx", config.getManage_idx());
		putIfNotEmpty(params, "board_type", config.getBoard_type());
		putIfNotEmpty(params, "endRowNum", config.getEndRowNum());
		putIfNotEmpty(params, "kiosk_yn", config.getKiosk_yn());

		return sendAPI("boardList.do", params);
	}

	private static void putIfNotEmpty(Map<String, Object> params, String key, String value, boolean stripDash) {
		if (StringUtils.isNotEmpty(value)) {
			params.put(key, stripDash ? value.replaceAll("-", "") : value);
		}
	}

	private static void putIfNotEmpty(Map<String, Object> params, String key, String value) {
		putIfNotEmpty(params, key, value, false);
	}
}
