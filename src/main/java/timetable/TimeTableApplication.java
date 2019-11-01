package timetable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.*;

@Controller
@SpringBootApplication
public class TimeTableApplication {
    public static void main(String[] args) {
        SpringApplication.run(TimeTableApplication.class, args);
    }
}
