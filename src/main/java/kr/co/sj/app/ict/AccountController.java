package kr.co.sj.app.ict;

import kr.co.sj.framework.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller("ictAccount")
@RequestMapping(value = {"/ict/{context_path}/account"})
public class AccountController extends BaseController {

    public final static String ICT_TYPE = "/ict/";
    public final static String PATH = "/account/";

    private String basePath (String context_path) {
        return ICT_TYPE + context_path + PATH;
    }

    @RequestMapping(value = {"/index.*"})
    public String index (@PathVariable String context_path, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        return basePath(context_path) + "index";
    }

    @RequestMapping(value = {"/myPage.*"})
    public String myPage (@PathVariable String context_path, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        return basePath(context_path) + "myPage";
    }

    @RequestMapping(value = {"/rfid.*"})
    public String rfid (@PathVariable String context_path, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        return basePath(context_path) + "rfid";
    }
}
