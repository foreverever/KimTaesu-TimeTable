package timetable.domain.lecture;

import org.junit.Before;
import org.junit.Test;

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
}