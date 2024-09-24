package com.yj.reservation.config.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.HashMap;
import java.util.Map;

/**
 * 自定义用户名验证过滤器
 */
public class MmUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        System.out.println("自定义用户验证过滤器");
        if (!HttpMethod.POST.name().equals(request.getMethod())) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }

        if (MediaType.APPLICATION_JSON_VALUE.equals(request.getContentType()) || MediaType.APPLICATION_JSON_UTF8_VALUE.equals(request.getContentType())) {
            Map<String, String> loginData = new HashMap<>(16);
            try {
                loginData = new ObjectMapper().readValue(request.getInputStream(), Map.class);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
//                String paramCode = loginData.get("code");
//                try {
//                    checkCode(response,paramCode,sessionCode);
//                } catch (Exception e) {
//                    throw new RuntimeException(e);
//                }
            }
            String username = loginData.get(getUsernameParameter());
            String password = loginData.get(getPasswordParameter());
            if (username == null) {
                username = "";
            }
            if (password == null) {
                password = "";
            }

            System.out.println("用户名密码：" + username + ":" +password);

            username = username.trim();
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
            setDetails(request, authRequest);
            return this.getAuthenticationManager().authenticate(authRequest);
        } else {
//            try {
//                checkCode(response,request.getParameter("code"), sessionCode);
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
            System.out.println("走了这里");
            return super.attemptAuthentication(request, response);
        }
//        return super.attemptAuthentication(request, response);
    }

    /**
     * 检查验证码
     * @param response
     * @param paramCode
     * @param sessionCode
     */
    private void checkCode(HttpServletResponse response, String paramCode, String sessionCode) throws Exception {
//        if (StringUtils.isBlank(paramCode)) {
//            R r = R.error(ResponseEnum.VERIFY_CODE_IS_NULL.getCode(), ResponseEnum.VERIFY_CODE_IS_NULL.getMessage());
//            response.setContentType("application/json;charset=utf-8");
//            PrintWriter out = response.getWriter();
//            out.write(new ObjectMapper().writeValueAsString(r));
//            out.flush();
//            out.close();
//            return;
//        }
//        if (StringUtils.isBlank(sessionCode)) {
//            R r = R.error(ResponseEnum.VERIFY_CODE_IS_EXPIRED.getCode(), ResponseEnum.VERIFY_CODE_IS_EXPIRED.getMessage());
//            response.setContentType("application/json;charset=utf-8");
//            PrintWriter out = response.getWriter();
//            out.write(new ObjectMapper().writeValueAsString(r));
//            out.flush();
//            out.close();
//            return;
//        }
//        if (!StringUtils.equals(paramCode.toLowerCase(), sessionCode.toLowerCase())) {
//            R r = R.error(ResponseEnum.VERIFY_CODE_IS_NOT_MATCH.getCode(), ResponseEnum.VERIFY_CODE_IS_NOT_MATCH.getMessage());
//            response.setContentType("application/json;charset=utf-8");
//            PrintWriter out = response.getWriter();
//            out.write(new ObjectMapper().writeValueAsString(r));
//            out.flush();
//            out.close();
//            return;
//        }
    }

}
