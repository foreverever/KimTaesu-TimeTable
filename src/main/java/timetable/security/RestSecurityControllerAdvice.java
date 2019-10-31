package timetable.security;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import timetable.security.exception.CannotRegisterLecture;
import timetable.support.domain.ErrorMessage;

@RestControllerAdvice(annotations = RestController.class)
public class RestSecurityControllerAdvice {

    @ExceptionHandler(CannotRegisterLecture.class)
    public ErrorMessage cannotRegisterLecture(CannotRegisterLecture e) {
        return new ErrorMessage(e.getMessage());
    }
}
