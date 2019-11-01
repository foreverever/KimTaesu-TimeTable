package timetable.domain.lecture;

import org.junit.Before;
import org.junit.Test;
import timetable.security.exception.CannotRegisterLecture;
import timetable.support.utils.LectureUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;


public class LectureTest {
    private Lecture lecture1;
    private Lecture lecture2;
    private List<Lecture> lectures;

    @Before
    public void setUp() throws Exception {
        lecture1 = new Lecture("수목");
        lecture2 = new Lecture("수목");
        lectures = new ArrayList<>();
    }

    @Test
    public void registered() {
        lecture1.register();
        assertThat(lecture1.isRegistered()).isTrue();
    }

    @Test
    public void calcTime() {
        String sampleTime1 = "09:00";
        String sampleTime2 = "10:00";
        int a = Integer.parseInt(LectureUtils.getHourOfTime(sampleTime1));
        int b = Integer.parseInt(LectureUtils.getHourOfTime(sampleTime2));
        assertThat(a < b).isTrue();
    }

    @Test
    public void duplicateString() {
        String dates1 = "월목";
        String dates2 = "목금";
        assertThat(dates1.contains(""+dates2.charAt(0))).isTrue();
    }

    @Test
    public void isNotDuplicateDate() {
        lecture1.setDates("월금");
        lecture2.setDates("수목");
        assertThat(lecture1.isDuplicateDate(lecture2)).isFalse();
        assertThat(lecture2.isDuplicateDate(lecture1)).isFalse();
    }

    @Test
    public void isDuplicateDate() {
        lecture1.setDates("목금");
        lecture2.setDates("수목");
        assertThat(lecture1.isDuplicateDate(lecture2)).isTrue();
        assertThat(lecture2.isDuplicateDate(lecture1)).isTrue();
    }

    @Test
    public void isDuplicateDate_2() {
        lecture1.setDates("화");
        lecture2.setDates("화수");
        assertThat(lecture1.isDuplicateDate(lecture2)).isTrue();
        assertThat(lecture2.isDuplicateDate(lecture1)).isTrue();
    }

    @Test
    public void isSameDate() {
        assertThat(lecture1.isDuplicateDate(lecture2)).isTrue();
        assertThat(lecture2.isDuplicateDate(lecture1)).isTrue();
    }

    @Test(expected = CannotRegisterLecture.class)
    public void isNotPossibleRegister() {
        lecture1.setStartTime(LocalDateTime.of(2019,11,11, 10, 0));
        lecture1.setEndTime(LocalDateTime.of(2019,11,11, 12, 0));
        lecture2.setStartTime(LocalDateTime.of(2019,11,11, 10, 0));
        lecture2.setEndTime(LocalDateTime.of(2019,11,11, 12, 0));

        lectures.add(lecture2);
        lecture1.isPossibleRegister(lectures);
    }

    @Test
    public void isPossibleRegister() {
        lecture1.setStartTime(LocalDateTime.of(2019,11,11, 10, 0));
        lecture1.setEndTime(LocalDateTime.of(2019,11,11, 12, 0));
        lecture2.setStartTime(LocalDateTime.of(2019,11,11, 12, 0));
        lecture2.setEndTime(LocalDateTime.of(2019,11,11, 13, 0));

        lectures.add(lecture2);
        lecture1.isPossibleRegister(lectures);
        assertThat(lecture1.isPossibleRegister(lectures)).isTrue();
    }
}