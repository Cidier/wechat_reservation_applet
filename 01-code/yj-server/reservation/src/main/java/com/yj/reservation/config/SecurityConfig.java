package com.yj.reservation.config;

//import com.yj.reservation.service.cms.MmSysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @EnableGlobalMethodSecurity :启用方法级别的认证
 *      prePostEnabled：Boolean 默认是false
 *          true：表示可以使用@PreAuthorize注解和 @PostAuthorize
 *
 * @EnableWebSecurity //表示启用spring security安全框架功能
 *
 * @Configuration //表示当前类是一 个配置类(相当于是spring的xml配置文件) , 在这个类方法的返回值是java 对象,这些对象放入到spring容器中
 */
@Configuration
//@EnableWebSecurity //开启SpringSecurity的自定义配置（在SpringBoot项目中可以忽略此注解）
@EnableMethodSecurity //开启方法授权
public class SecurityConfig{

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //所有请求都可以访问
        return http.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(autz -> autz.anyRequest().permitAll()).build();
    }

    /**
     * 自定义加密方式
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(4);
    }

///**
//    @Autowired
////    private MmSysUserService sysUserService;
//
//
//    /**
//     * 配置HttpSecurity
//     * @param http
//     * @return
//     * @throws Exception
//     */
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        return http
//                .authorizeHttpRequests(
//                        (authz) ->
//                                authz.anyRequest() //任何请求
//                                .authenticated() //都需要身份验证
//                )
//                .sessionManagement((session)->
//                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)  // 不通过session获取SecurityContext
//                )
//                .build();
//    }
//
//    /**
//     * 自定义加密方式
//     * @return
//     */
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//*/
}