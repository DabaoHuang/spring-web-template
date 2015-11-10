package ocean.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by ocean on 2015/11/10.
 * 用來測試 web 是否有無回應
 */

@Controller
@RequestMapping("/test")   //spring mapping url
public class TestControl {
    /**詢問 api 是否有在正常運作*/
    @ResponseBody
    @RequestMapping(value="/ready", method = RequestMethod.GET)
    public Object login(HttpSession session){
        return "YES";
    }
}