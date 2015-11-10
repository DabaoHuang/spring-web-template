package ocean.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

/**
 * Created by ocean on 2015/11/10.
 * 統一回傳欄位讓前端俱有判斷依據
 */

@Aspect
@Service
@Order(1)
public class ResponseProxy {

    @Around(value="@annotation(responseBody)")
    public Object executed(ProceedingJoinPoint pjp, ResponseBody responseBody){
        HashMap<String, Object> result =  new HashMap<String, Object>();
        try {
            result.put("success", true);
            result.put("result" , pjp.proceed());
        }catch (Throwable error){
            //do log
            //log library println
            result.put("success", false);
            result.put("result" , null);
            result.put("error"  , error.getMessage());
        }finally {
            return result;
        }
    }

}
