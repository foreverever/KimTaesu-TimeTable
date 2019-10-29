package timetable.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import timetable.domain.lecture.Lecture;
import timetable.service.lecture.LectureService;

import java.util.List;

@RestController
@RequestMapping("/api/lectures")
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

    @GetMapping("")
    public List<Lecture> search(String search){
        log.debug("search : {}", search);
        List<Lecture> lectures = lectureService.findBySearch(search);
        log.debug("searchResult : {}", lectures);
        return lectures;
    }
}
