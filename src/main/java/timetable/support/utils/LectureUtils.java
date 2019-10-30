package timetable.support.utils;

public class LectureUtils {

    public static final String TWO = "two-hr";
    public static final String MON = "월";
    public static final String TUE = "화";
    public static final String WED = "수";
    public static final String THU = "목";
    public static final String FRI = "금";

    public static String SubCodeNum(String code) {
        return code.substring(7);
    }

    public static String getHourOfStartTime(String formattedStartTime) {
        return formattedStartTime.substring(0, 2);
    }
}
