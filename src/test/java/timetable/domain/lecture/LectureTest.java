package timetable.domain.lecture;

import org.junit.Before;
import org.junit.Test;
import timetable.support.utils.LectureUtils;

import static org.assertj.core.api.Java6Assertions.assertThat;


public class LectureTest {
    private Lecture lecture;

    @Before
    public void setUp() throws Exception {
        lecture = new Lecture();
    }

    @Test
    public void registered() {
        lecture.register();
        assertThat(lecture.isRegistered()).isTrue();
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
}