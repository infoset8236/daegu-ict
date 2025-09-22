package kr.co.sj.app.ict;

import kr.co.sj.framework.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller("ictSmart")
@RequestMapping(value = {"/ict/{context_path}/smart"})
public class SmartController extends BaseController {

    public final static String ICT_TYPE = "/ict/";
    public final static String PATH = "/smart/";

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
    public String custom (@PathVariable String context_path, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {

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
    public String keyword (@PathVariable String context_path, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        return basePath(context_path) + "keyword";
    }

    @RequestMapping(value = {"/librarian.*"})
    public String librarian (@PathVariable String context_path, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        return basePath(context_path) + "librarian";
    }

    @RequestMapping(value = {"/result.*"})
    public String result (@PathVariable String context_path, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        return basePath(context_path) + "result";
    }
}
