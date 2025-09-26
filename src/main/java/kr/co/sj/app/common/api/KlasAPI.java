package kr.co.sj.app.common.api;

import kr.co.sj.app.cms.member.Member;
import kr.co.sj.app.common.bean.LibraryConfig;
import kr.co.sj.framework.utils.CalculateHashUtils;
import kr.co.sj.framework.utils.StaticVariables;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

@Controller("KLAS")
@RequestMapping(value = {"/api/klas"})
public class KlasAPI {
	protected final static Logger log = LoggerFactory.getLogger(KlasAPI.class);

    public final static String KLAS_API_URL = ResourceBundle.getBundle("api").getString("klas.api.url");

	private final static String API_KEY = "79724C6D73152DC1035B16B6198665D34A640D5D11E8ACD60083FA80FE417E58";

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
			String apiUrl = KLAS_API_URL + requestName;
			connection = initConn(apiUrl);

			DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(wr, "UTF-8"));

			if ( param != null ) {
				Set<String> keys = param.keySet();
				List<String> paramList = new ArrayList<String>();
				for ( String oneKey : keys ) {
					paramList.add(String.format("%s=%s", oneKey, param.get(oneKey)));
				}

				log.info("@@@@@@@@@@@@@@@@@@ KLAS_API_URL : " + apiUrl + "?" + StringUtils.join(paramList, "&"));

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

	@RequestMapping(value = {"/bestBook.*"})
	public static Map<String, Object> getBestBookList(LibraryConfig config) {
		Map<String, Object> params = new HashMap<>();

		if (StringUtils.isNotEmpty(config.getManageCode())) {
			params.put("manage_code", config.getManageCode());
		}

		putIfNotEmpty(params, "display", Integer.toString(config.getDisplay()));

		putIfNotEmpty(params, "startdate", config.getSearch_start_date(), true);
		putIfNotEmpty(params, "enddate", config.getSearch_end_date(), true);

		putIfNotEmpty(params, "option", config.getBooktype());
		putIfNotEmpty(params, "reg_code", config.getRegCode());
		putIfNotEmpty(params, "subject_code", config.getSubjectCode());
		putIfNotEmpty(params, "media_code", config.getMedia_code());

		if (StringUtils.isNotEmpty(config.getShelfCode())) {
			params.put("shelf_loc_code", config.getShelfCode());
		}

		return sendAPI("bookloanbest", params);
	}

	@RequestMapping(value = {"/newBook.*"})
	public static Map<String, Object> getNewBookList(LibraryConfig config) {
		Map<String, Object> params = new HashMap<>();

		if (StringUtils.isNotEmpty(config.getManageCode())) {
			params.put("manage_code", config.getManageCode());
		}

		putIfNotEmpty(params, "pageno", Integer.toString(config.getPageno()));
		putIfNotEmpty(params, "display", Integer.toString(config.getDisplay()));

		putIfNotEmpty(params, "startdate", config.getSearch_start_date(), true);
		putIfNotEmpty(params, "enddate", config.getSearch_end_date(), true);

		putIfNotEmpty(params, "option", config.getBooktype());

		return sendAPI("newbooklist", params);
	}

	@RequestMapping(value = {"/login.*"})
	@ResponseBody
	public static Map<String, Object> login(Member member, HttpServletRequest request) {
		Map<String, Object> params = new HashMap<>();
		Map<String, Object> result = new HashMap<>();

		try {
			params.put("id", member.getMember_id());
			params.put("password", CalculateHashUtils.calculateHashSHA256(member.getMember_pw()));

			Map<String, Object> apiResult = sendAPI("userlogin", params);

			if (apiResult != null && "SUCCESS".equals(apiResult.get("RESULT_INFO"))) {
				@SuppressWarnings("unchecked")
				Map<String, Object> userData = (Map<String, Object>) apiResult.get("USER_DATA");

				if (userData != null) {
					setMemberData(member, apiResult);
				}

				HttpSession session = request.getSession();
				session.setAttribute(StaticVariables.MEMBER, member);

				result.put("result", "SUCCESS");
			} else {
				result.put("result", "FAIL");

				if (apiResult != null) {
					if (apiResult.get("RESULT_MESSAGE") != null) {
						result.put("message", apiResult.get("RESULT_MESSAGE").toString());
					} else if (apiResult.get("message") != null) {
						result.put("message", apiResult.get("message").toString());
					} else {
						result.put("message", "아이디 또는 비밀번호가 올바르지 않습니다.");
					}
				} else {
					result.put("message", "로그인 서버 응답이 없습니다.");
				}
			}
		} catch (Exception e) {
			log.error("Login error", e);
			result.put("result", "FAIL");
			result.put("message", "로그인 중 오류가 발생했습니다.");
		}

		return result;
	}

	@RequestMapping(value = {"/rfidLogin.*"})
	@ResponseBody
	public static Map<String, Object> rfidLogin(Member member, HttpServletRequest request) {
		Map<String, Object> params = new HashMap<>();
		Map<String, Object> result = new HashMap<>();

		try {
			params.put("option", 3);
			params.put("user_no", member.getMember_id().toUpperCase());
			params.put("api_key", API_KEY);

			Map<String, Object> apiResult = sendAPI("userlogin", params);

			boolean loginSuccess = false;

			if (apiResult != null && "SUCCESS".equals(apiResult.get("RESULT_INFO"))) {
				setMemberData(member, apiResult);
				loginSuccess = true;
			} else {
				params.clear();
				params.put("option", 4);
				params.put("workno", member.getMember_id().toUpperCase());
				params.put("api_key", API_KEY);

				apiResult = sendAPI("userlogin", params);

				if (apiResult != null && "SUCCESS".equals(apiResult.get("RESULT_INFO"))) {
					setMemberData(member, apiResult);
					loginSuccess = true;
				}
			}

			if (loginSuccess) {
				HttpSession session = request.getSession();
				session.setAttribute(StaticVariables.MEMBER, member);
				result.put("result", "SUCCESS");
			} else {
				result.put("result", "FAIL");
				if (apiResult != null) {
					if (apiResult.get("RESULT_MESSAGE") != null) {
						result.put("message", apiResult.get("RESULT_MESSAGE").toString());
					} else if (apiResult.get("message") != null) {
						result.put("message", apiResult.get("message").toString());
					} else {
						result.put("message", "아이디 또는 비밀번호가 올바르지 않습니다.");
					}
				} else {
					result.put("message", "로그인 서버 응답이 없습니다.");
				}
			}
		} catch (Exception e) {
			log.error("Login error", e);
			result.put("result", "FAIL");
			result.put("message", "로그인 중 오류가 발생했습니다.");
		}

		return result;
	}

	@RequestMapping(value = {"/logout.*"})
	@ResponseBody
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();

		return "";
	}

	private static void putIfNotEmpty(Map<String, Object> params, String key, String value, boolean stripDash) {
		if (StringUtils.isNotEmpty(value)) {
			params.put(key, stripDash ? value.replaceAll("-", "") : value);
		}
	}

	private static void putIfNotEmpty(Map<String, Object> params, String key, String value) {
		putIfNotEmpty(params, key, value, false);
	}

	@SuppressWarnings("unchecked")
	private static void setMemberData(Member member, Map<String, Object> apiResult) {
		Map<String, Object> userData = (Map<String, Object>) apiResult.get("USER_DATA");
		if (userData != null) {
			member.setUser_no((String) userData.get("USER_NO"));
			member.setRec_key((String) userData.get("REC_KEY"));
			member.setMember_name((String) userData.get("NAME"));
			member.setMember_id((String) userData.get("USER_ID"));
			member.setMember_class((String) userData.get("USER_CLASS"));
			member.setUser_class_code((String) userData.get("USER_CLASS_CODE"));
			member.setUser_position_code((String) userData.get("USER_POSITION_CODE"));
			member.setManage_code((String) userData.get("USER_MANAGE_CODE"));
			member.setPhone((String) userData.get("H_PHONE"));
			member.setEmail((String) userData.get("E_MAIL"));
			member.setGpin_sex((String) userData.get("GPIN_SEX"));

			String birthday = String.valueOf(userData.get("BIRTHDAY"));

			if (StringUtils.isNotEmpty(birthday) && !StringUtils.equals(birthday, "null")) {
				member.setBirth_day(birthday.replaceAll("/", "-"));
			}
		}
	}
}
