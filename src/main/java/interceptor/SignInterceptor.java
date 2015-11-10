package interceptor;

import key.Key;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Created by ocean on 2015/11/10.
 */
public class SignInterceptor implements HandlerInterceptor{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean isSign  = false;
        HttpSession session = request.getSession(true);
        Object user = session.getAttribute(Key.USER);
        try{
            if (user == null) {
                isSign = false;
            } else {
                isSign = (StringUtils.isNotBlank(user.toString())) ? true : false;
            }
        }catch (Exception e){
            isSign = false;
        }
        if(!isSign){
            //導向登入頁
            //response.sendRedirect(URLEncoder.encode(request.getRequestURL().toString(), "UTF-8"));
        }
        return isSign;
    }
    @Override
    public void postHandle(	HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
