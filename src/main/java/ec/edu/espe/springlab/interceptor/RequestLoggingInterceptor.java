package ec.edu.espe.springlab.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class RequestLoggingInterceptor implements HandlerInterceptor {
    //Tomar el tiempo de inicio y registra el endpoint
    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) {
        req.setAttribute("t0", System.currentTimeMillis());
        System.out.println("preHandle" + req.getMethod() + " " + req.getRequestURI());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest req, HttpServletResponse resp, Object handler, Exception ex){
        Long t0 = (Long)req.getAttribute("t0");
        long slapsed = (t0 == null) ? -1 : (System.currentTimeMillis() - t0);
        System.out.println("afterCpletion -> status: " + resp.getStatus() + "time: " + slapsed + "ms");
    }
}
