package timetable.domain.lecture;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LectureRepository extends JpaRepository<Lecture, Long> {
    @Query("select a from Lecture a where a.name like concat(:search,'%') or a.code like concat(:search,'%') or a.professor like concat(:search,'%')")
    List<Lecture> findBySearch(@Param("search") String search);
    Lecture findByCode(String code);

    @Query("select a from Lecture a where a.registered = false")
    List<Lecture> findByRegistered();
//    @Query("select a from Lecture a where a.name = :search or a.code = :search or a.professor = :search")
//    List<Lecture> findBySearch(@Param("search") String search);
}
