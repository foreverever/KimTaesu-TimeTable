package timetable.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import timetable.security.exception.CannotRegisterLecture;
import timetable.security.exception.CannotRegisterMemo;
import timetable.support.domain.ErrorMessage;

@RestControllerAdvice(annotations = RestController.class)
public class RestSecurityControllerAdvice {

    private static final Logger log = LoggerFactory.getLogger(RestSecurityControllerAdvice.class);

    @ExceptionHandler(CannotRegisterLecture.class)
    public ErrorMessage cannotRegisterLecture(CannotRegisterLecture e) {
        log.debug("CannotRegisterLecture is happened!!");
        return new ErrorMessage(e.getMessage());
    }

    @ExceptionHandler(CannotRegisterMemo.class)
    public ErrorMessage cannotRegisterMemo(CannotRegisterMemo e) {
        log.debug("cannotRegisterMemo is happened!!");
        return new ErrorMessage(e.getMessage());
    }
}
