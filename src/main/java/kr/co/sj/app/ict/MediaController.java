package kr.co.sj.app.ict;

import kr.co.sj.framework.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller("ictMedia")
@RequestMapping(value = {"/ict/{context_path}/media"})
public class MediaController {

    public final static String ICT_TYPE = "/ict/";
    public final static String PATH = "/media/";

    private String basePath (String context_path) {
        return ICT_TYPE + context_path + PATH;
    }

    @RequestMapping(value = {"/etiquette{num}.*"})
    public String etiquette (@PathVariable String context_path, @PathVariable("num") int num, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        return basePath(context_path) + "etiquette" + num;
    }

    @RequestMapping(value = {"/newBook{num}.*"})
    public String newBook (@PathVariable String context_path, @PathVariable("num") int num, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        return basePath(context_path) + "newBook" + num;
    }

    @RequestMapping(value = {"/news.*"})
    public String news (@PathVariable String context_path, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        return basePath(context_path) + "news";
    }

    @RequestMapping(value = {"/notice{num}.*"})
    public String notice (@PathVariable String context_path, @PathVariable("num") int num, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        return basePath(context_path) + "notice" + num;
    }

    @RequestMapping(value = {"/return.*"})
    public String mediaReturn (@PathVariable String context_path, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        return basePath(context_path) + "return";
    }

    @RequestMapping(value = {"/stairs{num}.*"})
    public String stairs (@PathVariable String context_path, @PathVariable("num") int num, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        return basePath(context_path) + "stairs" + num;
    }

    @RequestMapping(value = {"/weather.*"})
    public String weather (@PathVariable String context_path, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        return basePath(context_path) + "weather";
    }

    @RequestMapping(value = {"/chEtiquette{num}.*"})
    public String chEtiquette (@PathVariable String context_path, @PathVariable("num") int num, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        return basePath(context_path) + "chEtiquette" + num;
    }
}
