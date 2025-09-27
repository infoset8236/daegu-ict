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
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller("KLAS")
@RequestMapping(value = {"/api/klas"})
public class KlasAPI {
	protected final static Logger log = LoggerFactory.getLogger(KlasAPI.class);

    public final static String KLAS_API_URL = ResourceBundle.getBundle("api").getString("klas.api.url");
	public final static String KEYWORD_API_URL = ResourceBundle.getBundle("api").getString("keyword.api.url");

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

	public static Map<String, Object> sendKlasAPI(String requestName, Map<String, Object> param) {
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

	public static Map<String, Object> sendKeywordAPI(String requestName, Map<String, Object> param) {
		HttpURLConnection connection = null;
		Map<String, Object> resultMap = null;
		try {
			String apiUrl = KEYWORD_API_URL + requestName;
			connection = initConn(apiUrl);

			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
			connection.setDoOutput(true);

			ObjectMapper om = new ObjectMapper();
			String jsonBody = om.writeValueAsString(param);

			log.info("@@@@@@@@@@@@@@@@@@ KEYWORD_API_URL : " + apiUrl);
        	log.info("@@@@@@@@@@@@@@@@@@ REQUEST BODY    : " + jsonBody);

			try (OutputStream os = connection.getOutputStream()) {
				byte[] input = jsonBody.getBytes("UTF-8");
				os.write(input, 0, input.length);
				os.flush();
			}

			String result = IOUtils.toString(connection.getInputStream(), "UTF-8").trim();
			resultMap = om.readValue(result, new TypeReference<Map<String, Object>>() {});
		} catch (Exception e) {
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

		return sendKlasAPI("bookloanbest", params);
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

		return sendKlasAPI("newbooklist", params);
	}

	@RequestMapping(value = {"/bookSearch.*"})
	public static Map<String, Object> bookSearch(LibraryConfig config) {
		Map<String, Object> params = new HashMap<>();

		putIfNotEmpty(params, "manage_code", config.getManageCode());
		putIfNotEmpty(params, "search_type", config.getSearch_type());

		if (StringUtils.isNotEmpty(config.getSearchType()) && StringUtils.isNotEmpty(config.getSearch_text())) {
			if("title".equals(config.getSearchType())) {
				params.put("search_title", config.getSearch_text());
			}
			if("author".equals(config.getSearchType())) {
				params.put("search_author", config.getSearch_text());
			}
			if("publisher".equals(config.getSearchType())) {
				params.put("search_publisher", config.getSearch_text());
			}
			if("keyword".equals(config.getSearchType())) {
				params.put("search_keyword", config.getSearch_text());
			}
			if("isbn".equals(config.getSearchType())) {
				params.put("search_isbn_issn", config.getSearch_text());
			}
		}

		putIfNotEmpty(params, "pageno", Integer.toString(config.getPageno()));
		putIfNotEmpty(params, "display", Integer.toString(config.getDisplay()));

		putIfNotEmpty(params, "orderby", config.getOrderby());
		putIfNotEmpty(params, "orderby_item", config.getOrderby_item());

		return sendKlasAPI("bookandnonbooksearch", params);
	}

	@RequestMapping(value = {"/bookDetail.*"})
	public static Map<String, Object> bookDetail(LibraryConfig config) {
		Map<String, Object> params = new HashMap<>();

		putIfNotEmpty(params, "manage_code", config.getManageCode());
		putIfNotEmpty(params, "reg_no", config.getRegNo());

		return sendKlasAPI("getbookinfo", params);
	}

	@RequestMapping(value = {"/keyword.*"})
	public static List<Map<String, Object>> keyword(LibraryConfig config) {
		Map<String, Object> params = new HashMap<>();
		Map<String, Object> result = null;

		String keywordStr = config.getKeyword();
		if (StringUtils.isNotEmpty(keywordStr)) {
			String[] keywords = keywordStr.split(",");
			params.put("keyword", Arrays.asList(keywords));
		}

		if (StringUtils.isNotEmpty(config.getSex())) {
			if (config.getSex().equals("0")) {
				params.put("gender", "남");
			} else {
				params.put("gender", "여");
			}
		}

		if (StringUtils.isNotEmpty(config.getBirth_year())) {
			LocalDate now = LocalDate.now();

			int year = now.getYear();
			int birth_year = Integer.parseInt(config.getBirth_year());

			int age = year - birth_year + 1;

			if (age <= 4) {
				params.put("age", "영유아");
			} else if (age >= 5 && age <= 7) {
				params.put("age", "유아");
			} else if (age >= 8 && age <= 13) {
				params.put("age", "초등");
			} else if (age >= 14 && age <= 19) {
				params.put("age", "청소년");
			} else if (age >= 20 && age <= 29) {
				params.put("age", "20대 이상");
			} else if (age >= 30 && age <= 39) {
				params.put("age", "30대 이상");
			} else if (age >= 40 && age <= 49) {
				params.put("age", "40대 이상");
			} else if (age >= 50 && age <= 59) {
				params.put("age", "50대 이상");
			} else if (age >= 60) {
				params.put("age", "60대 이상");
			} else {
				params.put("age", "20대 이상");
			}
		}

		if(StringUtils.isNotEmpty(config.getBook_keyword_age())) {
			int age = Integer.parseInt(config.getBook_keyword_age());

			if (age <= 4) {
				params.put("age", "영유아");
			} else if (age >= 5 && age <= 7) {
				params.put("age", "유아");
			} else if (age >= 8 && age <= 13) {
				params.put("age", "초등");
			} else if (age >= 14 && age <= 19) {
				params.put("age", "청소년");
			} else if (age >= 20 && age <= 29) {
				params.put("age", "20대 이상");
			} else if (age >= 30 && age <= 39) {
				params.put("age", "30대 이상");
			} else if (age >= 40 && age <= 49) {
				params.put("age", "40대 이상");
			} else if (age >= 50 && age <= 59) {
				params.put("age", "50대 이상");
			} else if (age >= 60) {
				params.put("age", "60대 이상");
			} else {
				params.put("age", "20대 이상");
			}
		}

		result = sendKeywordAPI("recommendation/topic", params);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		if (result != null) {
			try {
				List<Map<String, Object>> items = (List<Map<String, Object>>) result.get("result");
				for (int i = 0; i < items.size(); i++) {
					list.add(items.get(i));
				}
			} catch (Exception e) {
//				log.error(e.getMessage());
			}
		}

		return list;
	}

	public static Map<String, Object> useranalyzedbooks(LibraryConfig config) {
		Map<String, Object> params = new HashMap<>();

		if (StringUtils.isNotEmpty(config.getUserkey())) {
			params.put("userkey", config.getUserkey());
		}

		if (StringUtils.isNotEmpty(config.getManageCode())) {
			params.put("manage_code", config.getManageCode());
		}

		Calendar cal = Calendar.getInstance();

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String nowDate = sdf.format(date);

		cal.setTime(date);
		cal.add(Calendar.YEAR,-2);

		params.put("weight", "5");
		params.put("gender", config.getSex());
		params.put("enddate",nowDate);
		params.put("startdate",sdf.format(new Date(cal.getTimeInMillis())));

		if (StringUtils.isNotEmpty(config.getBirth_year())) {
			String[] age_split = config.getBirth_year().split("-");

			config.setBirth_year(age_split[0]);

			String age_code = "9";

			LocalDate now = LocalDate.now();
			int now_year = now.getYear();

			int age = now_year - Integer.parseInt(config.getBirth_year()) +1;

			if (age <= 10) {
				age_code = "0";
			} else if (age >= 10 && age < 20) {
				age_code = "1";
			} else if (age >= 20 && age < 30) {
				age_code = "2";
			} else if (age >= 30 && age < 40) {
				age_code = "3";
			} else if (age >= 40 && age < 50) {
				age_code = "4";
			} else if (age >= 50 && age < 60) {
				age_code = "5";
			} else if (age >= 60 && age < 70) {
				age_code = "6";
			} else if (age >= 70 && age < 80) {
				age_code = "7";
			} else if (age >= 80) {
				age_code = "8";
			} else {
				age_code = "9";
			}

			params.put("age", age_code);
		}

		return sendKlasAPI("getuseranalyzedbooks", params);
	}

	@RequestMapping(value = {"/login.*"})
	@ResponseBody
	public static Map<String, Object> login(Member member, HttpServletRequest request) {
		Map<String, Object> params = new HashMap<>();
		Map<String, Object> result = new HashMap<>();

		try {
			params.put("id", member.getMember_id());
			params.put("password", CalculateHashUtils.calculateHashSHA256(member.getMember_pw()));

			String from = member.getFrom();
			String mode;

			Map<String, Object> apiResult = sendKlasAPI("userlogin", params);

			if (apiResult != null && "SUCCESS".equals(apiResult.get("RESULT_INFO"))) {
				@SuppressWarnings("unchecked")
				Map<String, Object> userData = (Map<String, Object>) apiResult.get("USER_DATA");

				if (userData != null) {
					setMemberData(member, apiResult);
				}

				HttpSession session = request.getSession();
				session.setAttribute(StaticVariables.MEMBER, member);

				if (from != null && (from.equals("smart") || from.equals("touch"))) {
					mode = from;
				} else {
					mode = "touch";
				}

				session.setAttribute("mode", mode);

				result.put("result", "SUCCESS");
				result.put("mode", mode);

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

			Map<String, Object> apiResult = sendKlasAPI("userlogin", params);

			boolean loginSuccess = false;

			String from = member.getFrom();
			String mode;

			if (apiResult != null && "SUCCESS".equals(apiResult.get("RESULT_INFO"))) {
				setMemberData(member, apiResult);
				loginSuccess = true;
			} else {
				params.clear();
				params.put("option", 4);
				params.put("workno", member.getMember_id().toUpperCase());
				params.put("api_key", API_KEY);

				apiResult = sendKlasAPI("userlogin", params);

				if (apiResult != null && "SUCCESS".equals(apiResult.get("RESULT_INFO"))) {
					setMemberData(member, apiResult);
					loginSuccess = true;
				}
			}

			if (loginSuccess) {
				HttpSession session = request.getSession();
				session.setAttribute(StaticVariables.MEMBER, member);

				if (from != null && (from.equals("smart") || from.equals("touch"))) {
					mode = from;
				} else {
					mode = "touch";
				}

				session.setAttribute("mode", mode);

				result.put("result", "SUCCESS");
				result.put("mode", mode);

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
			member.setLogin(true);

			String birthday = String.valueOf(userData.get("BIRTHDAY"));

			if (StringUtils.isNotEmpty(birthday) && !StringUtils.equals(birthday, "null")) {
				member.setBirth_day(birthday.replaceAll("/", "-"));

				try {
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
					LocalDate birthDate = LocalDate.parse(birthday, formatter);
					LocalDate today = LocalDate.now();

					int age = Period.between(birthDate, today).getYears();

					LocalDate thisYearsBirthday = birthDate.withYear(today.getYear());
					if (today.isBefore(thisYearsBirthday)) {
						age -= 1;
					}

					member.setAge(String.valueOf(age));

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
