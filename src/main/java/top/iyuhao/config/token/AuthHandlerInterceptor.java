package top.iyuhao.config.token;


import cn.hutool.json.JSONObject;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import top.iyuhao.token.TokenUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yuhao
 * @date 2023/9/6
 **/
@Component
public class AuthHandlerInterceptor implements HandlerInterceptor {

    /**
     * 一个拦截器
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //跨域请求会首先发一个option请求，直接返回正常状态并通过拦截器
        if ("OPTIONS".equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }
        response.setCharacterEncoding("utf-8");
        String token = request.getHeader("Authorization");

        if (token != null) {
            Pair<String, String> result = TokenUtils.verify(token);
            return true;
        }
        String web = request.getHeader("web");
        return web != null;
    }
}