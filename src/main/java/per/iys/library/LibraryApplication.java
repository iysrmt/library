package per.iys.library;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class LibraryApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(LibraryApplication.class, args);
    }


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
        String[] excludePath = {"/", "/layui/**", "/favicon.ico", "/background.jpg", "/system/user/login"};
        registry.addInterceptor(interceptor).addPathPatterns(path).excludePathPatterns(excludePath);
    }
}
