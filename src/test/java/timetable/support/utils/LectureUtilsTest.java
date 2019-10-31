package timetable.support.utils;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;


public class LectureUtilsTest {

    private String code;
    private String startTime;

    @Before
    public void setUp() throws Exception {
        code = "PG1807-02";
        startTime = "12:00";
    }

    @Test
    public void subCodeNum() {
        assertThat(LectureUtils.SubCodeNum(code)).isEqualTo("02");
    }

    @Test
    public void getHourOfStartTime() {
        assertThat(LectureUtils.getHourOfTime(startTime)).isEqualTo("12");
    }
}