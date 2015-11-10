package ocean.viewControl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.regex.Pattern;

/**
 * Created by ocean on 2015/11/10.
 *
 */

@Controller
public class ViewController {

    @RequestMapping(value = {"**/*.html"}, method = {RequestMethod.GET})
    public ModelAndView pageFilter(HttpServletRequest request, HttpServletResponse response, HttpSession session){

        //去除.html
        String url  = request.getServletPath();
        String view = url.replaceAll(".html$", "");
        //防止瀏覽器cache
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        response.setDateHeader("Expires", 0); // Proxies.


        HashMap<String, Object> model = new HashMap<String, Object>();
        model.put("view", view);
        model.put("pageRoot", request.getContextPath());

        System.out.println("============");
        System.out.println(view);
        System.out.println("============");
        return new ModelAndView(view, model);

    }



}
