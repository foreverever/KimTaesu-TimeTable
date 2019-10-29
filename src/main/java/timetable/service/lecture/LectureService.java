package timetable.service.lecture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import timetable.domain.lecture.Lecture;
import timetable.domain.lecture.LectureRepository;

import java.util.List;

@Service
public class LectureService {

    @Autowired
    private LectureRepository lectureRepository;

    public List<Lecture> findAll() {
        return lectureRepository.findAll();
    }

    public Lecture findById(long id) {
        return lectureRepository.findById(id)
                .orElseThrow(RuntimeException::new);
    }
}
