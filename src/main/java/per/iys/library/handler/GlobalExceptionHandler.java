package per.iys.library.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import per.iys.library.commons.domain.Result;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result AllException(Exception e) {
        e.printStackTrace();
        log.error("{}", e.getMessage());
        return Result.fail().message("系统繁忙, 请稍后重试...");
    }
}
