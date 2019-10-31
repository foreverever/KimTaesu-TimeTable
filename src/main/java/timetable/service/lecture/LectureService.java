package timetable.service.lecture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import timetable.domain.lecture.Lecture;
import timetable.domain.lecture.LectureRepository;
import timetable.domain.lecture.memo.Memo;
import timetable.domain.lecture.memo.MemoRepository;

import java.util.List;

@Service
public class LectureService {

    @Autowired
    private LectureRepository lectureRepository;

    @Autowired
    private MemoRepository memoRepository;

    public List<Lecture> findAll() {
        return lectureRepository.findAll();
    }

    public Lecture findById(long id) {
        return lectureRepository.findById(id)
                .orElseThrow(RuntimeException::new);
    }

    public List<Lecture> findBySearch(String search) {
        return lectureRepository.findBySearch(search);
    }

    @Transactional
    public Lecture findByCode(String code) {
        Lecture lecture = lectureRepository.findByCode(code);
        lecture.register();
        return lecture;
    }

    public List<Lecture> findByRegistered() {
        return lectureRepository.findByRegistered();
    }

    public List<Lecture> findDayLectures(String weekOfDay) {
        return lectureRepository.findByRegisteredAndDatesContaining(true, weekOfDay);
    }

    @Transactional
    public Memo addMemo(long lectureId, Memo memo) {
        Lecture lecture = lectureRepository.findById(lectureId)
                .orElseThrow(RuntimeException::new);
        lecture.addMemo(memo);
        return memo;
    }

    @Transactional
    public void deleteMemo(long lectureId, long id) {
        Lecture lecture = lectureRepository.findById(lectureId)
                .orElseThrow(RuntimeException::new);
        lecture.deleteMemo(id);
    }
}
