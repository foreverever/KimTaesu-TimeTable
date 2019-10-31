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

import static timetable.support.utils.LectureUtils.*;

@Controller
public class HomeController {
    private static final Logger log = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private LectureService lectureService;

    @GetMapping("/")
    public String home(Model model) {
        log.debug("HOME!!");
        List<Lecture> lectures = lectureService.findByNotRegistered();
        List<Lecture> monLectures = lectureService.findDayLectures(MON);
        List<Lecture> tueLectures = lectureService.findDayLectures(TUE);
        List<Lecture> wedLectures = lectureService.findDayLectures(WED);
        List<Lecture> thuLectures = lectureService.findDayLectures(THU);
        List<Lecture> friLectures = lectureService.findDayLectures(FRI);

        model.addAttribute("lectures", lectures);
        model.addAttribute("monLectures", monLectures);
        model.addAttribute("tueLectures", tueLectures);
        model.addAttribute("wedLectures", wedLectures);
        model.addAttribute("thuLectures", thuLectures);
        model.addAttribute("friLectures", friLectures);
        return "/index";
    }
}
