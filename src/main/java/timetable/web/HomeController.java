package timetable.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import timetable.domain.lecture.Lecture;
import timetable.service.lecture.LectureService;

import java.util.List;

@Controller
public class HomeController {
    private static final Logger log = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private LectureService lectureService;

    @GetMapping("/")
    public String home(Model model) {
        log.debug("HOME!!");
        List<Lecture> lectures = lectureService.findAll();

        model.addAttribute("lectures", lectures);
        return "/index";
    }
}
