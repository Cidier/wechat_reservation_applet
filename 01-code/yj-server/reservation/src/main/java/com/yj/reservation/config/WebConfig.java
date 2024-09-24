package com.yj.reservation.config;

import com.yj.reservation.config.interceptor.AuthenticationInterceptor;
import com.yj.reservation.config.resolver.CurrentUserArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

   // 这个方法用来注册拦截器，我们自己写好的拦截器需要通过这里添加注册才能生效
   @Override
   public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthenticationInterceptor())
                .addPathPatterns("/**") // 配置拦截所有
                .excludePathPatterns("/login", "/gencode");//配置放行 登录 获取验证码 接口
   }

   /**
    * Controller 方法参数注入
    * @param argumentResolvers
    */
   @Override
   public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
      argumentResolvers.add(new CurrentUserArgumentResolver());
   }
}