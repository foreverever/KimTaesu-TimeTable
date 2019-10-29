package timetable.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import timetable.domain.lecture.Lecture;
import timetable.service.lecture.LectureService;

@RestController
@RequestMapping("/api/lecture")
public class ApiLectureController {

    private static final Logger log = LoggerFactory.getLogger(ApiLectureController.class);

    @Autowired
    private LectureService lectureService;

    @GetMapping("/{id}")
    public Lecture show(@PathVariable long id) {
        Lecture lecture = lectureService.findById(id);
        log.debug("lecture : {}" , lecture.toString());
        return lecture;
    }

}
