package kr.co.sj.app.common.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import java.io.StringReader;

@Controller("etcAPI")
@RequestMapping(value = {"/api/etc"})
public class EtcAPI {
	protected final static Logger log = LoggerFactory.getLogger(EtcAPI.class);

	public final static String NEWS_API_URL = "https://api.newswire.co.kr/rss/region/6";

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

	public static String getNews() {
		HttpURLConnection connection = null;
		Map<String, Object> resultMap = null;
		BufferedReader br = null;

		try {
			connection = initConn(NEWS_API_URL);

			int responseCode = connection.getResponseCode();

			if(responseCode==200) { // 정상 호출
				br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
			} else {  // 에러 발생
				br = new BufferedReader(new InputStreamReader(connection.getErrorStream(), "UTF-8"));
			}

			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				response.append(inputLine);
			}

			String result = response.toString();
			log.info("@@@@@@@@@@@@@@@@ NEWS_API_RESULT : " + result);

			return result;

		} catch(Exception e) {
			log.error("################## Exception : " + e);
		}

		return null;
	}

	@RequestMapping(value = {"/news.do"}, produces = "application/json; charset=UTF-8")
	@ResponseBody
	public Map<String, Object> getNewsList() {
		Map<String, Object> resultMap = new HashMap<>();
		List<Map<String, String>> newsList = new ArrayList<>();

		try {
			String xml = getNews();

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			InputSource is = new InputSource(new StringReader(xml));
			Document doc = builder.parse(is);

			NodeList items = doc.getElementsByTagName("item");

			DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
			DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

			for (int i = 0; i < items.getLength(); i++) {
				Element item = (Element) items.item(i);

				String title = item.getElementsByTagName("title").item(0).getTextContent();
				String link = item.getElementsByTagName("link").item(0).getTextContent();
				String pubDateRaw = item.getElementsByTagName("pubDate").item(0).getTextContent();

				String pubDateFormatted = pubDateRaw;
				try {
					ZonedDateTime dateTime = ZonedDateTime.parse(pubDateRaw, inputFormatter);
					pubDateFormatted = dateTime.format(outputFormatter);
				} catch (Exception e) {
					pubDateFormatted = pubDateRaw;
				}

				Map<String, String> news = new HashMap<>();
				news.put("title", title.trim());
				news.put("link", link.trim());
				news.put("pubDate", pubDateFormatted.trim());

				newsList.add(news);
			}

			resultMap.put("result", "SUCCESS");
			resultMap.put("list", newsList);

		} catch (Exception e) {
			log.error("NEWS RSS 파싱 실패", e);
			resultMap.put("result", "FAIL");
			resultMap.put("message", e.getMessage());
		}

		return resultMap;
	}


}
