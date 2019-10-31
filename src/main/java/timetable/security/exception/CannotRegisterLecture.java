package timetable.security.exception;

public class CannotRegisterLecture extends RuntimeException {
    public CannotRegisterLecture(String message) {
        super(message);
    }
}
