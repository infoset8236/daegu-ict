package kr.co.sj.app.ict;

import kr.co.sj.framework.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller("ictTouch")
@RequestMapping(value = {"/ict/{context_path}/touch"})
public class TouchController {

    public final static String ICT_TYPE = "/ict/";
    public final static String PATH = "/touch/";

    private String basePath (String context_path) {
        return ICT_TYPE + context_path + PATH;
    }

    @RequestMapping(value = {"/courseDetail.*"})
    public String courseDetail (@PathVariable String context_path, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        return basePath(context_path) + "courseDetail";
    }

    @RequestMapping(value = {"/courseForm.*"})
    public String courseForm (@PathVariable String context_path, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        return basePath(context_path) + "courseForm";
    }

    @RequestMapping(value = {"/courseList.*"})
    public String courseList (@PathVariable String context_path, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        return basePath(context_path) + "courseList";
    }

    @RequestMapping(value = {"/event.*"})
    public String event (@PathVariable String context_path, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        return basePath(context_path) + "event";
    }

    @RequestMapping(value = {"/facility.*"})
    public String facility (@PathVariable String context_path, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        return basePath(context_path) + "facility";
    }

    @RequestMapping(value = {"/index.*"})
    public String index (@PathVariable String context_path, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        return basePath(context_path) + "index";
    }

    @RequestMapping(value = {"/information.*"})
    public String information (@PathVariable String context_path, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        return basePath(context_path) + "information";
    }

    @RequestMapping(value = {"/notice.*"})
    public String notice (@PathVariable String context_path, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        return basePath(context_path) + "notice";
    }

    @RequestMapping(value = {"/return.*"})
    public String touchReturn (@PathVariable String context_path, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        return basePath(context_path) + "return";
    }
}
