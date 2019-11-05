package timetable.domain.lecture.memo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemoRepository extends JpaRepository<Memo, Long> {
    void deleteByLectureId(long id);
}
