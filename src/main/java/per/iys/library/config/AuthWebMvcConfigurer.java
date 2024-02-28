package per.iys.library.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AuthWebMvcConfigurer implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        HandlerInterceptor interceptor = new HandlerInterceptor() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                Object attribute = request.getSession().getAttribute("sessionUser");
                if (attribute == null) {
                    response.sendRedirect(request.getContextPath() + "/");
                    return false;
                }
                return true;
            }
        };

        String[] path = {"/**"};
        String[] excludePath = {
                "/",
                "/layui/**",
                "/favicon.ico",
                "/background.jpg",
                "/system/user/login"
        };

        registry.addInterceptor(interceptor).addPathPatterns(path).excludePathPatterns(excludePath);
    }
}
