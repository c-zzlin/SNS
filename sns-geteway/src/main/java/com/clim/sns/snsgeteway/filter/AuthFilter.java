package com.clim.sns.snsgeteway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;
import java.util.UUID;

@Component
public class AuthFilter extends ZuulFilter {

    @Autowired
    private StringRedisTemplate redisUtil;

    Logger logger = LoggerFactory.getLogger(getClass());
    //排除过滤的 uri 地址
    private static final String LOGIN_URI = "/user/login";
    private static final String REGISTER_URI = "/user/register";

    private static final String AUTH_TOKEN = "token";

    @Override
    public Object run() throws ZuulException {
        logger.info("====== 开始鉴权=======");
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        HttpServletResponse response = requestContext.getResponse();
        String token = request.getHeader(AUTH_TOKEN);
        logger.info("前端传的token是====="+token);
        String object = redisUtil.opsForValue().get(token);
        logger.info("user_id={}",object);


        //token存在，删除原来的token，重新生成token返回给客户端
        if(!Objects.isNull(object)){
            logger.info("存在token======");
            /*redisUtil.delete(token);
            String uuidToken = UUID.randomUUID().toString();
            redisUtil.opsForValue().set(uuidToken,uuidToken);*/
            response.setHeader("Access-Control-Expose-Headers",
                    "Cache-Control,Content-Type,Expires,Pragma,Content-Language,Last-Modified,token");
           // response.setHeader("token", uuidToken); // 设置响应头
            response.setStatus(HttpStatus.SC_OK);
        }else {
            logger.info("===不存在token");
            //不存在，直接返回验证失败，让其重新登录
            requestContext.setSendZuulResponse(false);//不会被zuul路由转发，也就是不会请求到后端具体的服务。但是如果当前filter后面还有其他filter的话，其他filter依然会执行
//        		requestContext.setResponseStatusCode(HttpStatus.SC_UNAUTHORIZED);//和response.setStatus(HttpStatus.SC_UNAUTHORIZED);一样的效果
            response.setStatus(HttpStatus.SC_UNAUTHORIZED);//401
            requestContext.setResponseBody("token验证失败!");
        }
        return null;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        HttpServletResponse response = requestContext.getResponse();
        String requestURI = request.getRequestURI();
        //登录和注册放行
        if(LOGIN_URI.equals(requestURI)||REGISTER_URI.equals(requestURI)){
            String uuidToken = UUID.randomUUID().toString();
            redisUtil.opsForValue().set(uuidToken,uuidToken);
            response.setHeader("Access-Control-Expose-Headers",
                    "Cache-Control,Content-Type,Expires,Pragma,Content-Language,Last-Modified,token");
            response.setHeader("token", uuidToken); // 设置响应头
            return false;
        }
        return true;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.SERVLET_DETECTION_FILTER_ORDER-1;
    }

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

}
