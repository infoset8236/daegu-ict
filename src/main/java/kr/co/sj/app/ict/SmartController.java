package kr.co.sj.app.ict;

import kr.co.sj.app.cms.member.Member;
import kr.co.sj.app.common.api.KlasAPI;
import kr.co.sj.app.common.bean.LibraryConfig;
import kr.co.sj.framework.base.BaseController;
import kr.co.sj.framework.utils.StaticVariables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("ictSmart")
@RequestMapping(value = {"/ict/{context_path}/smart"})
public class SmartController extends BaseController {

    public final static String ICT_TYPE = "/ict/";
    public final static String PATH = "/smart/";

    @Autowired
    private LibraryService libraryService;

    private String basePath (String context_path) {
        return ICT_TYPE + context_path + PATH;
    }

    @RequestMapping(value = {"/bigData.*"})
    public String bigData (@PathVariable String context_path, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        return basePath(context_path) + "bigData";
    }

    @RequestMapping(value = {"/chart.*"})
    public String chart (@PathVariable String context_path, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        return basePath(context_path) + "chart";
    }

    @RequestMapping(value = {"/custom.*"})
    public String custom (@PathVariable String context_path, LibraryConfig libraryConfig, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Member member = (Member)session.getAttribute(StaticVariables.MEMBER);

        libraryConfig.setUserkey(member.getRec_key());
        libraryConfig.setBirth_year(member.getBirth_day());
        libraryConfig.setSex(member.getGpin_sex());

        Map<String, Object> result = KlasAPI.useranalyzedbooks(libraryConfig);

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if(result != null) {
			list = (List<Map<String, Object>>)result.get("LIST_DATA");
		}

        model.addAttribute("list", list);

        return basePath(context_path) + "custom";
    }

    @RequestMapping(value = {"/detail.*"})
    public String detail (@PathVariable String context_path, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        return basePath(context_path) + "detail";
    }

    @RequestMapping(value = {"/index.*"})
    public String index (@PathVariable String context_path, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        return basePath(context_path) + "index";
    }

    @RequestMapping(value = {"/keyword.*"})
    public String keyword (@PathVariable String context_path, LibraryConfig libraryConfig, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Map<String, Object>> result = KlasAPI.keyword(libraryConfig);

        List<Map<String, Object>> enrichedBookList = new ArrayList<>();

        for (Map<String, Object> book : result) {
            String isbn = (String) book.get("isbn");
            libraryConfig.setIsbn(isbn);
            libraryConfig.setManageCode(libraryConfig.getManageCode());
            libraryConfig.setKeyword(null);

            Map<String, Object> bookDetail = KlasAPI.bookDetail(libraryConfig);

            Map<String, Object> enrichedBook = new HashMap<>();
            enrichedBook.putAll(book);

            if (bookDetail != null && bookDetail.containsKey("LIST_DATA")) {
                List<Map<String, Object>> listData = (List<Map<String, Object>>) bookDetail.get("LIST_DATA");
                for (Map<String, Object> item : listData) {
                    if (item.containsKey("REG_NO")) {
                        enrichedBook.put("reg_no", item.get("REG_NO"));
                        break;
                    }
                }
            }

            enrichedBookList.add(enrichedBook);
        }

        model.addAttribute("bookSearch", enrichedBookList);

        return basePath(context_path) + "keyword";
    }

    @RequestMapping(value = {"/librarian.*"})
    public String librarian (@PathVariable String context_path, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        return basePath(context_path) + "librarian";
    }

    @RequestMapping(value = {"/result.*"})
    public String result (@PathVariable String context_path, Model model, LibraryConfig libraryConfig, HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, Object> result = new HashMap<String, Object>();

        result = KlasAPI.bookSearch(libraryConfig);

        List<Map<String, Object>> dataList = null;

        int count = 0;

        if (result != null && !result.isEmpty() && result.get("LIST_DATA") != null) {
			dataList = new ArrayList<Map<String,Object>>();
			dataList.addAll((List<Map<String, Object>>) result.get("LIST_DATA"));
			if (dataList != null && dataList.size() > 0) {
				Map<String, Object> countMap = dataList.get(0);
				if (countMap != null && !countMap.isEmpty() && countMap.containsKey("SEARCH_COUNT")) {
					count = Integer.parseInt(String.valueOf(countMap.get("SEARCH_COUNT")));
				}
                if (countMap != null && !countMap.isEmpty() && countMap.containsKey("SEARCH_COUNT")) {
                    dataList.remove(0);
                } else if (countMap != null && !countMap.isEmpty() && countMap.containsKey("TOTAL")) {
                    dataList.remove(0);
                }
			}
		}

        String searchType = libraryConfig.getSearchType();

        if ("title".equals(searchType)) {
            searchType = "도서명";
        }
        if ("author".equals(searchType)) {
            searchType = "저자명";
        }
        if ("publisher".equals(searchType)) {
            searchType = "발행처";
        }
        if ("keyword".equals(searchType)) {
            searchType = "키워드";
        }
        if ("isbn".equals(searchType)) {
            searchType = "ISBN";
        }

        model.addAttribute("searchType", searchType);
        model.addAttribute("search_text", libraryConfig.getSearch_text());
        model.addAttribute("bookCount", count);
        model.addAttribute("bookSearch", dataList);

        libraryConfig.setViewPage(libraryConfig.getPageno());
        libraryConfig.setRowCount(libraryConfig.getDisplay());
        libraryService.setPaging(model, count, libraryConfig);

        return basePath(context_path) + "result";
    }
}
