package timetable.support.utils;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;


public class LectureUtilsTest {

    private String code;
    private String code2;
    private String startTime;

    @Before
    public void setUp() throws Exception {
        code = "PG1807-02";
        code2 = "PG1807-15";
        startTime = "12:00";
    }

    @Test
    public void subCodeNum() {
        assertThat(LectureUtils.CalcCodeColorNum(code)).isEqualTo("02");
    }

    @Test
    public void getHourOfStartTime() {
        assertThat(LectureUtils.getHourOfTime(startTime)).isEqualTo("12");
    }

    @Test
    public void CalcCodeColorNum() {
        assertThat(LectureUtils.CalcCodeColorNum(code)).isEqualTo(2);
        assertThat(LectureUtils.CalcCodeColorNum(code2)).isEqualTo(5);
    }
}