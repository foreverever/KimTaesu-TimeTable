package timetable.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import timetable.domain.lecture.memo.Memo;
import timetable.service.lecture.LectureService;

@RestController
@RequestMapping("/api/lectures/{lectureId}/memos")
public class ApiMemoController {

    private static final Logger log = LoggerFactory.getLogger(ApiMemoController.class);

    @Autowired
    private LectureService lectureService;

    @PostMapping("")
    public Memo create(@PathVariable long lectureId, @RequestBody Memo memo) {
        log.debug("lectureId : {}", lectureId);
        Memo currentMemo = lectureService.addMemo(lectureId, memo);
        log.debug("memo : {}", currentMemo.toString());
        return currentMemo;
    }
}
