package kr.co.sj.app.ict;

import kr.co.sj.framework.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller("ictAi")
@RequestMapping(value = {"/ict/{context_path}/ai"})
public class AiController extends BaseController {

    public final static String ICT_TYPE = "/ict/";
    public final static String PATH = "/ai/";

    private String basePath (String context_path) {
        return ICT_TYPE + context_path + PATH;
    }

    @RequestMapping(value = {"/chat.*"})
    public String chat (@PathVariable String context_path, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        return basePath(context_path) + "chat";
    }

    @RequestMapping(value = {"/index.*"})
    public String index (@PathVariable String context_path, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        return basePath(context_path) + "index";
    }

    @RequestMapping(value = {"/result.*"})
    public String result (@PathVariable String context_path, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        return basePath(context_path) + "result";
    }

    @RequestMapping(value = {"/voice.*"})
    public String voice (@PathVariable String context_path, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        return basePath(context_path) + "voice";
    }
}
